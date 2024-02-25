package muslimov.vlad.gbspring.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import muslimov.vlad.gbspring.config.security.JwtTokenUtils;
import muslimov.vlad.gbspring.dto.JwtRequestDto;
import muslimov.vlad.gbspring.dto.JwtResponseDto;
import muslimov.vlad.gbspring.dto.UserCreateDto;
import muslimov.vlad.gbspring.dto.UserDto;
import muslimov.vlad.gbspring.exception.model.BadRequestException;
import muslimov.vlad.gbspring.model.Session;
import muslimov.vlad.gbspring.model.User;
import muslimov.vlad.gbspring.repository.SessionRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;
    private final SessionRepository sessionRepository;

    public JwtResponseDto login(JwtRequestDto jwtRequestDto) {
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(jwtRequestDto.name(), jwtRequestDto.password())
            );
        } catch (BadCredentialsException e) {
            throw new BadRequestException("Неверный логин или пароль");
        }

        UserDetails userDetails = userService.loadUserByUsername(jwtRequestDto.name());
        User user = userService.findByUserName(jwtRequestDto.name()).orElseThrow();
        if (sessionRepository.findByUserName(userDetails.getUsername()).isEmpty()) {
            sessionRepository.save(Session.builder()
                .user(user)
                .build()
            );
        }
        String token = jwtTokenUtils.generateToken(userDetails);
        return new JwtResponseDto(token);
    }

    public void logout(Long sessionId) {
        if (sessionRepository.findById(sessionId).isPresent()) {
            sessionRepository.deleteById(sessionId);
        }
    }

    public UserDto createUser(UserCreateDto userCreateDto) {
        if (!userCreateDto.password().equals(userCreateDto.confirmPassword())) {
            throw new BadRequestException("Пароли не совпадают");
        }

        userService.findByUserName(userCreateDto.name()).ifPresent(it -> {
            throw new BadRequestException("Пользователь с указанным именем уже существует");
        });

        return userService.createUser(userCreateDto);
    }
}