package com.example.itube;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    private EditText fullnameEditText, userNameEditText, passwordEditText, confirmPasswordEditText;
    private Button createButton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        fullnameEditText = findViewById(R.id.fullnameTextView);
        userNameEditText = findViewById(R.id.usernameTextView);
        passwordEditText = findViewById(R.id.passwordTextView);
        confirmPasswordEditText = findViewById(R.id.confirmTextView);
        createButton = findViewById(R.id.createButton);

        dbHelper = new DatabaseHelper(this);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname = fullnameEditText.getText().toString();
                String username = userNameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

                // Add user to database
                boolean isAdded = dbHelper.addUser(fullname, username, password);
                if (isAdded) {
                    Toast.makeText(SignupActivity.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(SignupActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
