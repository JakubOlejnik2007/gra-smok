package com.example.gra_smok;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int triesCounter = 3;
    private String message;
    private int randomNumber;

    
    /**
     * The `onCreate` function sets up the main activity layout and handles the logic for the play
     * button click event.
     * 
     * @param savedInstanceState The savedInstanceState parameter is a Bundle object that contains the
     * data saved from the previous state of the activity. It is used to restore the activity's
     * previous state when it is recreated, such as after a configuration change (e.g., screen
     * rotation) or when the activity is destroyed and recreated due to system constraints
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button playButton = findViewById(R.id.playButton);
        EditText numberInput = findViewById(R.id.numberInput);

        playButton.setOnClickListener(view -> {
            String numberInputValue = numberInput.getText().toString().trim();
            if(!checkIsInputValid(numberInputValue)) {
                this.message = "Wprowadzone dane są błędne! Liczba w polu powinna znajdować się w przedziale od 0 do 100";
            } else {
                boolean result = handleUserInsertValidInput(Integer.parseInt(numberInputValue));

                if(!result && this.triesCounter == 0) {
                    this.message += "\n\nLiczba jaką należało wpisać to:\t" + this.randomNumber + "\n\nSpróbuj od nowa\npokonać smoka";
                }

                if(result || this.triesCounter == 0) {
                    this.triesCounter = 3;
                    getRandomNumber();
                };

            }

            handleDialog(this.message);

        });

    }

    /**
     * The function handles user input and checks if it matches a randomly generated number, providing
     * appropriate feedback and updating the number of remaining tries.
     * 
     * @param value The value parameter represents the user's input, which is an integer value.
     * @return The method is returning a boolean value.
     */
    private boolean handleUserInsertValidInput(int value) {
        if(value == randomNumber) {
            this.message = "Udało Ci się pokonać smoka";
            return true;
        }

        this.message = "Nie udało Ci się pokonać smoka, musisz spróbować jeszcze raz.";
        this.triesCounter -= 1;
        this.message += "\n\n";
        this.message += value > this.randomNumber ? "Oczekiwana liczba jest mniejsza od wprowadzonej" : "Oczekiwana liczba jest większa od wprowadzonej";
        this.message += "\nPozostało prób:\t" + this.triesCounter;

        return false;
    }


    /**
     * The function checks if the input value is a non-empty string and a valid integer between 0 and
     * 100.
     * 
     * @param inputValue The inputValue parameter is a non-null String that represents the input value
     * to be checked.
     * @return The method is returning a boolean value.
     */
    private boolean checkIsInputValid(@NonNull String inputValue) {
        if (inputValue.isEmpty()) return false;

        int valueFromInput = Integer.parseInt(inputValue);

        return valueFromInput >= 0 && valueFromInput <= 100;
    }

    /**
     * The getRandomNumber function generates a random number between 0 and 99 and assigns it to the
     * instance variable randomNumber.
     */
    private void getRandomNumber() {
        Random random = new Random();
        this.randomNumber = random.nextInt(100);
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