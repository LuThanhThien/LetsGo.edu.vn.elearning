package vn.letsgo.elearning.entity.asset;

import jakarta.persistence.*;
import lombok.*;
import vn.letsgo.elearning.entity.asset.lesson.question.Question;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Getter @Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "asset_id")
@Table(name = "course")
public class Course extends Asset {

    @Column(name = "course_name")
    private String name;

    @Column(name = "course_description")
    private String description;

    @Column(name = "course_level")
    @Enumerated(value = EnumType.ORDINAL)
    private CourseLevel level;

    @Builder.Default
    @Column(name = "chapters")
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private Set<Chapter> chapters = new HashSet<>();



}
