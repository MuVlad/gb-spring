package muslimov.vlad.gbspring.rest;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import muslimov.vlad.gbspring.dto.JwtRequestDto;
import muslimov.vlad.gbspring.dto.JwtResponseDto;
import muslimov.vlad.gbspring.dto.UserCreateDto;
import muslimov.vlad.gbspring.dto.UserDto;
import muslimov.vlad.gbspring.service.AuthService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth")
    @Operation(
        summary = "Аутентификация пользователя",
        description = "При успешной аутентификации пользователь получает токен"
    )
    public HttpEntity<JwtResponseDto> createAuthToken(@RequestBody JwtRequestDto jwtRequestDto) {
        System.out.println();
        return ResponseEntity.ok(authService.createAuthToken(jwtRequestDto));
    }

    @PostMapping("/registration")
    @Operation(summary = "Создание нового пользоваьеля")
    public HttpEntity<UserDto> createNewUser(@RequestBody UserCreateDto userCreateDto) {
        return ResponseEntity.ok(authService.createUser(userCreateDto));
    }
}