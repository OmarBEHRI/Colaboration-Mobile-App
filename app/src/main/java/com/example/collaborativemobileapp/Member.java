package com.example.collaborativemobileapp;

// Model class for a Team Member
public class Member {
    private String id;
    private String email;
    private String name;
    private String projectId; // The project this member belongs to
    private String role; // Optional role in the project

    // Constructor
    public Member(String id, String email, String projectId) {
        this.id = id;
        this.email = email;
        this.projectId = projectId;
        this.name = null;
        this.role = null;
    }

    // Constructor with name
    public Member(String id, String email, String name, String projectId) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.projectId = projectId;
        this.role = null;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", projectId='" + projectId + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}