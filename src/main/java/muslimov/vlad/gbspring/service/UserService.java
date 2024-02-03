package muslimov.vlad.gbspring.service;

import lombok.RequiredArgsConstructor;
import muslimov.vlad.gbspring.model.User;
import muslimov.vlad.gbspring.model.UserCreateDto;
import muslimov.vlad.gbspring.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        userRepository.createUser(new UserCreateDto("Vanya", "vanya@mail.com"));
        userRepository.createUser(new UserCreateDto("Zoi", "zoi@mail.com"));
        userRepository.createUser(new UserCreateDto("Joni", "joni@mail.com"));
        return userRepository.getAllUsers();
    }

    public User getUserById(Long id) {
        return userRepository.getUser(id);
    }

    public User createUser(UserCreateDto userCreateDto) {
        return userRepository.createUser(userCreateDto);
    }
}
