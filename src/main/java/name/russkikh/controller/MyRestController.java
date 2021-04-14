package name.russkikh.controller;

import name.russkikh.model.User;
import name.russkikh.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rest")
public class MyRestController {
    private final UserService userService;

    public MyRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> usersList() {
        return userService.findAll();
    }

    @GetMapping(value = "/user/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getOne(@PathVariable long id) {
        return userService.findById(id).get();
    }

    @PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public void add(@RequestBody User user) {
        userService.save(user);
    }

    @PatchMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void update(@PathVariable long id, @RequestBody User user) {
        userService.save(user);
    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable long id) {
        userService.deleteById(id);
    }
}