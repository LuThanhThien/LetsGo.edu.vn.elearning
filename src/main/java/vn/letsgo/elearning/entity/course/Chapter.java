package vn.letsgo.elearning.entity.course;

import jakarta.persistence.*;
import vn.letsgo.elearning.entity.course.lesson.Lesson;
import vn.letsgo.elearning.entity.global.AuditMetaData;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "chapter")
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chapter_id")
    private Long id;

    @Column(name = "chapter_name")
    private String name;

    @Column(name = "chapter_status")
    private AssetCycleStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "lessons")
    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL)
    private List<Lesson> lessons;

    @OneToOne
    @JoinColumn(name = "bin_id")
    private AssetRecycleBin recycleBin;

    @Column(name = "metadata")
    private AuditMetaData metaData;
}
