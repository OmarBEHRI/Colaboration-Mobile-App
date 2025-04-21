package com.example.collaborativemobileapp;

// Simple data model for a Project
public class Project {
    private String id; // Or long, depending on your backend
    private String title;
    private String description;
    private String dueDate; // Store the due date as a string
    // Add other relevant fields like creation date, owner, members, etc. later

    // Constructor (can add more constructors as needed)
    public Project(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = null; // Default value
    }
    
    // Constructor with due date
    public Project(String id, String title, String description, String dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
    }

    // Getters (and potentially Setters if needed)
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
    
    public String getDueDate() {
        return dueDate;
    }
    
    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    // toString() can be helpful for debugging
    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate='" + dueDate + '\'' +
                '}';
    }
}