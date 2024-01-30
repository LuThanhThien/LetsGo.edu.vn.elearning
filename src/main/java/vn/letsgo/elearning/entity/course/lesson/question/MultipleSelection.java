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
@Table(name = "multiple_selection")
public class MultipleSelection extends Question {

    @ElementCollection
    @CollectionTable(
            name = "multiple_selection_collection",
            joinColumns = @JoinColumn(name = "question_id")
    )
    @Column(name = "selections")
    private List<String> selections;

    @ElementCollection
    @CollectionTable(
            name = "multiple_selection_correct_answers",
            joinColumns = @JoinColumn(name = "question_id")
    )
    @Column(name = "correct_answers")
    private List<String> correctAnswers;

}
