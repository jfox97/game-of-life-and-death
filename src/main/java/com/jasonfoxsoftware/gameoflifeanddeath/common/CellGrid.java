package com.jasonfoxsoftware.gameoflifeanddeath.common;

import java.util.HashMap;

public class CellGrid {

    private int gridWidth;
    private int gridHeight;

    private HashMap<String, Cell> aliveCells;

    public CellGrid(int gridWidth, int gridHeight) {
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;

        aliveCells = new HashMap<String, Cell>(gridHeight * gridWidth);
    }
}
