package ru.isa.tmsystem.controller;


import org.springframework.web.bind.annotation.*;
import ru.isa.tmsystem.model.Client;
import ru.isa.tmsystem.service.UserService;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/api/user/create")
    public String createUser(@RequestBody Client client) {
        userService.createUser(client);
        return client.getAccess_type() + " created";
    }

    @DeleteMapping("api/delete/{email}")
    public void deleteUser(@PathVariable Client client) {
        userService.deleteUser(client);
    }

}
