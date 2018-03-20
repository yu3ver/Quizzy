package com.example.android.quizzy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startQuiz(View view) {
        Intent intentNext = new Intent(this, RadioButtonActivity.class);
        intentNext.putExtra("quizScore", 0);
        intentNext.putExtra("questionNumber", 0);
        startActivity(intentNext);
    }
}