package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.example.FindIslands.*;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FindIslandsTest {

    ArrayList<Cell> cellsList = new ArrayList<>();

    @BeforeEach
    void addData() {
        cellsList.clear();
        cellsList.add(new Cell(0, 0, 1));
        cellsList.add(new Cell(0, 1, 1));
        cellsList.add(new Cell(0, 2, 0));
        cellsList.add(new Cell(1, 0, 1));
        cellsList.add(new Cell(1, 1, 0));
        cellsList.add(new Cell(1, 2, 1));
    }

    @Test
    void testFindCellByCoordinates() {
        Cell findNotExisting = findCellByCoordinates(cellsList, 20, 20);
        assertNull(findNotExisting);

        Cell expectedCell = new Cell(0, 0, 1);
        Cell find01 = findCellByCoordinates(cellsList, 0, 0);

        assertEquals(expectedCell, find01);
    }

    @Test
    void testGetRightLimit() {
        int rightLimit = getRightLimit(cellsList);
        assertEquals(2, rightLimit);
    }

    @Test
    void testGetIslandIdForExistingCell() {
        ArrayList<Island> islands = new ArrayList<>();

        Island island1 = new Island();
        island1.addCell(new Cell(0, 0, 1));
        island1.addCell(new Cell(0, 1, 1));
        islands.add(island1);

        Island island2 = new Island();
        island1.addCell(new Cell(1, 0, 1));
        islands.add(island2);

        Island island3 = new Island();
        island3.addCell(new Cell(1, 2, 1));
        islands.add(island3);

        int island1int = getIslandIdForExistingCell(islands, new Cell(0, 0, 1));
        assertEquals(0, island1int);

        int island3int = getIslandIdForExistingCell(islands, new Cell(1, 2, 1));
        assertEquals(2, island3int);

        int islandNotFound = getIslandIdForExistingCell(islands, new Cell(7, 5, 1));
        assertEquals(-1, islandNotFound);
    }

}