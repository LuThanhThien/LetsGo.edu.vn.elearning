package vn.letsgo.elearning.entity.user.payment;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import vn.letsgo.elearning.entity.user.User;

import java.math.BigDecimal;

@Embeddable
@Setter @Getter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Wallet {

    private BigDecimal balance;


    //== Business logic ==//
    public void addFunds(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0) {
            this.balance = this.balance.add(amount);
        } else {
            throw new IllegalArgumentException("Amount must be greater than zero.");
        }
    }

    public void deductFunds(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) > 0 && amount.compareTo(this.balance) <= 0) {
            this.balance = this.balance.subtract(amount);
        } else {
            throw new IllegalArgumentException("Invalid amount or insufficient funds.");
        }
    }

    public boolean hasSufficientFunds(BigDecimal amount) {
        return amount.compareTo(this.balance) <= 0;
    }

}
