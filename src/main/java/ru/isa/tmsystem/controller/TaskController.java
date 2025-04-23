package ru.isa.tmsystem.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.isa.tmsystem.dto.TaskDTO;
import ru.isa.tmsystem.model.Task;
import ru.isa.tmsystem.model.User;
import ru.isa.tmsystem.service.TaskService;

import java.util.List;
@Tag(name = "Task controller")
@RestController
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(summary = "Create task", description = "Returns task id")
    @PostMapping("api/task/create")
    public String createTask(@RequestBody TaskDTO taskDTO) {
        Task createdTask = taskService.createTask(taskDTO);
        return "Task created with id: " + createdTask.getId();
    }

    @Operation(summary = "Update task by id", description = "Update task")
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
