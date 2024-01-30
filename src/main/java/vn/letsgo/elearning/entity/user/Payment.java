package vn.letsgo.elearning.entity.user;

import jakarta.persistence.*;
import vn.letsgo.elearning.entity.global.AuditMetaData;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "payment")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long id;

    @Builder.Default
    @Column(name = "payment_date")
    private LocalDateTime paymentDate = LocalDateTime.now();

    @Column(name = "amount")
    private double amount;

    @Builder.Default
    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "enrollments")
    @OneToMany(mappedBy = "payment", cascade = CascadeType.ALL)
    private List<Enrollment> enrollments;

    @Column(name = "metadata")
    private AuditMetaData metaData;

}
