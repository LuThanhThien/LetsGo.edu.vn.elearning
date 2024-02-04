package vn.letsgo.elearning.repository.asset;

import vn.letsgo.elearning.entity.asset.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICourseRepository extends JpaRepository<Course, Long> {
}
