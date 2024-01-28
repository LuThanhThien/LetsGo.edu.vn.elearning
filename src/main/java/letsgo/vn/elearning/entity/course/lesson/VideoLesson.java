package letsgo.vn.elearning.entity.course.lesson;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.*;

@Entity
@PrimaryKeyJoinColumn(name = "video_id")
public class VideoLesson extends Lesson {
    private String content;
}
