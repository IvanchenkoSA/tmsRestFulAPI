package ru.isa.tmsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.isa.tmsystem.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
