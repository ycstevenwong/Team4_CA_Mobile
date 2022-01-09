package com.example.team4_ca_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener {
    Button logout,leaderboard;
    Button startGameBtn, startGameBtnv2, congratulationScreenBtn;
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

        leaderboard = findViewById(R.id.leaderboardBtn);
        leaderboard.setOnClickListener(this);

        startGameBtn = findViewById(R.id.startGameBtn);
        startGameBtn.setOnClickListener(this);


        startGameBtnv2 = findViewById(R.id.startGameBtnv2);
        startGameBtnv2.setOnClickListener(this);

        congratulationScreenBtn = findViewById(R.id.congratulationScreen);
        congratulationScreenBtn.setOnClickListener(this);
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
        else if(id == R.id.startGameBtnv2) {
            startGamev2();
        }

        else if(id == R.id.congratulationScreen) {
            startCongratulationScreen();
        }else if(id == R.id.leaderboardBtn){
            startLeaderboard();
        }
    }

    private void startCongratulationScreen() {
        Intent intent = new Intent(this, CongratulationScreen.class);
        startActivity(intent);
    }

    public void startGame() {
        Intent intent = new Intent(this, FetchActivity.class);
        startActivity(intent);
    }


    public void startGamev2() {
        Intent intent = new Intent(this, activityFetchImages.class);
        startActivity(intent);
    }
    public void startLeaderboard(){
        Intent intent = new Intent(this, LeaderboardActivity.class);
        startActivity(intent);
    }
}









