package vn.letsgo.elearning.service.user.payment;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import vn.letsgo.elearning.entity.user.User;
import vn.letsgo.elearning.entity.user.study.Enrollment;
import vn.letsgo.elearning.repository.user.payment.IPaymentRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PaymentServiceTest {

    @Mock
    private IPaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    @Test
    void testProcessEnrollmentPayment() {
        // Arrange
        User user = new User();  // Create a user instance
        Enrollment enrollment = Enrollment.createEnrollment(user);

        // Act
        paymentService.processEnrollmentPayment(enrollment);

        // Assert
        assertEquals(enrollment, user.getEnrollments().iterator().next());
    }
}
