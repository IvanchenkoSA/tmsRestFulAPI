package ru.isa.tmsystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.isa.tmsystem.dto.TaskDTO;
import ru.isa.tmsystem.model.Task;
import ru.isa.tmsystem.model.User;
import ru.isa.tmsystem.service.TaskService;

import java.util.List;

@RestController
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("api/task/create")
    public String createTask(@RequestBody TaskDTO taskDTO) {
        Task createdTask = taskService.createTask(taskDTO);
        return "Task created with id: " + createdTask.getId();
    }

    @PutMapping("api/task/update/{id}")
    public void updateTask(
            @PathVariable Long id,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Task.Status status,
            @RequestParam(required = false) Task.Priority priority,
            @RequestParam(required = false) User user) {
        Task task = new Task(priority, status, description, title, user);
        System.out.println("Received task details: " + title + ", " + description + ", " + status + ", " + priority + ", " + user);
        taskService.updateTask(id, task);
    }

    @DeleteMapping("api/task/delete/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
    }

    @GetMapping("api/task/getAll")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @DeleteMapping("api/task/delete-all")
    public void deleteAllTasks() {
        taskService.deleteAllTasks();
    }

}
