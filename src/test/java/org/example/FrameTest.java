package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.example.Frame.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class FrameTest {

    @Test
    public void testExtractArraysOneRow() {
        String[] expectedFirstRow = {"1", "1", "0", "1", "1", "0", "1"};
        String[][] expectedArrayFirstRow = {expectedFirstRow};
        String[][] resultSimpleRow = extractArrays(ISLAND_DEMO_VALUE_SIMPLE_ROW);
        assertArrayEquals(expectedArrayFirstRow, resultSimpleRow);
    }

    @Test
    public void testExtractArraysMultipleRows() {
        String[][] expectedArrayMultipleRows = {{"1", "1", "1", "1", "0"}, {"1", "1", "0", "1", "0"},
                {"1", "1", "0", "0", "0"}, {"0", "0", "0", "0", "0"}};
        String[][] resultMultipleRows = extractArrays(ISLAND_DEMO_VALUE);
        assertArrayEquals(expectedArrayMultipleRows, resultMultipleRows);
    }

    @Test
    void testGetCells() {
        String row = "1101101";
        ArrayList<Cell> cellsList = getCells(row);
        System.out.println(cellsList);
    }
}