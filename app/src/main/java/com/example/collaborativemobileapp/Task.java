package com.example.collaborativemobileapp;

// Model class for a Task
public class Task {
    private String id;
    private String projectId; // The project this task belongs to
    private String title;
    private String description;
    private boolean completed;
    private String assignedTo; // Could be a user ID or email

    // Constructor
    public Task(String id, String projectId, String title, String description) {
        this.id = id;
        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.completed = false;
        this.assignedTo = null;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getProjectId() {
        return projectId;
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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", projectId='" + projectId + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", completed=" + completed +
                ", assignedTo='" + assignedTo + '\'' +
                '}';
    }
}