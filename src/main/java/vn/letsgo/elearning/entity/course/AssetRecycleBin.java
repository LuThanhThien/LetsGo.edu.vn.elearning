package vn.letsgo.elearning.entity.course;

import jakarta.persistence.*;
import vn.letsgo.elearning.entity.course.lesson.Lesson;
import vn.letsgo.elearning.entity.global.RecycleBin;

@Entity
@DiscriminatorValue(value = "A")
public class AssetRecycleBin extends RecycleBin {
    @OneToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToOne
    @JoinColumn(name = "module_id")
    private Chapter chapter;

    @OneToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    public String occupiedBy() {
        if (this.course != null) {
            return this.course.getClass().getName();
        } else if (this.chapter != null) {
            return this.chapter.getClass().getName();
        } else if (this.lesson != null) {
            return this.lesson.getClass().getName();
        }
        return null;
    }

    public boolean isOccupied() {
        return this.occupiedBy() != null ? true : false;
    }
}
