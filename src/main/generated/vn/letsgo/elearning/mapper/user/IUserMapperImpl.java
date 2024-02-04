package vn.letsgo.elearning.mapper.user;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import vn.letsgo.elearning.dto.user.UserDto;
import vn.letsgo.elearning.dto.user.authentication.RegisterDto;
import vn.letsgo.elearning.entity.user.User;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-02-03T11:07:56+0700",
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
        user.location( userDto.getLocation() );
        user.school( userDto.getSchool() );
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
        userDto.location( user.getLocation() );
        userDto.school( user.getSchool() );
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

    @Override
    public void updateUserFromDto(UserDto dto, User entity) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getFirstName() != null ) {
            entity.setFirstName( dto.getFirstName() );
        }
        if ( dto.getLastName() != null ) {
            entity.setLastName( dto.getLastName() );
        }
        if ( dto.getBirthDate() != null ) {
            entity.setBirthDate( dto.getBirthDate() );
        }
        if ( dto.getGender() != null ) {
            entity.setGender( dto.getGender() );
        }
        if ( dto.getLocation() != null ) {
            entity.setLocation( dto.getLocation() );
        }
        if ( dto.getSchool() != null ) {
            entity.setSchool( dto.getSchool() );
        }
        if ( dto.getAvatar() != null ) {
            entity.setAvatar( dto.getAvatar() );
        }
        if ( dto.getEmail() != null ) {
            entity.setEmail( dto.getEmail() );
        }
    }
}
