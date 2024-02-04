package vn.letsgo.elearning.service.user.study;

import vn.letsgo.elearning.dto.user.study.EnrollmentDto;
import vn.letsgo.elearning.entity.user.study.Enrollment;
import vn.letsgo.elearning.entity.user.User;

import java.util.List;
import java.util.Optional;

public interface IEnrollmentService {

    Enrollment enrollModule(User user, EnrollmentDto enrollmentDto);

    Enrollment save(Enrollment enrollment);

    Enrollment findById(long enrollmentId);

    List<Enrollment> findByModuleId(long id);

    List<Enrollment> findByUserId(long id);

    Optional<Enrollment> findByUserIdAndModuleId(long userId, long moduleId);

    boolean hasPaidForModule(Long userId, Long moduleId);
}
