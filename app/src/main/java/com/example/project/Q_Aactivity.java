package com.example.project;


import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Q_Aactivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qaactivity);

        Button proceed = findViewById(R.id.proceed);


        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent proceedIntent = new Intent(Q_Aactivity.this, Q_A2Activity.class);
                startActivity(proceedIntent);
            }
        });

    }
}