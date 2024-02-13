package com.example.gra_smok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button playButton = findViewById(R.id.playButton);
        EditText numberInput = findViewById(R.id.numberInput);

        playButton.setOnClickListener(view -> {
            String numberInputValue = numberInput.getText().toString().trim();
            String message;
            if(!checkIsInputValid(numberInputValue)) {
                message = "Wprowadzone dane są błędne! Liczba w polu powinna znajdować się w przedziale od 0 do 100";
            } else {

            }

        });

    }

    private boolean checkIsInputValid(String inputValue) {
        if (inputValue.isEmpty()) return false;

        int valueFromInput = Integer.parseInt(inputValue);

        if (valueFromInput < 0 || valueFromInput > 100) return false;
        return true;

    }
}