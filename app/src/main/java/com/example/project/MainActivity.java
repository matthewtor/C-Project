package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;

import org.json.JSONObject;

import okhttp3.*;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    EditText inputName;
    EditText inputPass;
    final String submitform = "https://localhost:5000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("xd");
        Log.d("myTag", "tag");

        Button send = findViewById(R.id.send);
        Button login = findViewById(R.id.LogIn);
        inputPass = (EditText) findViewById(R.id.inputName);
        inputName = (EditText) findViewById(R.id.inputPass);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pressLogin();
            }
        });
    }


    public void pressLogin() {
        String name = inputName.getText().toString();
        String pass = inputPass.getText().toString();
        JSONObject json = new JSONObject();
        try {
            json.put("username", name);
            json.put("password", pass);
        } catch (Exception e) {
            e.printStackTrace();
        }

        OkHttpClient client = new OkHttpClient();
        Request request = null;
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json.toString());
        request = new Request.Builder().url(submitform + "/submitform").post(requestBody).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // TODO
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody responsebody = response.body();
                if (response.isSuccessful() && responsebody != null) {
                    String responseString = responsebody.string();
                    saveKeyValue("session", responseString);
                    gotoUpdate();
                } else {
                    // if no response here TODO
                }

            }


        });
    }
    public void saveKeyValue(String key, String value) {
        SharedPreferences preferences = getSharedPreferences("MyAppPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }
    public void gotoRegister(){
        Intent sendIntent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(sendIntent);
    }
    public void gotoUpdate(){
        Intent sendIntent = new Intent(MainActivity.this, Register2Activity.class);
        startActivity(sendIntent);
    }
}