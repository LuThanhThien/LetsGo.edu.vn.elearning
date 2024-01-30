package vn.letsgo.elearning.mapper;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.SpringBootTest;
import vn.letsgo.elearning.dto.user.RegisterDto;
import vn.letsgo.elearning.dto.user.UserDto;
import vn.letsgo.elearning.entity.user.User;
import vn.letsgo.elearning.mapper.IUserMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class IUserMapperTest {

    private final IUserMapper mapper = Mappers.getMapper(IUserMapper.class);

    @Test
    void userDtoToUser() {
        // Arrange
        UserDto userDto = mock(UserDto.class);

        // Assuming your UserDto and User classes have corresponding fields like 'firstName' and 'lastName'
        when(userDto.getFirstName()).thenReturn("John");
        when(userDto.getLastName()).thenReturn("Doe");

        // Act
        User user = mapper.userDtoToUser(userDto);

        // Assert
        assertEquals(userDto.getFirstName(), user.getFirstName());
        assertEquals(userDto.getLastName(), user.getLastName());
    }

    @Test
    void userToUserDto() {
        // Arrange
        User user = mock(User.class);

        // Assuming your User and UserDto classes have corresponding fields like 'firstName' and 'lastName'
        when(user.getFirstName()).thenReturn("John");
        when(user.getLastName()).thenReturn("Doe");

        // Act
        UserDto userDto = mapper.userToUserDto(user);

        // Assert
        assertEquals(user.getFirstName(), userDto.getFirstName());
        assertEquals(user.getLastName(), userDto.getLastName());
    }

    @Test
    void registerDtoToUser() {
        // Arrange
        RegisterDto registerDto = mock(RegisterDto.class);

        when(registerDto.getUsername()).thenReturn("john.doe");
        when(registerDto.getPassword()).thenReturn("password123");

        // Act
        User user = mapper.registerDtoToUser(registerDto);

        // Assert
        assertEquals(registerDto.getUsername(), user.getUsername());
        assertEquals(registerDto.getPassword(), user.getPassword());
    }

    @Test
    void userToRegisterDto() {
        // Arrange
        User user = mock(User.class);

        // Assuming your User and RegisterDto classes have corresponding fields like 'username' and 'password'
        when(user.getUsername()).thenReturn("john.doe");
        when(user.getPassword()).thenReturn("password123");

        // Act
        RegisterDto registerDto = mapper.userToRegisterDto(user);

        // Assert
        assertEquals(user.getUsername(), registerDto.getUsername());
        assertEquals(user.getPassword(), registerDto.getPassword());
    }

    @Test
    void updateUserFromDto() {
        // Arrange
        UserDto userDto = mock(UserDto.class);
        User user = mock(User.class);

        when(user.getFirstName()).thenReturn("John");
        when(user.getId()).thenReturn(123L);
        when(userDto.getFirstName()).thenReturn("Updated John");

        // Act
        mapper.updateUserFromDto(userDto, user);

        // Assert
        assertEquals(userDto.getFirstName(), user.getFirstName());
        assertEquals(user.getId(), 123L);

    }
}

