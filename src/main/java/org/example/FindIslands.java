package org.example;

import java.util.ArrayList;

public class FindIslands {

    public static ArrayList<Island> discoverIsland(ArrayList<Cell> cellsList) {
        ArrayList<Island> islands = new ArrayList<>();

        for (int i = 0; i < cellsList.size(); i++) {
            Cell cell = cellsList.get(i);
            if (!cell.isVisited()) {
                cell.setVisited();
                if (cell.isLand()) {
                    // if is land, check for neighbors to see if they already are part of an Island
                    // to be an Island it must have been visited and contain land

                    // initial state is not found = -1
                    int islandId = -1;

                    // check left
                    islandId = checkNeighborsIsland(islands, cell.getX() - 1, cell.getY());
                    // check top if not found previously
                    if (islandId == -1) {
                        islandId = checkNeighborsIsland(islands, cell.getX(), cell.getY() + 1);
                    }
                    // check right if not found previously
                    if (islandId == -1) {
                        islandId = checkNeighborsIsland(islands, cell.getX() + 1, cell.getY());
                    }
                    // check bottom if not found previously
                    if (islandId == -1) {
                        islandId = checkNeighborsIsland(islands, cell.getX(), cell.getY() - 1);
                    }

                    if (islandId == -1) {
                        Island island = new Island();
                        island.addCell(cell);
                        islands.add(island);
                    } else {
                        islands.get(islandId).addCell(cell);
                    }

                }
            }
        }

        System.out.println("Input for cellsList:" + cellsList);
        System.out.println("Output Islands found:" + islands);
        return islands;
    }

    private static int checkNeighborsIsland(ArrayList<Island> islands, int x, int y) {
        for (int i = 0; i < islands.size(); i++) {
            if (islands.get(i).hasCell(x, y)) {
                return islands.get(i).getId();
            }
        }
        return -1;
    }

    public static int getRightLimit(ArrayList<Cell> cellsList) {
        return cellsList.stream().mapToInt(c -> c.getY()).max().orElse(0);
    }

    public static int getIslandIdForExistingCell(ArrayList<Island> islands, Cell cell) {
        for (int i = 0; i < islands.size(); i++) {
            if (islands.get(i).hasCell(cell)) {
                return islands.get(i).getId();
            }
        }
        return -1;
    }


    public static int getCellFrom(ArrayList<Island> islands, Cell cell) {
        for (int i = 0; i < islands.size(); i++) {
            if (islands.get(i).hasCell(cell)) {
                return islands.get(i).getId();
            }
        }
        return -1;
    }

    public static Cell findCellByCoordinates(ArrayList<Cell> cellsList, int x, int y) {
        return cellsList.stream().filter(cell -> cell.getX() == x).filter(cell -> cell.getY() == y).findFirst().orElse(null);
    }

}
