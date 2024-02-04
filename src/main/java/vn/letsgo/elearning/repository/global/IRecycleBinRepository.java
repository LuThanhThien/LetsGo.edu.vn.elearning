package vn.letsgo.elearning.repository.global;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.letsgo.elearning.entity.asset.RecycleBin;

@Repository
public interface IRecycleBinRepository extends JpaRepository<RecycleBin, Long> {
}
