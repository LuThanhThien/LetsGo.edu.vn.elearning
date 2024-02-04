package vn.letsgo.elearning.entity.asset.lesson;


import jakarta.persistence.*;
import lombok.Builder;
import vn.letsgo.elearning.entity.asset.Module;
import vn.letsgo.elearning.entity.asset.lesson.question.Question;
import vn.letsgo.elearning.entity.user.study.AssignmentAttempt;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "lesson_id")
@Table(name = "assignment")
public class Assignment extends Lesson {

    @Column(name = "content")
    private String content;

    @Column(name = "questions")
    @OneToMany(mappedBy = "assignment", cascade = CascadeType.ALL)
    private Set<Question> questions = new HashSet<>();

    @Builder.Default
    @Column(name = "assignment_attempts")
    @OneToMany(mappedBy = "assignment", cascade = CascadeType.ALL)
    private Set<AssignmentAttempt> assignmentAttempts = new HashSet<>();


}
