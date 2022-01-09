package com.example.team4_ca_mobile;

import static android.widget.Toast.LENGTH_SHORT;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.UUID;
public class activityFetchImages extends AppCompatActivity {

    // define all the variables
    Button fetchButton;
    String[] standardNum = {"1", "2", "3", "4", "5", "6"};
    ProgressBar progressBar;
    TextView progressBarTextView;
    EditText url;
    int i = 0;
    static  int row =0;
    static  int count=0;

    // the thread
    private Thread fetchImagesThread;

    // array of selected images
    ArrayList<ImageView> imageCollection = new ArrayList<ImageView>();

    // SFX variables
    private MediaPlayer bgmplayer = null;
    private int bgmPos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch_images);

        Intent result = getIntent();
        bgmPos = result.getIntExtra("bgmPos", 0);
        startBGMPlayer(bgmPos);

        // start the process
        initUIComponents();

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

    private void initUIComponents() {
        fetchButton = findViewById(R.id.fetchBtn);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(20);

        progressBarTextView = findViewById(R.id.progressBarText);

        url = findViewById(R.id.url);

       // Toast.makeText(this,url.getText().toString(),LENGTH_SHORT).show();
        ImageView imageView01 = findViewById(R.id.image01);
        ImageView imageView02 = findViewById(R.id.image02);
        ImageView imageView03 = findViewById(R.id.image03);
        ImageView imageView04 = findViewById(R.id.image04);
        ImageView imageView05 = findViewById(R.id.image05);
        ImageView imageView06 = findViewById(R.id.image06);
        ImageView imageView07 = findViewById(R.id.image07);
        ImageView imageView08 = findViewById(R.id.image08);
        ImageView imageView09 = findViewById(R.id.image09);
        ImageView imageView10 = findViewById(R.id.image10);
        ImageView imageView11 = findViewById(R.id.image11);
        ImageView imageView12 = findViewById(R.id.image12);
        ImageView imageView13 = findViewById(R.id.image13);
        ImageView imageView14 = findViewById(R.id.image14);
        ImageView imageView15 = findViewById(R.id.image15);
        ImageView imageView16= findViewById(R.id.image16);
        ImageView imageView17 = findViewById(R.id.image17);
        ImageView imageView18 = findViewById(R.id.image18);
        ImageView imageView19 = findViewById(R.id.image19);
        ImageView imageView20= findViewById(R.id.image20);

        // array to pass to the next activity
        ImageView[] imgViews = {imageView01,imageView02,imageView03,imageView04,imageView05,imageView06,
                imageView07,imageView08,imageView09,imageView10,imageView11,imageView12,imageView13,
                imageView14,imageView15,imageView16,imageView17,imageView18,imageView19,imageView20};

        fetchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String myUrl = url.getText().toString();

                // if url is empty or invalid
                if (myUrl.isEmpty() || myUrl.contains("https") != true) {
                    System.out.println("Oops, wrong URL");
                    //Toast.makeText(activityFetchImages.this, "Please enter a URL", LENGTH_SHORT).show();
                }

                fetchImagesThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if(fetchImagesThread.isInterrupted()) {
                            return;
                        }
                        try {
                            Document document = Jsoup.connect(myUrl).get();
                            //System.out.println(document.body());
                            Elements images = document.select("img[src]");
                            row = 0;
                            count = 0;

                            System.out.println("count of rows " + row);
                            System.out.println("count of counts " + count);

                            for (Element image : images) {
                                String imgSrc = image.attr("src");
                                System.out.println(imgSrc);

                                if (imgSrc.contains(".jpg") || imgSrc.contains(".jpeg")) {
                                    System.out.println("2count of rows " + row);
                                    System.out.println("2count of counts " + count);
                                    if (count < 20) {
                                        System.out.println("3count of rows " + row);
                                        System.out.println("3count of counts " + count);
                                        File destFile = makeFileDestPath(imgSrc);

                                      /*  if (row == 20) {
                                            row =0;
                                        }*/
                                        if (downloadImages(imgSrc, destFile)) {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    System.out.println("in ui thread");
                                                    //List of image view
                                                    String path = destFile.getAbsolutePath();

                                                    Bitmap bitmap = BitmapFactory.decodeFile(path);

                                                    System.out.println("4count of rows " + row);
                                                    System.out.println("4count of counts " + count);
                                                    if (count < 20) {
                                                        imgViews[count].setImageBitmap(bitmap);
                                                        progressBarTextView.setText
                                                                (String.format("Downloading %d of %d images...", count, 20));
                                                        }
                                                        progressBar.setVisibility(View.VISIBLE);
                                                        progressBar.setProgress(row);
                                                        count = count + 1;

                                                        if (count == 20) {
                                                        progressBar.setVisibility(View.GONE);
                                                        progressBarTextView.setText("Complete");
                                                    }
                                                        // row = row + 1;
                                                }
                                            });
                                        }
                                    }
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        /*
                            File destFile = makeFileDestPath(imgSrc);
                            downloadImages(imgSrc, destFile);*/
                    }
                });
                fetchImagesThread.start();
            }
        });

    }

    private boolean downloadImages(String url, File destFile) {
        try {
            URL myUrl = new URL(url);
            URLConnection conn = myUrl.openConnection();

            InputStream in = conn.getInputStream();
            FileOutputStream out = new FileOutputStream(destFile);

            byte[] buf = new byte[1024];
            int bytesRead = -1;
            while ((bytesRead = in.read(buf)) != -1) {
                out.write(buf, 0, bytesRead);
            }

            out.close();
            in.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private File makeFileDestPath(String imgURL)
    {
        String destFilename = UUID.randomUUID().toString() +
                imgURL.lastIndexOf(".") + 1;

        File dir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File destFile = new File(dir, destFilename );

       System.out.println(destFile.getAbsolutePath());
        return destFile;
    }

    private String saveToFile(Bitmap bitmapImage) {

        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir

        File mypath = new File(directory, standardNum[i]);
        i++;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }



    public void sendSelectedImage(View v) throws FileNotFoundException {
     // deselect and select logic
        ImageView selectedImage = (ImageView) v;

        if(imageCollection.size() <= 4) {

            //check if image is already in the collection
            if (imageCollection.contains(selectedImage)) {
                imageCollection.remove(selectedImage);
                Drawable clear = getResources().getDrawable(R.drawable.clear);
                selectedImage.setBackground(clear);

            } else {
                imageCollection.add(selectedImage);
                Drawable highlight = getResources().getDrawable(R.drawable.highlight);
                selectedImage.setBackground(highlight);
            }
        }
        else
        {
            imageCollection.add(selectedImage);
            Drawable highlight = getResources().getDrawable(R.drawable.highlight);
            selectedImage.setBackground(highlight);
            String filepath = "";
            for(int i = 0;i<6;i++)
            {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imageCollection.get(i).getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                 filepath = saveToFile(bitmap);
            }

            Intent i = new Intent(activityFetchImages.this, GamePlayActivity.class);
            i.putExtra("img_path", filepath);
            System.out.println(filepath);
            interruptBGMPlayer("stop");
            startActivity(i);
        }








        /*ImageView imageView = (ImageView) v;

        Drawable highlight = getResources().getDrawable(R.drawable.highlight);
        imageView.setBackground(highlight);
*/
        /*BitmapDrawable bd = (BitmapDrawable) selectedImage.getDrawable();
        Bitmap b = bd.getBitmap();
        String filepath = saveToFile(b);
        imgCount++;

        if (imgCount > 5)
        {
            Intent i = new Intent(activityFetchImages.this, MainActivity.class);
            i.putExtra("img_path", filepath);
            System.out.println(filepath);
            startActivity(i);
        }*/
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