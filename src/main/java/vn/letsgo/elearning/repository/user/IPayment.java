package vn.letsgo.elearning.repository.user;

import vn.letsgo.elearning.entity.user.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPayment extends JpaRepository<Payment, Long> {

}
