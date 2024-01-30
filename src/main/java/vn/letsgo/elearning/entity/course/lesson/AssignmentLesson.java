package vn.letsgo.elearning.entity.course.lesson;


import jakarta.persistence.*;
import vn.letsgo.elearning.entity.course.lesson.question.Question;

import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "lesson_id")
@Table(name = "assignment_lesson")
public class AssignmentLesson extends Lesson {

    @Column(name = "content")
    private String content;

    @Column(name = "questions")
    @OneToMany(mappedBy = "assignment", cascade = CascadeType.ALL)
    private List<Question> questions;

}
