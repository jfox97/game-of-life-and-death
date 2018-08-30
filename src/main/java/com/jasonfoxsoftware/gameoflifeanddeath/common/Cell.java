package com.jasonfoxsoftware.gameoflifeanddeath.common;

public class Cell
{

    public int x;
    public int y;

    public Cell (int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public Cell ()
    {
        this(0, 0);
    }

    public String toString()
    {
        return String.format("{%d, %d}", x, y);
    }

    public boolean equals(Object o)
    {

        if (o == this)
            return true;

        if (o instanceof Cell == false)
            return false;

        Cell cell = (Cell) o;

        return x == cell.x && y == cell.y;
    }
}
