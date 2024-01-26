package letsgo.vn.elearning.entity.user;

import letsgo.vn.elearning.entity.user.TokenType;
import jakarta.persistence.*;
import letsgo.vn.elearning.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "token")
public class Token {
    @Id
    @GeneratedValue
    @Column(name = "token_id")
    private Long id;

    @Column(unique = true, name = "token")
    private String token;

    @Enumerated(EnumType.STRING)
    @Column(name = "token_type")
    private TokenType tokenType = TokenType.BEARER;

    @Column(name = "revoked")
    private boolean revoked;

    @Column(name = "expired")
    private boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}
