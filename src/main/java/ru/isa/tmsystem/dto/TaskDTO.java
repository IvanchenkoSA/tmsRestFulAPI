package ru.isa.tmsystem.dto;

import ru.isa.tmsystem.model.Task;

public record TaskDTO(Task.Priority priority, Task.Status status, String description, String title, Long userId) {
}
