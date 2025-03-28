package ru.isa.tmsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.isa.tmsystem.model.Task;


@AllArgsConstructor
public class TaskReq {
    @NotBlank
    @Size(min = 3, max = 255)
    private String title;
    @NotBlank
    @Size(min = 3, max = 255)
    private String description;
    @NotNull
    private Task.STATUS status;
    @NotNull
    private Task.PRIORITY priority;

    private Long executorId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public @NotNull Task.STATUS getStatus() {
        return status;
    }

    public void setStatus(@NotNull Task.STATUS status) {
        this.status = status;
    }

    public @NotNull Task.PRIORITY getPriority() {
        return priority;
    }

    public void setPriority(@NotNull Task.PRIORITY priority) {
        this.priority = priority;
    }

    public Long getExecutorId() {
        return executorId;
    }

    public void setExecutorId(Long executorId) {
        this.executorId = executorId;
    }
}
