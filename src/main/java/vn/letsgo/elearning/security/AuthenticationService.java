package vn.letsgo.elearning.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import vn.letsgo.elearning.dto.user.authentication.LoginDto;
import vn.letsgo.elearning.dto.user.authentication.RegisterDto;
import vn.letsgo.elearning.entity.user.authentication.Token;
import vn.letsgo.elearning.entity.user.User;
import vn.letsgo.elearning.entity.user.authentication.Role;
import vn.letsgo.elearning.entity.user.authentication.TokenType;
import vn.letsgo.elearning.exception.user.NotAuthenticatedException;
import vn.letsgo.elearning.exception.user.UsernameAlreadyExistsException;
import vn.letsgo.elearning.mapper.user.IUserMapper;
import vn.letsgo.elearning.repository.user.authentication.ITokenRepository;
import vn.letsgo.elearning.repository.user.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    @Value("${application.security.jwt.max-device}")
    private int maxDevice;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final JwtService jwtService;

    @Autowired
    private final AuthenticationManager authManager;

    @Autowired
    private final IUserRepository userRepository;

    @Autowired
    private final ITokenRepository tokenRepository;

    public AuthenticationResponse register(RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new UsernameAlreadyExistsException(registerDto.getUsername());
        }
        registerDto.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        User user = IUserMapper.INSTANCE.registerDtoToUser(registerDto);
        user.setRole(Role.USER);
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(user, jwtToken);

        log.info("Register successfully");
        log.info("Extracted username: " + jwtService.extractUsername(jwtToken));
        log.info("JWT Token: " + jwtToken);
        logUserInfo();

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .message("Register successfully")
                .build();
    }

    public AuthenticationResponse login(LoginDto loginDto) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        var user = userRepository.findByUsername(loginDto.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        updateUserLastLogin(user.getUsername());

        log.info("Extracted username: " + jwtService.extractUsername(jwtToken));
        log.info("JWT Token: " + jwtToken);
        log.info("Login successfully");
        logUserInfo();

        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .message("Login successfully")
                .build();
    }

    public Object getCurrentPrinciple() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return authentication.getPrincipal();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public User getCurrentUser() {
        Object principle = this.getCurrentPrinciple();
        if (principle instanceof User) {
            return (User) principle;
        }
        else {
            throw new NotAuthenticatedException();
        }
    }

    public void logUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()) {
            if (authentication.getPrincipal() instanceof User) {
                // Get the principal (User)
                User user = (User) authentication.getPrincipal();
                log.info("Username: " + user.getUsername());
            } else {
                // Get the principal (User)
                log.info("Principle: " + authentication.getPrincipal());
            }

            // Get the authorities (roles)
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                log.info("Role: " + authority.getAuthority());
            }

            // Get additional details
            Object details = authentication.getDetails();
            if (details != null) {
                log.info("Details: " + details.toString());
            }

            // Get credentials (password)
            Object credentials = authentication.getCredentials();
            if (credentials != null) {
                log.info("Credentials: " + credentials.toString());
            }
        }
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        user.setToken(token);
        tokenRepository.save(token);
    }

    private List<Token> getRevokeList(User user) {
        var revokedUserTokens = tokenRepository.findAllValidTokenByUserId(user.getId());
        log.info("REVOKE LIST FOR " + user.getUsername() + ": \n" + revokedUserTokens);
        return revokedUserTokens;
    }

    private void revokeAllUserTokens(User user) {
        var revokedUserTokens = getRevokeList(user);
        if (revokedUserTokens.isEmpty()) {
            return;
        }
        revokedUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
            token.setRevokedDatetime(LocalDateTime.now());
        });
        tokenRepository.saveAll(revokedUserTokens);
    }

    private void updateUserLastLogin(String username) {
        User user = userRepository.findByUsername(username).get();
        if (user != null) {
            user.setLastLoginDatetime(LocalDateTime.now());
            userRepository.save(user);
        }
    }
}
