package vn.letsgo.elearning.dto.user.authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
    private String firstName;

    private String lastName;

    @Email(message = "Please enter correct email format")
    private String email;

    @NotEmpty(message = "User name cannot be empty")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", message = "User name must contain at least 8 characters including letters and numbers, and cannot contain special characters '@$!%*#?&'")
    private String username;

    @NotEmpty(message = "Password cannot be empty")
    @Pattern(regexp = "^(?=.*[@$!%*#?&])(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d@$!%*#?&]{6,}$", message = "Password must contain at least 6 characters including letters, special characters and numbers")
    private String password;

}
