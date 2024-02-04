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
@Table(name = "multiple_selection")
public class MultipleSelection extends Question {

    @Builder.Default
    @ElementCollection
    @CollectionTable(
            name = "multiple_selection_collection",
            joinColumns = @JoinColumn(name = "question_id")
    )
    @Column(name = "selections")
    private Set<String> selections = new HashSet<>();


}
