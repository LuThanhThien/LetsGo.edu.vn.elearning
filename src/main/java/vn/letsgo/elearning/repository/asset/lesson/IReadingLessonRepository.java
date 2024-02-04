package vn.letsgo.elearning.repository.asset.lesson;

import vn.letsgo.elearning.entity.asset.lesson.ReadingLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReadingLessonRepository extends JpaRepository<ReadingLesson, Long> {

}
