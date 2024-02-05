package muslimov.vlad.gbspring.rest;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import muslimov.vlad.gbspring.dto.UserCreateDto;
import muslimov.vlad.gbspring.dto.UserDto;
import muslimov.vlad.gbspring.service.UserService;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "Получение списка пользователей")
    public HttpEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение пользователя по id")
    public HttpEntity<UserDto> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping
    @Operation(summary = "Создание нового пользователя")
    public HttpEntity<UserDto> createUser(@RequestBody UserCreateDto userCreateDto) {
        return ResponseEntity.ok(userService.createUser(userCreateDto));
    }
}