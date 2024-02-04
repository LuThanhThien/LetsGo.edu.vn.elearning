package vn.letsgo.elearning.entity.asset;

import jakarta.persistence.*;
import vn.letsgo.elearning.entity.asset.lesson.Lesson;
import lombok.*;
import vn.letsgo.elearning.entity.user.study.Enrollment;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "asset_id")
@Table(name = "module")
public class Module extends Asset {

    @Column(name = "module_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;

    @Builder.Default
    @Column(name = "lessons")
    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL)
    private Set<Lesson> lessons = new HashSet<>();

    @Column(name = "module_price")
    private BigDecimal modulePrice;

    @Column(name = "module_order")
    private int moduleOrder;

}
