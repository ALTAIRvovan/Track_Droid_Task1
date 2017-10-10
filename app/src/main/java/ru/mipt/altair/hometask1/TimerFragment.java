package ru.mipt.altair.hometask1;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;


public class TimerFragment extends Fragment {

    private static final String FRAGMENT_TAG = "Timer fragment";

    protected CountDownTimer timer = null;

    private static final String IS_STOPPED_BUNDLE_TAG = "IS_STOPPED_BUNDLE_TAG";
    protected boolean isStopped = true;

    private static final String LAST_TICK_BUNDLE_TAG = "LAST_TICK_BUNDLE_TAG";
    protected long lastTick;

    protected long TIMER_STEP = 0;
    protected long TIMER_DURATION = 0;

    protected long duration;

    public TimerFragment() {
        // Required empty public constructor
    }

    private void updateDurationByLastTick() {
        if (lastTick > 0) {
            duration = lastTick;
        } else {
            duration = TIMER_DURATION;
        }
    }

    protected void createNewTimer() {
        timer = new CountDownTimer(duration, TIMER_STEP) {
            @Override
            public void onTick(long l) {
                onTimerTick(l);
            }

            @Override
            public void onFinish() {
                onTimerFinish();
            }
        };
        Log.d(FRAGMENT_TAG, "Timer created. Duration: " + duration + ", step: " + TIMER_STEP);
    }

    protected void loadDataFromBundle(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            lastTick = savedInstanceState.getLong(LAST_TICK_BUNDLE_TAG);
            isStopped = savedInstanceState.getBoolean(IS_STOPPED_BUNDLE_TAG);
            if (!isStopped) {
                updateDurationByLastTick();
            }
        } else {
            duration = TIMER_DURATION;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        loadDataFromBundle(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (!isStopped) {
            timer.start();
        }
    }

    public void startTimer() {
        Log.d(FRAGMENT_TAG, "Before start timer");
        if (timer != null && isStopped) {
            timer.start();
            Log.d(FRAGMENT_TAG, "Timer started");
        }
        isStopped = false;
    }

    @Override
    public void onPause() {
        super.onPause();

        timer.cancel();

        updateDurationByLastTick();
        createNewTimer();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadDataFromBundle(savedInstanceState);
        createNewTimer();
    }

    public void stopTimer() {
        if (timer != null && !isStopped) {
            timer.cancel();
            isStopped = true;
        }
    }

    @Override
    public void onDetach() {
        timer.cancel();
        isStopped = true;
        super.onDetach();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putLong(LAST_TICK_BUNDLE_TAG, lastTick);
        outState.putBoolean(IS_STOPPED_BUNDLE_TAG, isStopped);
    }

    protected void onTimerTick(long l) {
        Log.d(FRAGMENT_TAG, "timer ticked: " + l);
        lastTick = l;
    }

    protected void onTimerFinish() {
        Log.d(FRAGMENT_TAG, "timer finished");
    }
}
