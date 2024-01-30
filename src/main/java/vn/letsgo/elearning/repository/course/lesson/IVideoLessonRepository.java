package vn.letsgo.elearning.repository.course.lesson;

import vn.letsgo.elearning.entity.course.lesson.VideoLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVideoLessonRepository extends JpaRepository<VideoLesson, Long> {
}
