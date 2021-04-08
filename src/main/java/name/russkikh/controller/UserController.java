package name.russkikh.controller;

import name.russkikh.model.User;
import name.russkikh.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    private void addAttributes(Model model) {
        String username = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUsername();
        model.addAttribute("authorizedUser", username);
        model.addAttribute("userRoles", userService.findUserByName(username).get().rolesToString());
    }

    @GetMapping("/admin")
    public String getUserPage(Model model, @ModelAttribute("user") User user) {
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping("/user/{id}")
    public String show(@PathVariable long id, Model model) {
        User user = userService.findById(id).orElseThrow(() -> new UsernameNotFoundException("Invalid user id " + id));
        model.addAttribute("user", user);
        return "show";
    }
}
