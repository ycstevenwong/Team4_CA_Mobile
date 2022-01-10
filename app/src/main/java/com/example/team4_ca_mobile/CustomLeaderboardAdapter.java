package com.example.team4_ca_mobile;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomLeaderboardAdapter extends ArrayAdapter<Object> {

    private final Context context;
    protected List<Leaderboard> leaderboardList;



    public CustomLeaderboardAdapter(@NonNull Context context, List<Leaderboard> leaderboardList) {
        super(context, R.layout.leaderboard_row);
        this.context = context;
        this.leaderboardList=leaderboardList;
        addAll(new Object[leaderboardList.size()]);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.leaderboard_row,parent,false);
        }
        TextView username = convertView.findViewById(R.id.leaderboardUsername);
        username.setText(leaderboardList.get(position).getUsername());
        TextView time = convertView.findViewById(R.id.leaderboardTime);
        time.setText(leaderboardList.get(position).getTime().toString());
        return convertView;
    }


}
