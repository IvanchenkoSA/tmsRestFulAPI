package ru.isa.tmsystem.service;


import org.springframework.stereotype.Service;
import ru.isa.tmsystem.model.Client;
import ru.isa.tmsystem.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(Client client) {
        userRepository.save(client);
    }

    public void deleteUser(Client client) {
        userRepository.deleteByEmail(client.getEmail());
    }
}
