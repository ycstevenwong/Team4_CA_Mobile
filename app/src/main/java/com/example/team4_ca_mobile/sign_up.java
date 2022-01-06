package com.example.team4_ca_mobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class sign_up extends AppCompatActivity implements View.OnClickListener {
    EditText username;
    EditText password;
    Button submit;
    SharedPreferences userList;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        userList = getSharedPreferences("userList",MODE_PRIVATE);
        username = findViewById(R.id.signUpUsernameEditText);
        password = findViewById(R.id.signUpPasswordEditText);
        submit = findViewById(R.id.signUpBtn);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        boolean found = false;
        if(id == R.id.signUpBtn){
            SharedPreferences.Editor editor = userList.edit();
            for(int j = 0; j < getNextLargestNum();j++){
                if (userList.getString("signUpUsername" + j, null).equals(username.getText().toString())) {
                    found = true;
                }
            }
            if(found){
                Toast.makeText(this, "Username exist", Toast.LENGTH_SHORT).show();
            }else{
                editor.putString("signUpUsername"+getNextLargestNum(),username.getText().toString());
                editor.putString("signUpPassword"+getNextLargestNum(),password.getText().toString());
                editor.commit();
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                Toast.makeText(this, "Account created", Toast.LENGTH_SHORT).show();
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