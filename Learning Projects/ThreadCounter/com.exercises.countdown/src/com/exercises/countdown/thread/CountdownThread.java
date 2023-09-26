package com.exercises.countdown.thread;

import com.exercises.countdown.Countdown;

public class CountdownThread extends Thread {

    private Countdown threadCountdown;

    public CountdownThread(Countdown countdown) {
        threadCountdown = countdown;
    }


    @Override
    public void run() {
        threadCountdown.doCountdown();
    }
}
