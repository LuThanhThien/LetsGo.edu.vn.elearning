package vn.letsgo.elearning.mapper.user;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import vn.letsgo.elearning.dto.user.authentication.RegisterDto;
import vn.letsgo.elearning.dto.user.UserDto;
import vn.letsgo.elearning.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);

    public User userDtoToUser(UserDto userDto);

    public UserDto userToUserDto(User user);

    public User registerDtoToUser(RegisterDto registerDto);

    public RegisterDto userToRegisterDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromDto(UserDto dto, @MappingTarget User entity);

}
