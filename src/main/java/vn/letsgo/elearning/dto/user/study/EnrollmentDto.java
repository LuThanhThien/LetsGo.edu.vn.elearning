package vn.letsgo.elearning.dto.user.study;

import lombok.*;
import org.springframework.security.core.parameters.P;
import vn.letsgo.elearning.entity.asset.Module;
import vn.letsgo.elearning.entity.user.User;
import vn.letsgo.elearning.entity.user.payment.Payment;

import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentDto {

    private Set<Module> modules = new HashSet<>();

}
