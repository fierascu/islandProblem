package island.pojo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.UUID;

import static island.common.Util.getIslandIdForExistingCell;
import static org.junit.jupiter.api.Assertions.*;

class IslandTest {

    private static final ArrayList<Island> islands = new ArrayList<>();

    @BeforeAll
    static void setUpIslands() {
        islands.clear();
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
    }

    @Test
    void testHasCell() {

        boolean island1hasCel = islands.get(0).hasCell(0, 0);
        assertTrue(island1hasCel);

        boolean island2NotHasCel = islands.get(1).hasCell(0, 0);
        assertFalse(island2NotHasCel);

        boolean island3hasCel = islands.get(2).hasCell(1, 2);
        assertTrue(island3hasCel);

        boolean islandNotHasCel = islands.get(1).hasCell(5, 5);
        assertFalse(islandNotHasCel);

    }

    @Test
    void testIslandUUID() {

        UUID island1int = getIslandIdForExistingCell(islands, new Cell(0, 0, 1));
        assertNotNull(island1int);

        UUID island3int = getIslandIdForExistingCell(islands, new Cell(1, 2, 1));
        assertNotNull(island3int);

        UUID islandNotFound = getIslandIdForExistingCell(islands, new Cell(7, 5, 1));
        assertNull(islandNotFound);
    }

}