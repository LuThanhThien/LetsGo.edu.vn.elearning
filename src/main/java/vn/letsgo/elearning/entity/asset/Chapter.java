package vn.letsgo.elearning.entity.asset;

import jakarta.persistence.*;
import lombok.*;
import vn.letsgo.elearning.entity.user.study.Enrollment;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "asset_id")
@Table(name = "chapter")
public class Chapter extends Asset {

    @Column(name = "module_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @Builder.Default
    @Column(name = "modules")
    @OneToMany(mappedBy = "chapter", cascade = CascadeType.ALL)
    private Set<Module> modules = new HashSet<>();

    @Column(name = "chapter_order")
    private int chapterOrder;

}
