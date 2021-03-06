package com.example.team4_ca_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText username;
    EditText password;
    Button login;
    SharedPreferences currUser;
    SharedPreferences userList;
    int i = 0;

    // SFX variables
    private MediaPlayer bgmplayer = null;
    private int bgmPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Intent result = getIntent();
        bgmPos = result.getIntExtra("bgmPos", 0);
        startBGMPlayer(bgmPos);

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
                intent.putExtra("bgmPos", bgmplayer.getCurrentPosition());
                startActivity(intent);
            } else {
                Toast.makeText(this, "Username or password incorrect", Toast.LENGTH_SHORT).show();
            }
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
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra("bgmPos", bgmplayer.getCurrentPosition());
            interruptBGMPlayer("stop");
            startActivity(intent);
            return true;
        }
        // if key != back key, bubble up to default system behaviour
        return super.onKeyDown(keyCode, event);
    }

    public int getNextLargestNum(){
        while(userList.contains("signUpUsername"+i)){
            i++;
        }
        return i;
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
            bgmplayer.seekTo(bgmPos);
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