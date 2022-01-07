package com.example.team4_ca_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity implements View.OnClickListener {

    EditText username;
    EditText password;
    Button login;
    SharedPreferences currUser;
    SharedPreferences userList;
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        currUser = getSharedPreferences("currUser",MODE_PRIVATE);
        userList = getSharedPreferences("userList",MODE_PRIVATE);
        username = findViewById(R.id.loginUsernameEditText);
        password = findViewById(R.id.loginPasswordEditText);
        login = findViewById(R.id.loginBtn);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        boolean found = false;
        String currUsername="";
        if(id == R.id.loginBtn){
            SharedPreferences.Editor editor = currUser.edit();
            for(int j = 0; j < getNextLargestNum();j++){
                if(userList.getString("signUpUsername"+j,null).equals(username.getText().toString())){
                    if(userList.getString("signUpPassword"+j,null).equals(password.getText().toString())){
                        found = true;
                        currUsername = userList.getString("signUpUsername"+j,null);
                        editor.putString("username",currUsername);
                        editor.commit();
                    }
                }
            }
            if(found){
                editor.putString("username",currUsername);
                Intent intent = new Intent(this, MainMenuActivity.class);
                intent.putExtra("username",currUsername);
                startActivity(intent);
            }else{
                Toast.makeText(this, "Username or password incorrect", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public int getNextLargestNum(){
        while(userList.contains("signUpUsername"+i)){
            i++;
        }
        return i;
    }
}