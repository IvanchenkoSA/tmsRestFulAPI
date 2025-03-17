package ru.isa.tmsystem.service;

import org.springframework.stereotype.Service;
import ru.isa.tmsystem.model.Task;
import ru.isa.tmsystem.repository.TaskRepository;

import java.util.List;
import java.util.Optional;


@Service
public class TaskService {


    private TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public void updateTask(Long id, Task task) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isEmpty()) {
            throw new IllegalStateException("Юзера с id: " + id + " не существует.");
        }
        Task newTask = optionalTask.get();

        newTask.setTitle(task.getTitle());
        newTask.setDescription(task.getDescription());
        newTask.setPriority(task.getPriority());
        newTask.setStatus(task.getStatus());
        taskRepository.save(newTask);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

}
