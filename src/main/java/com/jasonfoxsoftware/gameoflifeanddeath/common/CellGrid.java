package com.jasonfoxsoftware.gameoflifeanddeath.common;

import java.util.HashMap;

public class CellGrid
{

    private int gridWidth;
    private int gridHeight;

    private HashMap<String, Cell> aliveCells;

    public CellGrid()
    {
        this(100, 100);
    }

    public CellGrid(int gridWidth, int gridHeight)
    {
        if (gridWidth < 1 || gridHeight < 1)
            throw new InvalidCoordinateException("Grid width or grid height cannot be set to a number < 1");

        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;

        aliveCells = new HashMap<String, Cell>(gridHeight * gridWidth);
    }

    public int getGridWidth()
    {
        return gridWidth;
    }

    public int getGridHeight()
    {
        return gridHeight;
    }

    public void setGridDimensions(int gridWidth, int gridHeight)
    {
        if (gridWidth < 1 || gridHeight < 1)
            throw new InvalidCoordinateException("Grid width or grid height cannot be set to a number < 1");

        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
    }

    public boolean birthCell(int x, int y)
    {
        Cell cell = new Cell(x, y);

        if (x > gridWidth || y > gridHeight)
            throw new CoordinateOutOfBoundsException(
                String.format("Cell %s contains one or more values outside of the range of {%d, %d}",
                    cell.toString(),
                    gridWidth,
                    gridHeight)
                );

        if (x < 0 || y < 0)
            throw new InvalidCoordinateException(
                String.format("Cell %s contains one or more negative values", cell.toString())
            );

        if (aliveCells.containsKey(cell.toString()))
            return false;

        aliveCells.put(cell.toString(), cell);
        return true;
    }

    public boolean killCell(int x, int y)
    {
        Cell cell = new Cell(x, y);

        if (x > gridWidth || y > gridHeight)
            throw new CoordinateOutOfBoundsException(
                    String.format("Cell %s contains one or more values outside of the range of {%d, %d}",
                            cell.toString(),
                            gridWidth,
                            gridHeight)
            );

        if (x < 0 || y < 0)
            throw new InvalidCoordinateException(
                    String.format("Cell %s contains one or more negative values", cell.toString())
            );

        if (aliveCells.containsKey(cell.toString()) == false)
            return false;

        aliveCells.remove(cell.toString());
        return true;
    }
}
