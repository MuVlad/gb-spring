package muslimov.vlad.gbspring.service;

import lombok.RequiredArgsConstructor;
import muslimov.vlad.gbspring.dto.UserCreateDto;
import muslimov.vlad.gbspring.dto.UserDto;
import muslimov.vlad.gbspring.mapper.UserMapper;
import muslimov.vlad.gbspring.model.Role;
import muslimov.vlad.gbspring.model.User;
import muslimov.vlad.gbspring.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

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
        user.setRole(Role.USER);
        final var saveUser = userRepository.save(user);
        return userMapper.toDto(saveUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with name: " + username));

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().name()));

        return new org.springframework.security.core.userdetails.User(
            user.getName(),
            user.getPassword(),
            roles
        );
    }
}