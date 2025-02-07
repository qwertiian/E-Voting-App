package com.example.e_votingapp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText votingId, name;
    private RadioButton option1, option2, option3;
    private Button submitButton;
    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        votingId = findViewById(R.id.voting_id);
        name = findViewById(R.id.name);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        submitButton = findViewById(R.id.submit);
        tableLayout = findViewById(R.id.tableLayout);

        submitButton.setEnabled(false);

        option1.setOnClickListener(v -> {
            if (option1.isChecked()) {
                option2.setChecked(false);
                option3.setChecked(false);
            }
        });
        option2.setOnClickListener(v -> {
            if (option2.isChecked()) {
                option1.setChecked(false);
                option3.setChecked(false);
            }
        });
        option3.setOnClickListener(v -> {
            if (option3.isChecked()) {
                option1.setChecked(false);
                option2.setChecked(false);
            }
        });

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                checkSubmitButton();
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        };

        votingId.addTextChangedListener(textWatcher);
        name.addTextChangedListener(textWatcher);

        tableLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSubmitButton();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String voterIdText = votingId.getText().toString().trim();
                String nameText = name.getText().toString().trim();
                String selectedCandidate = "";

                if (option1.isChecked()) {
                    selectedCandidate = "Mr. XYZ (ABC)";
                } else if (option2.isChecked()) {
                    selectedCandidate = "Ms. PQR (BCD)";
                } else if (option3.isChecked()) {
                    selectedCandidate = "Mr. LMN (DEF)";
                }

                if (voterIdText.isEmpty() || nameText.isEmpty() || selectedCandidate.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill in all details and select a candidate!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Vote Submitted Successfully!\nVoter ID: " + voterIdText + "\nName: " + nameText + "\nCandidate: " + selectedCandidate, Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void checkSubmitButton() {
        String voterIdText = votingId.getText().toString().trim();
        String nameText = name.getText().toString().trim();
        boolean isRadioSelected = option1.isChecked() || option2.isChecked() || option3.isChecked();

        // Enable submit button only when voterId, name, and a radio button are selected
        submitButton.setEnabled(!voterIdText.isEmpty() && !nameText.isEmpty() && isRadioSelected);
    }
}