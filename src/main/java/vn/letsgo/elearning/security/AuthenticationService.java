package vn.letsgo.elearning.security;

import vn.letsgo.elearning.dto.user.LoginDto;
import vn.letsgo.elearning.dto.user.RegisterDto;
import vn.letsgo.elearning.entity.user.Token;
import vn.letsgo.elearning.entity.user.User;
import vn.letsgo.elearning.entity.user.Role;
import vn.letsgo.elearning.entity.user.TokenType;
import vn.letsgo.elearning.mapper.IUserMapper;
import vn.letsgo.elearning.repository.user.ITokenRepository;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    @Autowired
    private PasswordEncoder passwordEncoder;

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
            return AuthenticationResponse.builder()
                    .message("User already exists!")
                    .build();
        }
        registerDto.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        User user = IUserMapper.INSTANCE.registerDtoToUser(registerDto);
        user.setRole(Role.USER);
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        log.info("Extracted username: " + jwtService.extractUsername(jwtToken));
        log.info("JWT Token: " + jwtToken);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(user, jwtToken);

        log.info("Register successfully");
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
        log.info("PERMISSIONS (before): " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        log.info("Is authenticated? (before): " + SecurityContextHolder.getContext().getAuthentication().isAuthenticated());

        securityContext.setAuthentication(authentication);
        log.info("PERMISSION (after): " + SecurityContextHolder.getContext().getAuthentication().getAuthorities());
        log.info("Is authenticated? (after): " + SecurityContextHolder.getContext().getAuthentication().isAuthenticated());


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
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .message("Login successfully")
                .build();
    }


    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    private void updateUserLastLogin(String username) {
        User user = userRepository.findByUsername(username).get();
        if (user != null) {
            user.setLastLoginDate(LocalDateTime.now());
            userRepository.save(user);
        }
    }
}
