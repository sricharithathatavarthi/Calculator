package com.charitha.loginapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalculatorActivity extends AppCompatActivity {

    private TextView tvDisplay;
    private String currentInput = "";
    private String operator = "";
    private double firstValue = Double.NaN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculator);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvDisplay = findViewById(R.id.tvDisplay);

        setNumericOnClickListener();
        setOperatorOnClickListener();
        setScientificOnClickListener();

        findViewById(R.id.btnC).setOnClickListener(v -> {
            currentInput = "";
            firstValue = Double.NaN;
            operator = "";
            tvDisplay.setText("0");
        });

        findViewById(R.id.btnDel).setOnClickListener(v -> {
            if (!currentInput.isEmpty()) {
                currentInput = currentInput.substring(0, currentInput.length() - 1);
                tvDisplay.setText(currentInput.isEmpty() ? "0" : currentInput);
            }
        });

        findViewById(R.id.btnEquals).setOnClickListener(v -> compute());
    }

    private void setNumericOnClickListener() {
        View.OnClickListener listener = v -> {
            Button b = (Button) v;
            currentInput += b.getText().toString();
            tvDisplay.setText(currentInput);
        };

        int[] numericButtons = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9, R.id.btnDot};
        for (int id : numericButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setOperatorOnClickListener() {
        View.OnClickListener listener = v -> {
            Button b = (Button) v;
            if (!currentInput.isEmpty()) {
                compute();
                operator = b.getText().toString();
                if (operator.equals("√")) {
                    double val = Double.parseDouble(currentInput);
                    currentInput = String.valueOf(Math.sqrt(val));
                    tvDisplay.setText(currentInput);
                    operator = "";
                } else {
                    firstValue = Double.parseDouble(currentInput);
                    currentInput = "";
                }
            }
        };

        int[] operatorButtons = {R.id.btnAdd, R.id.btnSub, R.id.btnMul, R.id.btnDiv, R.id.btnPow, R.id.btnSqrt};
        for (int id : operatorButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void setScientificOnClickListener() {
        View.OnClickListener listener = v -> {
            if (currentInput.isEmpty()) return;
            double val = Double.parseDouble(currentInput);
            double result = 0;
            int id = v.getId();
            
            if (id == R.id.btnSin) result = Math.sin(Math.toRadians(val));
            else if (id == R.id.btnCos) result = Math.cos(Math.toRadians(val));
            else if (id == R.id.btnTan) result = Math.tan(Math.toRadians(val));
            else if (id == R.id.btnCosec) result = 1.0 / Math.sin(Math.toRadians(val));
            else if (id == R.id.btnSec) result = 1.0 / Math.cos(Math.toRadians(val));
            else if (id == R.id.btnCot) result = 1.0 / Math.tan(Math.toRadians(val));
            else if (id == R.id.btnLog) result = Math.log10(val);
            else if (id == R.id.btnLn) result = Math.log(val);
            
            currentInput = String.valueOf(result);
            tvDisplay.setText(currentInput);
        };

        int[] scButtons = {R.id.btnSin, R.id.btnCos, R.id.btnTan, R.id.btnCosec, R.id.btnSec, R.id.btnCot, R.id.btnLog, R.id.btnLn};
        for (int id : scButtons) {
            findViewById(id).setOnClickListener(listener);
        }
    }

    private void compute() {
        if (!Double.isNaN(firstValue) && !currentInput.isEmpty()) {
            double secondValue = Double.parseDouble(currentInput);
            switch (operator) {
                case "+": firstValue += secondValue; break;
                case "-": firstValue -= secondValue; break;
                case "*": firstValue *= secondValue; break;
                case "/": firstValue /= secondValue; break;
                case "^": firstValue = Math.pow(firstValue, secondValue); break;
            }
            tvDisplay.setText("" + firstValue);
            currentInput = "" + firstValue;
            firstValue = Double.NaN;
            operator = "";
        }
    }
}
