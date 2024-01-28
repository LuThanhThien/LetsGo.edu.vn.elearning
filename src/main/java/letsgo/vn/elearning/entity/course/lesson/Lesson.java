package letsgo.vn.elearning.entity.course.lesson;

import jakarta.persistence.*;
import letsgo.vn.elearning.entity.course.AssetCycleStatus;
import letsgo.vn.elearning.entity.course.Module;
import letsgo.vn.elearning.entity.global.AuditMetaData;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "lesson")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

    @Column(name = "lesson_status")
    private AssetCycleStatus status;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;

    @Column(name = "metadata")
    private AuditMetaData metaData;

}
