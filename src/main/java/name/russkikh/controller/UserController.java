package name.russkikh.controller;

import name.russkikh.model.User;
import name.russkikh.service.RoleService;
import name.russkikh.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;

@Controller
public class UserController {
    private UserService userService;
    private RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @ModelAttribute
    private void addAttributes(Model model) {
        String username = ((org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUsername();
        model.addAttribute("authorizedUser", username);
        model.addAttribute("userRoles", userService.findUserByName(username).get().rolesToString());
        model.addAttribute("allRoles", new HashSet<>(roleService.findAll()));
    }

    @GetMapping("/admin")
    public String getUserPage(Model model, @ModelAttribute("user") User user) {
//        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping("/user/{id}")
    public String show(@PathVariable long id, Model model) {
        User user = userService.findById(id).orElseThrow(() -> new UsernameNotFoundException("Invalid user id " + id));
        model.addAttribute("user", user);
        return "show";
    }

    @PostMapping("/user/new")
    public String createNewUser(@ModelAttribute("user") User user, Model model) {
        userService.save(user);
        return "redirect:/admin";
    }

    @PatchMapping("/update/{id}")
    public String updateUser(@PathVariable("id") long id, @ModelAttribute("user") User user, Model model) {
        userService.save(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable long id, Model model) {
        userService.deleteById(id);
        return "redirect:/admin";
    }
}
