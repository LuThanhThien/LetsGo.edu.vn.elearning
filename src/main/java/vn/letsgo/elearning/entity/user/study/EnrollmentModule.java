package vn.letsgo.elearning.entity.user.study;

import jakarta.persistence.*;
import lombok.*;
import vn.letsgo.elearning.entity.asset.Module;

import java.math.BigDecimal;

@Entity
@Getter @Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "enrollment_module")
public class EnrollmentModule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollment_module_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enrollment_id")
    private Enrollment enrollment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id")
    private Module module;

    @Column(name = "module_price")
    private BigDecimal price;

    //== Create Method ==//
    public static EnrollmentModule createEnrollmentModule(Module module, BigDecimal modulePrice) {
        EnrollmentModule enrollmentModule = new EnrollmentModule();
        enrollmentModule.setModule(module);
        enrollmentModule.setPrice(modulePrice);
        return enrollmentModule;
    }

}
