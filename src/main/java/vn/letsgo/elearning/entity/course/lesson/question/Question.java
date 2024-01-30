package vn.letsgo.elearning.entity.course.lesson.question;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import vn.letsgo.elearning.entity.course.lesson.AssignmentLesson;
import vn.letsgo.elearning.entity.global.AuditMetaData;

import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "question")
public abstract class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    @Column(name = "question_text", nullable = false)
    private String questionText;

    @ElementCollection
    @CollectionTable(
            name = "question_images",
            joinColumns = @JoinColumn(name = "question_id")
    )
    @Column(name = "image", length = 1000)
    private List<String> questionImages;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lesson_id")
    private AssignmentLesson assignment;

    @Column(name = "metadata")
    private AuditMetaData metaData;

}
