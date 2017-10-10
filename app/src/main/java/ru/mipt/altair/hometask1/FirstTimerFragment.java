package ru.mipt.altair.hometask1;


import android.content.Intent;


public class FirstTimerFragment extends TimerFragment {

    public FirstTimerFragment() {
        // Required empty public constructor
        TIMER_STEP = 2000;
        TIMER_DURATION = 2000;
    }

    @Override
    protected void onTimerFinish() {
        super.onTimerFinish();

        final Intent intent = new Intent(getActivity(), SecondActivity.class);
        startActivity(intent);

        getActivity().supportFinishAfterTransition();
    }
}
