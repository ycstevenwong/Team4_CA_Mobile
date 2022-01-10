package com.example.team4_ca_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    // defined animation variables
    Animation logoAnimation, nameAnimation, bottomTextAnimation;
    View whitePart, redPart;
    TextView appName, devName;
    static int TIMEOUT = 5000;

    // SFX variables
    private MediaPlayer sfxplayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
 /*       requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        */

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        playSFX();

        logoAnimation = AnimationUtils.loadAnimation(this, R.anim.logo_animation);
        nameAnimation = AnimationUtils.loadAnimation(this, R.anim.name_animation);
        bottomTextAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_text_animation);

        whitePart = findViewById(R.id.white_part);
        redPart = findViewById(R.id.red_part);

        appName = findViewById(R.id.main_text);
        devName = findViewById(R.id.bottom_text);

        whitePart.setAnimation(logoAnimation);
        redPart.setAnimation(logoAnimation);

        appName.setAnimation(nameAnimation);

        devName.setAnimation(bottomTextAnimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, TIMEOUT);
    }

    protected void playSFX() {
        if (sfxplayer != null) {
            resetSFXPlayer();
        }

        // create player to play sfx
        sfxplayer = MediaPlayer.create(this, R.raw.shiny_sound_effect);
        sfxplayer.start();
        }

    protected void resetSFXPlayer() {
        if (sfxplayer != null) {
            sfxplayer.stop();
            sfxplayer.release();
            sfxplayer = null;
        }
    }
}