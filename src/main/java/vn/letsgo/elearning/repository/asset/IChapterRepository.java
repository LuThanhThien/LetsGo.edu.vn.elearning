package vn.letsgo.elearning.repository.asset;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.letsgo.elearning.entity.asset.Chapter;

public interface IChapterRepository extends JpaRepository<Chapter, Long> {
}
