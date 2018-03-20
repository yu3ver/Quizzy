package com.example.android.quizzy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SummaryActivity extends AppCompatActivity {

    private int quizScore;
    private int questionNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Bundle bundleExtras = getIntent().getExtras();
        if (bundleExtras != null) {
            quizScore = bundleExtras.getInt("quizScore");
            questionNumber = bundleExtras.getInt("questionNumber");
        }
    }

    public void getGrade(View view) {
        TextView summaryView = findViewById(R.id.banner_summary);
        String resultSummary = getString(R.string.send_result_you_got) + quizScore +
                getString(R.string.send_result_of) + questionNumber + getString(R.string.send_result_correct);
        resultSummary = resultSummary + getText(R.string.banner_summary_answers);
        summaryView.setText(resultSummary);

        View buttonView = findViewById(R.id.button_share);
        buttonView.setVisibility(View.VISIBLE);

        View answersView = findViewById(R.id.layout_summary_answers);
        answersView.setVisibility(View.VISIBLE);

        CharSequence quizResult = getText(R.string.toast_result) + String.valueOf(quizScore) +
                getText(R.string.send_result_of) + String.valueOf(questionNumber);
        toastIt(quizResult);
        setupRetryButton();
    }

    public void setupRetryButton() {
        Button buttonBottom = findViewById(R.id.button_bottom);
        buttonBottom.setText(R.string.button_retry);
        buttonBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentNext = new Intent(SummaryActivity.this, MainActivity.class);
                startActivity(intentNext);
            }
        });
    }

    private void toastIt(CharSequence toastMessage) {
        Toast toast = Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.BOTTOM, 0, 200);
        toast.show();
    }

    public void shareResult(View view) {
        String resultMessage = getString(R.string.send_result_i_got) + quizScore +
                getString(R.string.send_result_of) + questionNumber + getString(R.string.send_result_correct);
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, resultMessage);
        shareIntent.setType("text/plain");
        startActivity(shareIntent);
    }

    @Override
    public void onBackPressed() {
        Intent exitIntent = new Intent(Intent.ACTION_MAIN);
        exitIntent.addCategory(Intent.CATEGORY_HOME);
        exitIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(exitIntent);
    }
}
