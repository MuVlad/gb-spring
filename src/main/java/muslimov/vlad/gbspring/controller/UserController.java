package muslimov.vlad.gbspring.controller;

import lombok.RequiredArgsConstructor;
import muslimov.vlad.gbspring.model.UserCreateDto;
import muslimov.vlad.gbspring.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "userProfile";
    }

    @PostMapping("/registration")
    public String createUser(@ModelAttribute UserCreateDto userCreateDto, Model model) {
        model.addAttribute("user", userCreateDto);
        return "redirect:/users/" + userService.createUser(userCreateDto).getId();
    }

    @GetMapping("/registration")
    public String registration(Model model, UserCreateDto userCreateDto) {
        model.addAttribute("user",userCreateDto);
        return "registration";
    }
}
