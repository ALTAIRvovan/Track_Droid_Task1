package ru.mipt.altair.hometask1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private static final String ACTIVITY_TAG = "Second activity";

    private static final String TIMER_TAG = "timer";

    private SecondTimerFragment timerFragment;

    private Button button = null;
    public TextView timerText = null;

    public void updateButtonText() {
        if (timerFragment.isStopped) {
            button.setText(R.string.start_timer_btn);
        } else {
            button.setText(R.string.stop_timer_btn);
        }
    }

    public void stopTimer() {
        Log.d(ACTIVITY_TAG, "Timer stop");
        timerFragment.stopTimer();
        updateButtonText();
    }

    public void startTimer() {
        Log.d(ACTIVITY_TAG, "Timer start");
        timerFragment.startTimer();
        updateButtonText();
    }

    public void onButtonPressed() {
        if (timerFragment.isStopped) {
            startTimer();
        } else {
            stopTimer();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        button = (Button) findViewById(R.id.timer_btn);
        timerText = (TextView) findViewById(R.id.timer_text);

        if (savedInstanceState == null) {
            timerFragment = new SecondTimerFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.second_activity, timerFragment, TIMER_TAG)
                    .commit();
            Log.d(ACTIVITY_TAG, "New fragment added");
        } else {
            timerFragment = (SecondTimerFragment) getSupportFragmentManager().findFragmentByTag(TIMER_TAG);
            Log.d(ACTIVITY_TAG, "Fragment loaded by tag");
        }

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                onButtonPressed();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateButtonText();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }
}
