package vn.letsgo.elearning.entity.user.authentication;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import vn.letsgo.elearning.entity.user.User;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter @Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "token")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(
            name = "token_id",
            columnDefinition = "BINARY(16)"
    )
    private UUID id;

    @Column(unique = true, name = "token")
    private String token;

    @Builder.Default
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

    // == META ==
    @Builder.Default
    @Column(name = "created_datetime")
    private LocalDateTime createdDatetime = LocalDateTime.now();

    @Column(name = "revoked_datetime")
    private LocalDateTime revokedDatetime;

    @Override
    public String toString() {
        return "Token{" +
                "\nid=" + id +
                ", \ntoken='" + token + "\'" +
                ", \ntokenType=" + tokenType +
                ", \nrevoked=" + revoked +
                ", \nexpired=" + expired +
                ", \nusername=" + (user != null ? user.getUsername() : "null") +
                ", \ncreatedTime=" + createdDatetime +
                ", \nrevokedTime=" + (revokedDatetime != null ? revokedDatetime : "null") +
                "}";
    }

}
