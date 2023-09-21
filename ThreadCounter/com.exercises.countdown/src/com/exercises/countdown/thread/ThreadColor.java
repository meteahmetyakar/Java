package com.exercises.countdown.thread;

public enum ThreadColor {
    ANSI_RESET("\u001B[0m"),
    ANSI_BLACK("\u001B[30m"),
    ANSI_RED("\u001B[31m"),
    ANSI_GREEN("\u001B[32m"),
    ANSI_BLUE("\u001B[34m"),
    ANSI_PURPLE("\u001B[35m"),
    ANSI_CYAN("\u001B[36m");

    private final String color;

    ThreadColor(String color)
    {
        this.color = color;
    }

    public String getColor()
    {
        return color;
    }

}
