package org.example;

import java.util.ArrayList;

public class FindIslands {

    public static ArrayList<Island> discoverIsland(ArrayList<Cell> cellsList) {

        int leftLimit = 0;
        int rightLimit = cellsList.stream().mapToInt(c -> c.getY()).max().orElse(0);

        ArrayList<Island> islands = new ArrayList<>();

        for (int i = 0; i < cellsList.size(); i++) {
            Cell cell = cellsList.get(i);
            if (!cell.isVisited()) {
                cell.setVisited();

                if (cell.isLand()) {
                    if (i == 0) {
                        islands = addIsland(islands, cell, cell);
                    } else {

                        // look left for island
                        Cell leftCell = new Cell(cell.getX(), cell.getY(), cell.getValue());
                    }


                }
            }
        }

        System.out.println("Input for cellsList:" + cellsList);
        System.out.println("Output Islands found:" + islands);
        return islands;
    }

    private static ArrayList<Island> checkNeighbor(ArrayList<Island> islands, Cell cell, Cell prevCell) {
        if (prevCell == null || prevCell.isLand() && prevCell.isVisited()) {
            islands = addIsland(islands, cell, prevCell);
        }
        return islands;
    }

    private static ArrayList<Island> addIsland(ArrayList<Island> islands, Cell cell, Cell prevCell) {
        int index = getIslandIdForExistingCell(islands, prevCell);
        Island island;
        if (index > -1) {
            island = islands.get(index);
            island.addCell(cell);
        } else {
            island = new Island();
            island.addCell(cell);
            islands.add(island);
        }
        return islands;
    }

    public static int getIslandIdForExistingCell(ArrayList<Island> islands, Cell cell) {
        for (int i = 0; i < islands.size(); i++) {
            if (islands.get(i).hasCell(cell)) {
                return islands.get(i).getId();
            }
        }
        return -1;
    }

}
