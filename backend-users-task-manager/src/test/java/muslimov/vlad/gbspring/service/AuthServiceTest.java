package muslimov.vlad.gbspring.service;

import jakarta.transaction.Transactional;
import muslimov.vlad.gbspring.dto.JwtRequestDto;
import muslimov.vlad.gbspring.dto.JwtResponseDto;
import muslimov.vlad.gbspring.dto.UserCreateDto;
import muslimov.vlad.gbspring.dto.UserDto;
import muslimov.vlad.gbspring.mapper.UserMapper;
import muslimov.vlad.gbspring.model.Session;
import muslimov.vlad.gbspring.model.User;
import muslimov.vlad.gbspring.repository.SessionRepository;
import muslimov.vlad.gbspring.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AuthServiceTest {

    @InjectMocks
    private AuthService authService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private SessionRepository sessionRepository;
    @Mock
    private UserService userService;
    @Mock
    private UserMapper userMapper;
    @Mock
    AuthenticationManager authenticationManager;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void login() {
        UserCreateDto newUser = new UserCreateDto("Ivan", "100", "100");
        final var user = User.builder().name("Ivan").password("100").build();
        final var request = new JwtRequestDto("Ivan", "100");


        userRepository.save(user);

        authService.login(request);

        Mockito.verify(sessionRepository, Mockito.atMost(1))
            .save(Session.builder().user(user).build());
    }

    @Test
    void logout() {
        final var user = User.builder().name("Ivan").password("100").build();
        final var request = new JwtRequestDto("Ivan", "100");


        userRepository.save(user);
        authService.login(request);
        final var sessionId = sessionRepository.findByUserName(request.name()).get().getId();
        authService.logout(sessionId);

        Mockito.verify(sessionRepository, Mockito.atMost(1)).deleteById(sessionId);
    }

    @Test
    @Transactional
    void createUser() {
        UserCreateDto newUser = new UserCreateDto("Ivan", "100", "100");

        final var user = authService.createUser(newUser);

        Mockito.verify(userRepository, Mockito.atMost(1)).save(userMapper.toEntity(user));
    }
}