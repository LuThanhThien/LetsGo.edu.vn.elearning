package letsgo.vn.elearning.mapper.interfaces;

import javax.annotation.processing.Generated;
import letsgo.vn.elearning.dto.user.RegisterDto;
import letsgo.vn.elearning.dto.user.UserDto;
import letsgo.vn.elearning.entity.user.User;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-28T12:46:20+0700",
    comments = "version: 1.6.0.Beta1, compiler: javac, environment: Java 17.0.9 (Oracle Corporation)"
)
@Component
public class IUserMapperImpl implements IUserMapper {

    @Override
    public User userDtoToUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.firstName( userDto.getFirstName() );
        user.lastName( userDto.getLastName() );
        user.birthDate( userDto.getBirthDate() );
        user.gender( userDto.getGender() );
        user.avatar( userDto.getAvatar() );
        user.email( userDto.getEmail() );

        return user.build();
    }

    @Override
    public UserDto userToUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDto.UserDtoBuilder userDto = UserDto.builder();

        userDto.firstName( user.getFirstName() );
        userDto.lastName( user.getLastName() );
        userDto.birthDate( user.getBirthDate() );
        userDto.gender( user.getGender() );
        userDto.avatar( user.getAvatar() );
        userDto.email( user.getEmail() );

        return userDto.build();
    }

    @Override
    public User registerDtoToUser(RegisterDto registerDto) {
        if ( registerDto == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.firstName( registerDto.getFirstName() );
        user.lastName( registerDto.getLastName() );
        user.email( registerDto.getEmail() );
        user.username( registerDto.getUsername() );
        user.password( registerDto.getPassword() );

        return user.build();
    }

    @Override
    public RegisterDto userToRegisterDto(User user) {
        if ( user == null ) {
            return null;
        }

        RegisterDto.RegisterDtoBuilder registerDto = RegisterDto.builder();

        registerDto.firstName( user.getFirstName() );
        registerDto.lastName( user.getLastName() );
        registerDto.email( user.getEmail() );
        registerDto.username( user.getUsername() );
        registerDto.password( user.getPassword() );

        return registerDto.build();
    }
}
