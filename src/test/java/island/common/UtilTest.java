package island.common;

import island.pojo.Cell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static island.common.Util.*;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UtilTest {
    ArrayList<Cell> cells = new ArrayList<>();

    @BeforeEach
    void addDataToCellList() {
        cells.clear();
        cells.add(new Cell(0, 0, 1));
        cells.add(new Cell(0, 1, 1));
        cells.add(new Cell(0, 2, 0));
        cells.add(new Cell(1, 0, 1));
        cells.add(new Cell(1, 1, 0));
        cells.add(new Cell(1, 2, 1));
    }

    @Test
    void testFindCellByCoordinates() {
        Cell findNotExisting = findCellByCoordinates(cells, 20, 20);
        assertNull(findNotExisting);

        Cell expectedCell = new Cell(0, 0, 1);
        Cell find01 = findCellByCoordinates(cells, 0, 0);

        assertEquals(expectedCell, find01);
    }

    @Test
    void testGetRightLimit() {
        int rightLimit = getRightLimit(cells);
        assertEquals(2, rightLimit);
    }

    @Test
    public void testExtractArraysOneRow() {
        String[] expectedFirstRow = {"1", "1", "0", "1", "1", "0", "1"};
        String[][] expectedArrayFirstRow = {expectedFirstRow};
        String[][] resultSimpleRow = extractArrays(Constants.ISLAND_DEMO_VALUE_SIMPLE_ROW);
        assertArrayEquals(expectedArrayFirstRow, resultSimpleRow);
    }

    @Test
    public void testExtractArraysMultipleRows() {
        String[][] expectedArrayMultipleRows = {{"1", "1", "1", "1", "0"}, {"1", "1", "0", "1", "0"},
                {"1", "1", "0", "0", "0"}, {"0", "0", "0", "0", "0"}};
        String[][] resultMultipleRows = extractArrays(Constants.ISLAND_DEMO_VALUE);
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
}