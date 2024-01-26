package letsgo.vn.elearning.entity.course;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;

@Entity
@Data
@Builder
@RequiredArgsConstructor
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

}
