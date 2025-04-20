package com.yourcompany.projectflowapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ProjectDetailActivity extends AppCompatActivity {

    private TextView titleTextView;
    private TextView descriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);

        titleTextView = findViewById(R.id.detailProjectTitleTextView);
        descriptionTextView = findViewById(R.id.detailProjectDescriptionTextView);

        // Get the project ID passed from MainActivity
        String projectId = getIntent().getStringExtra("PROJECT_ID");

        if (projectId == null) {
            // Handle error: No project ID provided
            Toast.makeText(this, "Error: Project ID missing", Toast.LENGTH_LONG).show();
            finish(); // Close the activity if no ID
            return;
        }

        // TODO: Load project details based on projectId (from database/API)
        // Placeholder: Display the ID for now
        loadProjectDetails(projectId);
    }

    private void loadProjectDetails(String projectId) {
        // Placeholder implementation - In a real app, fetch data based on ID
        // For now, just display dummy data or the ID itself
        titleTextView.setText("Project Details for ID: " + projectId);
        descriptionTextView.setText("This is where the full description for project " + projectId + " would go.");

        // Example of setting a title for the activity's action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Project Details");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Show back button
        }
    }

    // Handle the back button in the action bar
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // Default behavior: go back
        return true;
    }
}