package letsgo.vn.elearning.dto.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginDto {
    @NotEmpty(message = "Please enter user name or email")
    private String username;

    @NotEmpty(message = "Please enter your password")
    private String password;
}
