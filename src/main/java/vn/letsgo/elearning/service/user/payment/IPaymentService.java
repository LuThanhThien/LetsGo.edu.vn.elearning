package vn.letsgo.elearning.service.user.payment;

import vn.letsgo.elearning.entity.user.payment.Payment;
import vn.letsgo.elearning.entity.user.payment.PaymentStatus;
import vn.letsgo.elearning.entity.user.study.Enrollment;

public interface IPaymentService {
    Payment save(Payment payment);

    Payment findById(long paymentId);

    PaymentStatus processEnrollmentPayment(Enrollment enrollment);

    PaymentStatus processPayment(Payment payment);
}
