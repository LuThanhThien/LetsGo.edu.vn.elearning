package vn.letsgo.elearning.service.user.study;

import vn.letsgo.elearning.dto.user.study.EnrollmentDto;
import vn.letsgo.elearning.entity.asset.Module;
import vn.letsgo.elearning.entity.user.study.Enrollment;
import vn.letsgo.elearning.entity.user.User;

import java.util.List;
import java.util.Set;

public interface IEnrollmentService {

    Enrollment enroll(User user, EnrollmentDto enrollmentDto);

    Enrollment save(Enrollment enrollment);

    Enrollment findById(long enrollmentId);

    List<Enrollment> findByModuleId(long id);

    List<Enrollment> findByUserId(long id);

}
