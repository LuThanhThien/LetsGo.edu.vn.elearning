package letsgo.vn.elearning.security;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String accessToken;

    private String refreshToken;

    private String message;
}
