package vn.letsgo.elearning.repository.course.lesson;

import vn.letsgo.elearning.entity.course.lesson.AssignmentLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAssignmentRepository extends JpaRepository<AssignmentLesson, Long> {
}
