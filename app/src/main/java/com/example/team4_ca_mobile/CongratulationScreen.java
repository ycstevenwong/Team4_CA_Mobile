package com.example.team4_ca_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;

public class CongratulationScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulation_screen);

        MediaPlayer player;
        player = MediaPlayer.create(this,R.raw.win_sound_effect);
        player.start();
    }
}