package vn.letsgo.elearning.entity.asset.lesson.question;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import vn.letsgo.elearning.entity.asset.lesson.Assignment;
import vn.letsgo.elearning.entity.global.AuditEntity;
import vn.letsgo.elearning.entity.global.Media;

import java.util.List;

@Entity
@Getter @Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "question")
public abstract class Question{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    @Column(name = "question_title", nullable = false)
    private String questionTitle;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "question_media",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name = "media_id")
    )
    private List<Media> mediaList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private Assignment assignment;

    @Embedded
    @Column(name = "metadata")
    private AuditEntity metaData;

}
