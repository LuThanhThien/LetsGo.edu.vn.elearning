package vn.letsgo.elearning.repository.asset;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.letsgo.elearning.entity.asset.Module;

@Repository
public interface IModuleRepository extends JpaRepository<Module, Long> {
}
