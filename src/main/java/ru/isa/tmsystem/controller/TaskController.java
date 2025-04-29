package ru.isa.tmsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.isa.tmsystem.dto.TaskDTO;
import ru.isa.tmsystem.model.Task;
import ru.isa.tmsystem.model.User;
import ru.isa.tmsystem.service.TaskService;
import ru.isa.tmsystem.service.UserService;

import java.util.List;
@Tag(name = "Task controller")
@RestController
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @Operation(summary = "Create task", description = "Returns task id")
    @PostMapping("api/task/create")
    public Long createTask(@RequestBody TaskDTO taskDTO) {
        Task createdTask = taskService.createTask(taskDTO);
        return createdTask.getId();
    }


    @Operation(summary = "Update task by id", description = "Update task")
    @PutMapping("api/task/update/{id}")
    public void updateTask(
            @PathVariable Long id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Task.Status status,
            @RequestParam(required = false) Task.Priority priority,
            @RequestParam(required = false) Long userId) {
        Task existingTask = taskService.getTask(id);

        if (existingTask == null) {
            throw new EntityNotFoundException("Task not found with id: " + id);
        }
        if (title != null) {
            existingTask.setTitle(title);
        }
        if (description != null) {
            existingTask.setDescription(description);
        }
        if (status != null) {
            existingTask.setStatus(status);
        }
        if (priority != null) {
            existingTask.setPriority(priority);
        }
        if (userId != null) {
            User user = userService.getUser(userId);
            if (user != null) {
                existingTask.setAuthor(user);
            } else {
                throw new EntityNotFoundException("User not found with id: " + userId);
            }
        }
        taskService.updateTask(existingTask);
    }


    @Operation(summary = "Delete task by id", description = "Delete task")
    @DeleteMapping("api/task/delete/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @Operation(summary = "Get all Tasks", description = "Return List<Task>")
    @GetMapping("api/task/getAll")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @Operation(summary = "Get task by id", description = "Return task")
    @GetMapping("/api/task/getById/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTask(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @Operation(summary = "Delete all task", description = "Delete all tasks")
    @DeleteMapping("api/task/delete-all")
    public void deleteAllTasks() {
        taskService.deleteAllTasks();
    }

}
