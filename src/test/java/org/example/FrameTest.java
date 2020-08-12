package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.example.Constants.ISLAND_DEMO_VALUE;
import static org.example.Constants.ISLAND_DEMO_VALUE_SIMPLE_ROW;
import static org.example.FindIslands.discoverIsland;
import static org.example.Frame.extractArrays;
import static org.example.Frame.getCells;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void testVisitCells() {
        String row = "1101101";
        ArrayList<Cell> cellsList = getCells(row);
        cellsList.forEach(e -> e.setVisited());
        System.out.println(cellsList);
    }

    @Test
    void testDiscoverIslandsRow() {
        ArrayList<Cell> cellsList = getCells(ISLAND_DEMO_VALUE_SIMPLE_ROW);
        System.out.println(cellsList);

        ArrayList<Island> islands = discoverIsland(cellsList);
        int cellsWithLand = islands.stream().mapToInt(e -> (int) e.getCells().stream().count()).sum();

        assertEquals(5, cellsWithLand);
        assertEquals(3, islands.size());
        System.out.println(islands);
    }

    @Test
    void testDiscoverIslandsMatrix1() {
        ArrayList<Cell> cellsList = getCells(ISLAND_DEMO_VALUE);
        System.out.println(cellsList);


        int cellsWithLand = (int) cellsList.stream().filter(c -> c.isLand()).count();
        System.out.println(cellsList);

        assertEquals(9, cellsWithLand);

        ArrayList<Island> islands = discoverIsland(cellsList);
        int cellsWithLandFromIslands = islands.stream().mapToInt(e -> (int) e.getCells().stream().count()).sum();


        assertEquals(9, cellsWithLandFromIslands);
        assertEquals(1, islands.size());
        System.out.println(islands);
    }

}