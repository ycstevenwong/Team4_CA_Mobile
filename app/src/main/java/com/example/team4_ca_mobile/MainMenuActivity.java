package com.example.team4_ca_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener {
    Button logout,leaderboard;
    Button startGameBtn, startGameBtnv2, congratulationScreenBtn;
    TextView username;
    SharedPreferences currUser;

    // SFX variables
    private MediaPlayer bgmplayer = null;
    private int bgmPos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        currUser = getSharedPreferences("currUser",MODE_PRIVATE);

        username = findViewById(R.id.main_menuTxtView);
        Intent intent = getIntent();
        bgmPos = intent.getIntExtra("bgmPos", 0);
        startBGMPlayer(bgmPos);

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
            intent.putExtra("bgmPos", bgmplayer.getCurrentPosition());
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            Intent intent = new Intent(this, login.class);
            intent.putExtra("bgmPos", bgmplayer.getCurrentPosition());
            interruptBGMPlayer("stop");
            startActivity(intent);
            return true;
        }
        // if key != back key, bubble up to default system behaviour
        return super.onKeyDown(keyCode, event);
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
        intent.putExtra("bgmPos", bgmplayer.getCurrentPosition());
        startActivity(intent);
    }
    public void startLeaderboard(){
        Intent intent = new Intent(this, LeaderboardActivity.class);
        intent.putExtra("bgmPos", bgmplayer.getCurrentPosition());
        startActivity(intent);
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









