package vn.letsgo.elearning.repository.course;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.letsgo.elearning.entity.course.Chapter;

@Repository
public interface IChapterRepository extends JpaRepository<Chapter, Long> {
}
