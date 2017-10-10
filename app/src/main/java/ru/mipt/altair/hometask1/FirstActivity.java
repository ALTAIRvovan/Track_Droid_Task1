package ru.mipt.altair.hometask1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FirstActivity extends AppCompatActivity {

    private static final String TIMER_TAG = "timer";

    private TimerFragment timerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        if (savedInstanceState == null) {
            timerFragment = new FirstTimerFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.first_activity, timerFragment, TIMER_TAG)
                    .commit();
        } else {
            timerFragment = (TimerFragment) getSupportFragmentManager().findFragmentByTag(TIMER_TAG);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        timerFragment.startTimer();
    }
}
