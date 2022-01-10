package com.example.team4_ca_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CongratulationScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulation_screen);

        MediaPlayer player;
        player = MediaPlayer.create(this,R.raw.win_sound_effect);
        player.start();

        Intent intent = getIntent();
        int score = intent.getIntExtra("totalTime", 0);
        TextView textViewScore = findViewById(R.id.score);
        textViewScore.setText("Score: " + score);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainMenu = new Intent(getApplicationContext(), MainMenuActivity.class);
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