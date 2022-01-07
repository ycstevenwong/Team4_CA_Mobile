package com.example.team4_ca_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener {
    Button logout;
    Button startGameBtn;
    TextView username;
    SharedPreferences currUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        currUser = getSharedPreferences("currUser",MODE_PRIVATE);

        username = findViewById(R.id.main_menuTxtView);
        Intent intent = getIntent();
        String user = intent.getStringExtra("username");
        username.setText(user);

        logout = findViewById(R.id.logoutBtn);
        logout.setOnClickListener(this);

        startGameBtn = findViewById(R.id.startGameBtn);
        startGameBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        SharedPreferences.Editor editor = currUser.edit();
        int id = v.getId();
        if(id == R.id.logoutBtn){
            editor.clear();
            editor.commit();
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            Toast.makeText(this, "You've logged out", Toast.LENGTH_SHORT).show();
        }
        else if(id == R.id.startGameBtn) {
            startGame();
        }
    }

    public void startGame() {
        Intent intent = new Intent(this, FetchActivity.class);
        startActivity(intent);
    }

}









