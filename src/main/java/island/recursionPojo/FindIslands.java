package island.recursionPojo;

import island.pojo.Cell;
import island.pojo.Island;

import java.util.ArrayList;

import static island.common.Util.checkNeighborsIsland;

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

}
