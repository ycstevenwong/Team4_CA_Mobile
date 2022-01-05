package com.example.team4_ca_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class sign_up extends AppCompatActivity implements View.OnClickListener {
    EditText username;
    EditText password;
    Button submit;
    SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        pref = getSharedPreferences("signup",MODE_PRIVATE);
        username = findViewById(R.id.signUpUsernameEditText);
        password = findViewById(R.id.signUpPasswordEditText);
        submit = findViewById(R.id.signUpBtn);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.signUpBtn){
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("signUpUsername",username.getText().toString());
            editor.putString("signUpPassword",password.getText().toString());
            editor.commit();
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
    }
}