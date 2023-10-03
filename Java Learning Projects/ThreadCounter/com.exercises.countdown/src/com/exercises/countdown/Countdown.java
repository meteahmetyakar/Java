package com.exercises.countdown;

import com.exercises.countdown.thread.ThreadColor;

public class Countdown {

    private int counter;

    //if we do not use synchronized keyword then race conditions occur
    public synchronized void doCountdown() {
        String color = switch (Thread.currentThread().getName()) {
            case "Thread 1" -> ThreadColor.ANSI_RED.getColor();
            case "Thread 2" -> ThreadColor.ANSI_PURPLE.getColor();
            default -> ThreadColor.ANSI_GREEN.getColor();
        };

        for (counter = 10; counter > 0; counter--)
            System.out.println(color + Thread.currentThread().getName() + ": i = " + counter);

    }
}
