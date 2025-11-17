package com.example.slotgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ScoreActivity extends AppCompatActivity {
    private TextView tvGames, tvScore, tvHello;
    private Button btnBack;
    private Intent intent;
    private Intent receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        tvHello = findViewById(R.id.hello);
        tvGames = findViewById(R.id.num_games);
        tvScore = findViewById(R.id.num_cor);
        btnBack = findViewById(R.id.back);
        intent = new Intent(ScoreActivity.this, MainActivity.class);
        receiver = getIntent();

        tvHello.setText("Hello " + receiver.getStringExtra("NAME"));

        tvGames.setText("Number of games: " + MainActivity.gameCount);
        tvScore.setText("Number of correct answers: " + MainActivity.cor);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });
    }
}