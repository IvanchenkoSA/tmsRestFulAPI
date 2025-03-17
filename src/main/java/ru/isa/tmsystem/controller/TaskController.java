package ru.isa.tmsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.isa.tmsystem.model.Task;
import ru.isa.tmsystem.service.TaskService;

import java.util.List;

@RestController
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/api/create")
    public String createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return "Task created with id: " + createdTask.getId();
    }

    @PutMapping("/api/update/{id}")
    public void updateTask(
            @PathVariable Long id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String priority) {
        Task task = new Task(title, description, status, priority);
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
