package com.example.team4_ca_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginOrSignUpBtn();
    }

    protected void loginOrSignUpBtn(){
        int[] ids = {R.id.loginButton,R.id.signUpButton};
        for(int i = 0; i < ids.length;i++){
            Button btn = findViewById(ids[i]);
            if(btn!=null){
                btn.setOnClickListener(this);
            }
        }
    }
    @Override
    public void onClick(View v){
        int id = v.getId();
        if(id == R.id.loginButton){
            Intent intent = new Intent(this,login.class);
            startActivity(intent);
        }
        if(id == R.id.signUpButton){
            Intent intent = new Intent(this,sign_up.class);
            startActivity(intent);
        }
    }
}