package ru.isa.tmsystem.dto;

import ru.isa.tmsystem.model.User;

public record UserDTO(String username, String password, User.ROLE role) {
}
