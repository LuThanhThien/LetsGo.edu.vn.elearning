package vn.letsgo.elearning.entity.asset.lesson.question;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "question_id")
@Table(name = "multiple_choice")
public class MultipleChoice extends Question {

    @Builder.Default
    @ElementCollection
    @CollectionTable(
            name = "multiple_choice_collection",
            joinColumns = @JoinColumn(name = "question_id")
    )
    @Column(name = "choices")
    private Set<String> choices = new HashSet<>();

    @Column(name = "correct_answer")
    private String correctAnswer;


}
