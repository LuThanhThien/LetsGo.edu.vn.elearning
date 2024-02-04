package vn.letsgo.elearning.security.author;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.letsgo.elearning.service.user.study.EnrollmentService;

@Service
@RequiredArgsConstructor
public class ModuleAccessService {

    @Autowired
    private final EnrollmentService enrollmentService;

    public boolean hasPaidForModule(Long userId, Long moduleId) {
        // Implement logic to check if the user has paid for the specified module
        return enrollmentService.hasPaidForModule(userId, moduleId);
    }
}