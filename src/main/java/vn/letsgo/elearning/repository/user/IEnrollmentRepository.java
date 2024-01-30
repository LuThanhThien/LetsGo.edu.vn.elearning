package vn.letsgo.elearning.repository.user;

import vn.letsgo.elearning.entity.user.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEnrollmentRepository extends JpaRepository<Enrollment, Long> {
}
