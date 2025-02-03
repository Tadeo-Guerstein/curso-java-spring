package guru.springfamework.api.v1.mapper;

import guru.springfamework.api.v1.model.UserDTO;
import guru.springfamework.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User convertUserDtoToUser(UserDTO userDTO);
    UserDTO convertUserToUserDto(User user);
}
