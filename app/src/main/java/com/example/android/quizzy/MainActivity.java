package com.example.android.quizzy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Fing Start button
        Button start = findViewById(R.id.button_bottom);

        // Assign listener to Start button
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Go to next question when button clicked. Take along quizScore and questionNumber.
                Intent intentNext = new Intent(MainActivity.this, RadioButtonActivity.class);
                intentNext.putExtra("quizScore", 0);
                intentNext.putExtra("questionNumber", 0);
                startActivity(intentNext);
            }
        });
    }
}