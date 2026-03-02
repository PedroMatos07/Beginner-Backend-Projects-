package entity;

import enums.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Task {
    private Status status;
    private static int counter = 1;
    private Integer id;
    private String description;
    private String createdAt;
    private String updatedAt;
    private final LocalDateTime date = LocalDateTime.now();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");


    public Task(String description) {
        this.status = Status.TO_DO;
        this.createdAt = setCreatedAt();
        this.id = counter++;
        this.description = description;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return this.status;
    }

    public Integer getId() {
        return id;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String setUpdatedAt() {
        return date.format(formatter);
    }

    public String setCreatedAt() {
        return date.format(formatter);
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setUpdateDescription(String updatedDescription) {
        this.description = updatedDescription;
        this.updatedAt = setUpdatedAt();
    }

    public void updateStatus(String status) {
        if (status.equals("mark-in-progress")) {
            this.status = Status.IN_PROGRESS;
            this.updatedAt = setUpdatedAt();
        } else if (status.equals("mark-done")) {
            this.status = Status.DONE;
            this.updatedAt = setUpdatedAt();
        }
    }

    @Override
    public String toString() {
        return "Task{" +
                "status=" + status +
                ", id=" + id +
                ", description='" + description + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
