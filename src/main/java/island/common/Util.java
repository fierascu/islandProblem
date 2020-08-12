package island.common;

import island.pojo.Cell;
import island.pojo.Island;

import java.util.ArrayList;
import java.util.UUID;

public class Util {
    public static UUID checkNeighborsIsland(ArrayList<Island> islands, int x, int y) {
        for (int i = 0; i < islands.size(); i++) {
            if (islands.get(i).hasCell(x, y)) {
                return islands.get(i).getUuid();
            }
        }
        return null;
    }

    public static UUID getIslandIdForExistingCell(ArrayList<Island> islands, Cell cell) {
        for (int i = 0; i < islands.size(); i++) {
            if (islands.get(i).hasCell(cell)) {
                return islands.get(i).getUuid();
            }
        }
        return null;
    }

    public static int getRightLimit(ArrayList<Cell> cellsList) {
        return cellsList.stream().mapToInt(c -> c.getY()).max().orElse(0);
    }

    public static Cell findCellByCoordinates(ArrayList<Cell> cellsList, int x, int y) {
        return cellsList.stream().filter(cell -> cell.getX() == x).filter(cell -> cell.getY() == y).findFirst().orElse(null);
    }

    public static String setReplacedCharacter(String initialText, char charToReplace, int currentPosition) {
        StringBuilder replacedInitialText = new StringBuilder(initialText);
        replacedInitialText.setCharAt(currentPosition, charToReplace);
        return replacedInitialText.toString();
    }

    public static char getCharToReplace(char charAtZero) {
        char charToReplace;
        if (charAtZero == Constants.CHAR_FOR_LAND) {
            charToReplace = Constants.CHAR_FOUND_TOKEN;
        } else {
            charToReplace = Constants.CHAR_NOT_FOUND_TOKEN;
        }
        return charToReplace;
    }

    public static String[][] extractArrays(String initialText) {
        ArrayList<ArrayList<String>> matrix = new ArrayList<>();

        String[] lines = initialText.split("\n");
        for (int rowIndex = 0; rowIndex < lines.length; rowIndex++) {
            ArrayList<String> row = new ArrayList<>();
            for (int colIndex = 0; colIndex < lines[rowIndex].length(); colIndex++) {
                String e = String.valueOf(lines[rowIndex].charAt(colIndex));
                row.add(e);
            }
            matrix.add(row);
        }

        int rowSize = matrix.size();
        int columnSize = matrix.get(0).size();

        String[][] arrays = new String[rowSize][columnSize];
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                arrays[i][j] = matrix.get(i).get(j);
            }
        }

        return arrays;
    }

    public static ArrayList<Cell> getCells(String initialString) {
        ArrayList<Cell> cells = new ArrayList<>();
        String[][] matrix = extractArrays(initialString);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                cells.add(new Cell(i, j, matrix[i][j]));
            }
        }
        return cells;
    }

    public static boolean isAlphaNumericCharacter(char charAtZero) {
        return Constants.PATTERN_FOR_LETTERS_AND_NUMBERS.matcher("" + charAtZero).matches();
    }


}
