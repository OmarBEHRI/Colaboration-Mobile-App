package com.example.collaborativemobileapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;
import android.content.Intent;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreateProjectActivity extends AppCompatActivity {

    private TextInputEditText editTextProjectTitle;
    private TextInputEditText editTextProjectDescription;
    private TextInputEditText editTextProjectDueDate;
    private MaterialButton buttonCreateProject;
    private Toolbar toolbar;
    private Calendar calendar;
    private SimpleDateFormat dateFormatter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_project);

        // Initialize date formatter
        calendar = Calendar.getInstance();
        dateFormatter = new SimpleDateFormat("MMM dd, yyyy", Locale.US);

        // Initialize UI elements
        initializeViews();
        setupToolbar();
        setupDatePicker();
        setupCreateButton();
    }

    private void initializeViews() {
        editTextProjectTitle = findViewById(R.id.editTextProjectTitle);
        editTextProjectDescription = findViewById(R.id.editTextProjectDescription);
        editTextProjectDueDate = findViewById(R.id.editTextProjectDueDate);
        buttonCreateProject = findViewById(R.id.buttonCreateProject);
        toolbar = findViewById(R.id.createProjectToolbar);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Create New Project");
        }
    }

    private void setupDatePicker() {
        // Set up date picker dialog for due date field
        editTextProjectDueDate.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    this,
                    (view, year, month, dayOfMonth) -> {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        updateDueDateLabel();
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });
    }

    private void updateDueDateLabel() {
        editTextProjectDueDate.setText(dateFormatter.format(calendar.getTime()));
    }

    private void setupCreateButton() {
        // Set up Button click listener
        buttonCreateProject.setOnClickListener(v -> {
            String title = editTextProjectTitle.getText().toString().trim();
            String description = editTextProjectDescription.getText().toString().trim();
            String dueDate = editTextProjectDueDate.getText().toString().trim();

            // Basic validation
            if (title.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "Please fill in required fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create a new project object with all fields including due date
            // Using timestamp for a simple unique ID for now
            String tempId = String.valueOf(System.currentTimeMillis());
            Project newProject = new Project(tempId, title, description, dueDate.isEmpty() ? null : dueDate);
            
            System.out.println("Created project: " + newProject.getTitle()); // Log for debugging

            Toast.makeText(this, "Project '" + title + "' created", Toast.LENGTH_SHORT).show();

            // Set result to indicate success
            setResult(RESULT_OK);

            // Close the activity and return to MainActivity
            finish();
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}