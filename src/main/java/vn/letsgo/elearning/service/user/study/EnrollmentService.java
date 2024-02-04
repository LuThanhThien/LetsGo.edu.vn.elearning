package vn.letsgo.elearning.service.user.study;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.letsgo.elearning.dto.user.study.EnrollmentDto;
import vn.letsgo.elearning.entity.asset.Module;
import vn.letsgo.elearning.entity.user.study.Enrollment;
import vn.letsgo.elearning.entity.user.User;
import vn.letsgo.elearning.repository.user.study.IEnrollmentRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EnrollmentService implements IEnrollmentService {

    @Autowired
    private final IEnrollmentRepository enrollmentRepository;

    @Override
    public Enrollment enrollModule(User user, EnrollmentDto enrollmentDto) {

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

    @Override
    public Optional<Enrollment> findByUserIdAndModuleId(long userId, long moduleId) {
        return enrollmentRepository.findByUserIdAndModuleId(userId, moduleId);
    }

    @Override
    public boolean hasPaidForModule(Long userId, Long moduleId) {
        Optional<Enrollment> enrollments = enrollmentRepository.findByUserIdAndModuleId(userId, moduleId);
        Enrollment enrollment = enrollments.get();
        if (enrollment == null) { return false; }
        return enrollment.isPaid();
    }
}
