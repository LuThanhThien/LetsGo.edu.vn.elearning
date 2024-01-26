package letsgo.vn.elearning.entity.course;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;


@Entity
@Data
@Builder
@RequiredArgsConstructor
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

    @Column(name = "course_cycle_status")
    @Enumerated(value = EnumType.STRING)
    private AssetCycleStatus statusCycle;

    @Column(name = "course_display_status")
    private AssetDisplayStatus displayStatus;

    @Column(name = "number_enrollment")
    private int numberEnrollment;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();


    @Column(name = "latest_edit_date")
    private LocalDateTime latestEditDate;


}
