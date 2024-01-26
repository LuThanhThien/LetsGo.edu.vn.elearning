package letsgo.vn.elearning.dto.user;

import letsgo.vn.elearning.entity.user.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Long phoneNumber;

    private String address;

    private String avatar;

    private String facebook;

    private String email;

    private String userName;

    private String userPassword;
}
