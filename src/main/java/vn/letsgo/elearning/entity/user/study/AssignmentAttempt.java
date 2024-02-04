package vn.letsgo.elearning.entity.user.study;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import vn.letsgo.elearning.entity.asset.lesson.Assignment;
import vn.letsgo.elearning.entity.global.AuditEntity;
import vn.letsgo.elearning.entity.user.User;

@Entity
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "assignment_attempt")
public class AssignmentAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_attempt_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "score_archived")
    private int scoreArchived;

    @Embedded
    @Column(name = "metadata")
    private AuditEntity metaData;

}
