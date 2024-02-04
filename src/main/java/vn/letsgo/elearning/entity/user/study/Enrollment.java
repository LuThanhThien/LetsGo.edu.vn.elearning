package vn.letsgo.elearning.entity.user.study;

import jakarta.persistence.*;
import lombok.*;
import vn.letsgo.elearning.entity.asset.Module;
import vn.letsgo.elearning.entity.global.AuditEntity;
import vn.letsgo.elearning.entity.user.User;
import vn.letsgo.elearning.entity.user.payment.Payment;
import vn.letsgo.elearning.entity.user.payment.PaymentStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static vn.letsgo.elearning.entity.user.study.EnrollmentStatus.ENROLLED;

@Entity
@Getter @Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "enrollment")
public class Enrollment extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollment_id")
    private Long id;

    @Builder.Default
    @Column(name = "enrollment_datetime")
    private LocalDateTime enrollmentDatetime = LocalDateTime.now();

    @Builder.Default
    @Column(name = "enrollment_status")
    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status = ENROLLED;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder.Default
    @OneToMany(mappedBy = "enrollment", cascade = CascadeType.ALL)
    private Set<EnrollmentModule> enrollmentModules = new HashSet<>();

    @OneToOne(mappedBy = "enrollment")
    private Payment payment;

    @Column(name = "completed_datetime")
    private LocalDateTime completedDatetime;


    //== Association Assist Method ==//
    private void setEnrollmentModule(EnrollmentModule enrollmentModule) {
        this.enrollmentModules.add(enrollmentModule);
        enrollmentModule.setEnrollment(this);
    }

    public void setStatus(EnrollmentStatus enrollmentStatus) {
        if (this.status == EnrollmentStatus.COMPLETED) {
            return;
        }
        this.status = enrollmentStatus;
    }

    //== Create Method ==//
    public static Enrollment createEnrollment(
            User user,
            Module... modules
    ) {
        Enrollment enrollment = Enrollment.builder()
                .build();
        user.setEnrollment(enrollment);

        for (Module module: modules) {
            enrollment.setEnrollmentModule(
                    EnrollmentModule.createEnrollmentModule(module, module.getModulePrice())
            );
        }
        return enrollment;
    }

    //== Business logic ==//
    public BigDecimal getTotalPrice() {
        return enrollmentModules.stream()
                .map(EnrollmentModule::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public PaymentStatus getPaymentStatus() {
        return payment.getPaymentStatus();
    }

    public boolean isPaid() {
        return (getPaymentStatus() == PaymentStatus.COMPLETED) ? true : false;
    }


}

