package vn.letsgo.elearning.service.user.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.letsgo.elearning.entity.user.payment.Payment;
import vn.letsgo.elearning.entity.user.payment.PaymentStatus;
import vn.letsgo.elearning.entity.user.study.Enrollment;
import vn.letsgo.elearning.exception.DataNotFoundException;
import vn.letsgo.elearning.repository.user.payment.IPaymentRepository;

import java.util.Optional;

import static vn.letsgo.elearning.entity.user.payment.PaymentStatus.COMPLETED;
import static vn.letsgo.elearning.entity.user.payment.PaymentStatus.FAILED;

@Service
@RequiredArgsConstructor
public class PaymentService implements IPaymentService {

    @Autowired
    private final IPaymentRepository paymentRepository;


    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment findById(long paymentId) {
        Optional<Payment> optionalPayment = paymentRepository.findById(paymentId);

        if (optionalPayment.get() == null) {
            throw new DataNotFoundException(
                    Payment.class,
                    "Cannot find payment with id: " + paymentId
            );
        }
        return optionalPayment.get();
    }

    @Override
    public PaymentStatus processEnrollmentPayment(Enrollment enrollment) {
        Payment payment = Payment.createEnrollmentPayment(
            enrollment.getUser(),
            enrollment
        );
        return this.processPayment(payment);
    }

    @Override
    public PaymentStatus processPayment(Payment payment) {
        boolean isCompleted = false;

        if (payment.getAmount().equals(0)) {
            isCompleted = true;
        } else {
            // payment logic here
        }

        if (isCompleted) {
            payment.setPaymentStatus(COMPLETED);
        } else {
            payment.setPaymentStatus(FAILED);
        }
        this.save(payment);

        return payment.getPaymentStatus();
    }
}
