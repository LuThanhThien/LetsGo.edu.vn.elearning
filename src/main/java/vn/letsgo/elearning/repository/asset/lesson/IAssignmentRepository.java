package vn.letsgo.elearning.repository.asset.lesson;

import vn.letsgo.elearning.entity.asset.lesson.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAssignmentRepository extends JpaRepository<Assignment, Long> {
}
