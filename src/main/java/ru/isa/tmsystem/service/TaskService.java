package ru.isa.tmsystem.service;

import org.springframework.stereotype.Service;
import ru.isa.tmsystem.dto.TaskDTO;
import ru.isa.tmsystem.model.Task;
import ru.isa.tmsystem.model.User;
import ru.isa.tmsystem.repository.TaskRepository;
import ru.isa.tmsystem.repository.UserRepository;

import java.util.List;
import java.util.Optional;


@Service
public class TaskService {


    private TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }


    public User getUser(Long id) {
        return userRepository.findByUsername(userRepository.getOne(id).getUsername());
    }

    public Task createTask(TaskDTO task) {
        User user = getUser(task.userId());
        Task newTask = new Task(task.priority(), task.status(), task.description(), task.title(), user);
        return taskRepository.save(newTask);
    }

    public void updateTask(Long id, Task task) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()) {
            throw new IllegalStateException("Задачи с id: " + id + " не существует.");
        }
        Task newTask = optionalTask.get();

        newTask.setTitle(task.getTitle());
        newTask.setDescription(task.getDescription());
        newTask.setPriority(task.getPriority());
        newTask.setStatus(task.getStatus());
        newTask.setAuthor(task.getAuthor());
        taskRepository.save(newTask);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTask(Long id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.orElse(null);
    }

    public void deleteAllTasks() {
        taskRepository.deleteAll();
    }

}
