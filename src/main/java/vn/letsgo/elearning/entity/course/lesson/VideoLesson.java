package vn.letsgo.elearning.entity.course.lesson;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@PrimaryKeyJoinColumn(name = "lesson_id")
@Table(name = "video_lesson")
public class VideoLesson extends Lesson {
    private String content;
}
