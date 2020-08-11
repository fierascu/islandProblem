package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Frame {

    public static final char CHAR_FOUND_TOKEN = '¹';
    public static final char CHAR_NOT_FOUND_TOKEN = '°';
    public static final char CHAR_FOR_ISLAND = '1';
    public static final int HARD_STOP_VALUE = 100;
    public static final int DELAY = 200;

    public static String ISLAND_DEMO_VALUE = "11110\n" +
            "11010\n" +
            "11000\n" +
            "00000";

    public static String ISLAND_DEMO_VALUE_SIMPLE_ROW = "1101101";

    private static Pattern PATTERN_FOR_LETTERS_AND_NUMBERS = Pattern.compile("^[a-zA-Z0-9]+$");

    private Timer refreshUiTimer;


    Frame() {
        JFrame jFrame = new JFrame("Island Problem");
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.setSize(400, 500);
        jFrame.setVisible(true);
        jFrame.setLayout(new BorderLayout());

        JLabel headerLabel = new JLabel("Press Solve button");
        jFrame.add(headerLabel, BorderLayout.NORTH);

        final JTextArea textArea = new JTextArea(ISLAND_DEMO_VALUE_SIMPLE_ROW);
        textArea.setLineWrap(true);
        Font font = new Font("monospaced", Font.BOLD, 20);
        textArea.setFont(font);
        textArea.setForeground(Color.DARK_GRAY);
        jFrame.add(textArea, BorderLayout.CENTER);


        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            textArea.setText(ISLAND_DEMO_VALUE_SIMPLE_ROW);
            headerLabel.setText("RESETED");
            if (refreshUiTimer != null && refreshUiTimer.isRunning()) {
                refreshUiTimer.stop();
            }
        });

        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(e -> computeIslands(textArea, headerLabel));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BorderLayout());
        buttonsPanel.add(resetButton, BorderLayout.EAST);
        buttonsPanel.add(solveButton, BorderLayout.WEST);

        jFrame.add(buttonsPanel, BorderLayout.SOUTH);

        solveButton.requestFocus();
    }

    private static char getCharToReplace(char charAtZero) {
        char charToReplace;
        if (charAtZero == CHAR_FOR_ISLAND) {
            charToReplace = CHAR_FOUND_TOKEN;
        } else {
            charToReplace = CHAR_NOT_FOUND_TOKEN;
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

        String arrays[][] = new String[rowSize][columnSize];
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

    private void computeIslands(final JTextArea textArea, JLabel headerLabel) {
        AtomicInteger currentPosition = new AtomicInteger();
        AtomicInteger timeElapsed = new AtomicInteger();
        AtomicInteger islandFound = new AtomicInteger();
        AtomicBoolean isLand = new AtomicBoolean(false);
        Integer lineLength = 4;

        refreshUiTimer = new Timer(DELAY, e -> {
            String initialText = textArea.getText();

            boolean isPrevNeighborCharAnIsland = false;

            if (currentPosition.get() < initialText.length()) {
                char charAtZero = initialText.charAt(currentPosition.get());

                if (isAlphaNumericCharacter(charAtZero)) {
                    char charToReplace = getCharToReplace(charAtZero);
                    textArea.setText(
                            setReplacedCharacter(initialText, charToReplace, currentPosition.get())
                    );

                    if (charToReplace == CHAR_FOUND_TOKEN) {


                        // fist row, first column
                        if (!isPrevNeighborCharAnIsland && islandFound.get() == 0) {
                            isPrevNeighborCharAnIsland = true;
                            islandFound.getAndIncrement();
                        }


                        isLand.getAndSet(true);
                        if (isPrevNeighborCharAnIsland) {
                            islandFound.getAndIncrement();
                        }
                        headerLabel.setText(islandFound.get() + " islands found");

                        isPrevNeighborCharAnIsland = true;

                    } else {
                        isPrevNeighborCharAnIsland = false;
                    }
                } else {
                    // is end or start of line
                    isLand.getAndSet(false);
                }

                System.out.println("currentPosition = " + currentPosition.get());

                if (currentPosition.get() >= textArea.getText().length() ||
                        timeElapsed.get() > HARD_STOP_VALUE) {
                    refreshUiTimer.stop();
                    textArea.setCaretPosition(0);
                } else {
                    currentPosition.getAndIncrement();
                    timeElapsed.getAndIncrement();
                }
            }
        });
        refreshUiTimer.start();
    }

    private boolean isAlphaNumericCharacter(char charAtZero) {
        return PATTERN_FOR_LETTERS_AND_NUMBERS.matcher("" + charAtZero).matches();
    }

    private String setReplacedCharacter(String initialText, char charToReplace, int currentPosition) {
        StringBuilder replacedInitialText = new StringBuilder(initialText);
        replacedInitialText.setCharAt(currentPosition, charToReplace);
        return replacedInitialText.toString();
    }

}

