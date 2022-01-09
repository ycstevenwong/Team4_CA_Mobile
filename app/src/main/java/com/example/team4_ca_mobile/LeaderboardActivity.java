package com.example.team4_ca_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
    List<Leaderboard> topTen;
    ListView listView;
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        lbPref = getSharedPreferences("leaderboard",MODE_PRIVATE);
        SharedPreferences.Editor editor = lbPref.edit();
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
            startActivity(intent);
        }
    }
    public int getNextLargestNum(){
        while(lbPref.contains("user"+i)){
            i++;
        }
        return i;
    }
    private void sortTheList(List<Leaderboard> list){
        int pos = 0;
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
}