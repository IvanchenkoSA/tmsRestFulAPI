package ru.isa.tmsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.isa.tmsystem.model.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
