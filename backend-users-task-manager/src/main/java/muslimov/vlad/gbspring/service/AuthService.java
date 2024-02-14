package muslimov.vlad.gbspring.service;

import lombok.RequiredArgsConstructor;
import muslimov.vlad.gbspring.config.security.JwtTokenUtils;
import muslimov.vlad.gbspring.dto.JwtRequestDto;
import muslimov.vlad.gbspring.dto.JwtResponseDto;
import muslimov.vlad.gbspring.dto.UserCreateDto;
import muslimov.vlad.gbspring.dto.UserDto;
import muslimov.vlad.gbspring.exception.model.BadRequestException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    public JwtResponseDto createAuthToken(JwtRequestDto jwtRequestDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequestDto.username(), jwtRequestDto.password()));
        } catch (BadCredentialsException e) {
            throw new BadRequestException("Неверный логин или пароль");
        }

        UserDetails userDetails = userService.loadUserByUsername(jwtRequestDto.username());
        String token = jwtTokenUtils.generateToken(userDetails);
        return new JwtResponseDto(token);
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