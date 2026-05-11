package com.charitha.loginapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ChangePasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        String intentUsername = getIntent().getStringExtra("USERNAME");

        EditText etUser = findViewById(R.id.etUsernameChange);
        EditText etOld = findViewById(R.id.etOldPassword);
        EditText etNew = findViewById(R.id.etNewPassword);
        EditText etConfirm = findViewById(R.id.etConfirmNewPassword);
        Button btnSubmit = findViewById(R.id.btnSubmitChange);

        if (intentUsername != null) {
            etUser.setText(intentUsername);
            etUser.setEnabled(false);
        }

        btnSubmit.setOnClickListener(v -> {
            String username = etUser.getText().toString().trim();
            String oldPass = etOld.getText().toString().trim();
            String newPass = etNew.getText().toString().trim();
            String confirm = etConfirm.getText().toString().trim();

            if (TextUtils.isEmpty(username)) {
                Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
                return;
            }

            SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            String savedPass = prefs.getString(username, null);

            if (savedPass == null) {
                Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show();
            } else if (!savedPass.equals(oldPass)) {
                Toast.makeText(this, "Incorrect old password", Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(newPass)) {
                Toast.makeText(this, "New password cannot be empty", Toast.LENGTH_SHORT).show();
            } else if (!newPass.equals(confirm)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString(username, newPass);
                editor.apply();

                Toast.makeText(this, "Password changed successfully!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
