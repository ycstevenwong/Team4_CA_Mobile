package com.example.team4_ca_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CongratulationScreenActivity extends AppCompatActivity {

    SharedPreferences currUser;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulation_screen);

        currUser = getSharedPreferences("currUser",MODE_PRIVATE);

        MediaPlayer player;
        player = MediaPlayer.create(this,R.raw.sci_fi_laser_gun_shot_sound_effect);
        player.start();

        Intent intent = getIntent();
        int score = intent.getIntExtra("totalTime", 0);
        TextView textViewScore = findViewById(R.id.score);
        textViewScore.setText("Score: " + score);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = currUser.getString("username",null);

                Intent mainMenu = new Intent(getApplicationContext(), MainMenuActivity.class);
                mainMenu.putExtra("username",username);

                startActivity(mainMenu);
            }
        });

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent scoreBoard = new Intent(getApplicationContext(), LeaderboardActivity.class);
                startActivity(scoreBoard);
            }
        }, 5000);
    }
}