package com.charitha.loginapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText etUsername, etPassword;
    Button btnLogin, btnGoToSignup, btnGoogleLogin, btnMobileLogin, btnChangePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnGoToSignup = findViewById(R.id.btnGoToSignup);
        btnGoogleLogin = findViewById(R.id.btnGoogleLogin);
        btnMobileLogin = findViewById(R.id.btnMobileLogin);
        btnChangePassword = findViewById(R.id.btnChangePassword);

        btnLogin.setOnClickListener(v -> {
            String user = etUsername.getText().toString().trim();
            String pass = etPassword.getText().toString().trim();

            if (TextUtils.isEmpty(user)) {
                etUsername.setError("Username required");
            } else if (TextUtils.isEmpty(pass)) {
                etPassword.setError("Password required");
            } else {
                // Check credentials in SharedPreferences
                SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                String savedPass = prefs.getString(user, null);

                if (savedPass != null && savedPass.equals(pass)) {
                    Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                    intent.putExtra("USERNAME", user);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnChangePassword.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ChangePasswordActivity.class);
            String user = etUsername.getText().toString().trim();
            if (!TextUtils.isEmpty(user)) {
                intent.putExtra("USERNAME", user);
            }
            startActivity(intent);
        });

        btnGoToSignup.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SignupActivity.class));
        });

        btnGoogleLogin.setOnClickListener(v -> {
            // TODO: Implement Google Sign-In using Firebase or Google Identity Services
            Toast.makeText(this, "Google Sign-In coming soon!", Toast.LENGTH_SHORT).show();
        });

        btnMobileLogin.setOnClickListener(v -> {
            // TODO: Implement Mobile Number login (OTP verification)
            Toast.makeText(this, "Mobile Login coming soon!", Toast.LENGTH_SHORT).show();
        });
    }
}
