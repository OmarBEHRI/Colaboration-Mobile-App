package com.example.collaborativemobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class SignupActivity extends AppCompatActivity {

    private TextInputEditText usernameEditText;
    private TextInputEditText emailEditText;
    private TextInputEditText passwordEditText;
    private TextInputEditText confirmPasswordEditText;
    private Button signupButton;
    private TextView loginTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Get references to UI elements
        usernameEditText = findViewById(R.id.usernameEditText);
        emailEditText = findViewById(R.id.emailEditText_signup);
        passwordEditText = findViewById(R.id.passwordEditText_signup);
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText);
        signupButton = findViewById(R.id.signupButton);
        loginTextView = findViewById(R.id.loginTextView);

        // Placeholder: Set OnClickListener for Sign Up button
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Placeholder action: Show a Toast or navigate
                Toast.makeText(SignupActivity.this, "Sign Up Clicked (Placeholder)", Toast.LENGTH_SHORT).show();
                // Add navigation logic later (e.g., to MainActivity or back to Login)


            }
        });

        // Placeholder: Set OnClickListener for Login TextView
        loginTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to LoginActivity
                // Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                // startActivity(intent);
                finish(); // Finish SignupActivity to go back
            }
        });
    }
}