package com.example.team4_ca_mobile;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class GamePlayActivity extends AppCompatActivity
    implements View.OnClickListener, Chronometer.OnChronometerTickListener {

    TextView tv_p1;
    ImageView iv_11, iv_12, iv_13, iv_14, iv_21, iv_22, iv_23, iv_24, iv_31, iv_32, iv_33, iv_34;

    private ArrayList<String> cardsArray = new ArrayList<>();
    private int cardNumber = 0;
    ImageView firstCard, secondCard;
    private int playerPoints = 0;
    private Chronometer chronometer;
    private int clickTime = 0;
    SharedPreferences lbPref;
    SharedPreferences currUser;
    int i = 0;

    private MediaPlayer sfxplayer = null;
    private MediaPlayer bgmplayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);

        tv_p1 = findViewById(R.id.tv_p1);

        iv_11 = findViewById(R.id.iv_11);
        iv_12 = findViewById(R.id.iv_12);
        iv_13 = findViewById(R.id.iv_13);
        iv_14 = findViewById(R.id.iv_14);
        iv_21 = findViewById(R.id.iv_21);
        iv_22 = findViewById(R.id.iv_22);
        iv_23 = findViewById(R.id.iv_23);
        iv_24 = findViewById(R.id.iv_24);
        iv_31 = findViewById(R.id.iv_31);
        iv_32 = findViewById(R.id.iv_32);
        iv_33 = findViewById(R.id.iv_33);
        iv_34 = findViewById(R.id.iv_34);
        setupCards();

        cardsArray.add("1");
        cardsArray.add("1");
        cardsArray.add("2");
        cardsArray.add("2");
        cardsArray.add("3");
        cardsArray.add("3");
        cardsArray.add("4");
        cardsArray.add("4");
        cardsArray.add("5");
        cardsArray.add("5");
        cardsArray.add("6");
        cardsArray.add("6");
        Collections.shuffle(cardsArray);
        assignCards();

        startBGMPlayer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        interruptBGMPlayer("pause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        startBGMPlayer();
    }

    private void setupCards() {
        int[] ids = {R.id.iv_11, R.id.iv_12, R.id.iv_13, R.id.iv_14, R.id.iv_21, R.id.iv_22, R.id.iv_23, R.id.iv_24, R.id.iv_31, R.id.iv_32, R.id.iv_33, R.id.iv_34};
        for (int i = 0; i < ids.length; i++) {
            ImageView imageView = findViewById(ids[i]);
            if (imageView != null) {
                imageView.setOnClickListener(this);
            }
        }
    }

    private void assignCards() {
        iv_11.setTag(cardsArray.get(0));
        iv_12.setTag(cardsArray.get(1));
        iv_13.setTag(cardsArray.get(2));
        iv_14.setTag(cardsArray.get(3));
        iv_21.setTag(cardsArray.get(4));
        iv_22.setTag(cardsArray.get(5));
        iv_23.setTag(cardsArray.get(6));
        iv_24.setTag(cardsArray.get(7));
        iv_31.setTag(cardsArray.get(8));
        iv_32.setTag(cardsArray.get(9));
        iv_33.setTag(cardsArray.get(10));
        iv_34.setTag(cardsArray.get(11));
    }

    @Override
    public void onClick(View view) {
        if (view == iv_11) {
            flipCard(iv_11);
            if (cardNumber == 2) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        calculate();
                        checkEnd();
                    }
                }, 1000);
            }
        } else if (view == iv_12) {
            flipCard(iv_12);
            if (cardNumber == 2) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        calculate();
                        checkEnd();
                    }
                }, 1000);
            }
        } else if (view == iv_13) {
            flipCard(iv_13);
            if (cardNumber == 2) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        calculate();
                        checkEnd();
                    }
                }, 1000);
            }
        } else if (view == iv_14) {
            flipCard(iv_14);
            if (cardNumber == 2) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        calculate();
                        checkEnd();
                    }
                }, 1000);
            }
        } else if (view == iv_21) {
            flipCard(iv_21);
            if (cardNumber == 2) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        calculate();
                        checkEnd();
                    }
                }, 1000);
            }
        } else if (view == iv_22) {
            flipCard(iv_22);
            if (cardNumber == 2) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        calculate();
                        checkEnd();
                    }
                }, 1000);
            }
        } else if (view == iv_23) {
            flipCard(iv_23);
            if (cardNumber == 2) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        calculate();
                        checkEnd();
                    }
                }, 1000);
            }
        } else if (view == iv_24) {
            flipCard(iv_24);
            if (cardNumber == 2) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        calculate();
                        checkEnd();
                    }
                }, 1000);
            }
        } else if (view == iv_31) {
            flipCard(iv_31);
            if (cardNumber == 2) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        calculate();
                        checkEnd();
                    }
                }, 1000);
                //calculate();
            }
        } else if (view == iv_32) {
            flipCard(iv_32);
            if (cardNumber == 2) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        calculate();
                        checkEnd();
                    }
                }, 1000);
            }
        } else if (view == iv_33) {
            flipCard(iv_33);
            if (cardNumber == 2) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        calculate();
                        checkEnd();
                    }
                }, 1000);
            }
        } else if (view == iv_34) {
            flipCard(iv_34);
            if (cardNumber == 2) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        calculate();
                        checkEnd();
                    }
                }, 1000);
            }
        }
    }

    private void flipCard(ImageView imageView) {
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        String fileName = (String)imageView.getTag();
        File mTargetFile = new File(directory, fileName);
        Bitmap bitmap = BitmapFactory.decodeFile(mTargetFile.getAbsolutePath());
        //imageView.setImageBitmap(bitmap);

        final ObjectAnimator oa1 = ObjectAnimator.ofFloat(imageView, "scaleX", 1f, 0f);
        final ObjectAnimator oa2 = ObjectAnimator.ofFloat(imageView, "scaleX", 0f, 1f);

        oa1.setInterpolator(new DecelerateInterpolator());
        oa2.setInterpolator(new AccelerateDecelerateInterpolator());
        oa1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                imageView.setImageBitmap(bitmap);
                oa2.start();
            }
        });
        oa1.start();

        clickTime++;
        if(clickTime == 1){
            chronometer = (Chronometer) findViewById(R.id.chronometer);
            chronometer.setOnChronometerTickListener(this);
            // reset time
            chronometer.setBase(SystemClock.elapsedRealtime());
            // start time
            chronometer.start();
        }

        if (cardNumber == 0) {
            cardNumber = 1;
            firstCard = imageView;
            imageView.setEnabled(false);
        } else if (cardNumber == 1) {
            cardNumber = 2;
            secondCard = imageView;
            iv_11.setEnabled(false);
            iv_12.setEnabled(false);
            iv_13.setEnabled(false);
            iv_14.setEnabled(false);
            iv_21.setEnabled(false);
            iv_22.setEnabled(false);
            iv_23.setEnabled(false);
            iv_24.setEnabled(false);
            iv_31.setEnabled(false);
            iv_32.setEnabled(false);
            iv_33.setEnabled(false);
            iv_34.setEnabled(false);
        }
    }

    private void calculate() {
        String firstCardTag = (String) firstCard.getTag();
        String secondCardTag = (String) secondCard.getTag();
        if (firstCardTag.equals(secondCardTag)) {
            playSFX("match_found");
            firstCard.setVisibility(View.VISIBLE);
            secondCard.setVisibility(View.VISIBLE);
            playerPoints++;
            tv_p1.setText(playerPoints + " of 6 matches");
        } else {
            playSFX("match_not_found");
            final ObjectAnimator oa1 = ObjectAnimator.ofFloat(firstCard, "scaleX", 1f, 0f);
            final ObjectAnimator oa2 = ObjectAnimator.ofFloat(firstCard, "scaleX", 0f, 1f);
            final ObjectAnimator oa3 = ObjectAnimator.ofFloat(secondCard, "scaleX", 1f, 0f);
            final ObjectAnimator oa4 = ObjectAnimator.ofFloat(secondCard, "scaleX", 0f, 1f);

            oa1.setInterpolator(new DecelerateInterpolator());
            oa2.setInterpolator(new AccelerateDecelerateInterpolator());
            oa3.setInterpolator(new DecelerateInterpolator());
            oa4.setInterpolator(new AccelerateDecelerateInterpolator());

            oa1.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    firstCard.setImageResource(R.drawable.ic_back);
                    oa2.start();
                }
            });
            oa1.start();

            oa3.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    secondCard.setImageResource(R.drawable.ic_gray);
                    oa4.start();
                }
            });
            oa3.start();
            //firstCard.setImageResource(R.drawable.ic_back);
           // secondCard.setImageResource(R.drawable.ic_back);
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                cardNumber = 0;
                iv_11.setEnabled(true);
                iv_12.setEnabled(true);
                iv_13.setEnabled(true);
                iv_14.setEnabled(true);
                iv_21.setEnabled(true);
                iv_22.setEnabled(true);
                iv_23.setEnabled(true);
                iv_24.setEnabled(true);
                iv_31.setEnabled(true);
                iv_32.setEnabled(true);
                iv_33.setEnabled(true);
                iv_34.setEnabled(true);
            }
        }, 1000);
    }

    private void checkEnd() {
        if (playerPoints == 6) {
            chronometer.stop();

            int min = Integer.parseInt(chronometer.getText().toString().split(":")[0]);
            int sec = Integer.parseInt(chronometer.getText().toString().split(":")[1]);
            int totalTime  = min * 60 + sec;

            lbPref = getSharedPreferences("leaderboard",MODE_PRIVATE);
            currUser = getSharedPreferences("currUser",MODE_PRIVATE);
            String username = currUser.getString("username",null);
            SharedPreferences.Editor editor = lbPref.edit();
            editor.putString("user"+getNextLargestNum(),username);
            editor.putInt("userTime"+getNextLargestNum(),totalTime);
            editor.commit();

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(GamePlayActivity.this);
            interruptBGMPlayer("stop");

            alertDialogBuilder
                    .setMessage("GAME OVER!")
                    .setCancelable(false)
                    .setPositiveButton("NEW", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getApplicationContext(), GamePlayActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    })
                    .setNegativeButton("EXIT", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            playSFX("game_win");
        }
    }

    @Override
    public void onChronometerTick(Chronometer chronometer) {
        String time = chronometer.getText().toString();
        if (time.equals("00:00")) {
            Toast.makeText(this, "Time starts!", Toast.LENGTH_SHORT).show();
        }
    }
    public int getNextLargestNum(){
        while(lbPref.contains("user"+i)){
            i++;
        }
        return i;
    }

    protected void startBGMPlayer() {

        if (bgmplayer == null) {
            // play BGM
            bgmplayer = MediaPlayer.create(this, R.raw.background_music);
            bgmplayer.start();
            bgmplayer.setLooping(true);
        }
        else {
            bgmplayer.start();
        }
    }

    protected void playSFX(String gameState) {
        int resId = -1;

        if (sfxplayer != null) {
            resetSFXPlayer();
        }

        switch (gameState) {
            case "match_not_found":
                sfxplayer = MediaPlayer.create(this, R.raw.bonk_sound_effect);
                break;
            case "match_found":
                sfxplayer = MediaPlayer.create(this, R.raw.success_sound_effect);
                break;
            case "game_win":
                sfxplayer = MediaPlayer.create(this, R.raw.win_sound_effect);
                break;
        }
        sfxplayer.start();
    }

    protected void resetSFXPlayer() {
        if (sfxplayer != null) {
            sfxplayer.stop();
            sfxplayer.release();
            sfxplayer = null;
        }
    }

    protected void interruptBGMPlayer(String prompt) {
        if (bgmplayer != null) {
            if (prompt.equalsIgnoreCase("pause")) {
                bgmplayer.pause();
            }
            else {
                bgmplayer.stop();
                bgmplayer.release();
                bgmplayer = null;
            }
        }
    }
}