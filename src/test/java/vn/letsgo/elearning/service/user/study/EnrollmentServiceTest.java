package vn.letsgo.elearning.service.user.study;

import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.letsgo.elearning.dto.user.study.EnrollmentDto;
import vn.letsgo.elearning.entity.asset.Module;
import vn.letsgo.elearning.entity.user.study.Enrollment;
import vn.letsgo.elearning.entity.user.User;
import vn.letsgo.elearning.entity.user.study.EnrollmentModule;
import vn.letsgo.elearning.repository.asset.IModuleRepository;
import vn.letsgo.elearning.repository.user.study.IEnrollmentRepository;
import vn.letsgo.elearning.security.authen.AuthenticationService;
import vn.letsgo.elearning.service.user.UserService;
import vn.letsgo.elearning.service.user.payment.PaymentService;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class EnrollmentServiceTest {

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private IEnrollmentRepository enrollmentRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private IModuleRepository moduleRepository;


    @Test
    void testEnroll() {
        // Initialize mocks
        MockitoAnnotations.initMocks(this);

        // Mock current user
        User mockUser = User.builder().build();

        // Mock input DTO
        Module module1 = createModule("Module 1", BigDecimal.valueOf(20));
        Module module2 = createModule("Module 2", BigDecimal.valueOf(30));
        Set<Module> modules = createModuleSet(module1, module2);

        EnrollmentDto enrollmentDto = EnrollmentDto.builder().modules(modules).build();

        // Mock enrollment modules
        EnrollmentModule enrollmentModule1 = EnrollmentModule.builder().module(module1).price(BigDecimal.valueOf(20)).build();
        EnrollmentModule enrollmentModule2 = EnrollmentModule.builder().module(module2).price(BigDecimal.valueOf(30)).build();
        Set<EnrollmentModule> expectedEnrollmentModules = new HashSet<>();
        expectedEnrollmentModules.add(enrollmentModule1);
        expectedEnrollmentModules.add(enrollmentModule2);

        when(enrollmentRepository.save(any(Enrollment.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Perform the enrollment
        Enrollment result = enrollmentService.enrollModule(mockUser, enrollmentDto);

        // Verify interactions and assertions
//        verify(enrollmentRepository, times(1)).save(any(Enrollment.class));

        // Assert equal
        assertEquals(mockUser, result.getUser());
        assertEquals(expectedEnrollmentModules.size(), result.getEnrollmentModules().size());

        Iterator<EnrollmentModule> expectedIterator = expectedEnrollmentModules.iterator();
        Iterator<EnrollmentModule> actualIterator = result.getEnrollmentModules().iterator();

        while (expectedIterator.hasNext() && actualIterator.hasNext()) {
            EnrollmentModule expectedModule = expectedIterator.next();
            EnrollmentModule actualModule = actualIterator.next();

            assertEquals(expectedModule.getModule().getName(), actualModule.getModule().getName());
            assertEquals(expectedModule.getPrice(), actualModule.getPrice());
        }
    }


    @Test
    public void testFindByUserId() {
        // Create a user
        User user = createUser("test_user", 1L);
        userService.save(user);

        // Create an enrollment for the user
        Module module = createModule("Module", BigDecimal.valueOf(20));
        moduleRepository.save(module);

        Enrollment enrollment = Enrollment.createEnrollment(user, module);
        enrollmentService.save(enrollment);

        // Find enrollments by user
        List<Enrollment> foundEnrollments = enrollmentRepository.findByUserId(user.getId());

        // Assert
        assertEquals(enrollment.getUser().getId(), user.getId());
        assertEquals(1, foundEnrollments.size());
        assertEquals(enrollment.getId(), foundEnrollments.get(0).getId());

    }

    @Test
    public void testFindByModule() {
        // Create a user
        User user = createUser("test_user", 1L);
        userService.save(user);

        // Create a module
        Module module = createModule("Test Module", BigDecimal.valueOf(100));
        moduleRepository.save(module);

        // Create an enrollment with the specified module
        Enrollment enrollment1 = Enrollment.createEnrollment(user, module);
        enrollmentService.save(enrollment1);

        Enrollment enrollment2 = Enrollment.createEnrollment(user, module);
        enrollmentService.save(enrollment2);

        // Find enrollments by module
        List<Enrollment> foundEnrollments = enrollmentRepository.findByModuleId(module.getId());

        // Assert
        assertEquals(2, foundEnrollments.size());
        assertEquals(enrollment1.getId(), foundEnrollments.get(0).getId());
        assertEquals(enrollment2.getId(), foundEnrollments.get(1).getId());

    }

    // Helper methods to create entities
    private User createUser(String username, long id) {
        return User.builder()
                .id(id)
                .username(username)
                .password("password")
                .build();
    }

    private Module createModule(String moduleName, BigDecimal price) {
        return Module.builder()
                .name(moduleName)
                .modulePrice(price)
                .build();
    }

    private Set<Module> createModuleSet(Module... modules) {
        Set<Module> moduleSet = new HashSet<>();
        for (Module module: modules) {
            moduleSet.add(module);
        }
        return moduleSet;
    }
}
