package com.yourcompany.projectflowapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList; // Placeholder for project data
import java.util.List; // Import List

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

public class MainActivity extends AppCompatActivity implements ProjectAdapter.OnProjectClickListener {

    private static final int CREATE_PROJECT_REQUEST_CODE = 1; // Request code

    private Toolbar toolbar;
    private RecyclerView projectRecyclerView;
    private FloatingActionButton addProjectFab;
    private ProjectAdapter projectAdapter; // Uncommented

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI Elements
        toolbar = findViewById(R.id.toolbar);
        projectRecyclerView = findViewById(R.id.projectRecyclerView);
        addProjectFab = findViewById(R.id.addProjectFab);

        // Setup Toolbar
        setSupportActionBar(toolbar);

        // Setup RecyclerView
        projectRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // Initialize adapter with empty list and the click listener (this activity)
        projectAdapter = new ProjectAdapter(new ArrayList<>(), this);
        projectRecyclerView.setAdapter(projectAdapter);

        // Setup FAB Click Listener
        addProjectFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to CreateProjectActivity for result
                Intent intent = new Intent(MainActivity.this, CreateProjectActivity.class);
                startActivityForResult(intent, CREATE_PROJECT_REQUEST_CODE);
            }
        });

        // Load projects (Placeholder - will be done later)
        loadProjects();
    }

    // Placeholder method for loading projects
    private void loadProjects() {
        // Add dummy data for testing
        List<Project> dummyProjects = new ArrayList<>();
        dummyProjects.add(new Project("1", "Project Alpha", "Description for Alpha"));
        dummyProjects.add(new Project("2", "Project Beta", "Description for Beta"));
        dummyProjects.add(new Project("3", "Project Gamma", "Description for Gamma"));
        projectAdapter.setProjects(dummyProjects);
    }

    // Implementation for the OnProjectClickListener interface
    @Override
    public void onProjectClick(Project project) {
        // Navigate to ProjectDetailActivity
        Toast.makeText(this, "Clicked on: " + project.getTitle(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ProjectDetailActivity.class);
        intent.putExtra("PROJECT_ID", project.getId()); // Pass project ID
        startActivity(intent);
    }

    // Handle the result from CreateProjectActivity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CREATE_PROJECT_REQUEST_CODE && resultCode == RESULT_OK) {
            // Project created successfully, reload the list (placeholder)
            Toast.makeText(this, "New project created, refreshing list...", Toast.LENGTH_SHORT).show();
            loadProjects(); // Reload dummy data for now
            // TODO: In a real app, you'd fetch the updated list or add the specific new project
        }
    }
}