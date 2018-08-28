package com.jasonfoxsoftware.gameoflifeanddeath.common;

public class Cell {

    public int x;
    public int y;

    public Cell (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Cell () {
        this(0, 0);
    }

    public String toString() {
        return String.format("{%d, %d}", x, y);
    }
}
