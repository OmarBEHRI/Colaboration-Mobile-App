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

public class AddMemberFragment extends DialogFragment {

    private TextInputEditText memberEmailEditText;
    private MaterialButton addMemberSubmitButton;
    private String projectId;

    // Interface for communication with the host activity
    public interface MemberAdditionListener {
        void onMemberAdded(Member member);
    }

    private MemberAdditionListener listener;

    public static AddMemberFragment newInstance(String projectId) {
        AddMemberFragment fragment = new AddMemberFragment();
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
            listener = (MemberAdditionListener) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " must implement MemberAdditionListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_member, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        // Initialize views
        memberEmailEditText = view.findViewById(R.id.memberEmailEditText);
        addMemberSubmitButton = view.findViewById(R.id.addMemberSubmitButton);
        
        // Set up click listener for the submit button
        addMemberSubmitButton.setOnClickListener(v -> addMember());
    }

    private void addMember() {
        String email = memberEmailEditText.getText().toString().trim();
        
        // Validate input
        if (email.isEmpty()) {
            memberEmailEditText.setError("Member email is required");
            return;
        }
        
        if (!isValidEmail(email)) {
            memberEmailEditText.setError("Please enter a valid email address");
            return;
        }
        
        // Generate a unique ID for the member
        String memberId = UUID.randomUUID().toString();
        
        // Create a new Member object
        Member newMember = new Member(memberId, email, projectId);
        
        // Notify the listener that a member has been added
        if (listener != null) {
            listener.onMemberAdded(newMember);
            Toast.makeText(getContext(), "Member added successfully", Toast.LENGTH_SHORT).show();
            dismiss(); // Close the dialog
        }
    }
    
    // Simple email validation
    private boolean isValidEmail(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}