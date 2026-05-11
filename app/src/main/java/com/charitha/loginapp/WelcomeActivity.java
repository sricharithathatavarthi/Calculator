package com.charitha.loginapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        TextView tvWelcome = findViewById(R.id.tvWelcome);
        String username = getIntent().getStringExtra("USERNAME");

        if (username != null) {
            tvWelcome.setText(getString(R.string.welcome_user, username));
        } else {
            tvWelcome.setText(getString(R.string.welcome_default));
        }

        Button btnGoToCalculator = findViewById(R.id.btnGoToCalculator);
        btnGoToCalculator.setOnClickListener(v -> {
            Intent intent = new Intent(WelcomeActivity.this, CalculatorActivity.class);
            startActivity(intent);
        });
    }
}
