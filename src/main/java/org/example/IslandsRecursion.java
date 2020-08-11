package org.example;

import static org.example.Constants.CHAR_FOR_LAND;
import static org.example.Constants.CHAR_FOR_WATTER;

/**
 * Use Depth-First Search
 * <p>
 * If all the nodes in the grid are connected then after DFS all the nodes will be visited and there will be only one
 * Island in the grid. If there are more islands in the grid we need to multiple DFS to visit them all.
 * So Number of Islands will be equal to the number of DFS required to visit all isLands (1’s)
 * <p>
 * Start the DFS from the node with value 1 and try all four directions (right, left, up and down) to find any
 * connected 1’s. Once DFS is completed, check if there is an unvisited node with value 1 exists in the given grid,
 * if yes then start another DFS from that node. Keep counting no of DFS’s, this will be our answer- Number of Islands.
 * src: https://algorithms.tutorialhorizon.com/number-of-islands/
 */
public class IslandsRecursion {

    public static int numIslands(char[][] islandGrid) {
        int h = islandGrid.length;
        int l = islandGrid[0].length;
        int islandCount = 0;

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < l; j++) {
                if (islandGrid[i][j] == CHAR_FOR_LAND) {
                    checkCell(islandGrid, i, j);
                    islandCount++;
                }
            }
        }
        return islandCount;
    }

    /**
     * DFS: Depth-First Search
     *
     * @param islandGrid
     * @param row
     * @param col
     */
    public static void checkCell(char[][] islandGrid, int row, int col) {
        if (isBetweenBounds(islandGrid, row, col)) {
            return;
        }

        islandGrid[row][col] = CHAR_FOR_WATTER; // marking it visited
        checkCell(islandGrid, row + 1, col); // right
        checkCell(islandGrid, row - 1, col); // left
        checkCell(islandGrid, row, col + 1); // down
        checkCell(islandGrid, row, col - 1); // up
    }

    public static boolean isBetweenBounds(char[][] islandGrid, int row, int col) {
        int h = islandGrid.length;

        if (h == 0) {
            return false;
        }

        int l = islandGrid[0].length;

        return row < 0 || col < 0 || row >= h || col >= l || islandGrid[row][col] != CHAR_FOR_LAND;
    }

}