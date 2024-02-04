package vn.letsgo.elearning.entity.global;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "media")
public class Media extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "media_id")
    private Long id;

    @Column(name = "media_name")
    private String mediaName;

    @Column(name = "media_path")
    private String filePath;

    @Column(name = "media_type")
    private MediaType mediaType;


}
