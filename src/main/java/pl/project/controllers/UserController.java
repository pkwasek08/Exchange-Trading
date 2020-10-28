package pl.project.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.project.entities.User;
import pl.project.services.UserService;

import java.util.List;

@RestController
public class UserController {
    Logger log = LogManager.getLogger(this.getClass());
    @Autowired
    private UserService userService;

    @GetMapping("/user")
    @CrossOrigin(origins = "*")
    public List<User> getUser() {
        return userService.getAllUser();
    }

    @GetMapping("/user/{id}")
    @CrossOrigin(origins = "*")
    public User getUser(@PathVariable Integer id) {
        return userService.getUser(id);
    }

    @RequestMapping("/user/login")
    @CrossOrigin(origins = "*")
    public User getUserByEmailAndPassword(@RequestParam String email, @RequestParam String password) {
        return userService.getUserByEmailAndPassword(email, password);
    }

    @PostMapping(value = "/user")
    @CrossOrigin(origins = "*")
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @PutMapping(value = "/user/{id}")
    @CrossOrigin(origins = "*")
    public void updateUser(@RequestBody User user, @PathVariable Integer id) {
        userService.updateUser(id, user);
    }

    @DeleteMapping(value = "/user/{id}")
    @CrossOrigin(origins = "*")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }
}
