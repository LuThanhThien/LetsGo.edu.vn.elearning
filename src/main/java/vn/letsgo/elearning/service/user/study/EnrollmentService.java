package vn.letsgo.elearning.service.user.study;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.letsgo.elearning.dto.user.study.EnrollmentDto;
import vn.letsgo.elearning.entity.asset.Module;
import vn.letsgo.elearning.entity.user.study.Enrollment;
import vn.letsgo.elearning.entity.user.User;
import vn.letsgo.elearning.repository.user.study.IEnrollmentRepository;
import vn.letsgo.elearning.security.AuthenticationService;
import vn.letsgo.elearning.service.user.payment.PaymentService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService implements IEnrollmentService {

    @Autowired
    private final IEnrollmentRepository enrollmentRepository;

    @Autowired
    private final AuthenticationService authService;

    @Autowired
    private final PaymentService paymentService;

    @Override
    public Enrollment enroll(User user, EnrollmentDto enrollmentDto) {

        Enrollment enrollment = Enrollment.createEnrollment(
                user,
                enrollmentDto.getModules().toArray(new Module[0])
        );
        this.save(enrollment);

        return enrollment;
    }

    @Override
    public Enrollment save(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public Enrollment findById(long enrollmentId) {
        return enrollmentRepository.findById(enrollmentId).orElse(null);
    }

    @Override
    public List<Enrollment> findByModuleId(long id) {
        return enrollmentRepository.findByModuleId(id);
    }

    @Override
    public List<Enrollment> findByUserId(long id) {
        return enrollmentRepository.findByUserId(id);
    }

}
