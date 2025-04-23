package ru.isa.tmsystem.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.isa.tmsystem.model.User;
import ru.isa.tmsystem.service.UserService;

import java.util.List;

@Tag(name = "User controller")
@RestController()
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Create user", description = "Returns user")
    @PostMapping("/api/user/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        userService.createUser(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(summary = "Get user by id", description = "Returns user")
    @GetMapping("/api/user/getUser/{id}")
    public ResponseEntity<User> findUser(@PathVariable Long id) {
        User user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @Operation(summary = "Get all users", description = "Return List<User>")
    @GetMapping("/api/user/getAll")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> list = userService.getAllUsers();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Operation(summary = "Update user by id", description = "Update user")
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

    @Operation(summary = "Delete user by id", description = "Deleted user and return deleted entity")
    @DeleteMapping("/api/user/delete/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        User user = userService.deleteUser(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
