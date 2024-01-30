package vn.letsgo.elearning.repository.course.lesson;

import vn.letsgo.elearning.entity.course.lesson.ReadingLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReadingLessonRepository extends JpaRepository<ReadingLesson, Long> {

}
