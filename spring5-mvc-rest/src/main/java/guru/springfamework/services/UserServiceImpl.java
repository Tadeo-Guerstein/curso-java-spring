package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.UserMapper;
import guru.springfamework.api.v1.model.UserDTO;
import guru.springfamework.domain.User;
import guru.springfamework.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> {
                    UserDTO userDTO = userMapper.convertUserToUserDto(user);
                    return userDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userMapper.convertUserToUserDto(userRepository.findById(id).get());
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if(userRepository.existsUserByEmail(userDTO.getEmail())){
            throw new RuntimeException("Email in use");
        }

        return saveAndReturnUser(userMapper.convertUserDtoToUser(userDTO));
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User user = userMapper.convertUserDtoToUser(userDTO);
        user.setId(id);

        return saveAndReturnUser(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    private UserDTO saveAndReturnUser(User user){
        User userSaved = userRepository.save(user);

        return userMapper.convertUserToUserDto(userSaved);
    }
}
