package com.example.team4_ca_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;

public class FetchActivity extends AppCompatActivity implements View.OnClickListener {
    //UNDER TRIAL FOR RECYCLER VIEW, WORKS IF URL HARDCODED BUT CANT FETCH
    //CLICK FETCH TWICE TO SEE WHAT IT LOOKS LIKE
    private RecyclerView images;
    private RecyclerView.Adapter adapter;

    EditText url; //user's URL input text
    Button fetchBtn; //fetch images button
    ProgressBar progressBar;
    TextView progressBarTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch);

        fetchBtn = findViewById(R.id.fetchBtn);
        fetchBtn.setOnClickListener(this);

        url = findViewById(R.id.userUrl);
        url.setOnClickListener(this);

        ArrayList<Image> images = initImages();

        this.images = (RecyclerView) findViewById(R.id.allImages);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        this.images.setLayoutManager(mLayoutManager);

        adapter = new ImageAdapter(images);
        this.images.setAdapter(adapter);
    }


    private ArrayList<Image> initImages() {

        //EditText url = findViewById(R.id.userUrl);
        String userURL = url.getText().toString();

        ArrayList<Image> list = new ArrayList<>();
        list.add(new Image("https://bit.ly/CBImageCinque"));
        list.add(new Image("https://bit.ly/CBImageParis"));
        list.add(new Image("https://bit.ly/CBImageRio"));
        list.add(new Image("https://stocksnap.io/"));

        ArrayList<String> imgURL = addImageURL(userURL);
        for(String singleURL : imgURL) {
            list.add(new Image(singleURL));
        }

        /*new Thread(new Runnable() {
        // @Override
        // public void run() { //don't call any UI components in run()
        //code here to download images or call the download method
        //   if(Thread.interrupted()) { //to be in the download method
        //       return; //stop download
        //    }
              url = findViewById(R.id.url);*/

        return list;
    }

    private static ArrayList<String> addImageURL(String userURL) {

        ArrayList<String> fetchedURLs = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(userURL).get();
            Elements twentyImages = doc.select("img[src]");

            for (Element oneImage : twentyImages) {

                String imgSrc = oneImage.attr("src");

                if (imgSrc.contains(".jpg") == true || imgSrc.contains(".jpeg") == true) {
                    for(int i = 0; i < 20; i++) {
                fetchedURLs.add(imgSrc);
                System.out.println(imgSrc); }}

                /*if (imgSrc.contains(".jpg") || imgSrc.contains(".jpeg")) {
                    for(int i = 0; i < 20; i++) {
                    list.add(new Image(imgSrc));
                    list.add(new Image(String.format("https://%s", imgSrc)));
                  }
               } */
            }
            return fetchedURLs;
        }
        catch (Exception e) {
            System.out.println("Error");
            System.out.println(userURL);
        }
        return fetchedURLs;
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        url = findViewById(R.id.userUrl);

        if (id == R.id.fetchBtn) {

            ArrayList<Image> images = initImages();
            this.images = (RecyclerView) findViewById(R.id.allImages);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            this.images.setLayoutManager(mLayoutManager);

            adapter = new ImageAdapter(images);
            this.images.setAdapter(adapter);
        }
        else if (id == R.id.url) {
            if(url.getText().toString().substring(0, 7).contains("https://") != true) {
                Toast.makeText(FetchActivity.this, "Invalid URL", Toast.LENGTH_SHORT).show();

            }
        }
    }





}


















