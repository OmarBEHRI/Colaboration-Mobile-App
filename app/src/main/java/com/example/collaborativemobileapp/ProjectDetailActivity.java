package com.example.collaborativemobileapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ProjectDetailActivity extends AppCompatActivity implements AddTaskFragment.TaskCreationListener, AddMemberFragment.MemberAdditionListener {

    private TextView titleTextView;
    private TextView descriptionTextView;
    private ProgressBar progressBar;
    private TextView progressTextView;
    private RecyclerView tasksRecyclerView;
    private RecyclerView membersRecyclerView;
    private TextView emptyTasksTextView;
    private TextView emptyMembersTextView;
    private MaterialButton addTaskButton;
    private MaterialButton addMemberButton;
    private FloatingActionButton editProjectFab;
    private CollapsingToolbarLayout collapsingToolbar;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);

        // Initialize UI components
        initializeViews();
        setupToolbar();
        setupClickListeners();

        // Get the project ID passed from MainActivity
        String projectId = getIntent().getStringExtra("PROJECT_ID");

        if (projectId == null) {
            // Handle error: No project ID provided
            Toast.makeText(this, "Error: Project ID missing", Toast.LENGTH_LONG).show();
            finish(); // Close the activity if no ID
            return;
        }

        // Load project details based on projectId
        loadProjectDetails(projectId);
    }

    private void initializeViews() {
        // Text views
        titleTextView = findViewById(R.id.detailProjectTitleTextView);
        descriptionTextView = findViewById(R.id.detailProjectDescriptionTextView);
        progressTextView = findViewById(R.id.progressTextView);
        emptyTasksTextView = findViewById(R.id.emptyTasksTextView);
        emptyMembersTextView = findViewById(R.id.emptyMembersTextView);
        
        // Progress bar
        progressBar = findViewById(R.id.projectProgressBar);
        
        // RecyclerViews
        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        membersRecyclerView = findViewById(R.id.membersRecyclerView);
        
        // Buttons
        addTaskButton = findViewById(R.id.addTaskButton);
        addMemberButton = findViewById(R.id.addMemberButton);
        editProjectFab = findViewById(R.id.editProjectFab);
        
        // Toolbar components
        collapsingToolbar = findViewById(R.id.collapsingToolbar);
        toolbar = findViewById(R.id.detailToolbar);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupClickListeners() {
        // Add Task Button
        addTaskButton.setOnClickListener(v -> {
            // Show the AddTaskFragment dialog
            String projectId = getIntent().getStringExtra("PROJECT_ID");
            if (projectId != null) {
                AddTaskFragment addTaskFragment = AddTaskFragment.newInstance(projectId);
                addTaskFragment.show(getSupportFragmentManager(), "AddTaskFragment");
            } else {
                Toast.makeText(this, "Error: Project ID missing", Toast.LENGTH_SHORT).show();
            }
        });

        // Add Member Button
        addMemberButton.setOnClickListener(v -> {
            // Show the AddMemberFragment dialog
            String projectId = getIntent().getStringExtra("PROJECT_ID");
            if (projectId != null) {
                AddMemberFragment addMemberFragment = AddMemberFragment.newInstance(projectId);
                addMemberFragment.show(getSupportFragmentManager(), "AddMemberFragment");
            } else {
                Toast.makeText(this, "Error: Project ID missing", Toast.LENGTH_SHORT).show();
            }
        });

        // Edit Project FAB
        editProjectFab.setOnClickListener(v -> {
            // TODO: Implement edit project functionality
            Toast.makeText(this, "Edit Project functionality coming soon", Toast.LENGTH_SHORT).show();
        });
    }

    private void loadProjectDetails(String projectId) {
        // Placeholder implementation - In a real app, fetch data based on ID
        // For now, just display dummy data
        String projectTitle = "Project " + projectId;
        String projectDescription = "This is a detailed description for project " + projectId + ". It includes all the information about the project goals, timeline, and expected outcomes.";
        
        // Set project details
        titleTextView.setText(projectTitle);
        descriptionTextView.setText(projectDescription);
        collapsingToolbar.setTitle(projectTitle);
        
        // Set progress (dummy data)
        int progress = 65; // Example progress percentage
        progressBar.setProgress(progress);
        progressTextView.setText(progress + "%");
        
        // Setup RecyclerViews
        setupTasksRecyclerView();
        setupMembersRecyclerView();
    }

    private void setupTasksRecyclerView() {
        // TODO: Replace with actual task adapter and data
        // For now, we'll just show the empty state
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        emptyTasksTextView.setVisibility(View.VISIBLE);
        tasksRecyclerView.setVisibility(View.GONE);
    }

    private void setupMembersRecyclerView() {
        // TODO: Replace with actual members adapter and data
        // For now, we'll just show the empty state
        membersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        emptyMembersTextView.setVisibility(View.VISIBLE);
        membersRecyclerView.setVisibility(View.GONE);
    }
    
    // TaskCreationListener implementation
    @Override
    public void onTaskCreated(Task task) {
        // In a real app, you would add the task to your data source
        // and update the RecyclerView
        Toast.makeText(this, "Task created: " + task.getTitle(), Toast.LENGTH_SHORT).show();
        
        // For now, just hide the empty state and show a message
        emptyTasksTextView.setVisibility(View.GONE);
        tasksRecyclerView.setVisibility(View.VISIBLE);
        
        // TODO: Add the task to a list and update the RecyclerView
    }
    
    // MemberAdditionListener implementation
    @Override
    public void onMemberAdded(Member member) {
        // In a real app, you would add the member to your data source
        // and update the RecyclerView
        Toast.makeText(this, "Member added: " + member.getEmail(), Toast.LENGTH_SHORT).show();
        
        // For now, just hide the empty state and show a message
        emptyMembersTextView.setVisibility(View.GONE);
        membersRecyclerView.setVisibility(View.VISIBLE);
        
        // TODO: Add the member to a list and update the RecyclerView
    }

    // Handle the back button in the action bar
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // Default behavior: go back
        return true;
    }
}