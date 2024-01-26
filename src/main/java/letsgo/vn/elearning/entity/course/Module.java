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
@Table(name = "module")
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "module_name")
    private String name;

    @Column(name = "module_type")
    private ModuleType type;

    @Column(name = "module_status")
    private AssetCycleStatus status;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
