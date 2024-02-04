package vn.letsgo.elearning.entity.asset.lesson;

import jakarta.persistence.*;
import lombok.Builder;
import vn.letsgo.elearning.entity.global.Media;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@PrimaryKeyJoinColumn(name = "lesson_id")
@Table(name = "reading_lesson")
public class ReadingLesson extends Lesson {
    private String content;

    @Builder.Default
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "reading_lesson_media",
            joinColumns = @JoinColumn(name = "reading_lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "media_id")
    )
    private Set<Media> mediaSet  = new HashSet<>();

}
