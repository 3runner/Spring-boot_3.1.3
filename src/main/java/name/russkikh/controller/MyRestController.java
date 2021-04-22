package name.russkikh.controller;

import name.russkikh.model.User;
import name.russkikh.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class MyRestController {
    private final UserService userService;

    public MyRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/get_all_users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> usersList() {
        return userService.findAll();
    }

    @GetMapping(value = "/get_user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getOne(@PathVariable long id) {
        return userService.findById(id).get();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void add(@RequestBody User user) {
        if (user.getPassword().equals("")) {
            user.setPassword(user.getName());
        }
        userService.save(user);
    }

    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable long id, @RequestBody User user) {
        User currentUser = userService.getOne(id);

        if ((user.getPassword().equals("")) || (user.getPassword().equals(currentUser.getPassword()))) {
            user.setPassword(currentUser.getPassword());
        }

        userService.save(user);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable long id) {
        userService.deleteById(id);
    }
}