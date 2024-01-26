package letsgo.vn.elearning.mapper.interfaces;

import letsgo.vn.elearning.dto.user.RegisterDto;
import letsgo.vn.elearning.dto.user.UserDto;
import letsgo.vn.elearning.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    public User userDtoToUser(UserDto userDto);

    public UserDto userToUserDto(User user);

    public User registerDtoToUser(RegisterDto registerDto);

    public RegisterDto userToRegisterDto(User user);

}
