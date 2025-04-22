package ru.isa.tmsystem.service;

import org.springframework.stereotype.Service;
import ru.isa.tmsystem.model.User;
import ru.isa.tmsystem.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long id) {
        return userRepository.findByUsername(userRepository.getOne(id).getUsername());
    }

    public User deleteUser(Long userId) {
        User user = getUser(userId);
        userRepository.deleteById(userId);
        return user;
    }
}
