package guru.springfamework.services;

import guru.springfamework.api.v1.model.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUserById(Long id);
}
