package com.yourcompany.projectflowapp;

// Simple data model for a Project
public class Project {
    private String id; // Or long, depending on your backend
    private String title;
    private String description;
    // Add other relevant fields like creation date, owner, members, etc. later

    // Constructor (can add more constructors as needed)
    public Project(String id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
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

    // toString() can be helpful for debugging
    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}