package letsgo.vn.elearning.utility;

import letsgo.vn.elearning.entity.user.Role;
import letsgo.vn.elearning.entity.user.User;
import letsgo.vn.elearning.security.JwtService;
import letsgo.vn.elearning.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {

    @Autowired
    private final UserService userService;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    @Override
    public void run(String... args) throws Exception {

        if(userService.count() == 0) {

            User newAdmin = User.builder()
                    .username("admin@letsgo.vn")
                    .password(passwordEncoder.encode("qwer1234@"))
                    .firstName("ADMIN")
                    .lastName("LET'S GO")
                    .role(Role.ADMIN)
                    .build();
            userService.save(newAdmin);
            String adminToken = jwtService.generateToken(newAdmin);
            log.info("Admin token: " + adminToken);

            User newUser = User.builder()
                    .username("user@letsgo.vn")
                    .password(passwordEncoder.encode("qwer1234@"))
                    .firstName("USER")
                    .lastName("LET'S GO")
                    .role(Role.USER)
                    .build();
            userService.save(newUser);
            String userToken = jwtService.generateToken(newUser);
            log.info("User token: " + userToken);

        }
    }
}

