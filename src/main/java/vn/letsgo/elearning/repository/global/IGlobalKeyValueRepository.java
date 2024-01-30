package vn.letsgo.elearning.repository.global;

import vn.letsgo.elearning.entity.global.ApplicationSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGlobalKeyValueRepository extends JpaRepository<ApplicationSetting, Long> {
}
