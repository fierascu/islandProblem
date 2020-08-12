package island.recursionPojo;

import island.common.Constants;
import island.pojo.Cell;
import island.pojo.Island;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static island.common.Util.getCells;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FindIslandsTest {

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
    void testDiscoverIslandsRow() {
        ArrayList<Cell> cellsList = getCells(Constants.ISLAND_DEMO_VALUE_SIMPLE_ROW);
        System.out.println(cellsList);
        assertEquals(7, cellsList.size());

        ArrayList<Island> islands = FindIslands.discoverIsland(cellsList);
        assertEquals(3, islands.size());

        int cellsWithLandCount = islands.stream().mapToInt(e -> (int) e.getCells().stream().count()).sum();
        assertEquals(5, cellsWithLandCount);
        System.out.println(islands);
    }

    @Test
    void testDiscoverIslandsMatrix1() {
        ArrayList<Cell> cellsList = getCells(Constants.ISLAND_DEMO_VALUE);
        System.out.println(cellsList);

        int cellsWithLandCount = (int) cellsList.stream().filter(c -> c.isLand()).count();
        System.out.println(cellsList);
        assertEquals(9, cellsWithLandCount);

        ArrayList<Island> islands = FindIslands.discoverIsland(cellsList);
        int cellsWithLandFromIslandsCount = islands.stream().mapToInt(e -> (int) e.getCells().stream().count()).sum();

        assertEquals(9, cellsWithLandFromIslandsCount);
        assertEquals(1, islands.size());
        System.out.println(islands);
    }

}