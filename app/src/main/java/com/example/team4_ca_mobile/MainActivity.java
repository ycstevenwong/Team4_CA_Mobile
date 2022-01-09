package com.example.team4_ca_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.TextView;
// test comment
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private SharedPreferences currUser;
    private Intent intent;

    // SFX variables
    private MediaPlayer bgmplayer = null;
    private int bgmPos = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startBGMPlayer(bgmPos);

        currUser = getSharedPreferences("currUser",MODE_PRIVATE);

        if(currUser.contains("username")){
            intent = new Intent(this, MainMenuActivity.class);
            intent.putExtra("username",currUser.getString("username",null));
            startActivity(intent);
        }
        loginOrSignUpBtn();
        blinking();
    }

    @Override
    protected void onPause() {
        super.onPause();
        interruptBGMPlayer("pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        startBGMPlayer(bgmPos);
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
            intent.putExtra("bgmPos", bgmplayer.getCurrentPosition());
            interruptBGMPlayer("stop");
            startActivity(intent);
        }
        if(id == R.id.signUpButton){
            Intent intent = new Intent(this,sign_up.class);
            intent.putExtra("bgmPos", bgmplayer.getCurrentPosition());
            interruptBGMPlayer(("stop"));
            startActivity(intent);
        }
    }

    @SuppressLint("WrongConstant")
    private void blinking(){
        TextView textView = findViewById(R.id.houseOfCardTxtView);
        ObjectAnimator animator = ObjectAnimator.ofInt(textView,"textColor", Color.WHITE);
        animator.setDuration(1500);
        animator.setEvaluator(new ArgbEvaluator());
        animator.setRepeatMode(Animation.REVERSE);
        animator.setRepeatCount(Animation.INFINITE);
        animator.start();
    }

    protected void startBGMPlayer(int bgmPos) {
        if (bgmplayer == null) {
            // play BGM
            bgmplayer = MediaPlayer.create(this, R.raw.dramatic_intro_music);
            bgmplayer.seekTo(bgmPos);
            bgmplayer.start();
            bgmplayer.setLooping(true);
        }
        else {
            bgmplayer.start();
        }
    }

    protected int interruptBGMPlayer(String prompt) {
        if (bgmplayer != null) {
            if (prompt.equalsIgnoreCase("pause")) {
                bgmplayer.pause();
                bgmPos = bgmplayer.getCurrentPosition();
            }
            else {
                bgmplayer.stop();
                bgmplayer.release();
                bgmplayer = null;
                bgmPos = 0;
            }
        }
        return bgmPos;
    }
}