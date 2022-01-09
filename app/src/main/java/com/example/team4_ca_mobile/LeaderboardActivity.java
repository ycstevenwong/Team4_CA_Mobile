package com.example.team4_ca_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LeaderboardActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,View.OnClickListener {
    SharedPreferences lbPref;
    SharedPreferences currUser;
    Button back;
    List<Leaderboard> leaderboardList;
    ListView listView;
    int i = 0;

    // SFX variables
    private MediaPlayer bgmplayer = null;
    private int bgmPos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        Intent result = getIntent();
        bgmPos = result.getIntExtra("bgmPos", 0);
        startBGMPlayer(bgmPos);

        lbPref = getSharedPreferences("leaderboard",MODE_PRIVATE);
        currUser = getSharedPreferences("currUser",MODE_PRIVATE);
        leaderboardList = new ArrayList<>();
        for(int i = 0; i < getNextLargestNum(); i++){
            String username = lbPref.getString("user"+i,null);
            Integer time = lbPref.getInt("userTime"+i,0);
            Leaderboard curr = new Leaderboard(username,time);
            leaderboardList.add(curr);
        }
        sortTheList(leaderboardList);
        if(leaderboardList.size()>10){
            leaderboardList = leaderboardList.stream().limit(10).collect(Collectors.toList());
        }

        customLeaderboardAdapter adapter = new customLeaderboardAdapter(this,leaderboardList);
        listView = findViewById(R.id.leaderboardListView);
        if(listView!=null){
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(this);
        }


        back = findViewById(R.id.back);
        back.setOnClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.back){
            Intent intent = new Intent(this,MainMenuActivity.class);
            String username = currUser.getString("username",null);
            intent.putExtra("username",username);
            intent.putExtra("bgmPos", bgmplayer.getCurrentPosition());
            startActivity(intent);
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
            Intent intent = new Intent(this, MainMenuActivity.class);
            intent.putExtra("bgmPos", bgmplayer.getCurrentPosition());
            interruptBGMPlayer("stop");
            startActivity(intent);
            return true;
        }
        // if key != back key, bubble up to default system behaviour
        return super.onKeyDown(keyCode, event);
    }

    public int getNextLargestNum(){
        while(lbPref.contains("user"+i)){
            i++;
        }
        return i;
    }
    private void sortTheList(List<Leaderboard> list){
        if(list.size()==1){
            System.out.println("Only one inside");
        }else if(list.size()>1){
            Collections.sort(list, new Comparator<Leaderboard>() {
                @Override
                public int compare(Leaderboard o1, Leaderboard o2) {
                    return o1.getTime().compareTo(o2.getTime());
                }
            });
        }
    }

    protected void startBGMPlayer(int bgmPos) {
        SFX bgm = new SFX("dramatic_intro_music");
        if (bgmplayer == null) {
            // play BGM
            int resId = getResources().getIdentifier(bgm.getFname(), "raw", getPackageName());
            bgmplayer = MediaPlayer.create(this, resId);
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