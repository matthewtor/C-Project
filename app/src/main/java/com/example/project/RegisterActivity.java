package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.*;

public class RegisterActivity extends AppCompatActivity {
    SharedPreferences preferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
    String storedToken = preferences.getString("session", null);
    RadioGroup paymentDetailsGroup;// TODO fix something here causes whole app to crash
    RadioGroup paymentOptionsGroup;
    Button nextButton;
    EditText cardNumberEditText;
    EditText expiryDateEditText;
    EditText securityCodeEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_register);
            nextButton = findViewById(R.id.nextButton);
            paymentOptionsGroup = findViewById(R.id.paymentOptionsGroup);
            paymentDetailsGroup = findViewById(R.id.paymentDetailsGroup);
            cardNumberEditText = (EditText) findViewById(R.id.cardNumberEditText);
            expiryDateEditText = (EditText) findViewById(R.id.expiryDateEditText);
            securityCodeEditText = (EditText) findViewById(R.id.securityCodeEditText);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        }
            public void backToLogin(){
                Intent sendIntent = new Intent(this, MainActivity.class);
                startActivity(sendIntent);
            }

            public void pressNext(){//currently doesnt process payment info
                String cardnum = "1234123412341234";//cardNumberEditText.getText().toString();
                String cardexp = "1224";//expiryDateEditText.getText().toString();
                String cardsec = "123";//securityCodeEditText.getText().toString();
                int OptionsRadioButtonId = paymentOptionsGroup.getCheckedRadioButtonId();
                int DetailsRadioButtonId = paymentDetailsGroup.getCheckedRadioButtonId();
                String OptionsSelectStr;
                String DetailsSelectStr;
                if (OptionsRadioButtonId != -1) {
                    RadioButton OptionsSelect = findViewById(OptionsRadioButtonId);
                    OptionsSelectStr = OptionsSelect.getText().toString();
                }else OptionsSelectStr = null;
                if (DetailsRadioButtonId != -1) {
                    RadioButton DetailsSelect = findViewById(DetailsRadioButtonId);
                    DetailsSelectStr = DetailsSelect.getText().toString();
                }else DetailsSelectStr = null;
                JSONObject json = new JSONObject();
                try {
                    json.put("session", storedToken);
                    json.put("cardnum", cardnum);
                    json.put("cardexp", cardexp);
                    json.put("cardsec", cardsec);
                    json.put("payTime", OptionsSelectStr);
                    json.put("payType", DetailsSelectStr);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                OkHttpClient client = new OkHttpClient();
                RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());
                Request request = new Request.Builder().url("https://dominiks-site.onrender.com/submitpayform").post(requestBody).build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        //TODO
                    }
                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {
                            String responseData = response.body().string();
                            if (responseData!=null){
                                Intent sendIntent = new Intent(RegisterActivity.this, Register2Activity.class);
                                startActivity(sendIntent);
                            }
                        } else {
                            backToLogin();
                            // if no response here TODO
                        }
                    }
                });
            }



}