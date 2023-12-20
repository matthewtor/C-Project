package com.example.project;


import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button send = findViewById(R.id.send);
        Button secondButton = findViewById(R.id.secondButton);
        Button thirdButton = findViewById(R.id.thirdButton);
        Button fourthButton = findViewById(R.id.fourthButton);
        Button fifthButton = findViewById(R.id.fifthButton);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent(LoginActivity.this, UpdateActivity.class);
                startActivity(sendIntent);
            }
        });

        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent secondIntent = new Intent(LoginActivity.this, UpdatePaymentActivity.class);
                startActivity(secondIntent);
            }
        });

        thirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent thirdIntent = new Intent(LoginActivity.this, UpdateGP.class);
                startActivity(thirdIntent);
            }
        });

        fourthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fourthIntent = new Intent(LoginActivity.this, CommunicationActivity.class);
                startActivity(fourthIntent);
            }
        });
        fifthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fifthIntent = new Intent(LoginActivity.this, Q_Aactivity.class);
                startActivity(fifthIntent);
            }
        });
    }
}

