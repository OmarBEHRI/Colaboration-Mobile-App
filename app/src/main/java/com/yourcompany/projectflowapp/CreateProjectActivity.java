package com.yourcompany.projectflowapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

public class CreateProjectActivity extends AppCompatActivity {

    private EditText editTextProjectTitle;
    private EditText editTextProjectDescription;
    private Button buttonCreateProject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);

        // Initialize UI elements
        editTextProjectTitle = findViewById(R.id.editTextProjectTitle);
        editTextProjectDescription = findViewById(R.id.editTextProjectDescription);
        buttonCreateProject = findViewById(R.id.buttonCreateProject);

        // Set up Button click listener
        buttonCreateProject.setOnClickListener(v -> {
            String title = editTextProjectTitle.getText().toString().trim();
            String description = editTextProjectDescription.getText().toString().trim();

            // Basic validation
            if (title.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Placeholder: Create a new project object (replace with actual saving logic later)
            // Using timestamp for a simple unique ID for now
            String tempId = String.valueOf(System.currentTimeMillis());
            Project newProject = new Project(tempId, title, description);
            System.out.println("Created project: " + newProject.getTitle()); // Log for debugging

            Toast.makeText(this, "Project '" + title + "' created", Toast.LENGTH_SHORT).show();

            // Set result to indicate success
            setResult(RESULT_OK);

            // Close the activity and return to MainActivity
            finish();
        });
    }
}