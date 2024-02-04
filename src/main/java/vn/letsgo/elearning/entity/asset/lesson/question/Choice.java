package vn.letsgo.elearning.entity.asset.lesson.question;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import vn.letsgo.elearning.entity.global.AuditEntity;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "choice")
public class Choice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "choice_id")
    private Question question;

    @Column(name = "is_correct")
    private boolean isCorrect;

    @Embedded
    @Column(name = "metadata")
    private AuditEntity metaData;

}
