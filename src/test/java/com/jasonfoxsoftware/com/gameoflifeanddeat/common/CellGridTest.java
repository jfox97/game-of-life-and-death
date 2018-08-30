package com.jasonfoxsoftware.com.gameoflifeanddeat.common;

import com.jasonfoxsoftware.gameoflifeanddeath.common.CellGrid;
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
        testGrid.setGridDimensions(0, 0);
        testGrid.setGridDimensions(-1,-1);
    }
}
