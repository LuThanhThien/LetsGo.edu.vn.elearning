package letsgo.vn.elearning.entity.course;

import jakarta.persistence.*;
import letsgo.vn.elearning.entity.global.AuditMetaData;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "module")
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "module_name")
    private String name;

    @Column(name = "module_type")
    private ModuleType type;

    @Column(name = "module_status")
    private AssetCycleStatus status;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "metadata")
    private AuditMetaData metaData;
}
