package muslimov.vlad.gbspring.service;

import lombok.RequiredArgsConstructor;
import muslimov.vlad.gbspring.dto.UserCreateDto;
import muslimov.vlad.gbspring.dto.UserDto;
import muslimov.vlad.gbspring.mapper.UserMapper;
import muslimov.vlad.gbspring.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    UserRepository userRepository;
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

    public UserDto createUser(UserCreateDto userCreateDto) {
        final var saveUser = userRepository.save(userMapper.toEntity(userCreateDto));
        return userMapper.toDto(saveUser);
    }
}
