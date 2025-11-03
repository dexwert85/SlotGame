package com.example.slotgame;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.color.utilities.Score;

public class MainActivity extends AppCompatActivity {
    private TextView[] tvN;
    private int[] n;
    private int num, score;
    public static int gameCount = 0, cor = 0;
    private TextView  tvWheel, tvScore;
    private Button btnStartStop, btnNewGame, btnScore;
    private boolean isSpining = false;
    private Handler handler;
    private  Runnable runnable;
    private boolean[] found;
    private Intent scoreIn;
    private boolean tries = false;
    private int tryCount = 0;

    private void startSpining() {
        handler.post(runnable);
    }

    private void StopSpining() {
        handler.removeCallbacks(runnable);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvN = new TextView[6];
        tvN[0] = findViewById(R.id.n1);
        tvN[1] = findViewById(R.id.n2);
        tvN[2] = findViewById(R.id.n3);
        tvN[3] = findViewById(R.id.n4);
        tvN[4] = findViewById(R.id.n5);
        tvN[5] = findViewById(R.id.n6);

        tvWheel = findViewById(R.id.wheel);
        tvScore = findViewById(R.id.score);

        btnStartStop = findViewById(R.id.start_stop);
        btnNewGame = findViewById(R.id.new_game);
        btnScore = findViewById(R.id.ScoreActivityBtn);

        scoreIn = new Intent(MainActivity.this, ScoreActivity.class);

        handler = new Handler(Looper.getMainLooper());
        runnable = new Runnable() {
            @Override
            public void run() {
                num = (int)(Math.random() * (39)) + 1;
                tvWheel.setText(num + "");
                handler.postDelayed(runnable, 100);
            }
        };

        found = new boolean[6];
        for (int i = 0; i < found.length; i++) {
            found[i] = false;
        }

        n = new int[6];
        for (int i = 0; i < n.length; i++) {
            n[i] = (int)(Math.random() * (39)) + 1;
            tvN[i].setText(n[i] + "");
        }

        btnStartStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!tries) {
                    if (!isSpining) {
                        btnStartStop.setText("Stop");
                        btnStartStop.setBackgroundColor(Color.RED);
                        startSpining();
                    } else {
                        btnStartStop.setText("Start");
                        btnStartStop.setBackgroundColor(Color.GREEN);
                        StopSpining();
                        num = Integer.parseInt(tvWheel.getText().toString());
                        for (int i = 0; i < n.length; i++) {
                            if (num == n[i]) {
                                if (!found[i]) {
                                    score++;
                                    cor++;
                                    tvN[i].setBackgroundColor(Color.RED);
                                    tvScore.setText(score + " of 6");
                                    found[i] = true;
                                }
                            }
                        }
                    }

                    isSpining = !isSpining;
                    tryCount++;
                    if (tryCount == 12) {
                        tries = true;
                    }
                }
            }
        });

        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < n.length; i++) {
                    n[i] = (int)(Math.random() * (39)) + 1;
                    tvN[i].setText(n[i] + "");
                }

                score = 0;
                tvScore.setText(score + " of 6");
                tvWheel.setText("0");
                gameCount++;
                tries = false;
                tryCount = 0;
            }
        });

        btnScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(scoreIn);
            }
        });
    }
}