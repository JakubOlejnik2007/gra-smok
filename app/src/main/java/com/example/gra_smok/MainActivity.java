package com.example.gra_smok;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    /**
     * The onCreate function sets up the main activity layout and handles the button click event to
     * check if the user has won against a dragon based on the input number.
     * 
     * @param savedInstanceState The savedInstanceState parameter is a Bundle object that contains the
     * data saved from the previous state of the activity. It is used to restore the activity's
     * previous state, such as when the activity is recreated after being destroyed and re-created by
     * the system.
     */
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
                int value = Integer.parseInt(numberInputValue);
                boolean result = checkIfUserWon(value);
                message = result ? "Udało Ci się pokonać smoka!" : "Nie udało Ci się pokonać smoka, musisz spróbować jeszcze raz.";
            }
            handleDialog(message);

        });

    }

    /**
     * The function checks if the input value is valid by ensuring it is not empty and within the range
     * of 0 to 100.
     * 
     * @param inputValue The inputValue parameter is a string that represents the input value that
     * needs to be checked for validity.
     * @return The method is returning a boolean value.
     */
    private boolean checkIsInputValid(@NonNull String inputValue) {
        if (inputValue.isEmpty()) return false;

        int valueFromInput = Integer.parseInt(inputValue);

        return valueFromInput >= 0 && valueFromInput <= 100;
    }

    /**
     * The function checks if the given value is equal to a randomly generated number between 0 and 99.
     * 
     * @param value The value parameter represents the user's guess.
     * @return The method is returning a boolean value.
     */
    private boolean checkIfUserWon(int value) {
        Random random = new Random();
        return value == random.nextInt(100);
    }

    /**
     * The function "handleDialog" creates and displays an alert dialog with a given message and a
     * positive button.
     * 
     * @param message The message parameter is a string that represents the content of the dialog
     * message. It is the text that will be displayed to the user in the dialog box.
     */
    private void handleDialog(String message) {
        AlertDialog.Builder builder = new
                AlertDialog.Builder(MainActivity.this);
        builder.setMessage(message)
                .setTitle("Gra SMOK")
                .setPositiveButton("OK", (dialog, id) -> {});
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}