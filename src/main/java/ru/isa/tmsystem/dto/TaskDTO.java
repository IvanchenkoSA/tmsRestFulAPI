package ru.isa.tmsystem.dto;

import ru.isa.tmsystem.model.Task;
import ru.isa.tmsystem.model.User;

public record TaskDTO(Task.Priority priority, Task.Status status, String description, String title, Long userId) {
}
