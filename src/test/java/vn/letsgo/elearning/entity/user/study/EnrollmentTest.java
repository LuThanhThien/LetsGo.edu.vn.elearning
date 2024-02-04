package vn.letsgo.elearning.entity.user.study;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import vn.letsgo.elearning.entity.asset.Module;
import vn.letsgo.elearning.entity.user.User;
import vn.letsgo.elearning.entity.user.payment.Payment;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EnrollmentTest {

    @Test
    public void createEnrollment() {
        // given
        Module module = Module.builder()
                .name("module 1")
                .modulePrice(BigDecimal.valueOf(12))
                .build();

        User user = User.builder()
                .username("testing_user")
                .password("1234")
                .build();

        // when
        EnrollmentModule enrollmentModule = EnrollmentModule.createEnrollmentModule(
                module,
                module.getModulePrice()
        );

        Enrollment enrollment = Enrollment.createEnrollment(
                user,
               module
        );

        Payment payment = Payment.createEnrollmentPayment(
                user,
                enrollment
        );

        // then
        assertUserSet(user.getEnrollments(), enrollment, 1);
        assertUserSet(user.getPayments(), payment, 1);

    }

    public void assertUserSet(Set<?> userSet, Object object, int size) {
        assertNotNull(userSet);
        assertEquals(size, userSet.size());
        assertTrue(userSet.contains(object));
    }

}