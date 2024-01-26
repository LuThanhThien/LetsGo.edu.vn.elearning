package letsgo.vn.elearning.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static letsgo.vn.elearning.entity.user.Permission.*;
import static letsgo.vn.elearning.entity.user.Permission.USER_DELETE;
import static letsgo.vn.elearning.entity.user.Role.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SecurityFilterChainConfig {

    private static final String[]  WHITE_LIST_URL = {
            "/api/v1/auth/**"
    };
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        req -> req
                                .requestMatchers("/admin/**").hasAnyRole(ADMIN.name())
                                .requestMatchers(GET, "/admin/**").hasAnyAuthority(ADMIN_READ.name(), USER_READ.name())
                                .requestMatchers(POST, "/admin/**").hasAnyAuthority(ADMIN_CREATE.name(), USER_CREATE.name())
                                .requestMatchers(PUT, "/admin/**").hasAnyAuthority(ADMIN_UPDATE.name(), USER_UPDATE.name())
                                .requestMatchers(DELETE, "/admin/**").hasAnyAuthority(ADMIN_DELETE.name(), USER_DELETE.name())

                                .requestMatchers("/manager/**").hasAnyRole(
                                        ADMIN.toString(),
                                        MANAGER.name()
                                )
                                .requestMatchers(GET, "/manager/**").hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name(), USER_READ.name())
                                .requestMatchers(POST, "/manager/**").hasAnyAuthority(ADMIN_CREATE.name(), MANAGER_CREATE.name(), USER_CREATE.name())
                                .requestMatchers(PUT, "/manager/**").hasAnyAuthority(ADMIN_UPDATE.name(),MANAGER_UPDATE.name(), USER_UPDATE.name())
                                .requestMatchers(DELETE, "/manager/**").hasAnyAuthority(ADMIN_DELETE.name(), MANAGER_DELETE.name(), USER_DELETE.name())

                                .requestMatchers("/user/**").hasAnyRole(
                                        ADMIN.name(),
                                        MANAGER.name(),
                                        USER.name()
                                )
                                .requestMatchers(GET, "/user/**").hasAnyAuthority(USER_READ.name())
                                .requestMatchers(POST, "/user/**").hasAnyAuthority(USER_CREATE.name())
                                .requestMatchers(PUT, "/user/**").hasAnyAuthority(USER_UPDATE.name())
                                .requestMatchers(DELETE, "/user/**").hasAnyAuthority(USER_DELETE.name())

                                .requestMatchers(WHITE_LIST_URL).permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(LogoutConfigurer::permitAll);
        return http.build();
    }

//    https://www.baeldung.com/java-spring-fix-403-error

}
