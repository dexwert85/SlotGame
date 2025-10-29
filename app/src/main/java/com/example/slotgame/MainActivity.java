package com.example.slotgame;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private TextView[] tvN;
    private int[] n;
    private int num, score;
    private TextView  tvWheel, tvScore;
    private Button btnStartStop, btnNewGame;
    private boolean isSpining = false;

    private void startSpining() {
        isSpining = true;
    }

    private void StopSpining() {
        isSpining = false;
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

        n = new int[6];
        n[0] = (int)(Math.random() * (39)) + 1;
        n[1] = (int)(Math.random() * (39)) + 1;
        n[2] = (int)(Math.random() * (39)) + 1;
        n[3] = (int)(Math.random() * (39)) + 1;
        n[4] = (int)(Math.random() * (39)) + 1;
        n[5] = (int)(Math.random() * (39)) + 1;

        for (int i = 0; i < tvN.length; i++) {
            tvN[i].setText(n[i] + "");
        }

        btnStartStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isSpining) {
                    btnStartStop.setText("Stop");
                    btnStartStop.setBackgroundColor(Color.RED);
                    startSpining();
                } else {
                    btnStartStop.setText("Start");
                    btnStartStop.setBackgroundColor(Color.GREEN);
                    StopSpining();
                    num = Integer.parseInt(tvWheel.getText().toString());
                }
            }
        });
    }
}