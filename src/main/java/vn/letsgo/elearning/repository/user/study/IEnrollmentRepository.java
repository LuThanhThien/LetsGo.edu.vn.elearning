package vn.letsgo.elearning.repository.user.study;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.letsgo.elearning.entity.asset.Module;
import vn.letsgo.elearning.entity.user.study.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.letsgo.elearning.entity.user.User;

import java.util.List;

@Repository
public interface IEnrollmentRepository extends JpaRepository<Enrollment, Long> {

    @Query("""
            select e from Enrollment e inner join User u\s
            on e.user.id = u.id\s
            where u.id = :id
            """)
    List<Enrollment> findByUserId(@Param("id") long id);

    @Query("""
            select e from Enrollment e\s
            join e.enrollmentModules em\s
            where em.module.id = :id
            """)
    List<Enrollment> findByModuleId(@Param("id") long id);

}
