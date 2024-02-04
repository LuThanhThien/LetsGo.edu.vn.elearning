package vn.letsgo.elearning.entity.user.payment;

import jakarta.persistence.*;
import vn.letsgo.elearning.entity.global.AuditEntity;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import vn.letsgo.elearning.entity.user.User;
import vn.letsgo.elearning.entity.user.study.Enrollment;

import java.math.BigDecimal;

import static vn.letsgo.elearning.entity.user.payment.PaymentStatus.COMPLETED;
import static vn.letsgo.elearning.entity.user.payment.PaymentStatus.PENDING;

@Entity
@Getter @Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "payment")
public class Payment extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "enrollment_id")
    private Enrollment enrollment;

    //== Association Assist Method ==//
    public void setPaymentStatus(PaymentStatus paymentStatus) {
        if (this.paymentStatus == COMPLETED) {
            return;
        }
        this.paymentStatus = paymentStatus;
    }


    //== Create method ==//
    public static Payment createEnrollmentPayment(
            User user,
            Enrollment enrollment
    ) {
        Payment payment = Payment.builder()
                .enrollment(enrollment)
                .amount(enrollment.getTotalPrice())
                .paymentStatus(PENDING)
                .build();
        user.setPayment(payment);
        return payment;
    }

}
