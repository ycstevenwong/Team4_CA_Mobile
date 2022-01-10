package com.example.team4_ca_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity implements View.OnClickListener {
    //UNDER TRIAL FOR RECYCLER VIEW TO FECTH IMAGES

    private RecyclerView images;
    private RecyclerView.Adapter adapter;

    EditText url; //user's URL input text
    Button fetchBtn; //fetch images button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch);

        fetchBtn = findViewById(R.id.fetchBtn);
        fetchBtn.setOnClickListener(this);

        url = findViewById(R.id.userUrl);
        url.setOnClickListener(this);

        ArrayList<RecyclerImage> recyclerImages = initImages();

        this.images = (RecyclerView) findViewById(R.id.allImages);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        this.images.setLayoutManager(mLayoutManager);

        adapter = new RecyclerImageAdapter(recyclerImages);
        this.images.setAdapter(adapter);
    }

    private ArrayList<RecyclerImage> initImages() {

        ArrayList<RecyclerImage> list = new ArrayList<>();
        list.add(new RecyclerImage("https://bit.ly/CBImageCinque"));
        list.add(new RecyclerImage("https://bit.ly/CBImageParis"));
        list.add(new RecyclerImage("https://bit.ly/CBImageRio"));
        list.add(new RecyclerImage("https://cdn.stocksnap.io/img-thumbs/960w/machu%20picchu-l" +
                "andscape_1IXAEMT62N.jpg"));
        list.add(new RecyclerImage("https://media.cntraveler.com/photos/5c2cfc9f6b0c2057eb60d5" +
                "79/5:4/w_3440,h_2752,c_limit/Edinburgh%20Castle_GettyImages-157509228.jpg"));
        list.add(new RecyclerImage("https://cdn.stocksnap.io/img-thumbs/960w/machu%20picchu-lan" +
                "dscape_1IXAEMT62N.jpg"));
        list.add(new RecyclerImage("https://cdn.stocksnap.io/img-thumbs/960w/taj-mahal_2EJYQBPJ" +
                "UU.jpg"));
        list.add(new RecyclerImage("https://cdn.stocksnap.io/img-thumbs/960w/london%20bridge-uk" +
                "_CLELOQWXUP.jpg"));
        list.add(new RecyclerImage("https://cdn.stocksnap.io/img-thumbs/960w/tree-wanaka_DDIPPI" +
                "XLZP.jpg"));

        return list;

        //to add images from URL
        /* EditText url = findViewById(R.id.userUrl);
        String userURL = url.getText().toString();
        ArrayList<String> imgURL = addImageURL(userURL);
        for(String singleURL : imgURL) {
            list.add(new RecyclerImage(singleURL));
        }*/
    }

    private static ArrayList<String> addImageURL(String userURL) {

        ArrayList<String> fetchedURLs = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(userURL).get();
            Elements twentyImages = doc.select("img[src]");

            for (Element oneImage : twentyImages) {

                String imgSrc = oneImage.attr("src");

                if (imgSrc.contains(".jpg") || imgSrc.contains(".jpeg")) {
                    for(int i = 0; i < 20; i++) {
                        fetchedURLs.add(imgSrc);
                        System.out.println(imgSrc);
                    }
                }
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

            ArrayList<RecyclerImage> recyclerImages = initImages();
            this.images = (RecyclerView) findViewById(R.id.allImages);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
            this.images.setLayoutManager(mLayoutManager);

            adapter = new RecyclerImageAdapter(recyclerImages);
            this.images.setAdapter(adapter);
        }
        else if (id == R.id.url) {
            if(url.getText().toString().substring(0, 7).contains("https://") != true) {
                Toast.makeText(RecyclerViewActivity.this, "Invalid URL", Toast.LENGTH_SHORT).show();
            }
        }
    }

}


















