package ru.isa.tmsystem.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "executor_user_id")
    private Client executor;

    private Long executorId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    @NotNull
    private Client author;

    @Column(name = "author_id", insertable = false, updatable = false)
    private Long authorId;


    @OneToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;


    private String title;

    private String description;

    private STATUS status; // "в ожидании", "в процессе", "завершено"

    private PRIORITY priority; // "высокий", "средний", "низкий"

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;


    public Task(String title, String description, Long executorId, Long authorId, STATUS status, PRIORITY priority) {
        this.title = title;
        this.description = description;
        this.executorId = executorId;
        this.authorId = authorId;
        this.status = status;
        this.priority = priority;
    }

    public Task(PRIORITY priority, STATUS status, String description, String title) {
        this.priority = priority;
        this.status = status;
        this.description = description;
        this.title = title;
    }

    public Task() {

    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getExecutor() {
        return executor;
    }

    public void setExecutor(Client executor) {
        this.executor = executor;
    }

    public Long getExecutorId() {
        return executorId;
    }

    public void setExecutorId(Long executorId) {
        this.executorId = executorId;
    }

    public Client getAuthor() {
        return author;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

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

    public STATUS getStatus() {
        return status;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public PRIORITY getPriority() {
        return priority;
    }

    public void setPriority(PRIORITY priority) {
        this.priority = priority;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setAuthor(Client author) {
        this.author = author;
    }


    public enum PRIORITY {
        HIGH,
        MEDIUM,
        LOW
    }

    public enum STATUS {
        IN_ANTICIPATION,
        IN_PROGRESS,
        COMPLETED,
    }

}
