package com.jasonfoxsoftware.com.gameoflifeanddeat.common;

import com.jasonfoxsoftware.gameoflifeanddeath.common.Cell;
import com.jasonfoxsoftware.gameoflifeanddeath.common.CellGrid;
import com.jasonfoxsoftware.gameoflifeanddeath.common.CoordinateOutOfBoundsException;
import com.jasonfoxsoftware.gameoflifeanddeath.common.InvalidCoordinateException;
import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class CellGridTest
{
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void initCellGrid()
    {
        CellGrid testGrid = new CellGrid(1, 1);
        assertEquals(1, testGrid.getGridWidth());
        assertEquals(1, testGrid.getGridHeight());

        testGrid = new CellGrid();
        assertEquals(100, testGrid.getGridWidth());
        assertEquals(100, testGrid.getGridHeight());

        exception.expect(InvalidCoordinateException.class);
        testGrid = new CellGrid(0, 0);
        testGrid = new CellGrid(-1, -1);
    }

    @Test
    public void verifySetGridDimensions()
    {
        CellGrid testGrid = new CellGrid();

        testGrid.setGridDimensions(1, 1);
        assertEquals(1, testGrid.getGridWidth());
        assertEquals(1, testGrid.getGridHeight());
    }

    @Test
    public void setDimensionsShouldNotExceptValueLessThanOne()
    {
        CellGrid testGrid = new CellGrid();

        exception.expect(InvalidCoordinateException.class);
        testGrid.setGridDimensions(0, 5);
        testGrid.setGridDimensions(5,0);
    }

    @Test
    public void birthShouldNotAcceptInvalidCells()
    {
        CellGrid testGrid = new CellGrid(10, 10);

        testGrid.birthCell(7, 7);
        boolean cellAdded = testGrid.birthCell(7, 7);

        assertFalse(cellAdded);

        exception.expect(InvalidCoordinateException.class);
        testGrid.birthCell(0, 5);
        testGrid.birthCell(2,0);

        exception.expect(CoordinateOutOfBoundsException.class);
        testGrid.birthCell(11,4);
        testGrid.birthCell(3,11);
    }

    @Test
    public void clearShouldRemoveAllLiveCellsFromGrid()
    {
        CellGrid testGrid = new CellGrid(10, 10);

        testGrid.birthCell(2,5);
        testGrid.birthCell(6,2);
        testGrid.birthCell(2,7);
        testGrid.birthCell(1,1);
        testGrid.birthCell(10,10);

        testGrid.clear();
        Cell[] liveCells = testGrid.getLiveCells();

        assertEquals(0, liveCells.length);
    }

    @Test
    public void testOscillatingPatterns()
    {
        CellGrid testGrid = new CellGrid(5, 5);

        testGrid.birthCell(2, 3);
        testGrid.birthCell(3, 3);
        testGrid.birthCell(4, 3);

        testGrid.tick();
        Cell[] liveCells = testGrid.getLiveCells();

        assertEquals(3, liveCells.length);
        assertEquals(new Cell(3, 2), liveCells[0]);
        assertEquals(new Cell(3, 3), liveCells[1]);
        assertEquals(new Cell(3, 4), liveCells[2]);

        testGrid.tick();
        liveCells = testGrid.getLiveCells();

        assertEquals(3, liveCells.length);
        assertEquals(new Cell(4, 3), liveCells[0]);
        assertEquals(new Cell(2, 3), liveCells[1]);
        assertEquals(new Cell(3, 3), liveCells[2]);

        testGrid.clear();
        testGrid = new CellGrid(6,6);

        testGrid.birthCell(2,3);
        testGrid.birthCell(3,3);
        testGrid.birthCell(4,3);
        testGrid.birthCell(3,4);
        testGrid.birthCell(4,4);
        testGrid.birthCell(5,4);

        testGrid.tick();
        liveCells = testGrid.getLiveCells();

        assertEquals(new Cell(2, 3), liveCells[0]);
        assertEquals(new Cell(3, 2), liveCells[1]);
        assertEquals(new Cell(5, 3), liveCells[2]);
        assertEquals(new Cell(4, 5), liveCells[3]);
        assertEquals(new Cell(2, 4), liveCells[4]);
        assertEquals(new Cell(5, 4), liveCells[5]);

        testGrid.tick();
        liveCells = testGrid.getLiveCells();

        assertEquals(new Cell(4, 4), liveCells[0]);
        assertEquals(new Cell(2, 3), liveCells[1]);
        assertEquals(new Cell(3, 4), liveCells[2]);
        assertEquals(new Cell(4, 3), liveCells[3]);
        assertEquals(new Cell(3, 3), liveCells[4]);
        assertEquals(new Cell(5, 4), liveCells[5]);
    }
}
