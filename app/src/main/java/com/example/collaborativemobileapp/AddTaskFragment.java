package com.example.collaborativemobileapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.UUID;

public class AddTaskFragment extends DialogFragment {

    private TextInputEditText taskTitleEditText;
    private TextInputEditText taskDescriptionEditText;
    private MaterialButton addTaskSubmitButton;
    private String projectId;

    // Interface for communication with the host activity
    public interface TaskCreationListener {
        void onTaskCreated(Task task);
    }

    private TaskCreationListener listener;

    public static AddTaskFragment newInstance(String projectId) {
        AddTaskFragment fragment = new AddTaskFragment();
        Bundle args = new Bundle();
        args.putString("PROJECT_ID", projectId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            projectId = getArguments().getString("PROJECT_ID");
        }
        
        // Set dialog style to make it appear properly
        setStyle(DialogFragment.STYLE_NORMAL, R.style.Theme_CollaborativeMobileApp_Dialog);
        
        // Try to set the listener if the host activity implements the interface
        try {
            listener = (TaskCreationListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement TaskCreationListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_task, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        // Initialize views
        taskTitleEditText = view.findViewById(R.id.taskTitleEditText);
        taskDescriptionEditText = view.findViewById(R.id.taskDescriptionEditText);
        addTaskSubmitButton = view.findViewById(R.id.addTaskSubmitButton);
        
        // Set up click listener for the submit button
        addTaskSubmitButton.setOnClickListener(v -> createTask());
    }

    private void createTask() {
        String title = taskTitleEditText.getText().toString().trim();
        String description = taskDescriptionEditText.getText().toString().trim();
        
        // Validate input
        if (title.isEmpty()) {
            taskTitleEditText.setError("Task title is required");
            return;
        }
        
        // Generate a unique ID for the task
        String taskId = UUID.randomUUID().toString();
        
        // Create a new Task object
        Task newTask = new Task(taskId, projectId, title, description);
        
        // Notify the listener that a task has been created
        if (listener != null) {
            listener.onTaskCreated(newTask);
            Toast.makeText(getContext(), "Task created successfully", Toast.LENGTH_SHORT).show();
            dismiss(); // Close the dialog
        }
    }
}