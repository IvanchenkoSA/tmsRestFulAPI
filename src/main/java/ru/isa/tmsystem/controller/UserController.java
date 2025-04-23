package ru.isa.tmsystem.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.isa.tmsystem.model.User;
import ru.isa.tmsystem.service.UserService;

import java.util.List;

@RestController()
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/user/create")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/api/user/getUser/{id}")
    public ResponseEntity<User> findUser(@PathVariable Long id) {
        User user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/api/user/getAll")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> list = userService.getAllUsers();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping("/api/user/updateUser/{id}")
    public void updateUser(
            @PathVariable Long id,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) User.ROLE role) {
        User user = userService.findUserById(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
        userService.updateUser(id, user);
    }


    @DeleteMapping("/api/user/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        User user = userService.deleteUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
