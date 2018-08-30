package com.jasonfoxsoftware.com.gameoflifeanddeat.common;

import com.jasonfoxsoftware.gameoflifeanddeath.common.Cell;
import org.junit.*;
import static org.junit.Assert.*;

public class CellTest
{

    @Test
    public void initCell() {

        Cell testCell = new Cell(50, 50);

        assertEquals(50, testCell.x);
        assertEquals(50, testCell.y);
        testCell.x = 90;
        testCell.y = 45;
        assertEquals(90,testCell.x);
        assertEquals(45,testCell.y);
    }

    @Test
    public void verifyToStringFormat()
    {
        Cell testCell = new Cell(50, 50);
        String testCellString = testCell.toString();
        assertEquals("{50, 50}", testCellString);
    }

    @Test
    public void verifyEqualsLogic()
    {
        Cell testCell1 = new Cell(50, 50);
        Cell testCell2 = testCell1;
        assertTrue(testCell1.equals(testCell2));

        testCell2 = new Cell(50, 50);
        assertTrue(testCell1.equals(testCell2));

        testCell1.x = 39;
        testCell1.y = 21;
        testCell2.x = 39;
        testCell2.y = 21;
        assertTrue(testCell1.equals(testCell2));

        testCell2.x = 93;
        assertFalse(testCell1.equals(testCell2));

        assertFalse(testCell1.equals(new Integer(6)));
    }
}
