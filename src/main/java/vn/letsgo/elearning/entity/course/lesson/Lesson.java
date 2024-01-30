package vn.letsgo.elearning.entity.course.lesson;

import jakarta.persistence.*;
import vn.letsgo.elearning.entity.course.AssetCycleStatus;
import vn.letsgo.elearning.entity.course.AssetRecycleBin;
import vn.letsgo.elearning.entity.course.Chapter;
import vn.letsgo.elearning.entity.global.AuditMetaData;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "lesson")
public abstract class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lesson_id")
    private Long id;

    @Column(name = "lesson_name")
    private String name;

    @Column(name = "lesson_status")
    private AssetCycleStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;

    @OneToOne
    @JoinColumn(name = "bin_id")
    private AssetRecycleBin recycleBin;

    @Column(name = "metadata")
    private AuditMetaData metaData;

}
