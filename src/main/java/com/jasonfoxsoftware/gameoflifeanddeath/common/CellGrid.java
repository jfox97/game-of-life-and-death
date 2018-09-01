package com.jasonfoxsoftware.gameoflifeanddeath.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CellGrid
{

    private int gridWidth;
    private int gridHeight;

    private HashMap<String, Cell> aliveCellsCurrent;
    private HashMap<String, Cell> aliveCellsNextTick;

    public CellGrid()
    {
        this(100, 100);
    }

    public CellGrid(int gridWidth, int gridHeight)
    {
        setGridDimensions(gridWidth, gridHeight);

        aliveCellsCurrent = new HashMap<String, Cell>(gridHeight * gridWidth);
        aliveCellsNextTick = new HashMap<String, Cell>(gridHeight * gridWidth);
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

    // Adds new alive cell to the "current tick" map
    public boolean birthCell(int x, int y)
    {
        return birthCell(x, y, aliveCellsCurrent);
    }

    public void tick()
    {
        for (Map.Entry<String, Cell> entry : aliveCellsCurrent.entrySet())
        {
            Cell liveCell = entry.getValue();

            moveLiveCellToNextTick(liveCell);
            moveDeadNeighborsToNextTick(liveCell);
        }

        aliveCellsCurrent = aliveCellsNextTick;
        aliveCellsNextTick = new HashMap<String, Cell>(gridHeight * gridWidth);
    }

    public Cell[] getLiveCells()
    {
        ArrayList<Cell> liveCells = new ArrayList<Cell>();
        for (Map.Entry<String, Cell> entry : aliveCellsCurrent.entrySet())
        {
            liveCells.add(entry.getValue());
        }

        return liveCells.toArray(new Cell[0]);
    }

    public void clear()
    {
        aliveCellsCurrent.clear();
        aliveCellsNextTick.clear();
    }

    // Adds new alive cell to the "next tick" map
    private boolean birthCellNextTick(Cell cell)
    {
        return birthCell(cell.x, cell.y, aliveCellsNextTick);
    }

    private boolean birthCell(int x, int y, HashMap<String, Cell> aliveCellsMap)
    {
        Cell cell = new Cell(x, y);

        if (x < 1 || y < 1)
            throw new InvalidCoordinateException();

        if (x > gridWidth || y > gridHeight)
            throw new CoordinateOutOfBoundsException();

        if (aliveCellsMap.containsKey(cell.toString()))
            return false;

        aliveCellsMap.put(cell.toString(), cell);
        return true;
    }

    private boolean propogateLiveCell(Cell cell)
    {
        if (aliveCellsCurrent.containsKey(cell.toString()) == false)
            throw new CellNotLivingException(
                String.format("Cell {%d, %d} is not alive and cannot be passed to next tick"));

        if (aliveCellsNextTick.containsKey(cell.toString()))
            return false;

        aliveCellsNextTick.put(cell.toString(), cell);
        return true;
    }

    private int countLiveNeighbors(Cell cell)
    {
        int liveNeighborCount = 0;
        for (int x = cell.x - 1; x <= cell.x + 1; x++)
        {
            for (int y = cell.y - 1; y <= cell.y + 1; y++)
            {
                if (x == cell.x && y == cell.y)
                    continue;

                Cell neighborCell = new Cell(x, y);
                if (aliveCellsCurrent.containsKey(neighborCell.toString()))
                    liveNeighborCount++;
            }
        }
        return liveNeighborCount;
    }

    // Keeps cell alive if it meets the living conditions
    private void moveLiveCellToNextTick(Cell cell)
    {
        int liveNeighborCount = countLiveNeighbors(cell);
        if (liveNeighborCount > 1 && liveNeighborCount < 4)
            propogateLiveCell(cell);
    }

    // Makes a dead cell become alive if it meets the conditions
    private void moveDeadCellToNextTick(Cell cell)
    {
        int liveNeighborCount = countLiveNeighbors(cell);
        if (liveNeighborCount == 3)
            birthCellNextTick(cell);
    }

    /* Iteratively makes each individual neighbor cell to the given cell alive
       if it meets the proper conditions */
    private void moveDeadNeighborsToNextTick(Cell cell)
    {
        for (int x = cell.x - 1; x <= cell.x + 1; x++)
        {
            for (int y = cell.y - 1; y <= cell.y + 1; y++)
            {
                if (x == cell.x && y == cell.y)
                    continue;

                Cell neighborCell = new Cell(x, y);
                moveDeadCellToNextTick(neighborCell);
            }
        }
    }
}
