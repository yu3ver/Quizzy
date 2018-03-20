package com.example.android.quizzy;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

public class TrueFalseActivity extends AppCompatActivity {

    private int quizScore;
    private int questionNumber;
    private int progress = 0;
    private CountDownTimer mCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_true_false);

        Bundle bundleExtras = getIntent().getExtras();
        if (bundleExtras != null) {
            quizScore = bundleExtras.getInt("quizScore");
            questionNumber = bundleExtras.getInt("questionNumber");
        }
        startProgressBar();
    }

    private void startProgressBar() {
        final int timerSecs = 20;
        final ProgressBar mProgressBar;
        mProgressBar = findViewById(R.id.progressbar);
        mProgressBar.setProgress(progress);

        mCountDownTimer = new CountDownTimer(timerSecs * 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                progress++;
                mProgressBar.setProgress(progress * 100 / (timerSecs * 1000 / 1000));
            }

            @Override
            public void onFinish() {
                // Toast time expired and go to next question.
                mProgressBar.setProgress(100);
                toastIt(getText(R.string.toast_times_up));
                nextQuestion();
            }
        }.start();
    }

    public void submitAnswer(View view) {
        boolean correctAnswer;

        // All the radio buttons.
        RadioButton radioTrue = findViewById(R.id.radio_true);
        RadioButton radioFalse = findViewById(R.id.radio_false);

        // Current state of all the radio buttons.
        boolean radioTrueChecked = radioTrue.isChecked();
        boolean radioFalseChecked = radioFalse.isChecked();

        // Is any radio button checked?
        boolean checked = (radioTrueChecked || radioFalseChecked);

        // The correct answer is false.
        correctAnswer = radioFalseChecked;

        if (checked) {
            mCountDownTimer.cancel();
            if (correctAnswer) {
                quizScore++;
                toastIt(getText(R.string.toast_correct));

            } else {
                toastIt(getText(R.string.toast_incorrect));
            }
            nextQuestion();

        } else {
            toastIt(getText(R.string.toast_incomplete));
        }
    }

    private void nextQuestion() {
        questionNumber++;
        Intent intentNext = new Intent(this, SummaryActivity.class);
        intentNext.putExtra("quizScore", quizScore);
        intentNext.putExtra("questionNumber", questionNumber);
        startActivity(intentNext);
    }

    private void toastIt(CharSequence toastMessage) {
        Toast toast = Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 200);
        toast.show();
    }

    @Override
    public void onBackPressed() {
        toastIt(getText(R.string.toast_backbutton_disabled));
    }
}