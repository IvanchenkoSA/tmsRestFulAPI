package ru.isa.tmsystem.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.isa.tmsystem.dto.TaskReq;
import ru.isa.tmsystem.model.Client;
import ru.isa.tmsystem.model.Task;
import ru.isa.tmsystem.repository.TaskRepository;
import ru.isa.tmsystem.repository.UserRepository;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class TaskService {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public TaskService(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }


    @Transactional
    @Caching(evict = {
            @CacheEvict(value = "user_resp", condition = "#taskReq.executorId ne null", key = "#taskReq.executorId"),
            @CacheEvict(value = "user_resp", key = "#result.author.id")
    })
    public Task createTask(TaskReq taskReq, Authentication authentication) {

        Long userId = this.extractUserId(authentication);
        Client authorClient = userRepository.getReferenceById(userId);

        Task newTask = this.toTask(taskReq);

        Client executor;
        Long executorId = taskReq.getExecutorId();


        if (executorId != null) {
            executor = userRepository.findById(executorId).orElseThrow(
                    () -> new NoSuchElementException("ERROR: There is no User with executorId: " + executorId));
            newTask.setExecutor(executor);
        }

        newTask.setAuthor(authorClient);
        newTask.setCreatedAt(new Date());

        return taskRepository.save(newTask);
    }

    private Task toTask(TaskReq taskReq) {
        Task task = new Task();
        task.setTitle(taskReq.getTitle());
        task.setDescription(taskReq.getDescription());
        task.setStatus(taskReq.getStatus());
        task.setPriority(taskReq.getPriority());

        return task;
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

    public void deleteAllTasks() {
        taskRepository.deleteAll();
    }

    private Long extractUserId(Authentication auth) {
        Client client = null;
        if (auth instanceof UsernamePasswordAuthenticationToken token) {
            client = (Client) token.getPrincipal();
        }
        assert client != null;
        return client.getId();
    }

}
