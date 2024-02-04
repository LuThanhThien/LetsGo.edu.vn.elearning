package vn.letsgo.elearning.dto.user.authentication;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @NotEmpty(message = "Please enter user name or email")
    private String username;

    @NotEmpty(message = "Please enter your password")
    private String password;

    @Builder.Default
    private LocalDateTime lastLoginDatetime = LocalDateTime.now();
}
