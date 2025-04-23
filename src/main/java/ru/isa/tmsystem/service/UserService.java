package ru.isa.tmsystem.service;

import org.springframework.stereotype.Service;
import ru.isa.tmsystem.model.Comment;
import ru.isa.tmsystem.model.User;
import ru.isa.tmsystem.repository.UserRepository;

import java.util.List;
import java.util.Optional;

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

    public User findUserById(Long id) {
        Optional<User> optionalComment = userRepository.findById(id);
        return optionalComment.orElse(null);
    }

    public void updateUser(Long id, User user) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new IllegalStateException("Пользователя с id: " + id + " не существует.");
        }
        User newUser = optionalUser.get();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setRole(user.getRole());
        userRepository.save(newUser);
    }

    public User deleteUser(Long userId) {
        User user = getUser(userId);
        userRepository.deleteById(userId);
        return user;
    }
}
