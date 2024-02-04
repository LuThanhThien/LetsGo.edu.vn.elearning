package vn.letsgo.elearning.entity.asset.lesson;

import jakarta.persistence.*;
import vn.letsgo.elearning.entity.asset.Asset;
import vn.letsgo.elearning.entity.asset.Module;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "asset_id")
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "lesson")
public abstract class Lesson extends Asset {

    @Column(name = "lesson_name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id")
    private Module module;

    @Column(name = "lesson_order")
    private int lessonOrder;


}
