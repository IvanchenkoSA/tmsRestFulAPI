package ru.isa.tmsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.isa.tmsystem.model.Client;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Client, Long> {
    void deleteByEmail(String email);
}
