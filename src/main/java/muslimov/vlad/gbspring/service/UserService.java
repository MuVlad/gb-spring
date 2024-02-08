package muslimov.vlad.gbspring.service;

import lombok.RequiredArgsConstructor;
import muslimov.vlad.gbspring.dto.UserCreateDto;
import muslimov.vlad.gbspring.dto.UserDto;
import muslimov.vlad.gbspring.exception.model.NotFoundException;
import muslimov.vlad.gbspring.mapper.UserMapper;
import muslimov.vlad.gbspring.model.User;
import muslimov.vlad.gbspring.repository.RoleRepository;
import muslimov.vlad.gbspring.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    UserRepository userRepository;
    RoleRepository roleRepository;
    UserMapper userMapper;

    public List<UserDto> getUsers() {
        return userRepository
            .findAll()
            .stream()
            .map(userMapper::toDto)
            .toList();
    }

    public UserDto getUserById(Long id) {
        return userMapper.toDto(userRepository.findByIdOrThrow(id));
    }

    public Optional<User> findByUserName(String name) {
        return userRepository.findByName(name);
    }

    public UserDto createUser(UserCreateDto userCreateDto) {
        final var user = userMapper.toEntity(userCreateDto);
        user.setRoles(List.of(roleRepository.findByName("USER").orElseThrow(
            () -> new NotFoundException(("Роль USER не найдена"))
        )));
        final var saveUser = userRepository.save(user);
        return userMapper.toDto(saveUser);
    }
}
