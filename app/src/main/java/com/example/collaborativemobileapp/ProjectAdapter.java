package com.example.collaborativemobileapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {

    private List<Project> projectList;
    // Add listener interface for item clicks later (Step 12)
    private OnProjectClickListener listener;

    public interface OnProjectClickListener {
        void onProjectClick(Project project);
    }

    public ProjectAdapter(List<Project> projectList , OnProjectClickListener listener) {
        this.projectList = projectList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_project, parent, false);
        return new ProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        Project project = projectList.get(position);
        holder.bind(project , listener);
    }

    @Override
    public int getItemCount() {
        return projectList == null ? 0 : projectList.size();
    }

    // Method to update the list (e.g., after fetching data)
    public void setProjects(List<Project> projects) {
        this.projectList = projects;
        notifyDataSetChanged(); // Or use more specific notify methods for better performance
    }

    // ViewHolder class
    static class ProjectViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView descriptionTextView;
        TextView dueDateTextView;

        public ProjectViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.projectTitleTextView);
            descriptionTextView = itemView.findViewById(R.id.projectDescriptionTextView);
            dueDateTextView = itemView.findViewById(R.id.projectDueDateTextView);
        }

        public void bind(final Project project , final OnProjectClickListener listener) {
            titleTextView.setText(project.getTitle());
            descriptionTextView.setText(project.getDescription());
            
            // Set due date if available
            if (project.getDueDate() != null && !project.getDueDate().isEmpty()) {
                dueDateTextView.setVisibility(View.VISIBLE);
                dueDateTextView.setText("Due: " + project.getDueDate());
            } else {
                dueDateTextView.setVisibility(View.GONE);
            }

            // Add ripple effect animation
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onProjectClick(project);
                }
            });
        }
    }
}