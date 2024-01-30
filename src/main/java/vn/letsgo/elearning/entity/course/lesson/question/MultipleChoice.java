package vn.letsgo.elearning.entity.course.lesson.question;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "question_id")
@Table(name = "multiple_choice")
public class MultipleChoice extends Question {

    @ElementCollection
    @CollectionTable(
            name = "multiple_choice_collection",
            joinColumns = @JoinColumn(name = "question_id")
    )
    @Column(name = "choices")
    private List<String> choices;

    @Column(name = "correct_answer")
    private String correctAnswer;


}
