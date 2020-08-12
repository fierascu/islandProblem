package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IslandTest {

    @Test
    void testHasCell() {
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

        boolean island1hasCel = island1.hasCell(0, 0);
        assertTrue(island1hasCel);

        boolean island2NotHasCel = island2.hasCell(0, 0);
        assertFalse(island2NotHasCel);

        boolean island3hasCel = island3.hasCell(1, 2);
        assertTrue(island3hasCel);

        boolean islandNotHasCel = island1.hasCell(5, 5);
        assertFalse(islandNotHasCel);
    }
}