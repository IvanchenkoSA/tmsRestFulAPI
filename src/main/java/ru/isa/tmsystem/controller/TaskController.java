package ru.isa.tmsystem.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.isa.tmsystem.dto.TaskReq;
import ru.isa.tmsystem.model.Task;
import ru.isa.tmsystem.service.TaskService;

import java.util.List;

@RestController
@SecurityRequirement(name = "JWT Bearer")
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/api/create")
    public ResponseEntity<?> createTask(@Valid @RequestBody TaskReq task, Authentication auth) {
        Task createdTask = taskService.createTask(task, auth);
        return ResponseEntity.status(HttpStatus.CREATED).body("Task created with id: " + createdTask.getId());
    }

    @PutMapping("/api/update/{id}")
    public void updateTask(
            @PathVariable Long id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Task.STATUS status,
            @RequestParam(required = false) Task.PRIORITY priority) {
        Task task = new Task(priority, status, description, title);
        System.out.println("Received task details: " + title + ", " + description + ", " + status + ", " + priority);
        taskService.updateTask(id, task);
    }

    @DeleteMapping("api/delete/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @GetMapping("/api/getAll")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @DeleteMapping("api/delete-all")
    public void deleteAllTasks() {
        taskService.deleteAllTasks();
    }

}
