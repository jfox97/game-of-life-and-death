package com.jasonfoxsoftware.com.gameoflifeanddeat.common;

import com.jasonfoxsoftware.gameoflifeanddeath.common.Cell;
import org.junit.*;
import static org.junit.Assert.*;

public class CellTest {

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
    public void verifyToStringFormat() {
        Cell testCell = new Cell(50, 50);
        String testCellString = testCell.toString();
        assertEquals("{50, 50}", testCellString);
    }
}
