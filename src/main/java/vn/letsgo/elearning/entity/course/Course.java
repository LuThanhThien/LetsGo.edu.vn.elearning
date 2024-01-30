package vn.letsgo.elearning.entity.course;

import jakarta.persistence.*;
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
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;

    @Column(name = "course_name")
    private String name;

    @Column(name = "course_description")
    private String description;

    @Column(name = "course_level")
    @Enumerated(value = EnumType.ORDINAL)
    private CourseLevel level;

    @Column(name = "modules")
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Chapter> chapters;

    @Column(name = "course_cycle_status")
    @Enumerated(value = EnumType.STRING)
    private AssetCycleStatus statusCycle;

    @Column(name = "course_display_status")
    private AssetDisplayStatus displayStatus;

    @Column(name = "number_enrollment")
    private int numberEnrollment;

    @OneToOne
    @JoinColumn(name = "bin_id")
    private AssetRecycleBin recycleBin;

    @Column(name = "metadata")
    private AuditMetaData metaData;

}
