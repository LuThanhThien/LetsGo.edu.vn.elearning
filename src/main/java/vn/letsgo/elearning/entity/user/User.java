package vn.letsgo.elearning.entity.user;

import jakarta.persistence.*;
import lombok.*;
import org.checkerframework.checker.units.qual.C;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vn.letsgo.elearning.annotation.AssocMethod;
import vn.letsgo.elearning.entity.user.authentication.Role;
import vn.letsgo.elearning.entity.user.authentication.Token;
import vn.letsgo.elearning.entity.user.payment.Payment;
import vn.letsgo.elearning.entity.user.payment.Wallet;
import vn.letsgo.elearning.entity.user.study.AssignmentAttempt;
import vn.letsgo.elearning.entity.user.study.Enrollment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter @Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_first_name")
    private String firstName;

    @Column(name = "user_last_name")
    private String lastName;

    @Column(name = "user_birth_date")
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_gender")
    private Gender gender;

    @Column(name = "user_location")
    private String location;

    @Column(name = "user_school")
    private String school;

    @Column(name = "user_avatar")
    private String avatar;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Builder.Default
    @Column(name = "enrollments")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Enrollment> enrollments = new HashSet<>();

    @Builder.Default
    @Column(name = "payments")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Payment> payments = new HashSet<>();

    @Builder.Default
    @Column(name = "assignment_attempts")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<AssignmentAttempt> assignmentAttempts = new HashSet<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Wallet wallet;

    @Builder.Default
    @Column(name = "created_datetime")
    private LocalDateTime createdDatetime = LocalDateTime.now();

    @Column(name = "last_login_datetime")
    private LocalDateTime lastLoginDatetime;

    @Column(name = "role")
    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Builder.Default
    @Column(name = "tokens")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Token> tokens = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", \nfirstName='" + firstName + '\'' +
                ", \nlastName='" + lastName + '\'' +
                ", \nbirthDate=" + birthDate +
                ", \ngender=" + gender +
                ", \nlocation='" + location + '\'' +
                ", \nschool='" + school + '\'' +
                ", \navatar='" + avatar + '\'' +
                ", \nemail='" + email + '\'' +
                ", \nusername='" + username + '\'' +
                ", \npassword='" + password + '\'' +
                ", \nenrollments=" + enrollments +
                ", \ncreatedDate=" + createdDatetime +
                ", \nlastLoginDate=" + lastLoginDatetime +
                ", \nrole=" + role +
                ", \ntokens=" + tokens.stream().map(Token::getId).toList() + // Only show token IDs
                '}';
    }

    @PostPersist
    public void initialize() {
        // Create a new Wallet when a User is persisted
        this.wallet = new Wallet();
        this.wallet.setUser(this);
    }

    //== Association assist method ==//
    public void setEnrollment(Enrollment enrollment) {
        this.enrollments.add(enrollment);
        enrollment.setUser(this);
    }

    public void setPayment(Payment payment) {
        this.payments.add(payment);
        payment.setUser(this);
    }

    public void setToken(Token token) {
        this.tokens.add(token);
        token.setUser(this);
    }

    public void setWallet(Wallet wallet) {
        // do nothing
    }

}


