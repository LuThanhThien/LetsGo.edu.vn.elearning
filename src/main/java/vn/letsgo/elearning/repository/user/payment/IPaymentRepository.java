package vn.letsgo.elearning.repository.user.payment;

import vn.letsgo.elearning.entity.user.payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPaymentRepository extends JpaRepository<Payment, Long> {

}
