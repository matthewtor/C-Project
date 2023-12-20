package com.example.project;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class UpdateActivity extends AppCompatActivity {

    private EditText editTextName, editTextAddress, editTextCoverType, editTextNumber;
    private Button updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        editTextName = findViewById(R.id.editTextName);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextCoverType = findViewById(R.id.editTextCoverType);
        editTextNumber = findViewById(R.id.editTextNumber);
        updateButton = findViewById(R.id.updateButton);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString();
                String address = editTextAddress.getText().toString();
                String coverType = editTextCoverType.getText().toString();
                String number = editTextNumber.getText().toString();

            }
        });
    }
}
