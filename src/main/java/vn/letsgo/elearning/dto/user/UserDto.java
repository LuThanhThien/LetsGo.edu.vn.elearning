package vn.letsgo.elearning.dto.user;

import jakarta.persistence.Column;
import lombok.*;
import org.mapstruct.NullValuePropertyMappingStrategy;
import vn.letsgo.elearning.entity.user.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String location;

    private String school;

    private String avatar;

    private String email;

    private String userName;

    private String userPassword;
}
