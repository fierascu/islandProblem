package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Pattern;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Frame {

    public static final char CHAR_FOUND_TOKEN = '¹';
    public static final char CHAR_NOT_FOUND_TOKEN = '°';
    public static final char CHAR_FOR_ISLAND = '1';

    public static String ISLAND_DEMO_VALUE = "11110\n" +
            "11010\n" +
            "11000\n" +
            "00000";

    private static Pattern PATTERN_FOR_LETTERS_AND_NUMBERS = Pattern.compile("^[a-zA-Z0-9]+$");

    private Timer refreshUiTimer;


    Frame() {
        JFrame jFrame = new JFrame("Island Problem");
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.setSize(400, 500);
        jFrame.setVisible(true);
        jFrame.setLayout(new BorderLayout());

        JLabel headerLabel = new JLabel("Enter Island Metrics");
        jFrame.add(headerLabel, BorderLayout.NORTH);

        final JTextArea textArea = new JTextArea(ISLAND_DEMO_VALUE);
        textArea.setLineWrap(true);
        Font font = new Font("monospaced", Font.BOLD, 20);
        textArea.setFont(font);
        textArea.setForeground(Color.DARK_GRAY);
        jFrame.add(textArea, BorderLayout.CENTER);


        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            textArea.setText(ISLAND_DEMO_VALUE);
            if (refreshUiTimer != null && refreshUiTimer.isRunning()) {
                refreshUiTimer.stop();
            }
        });

        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(e -> computeIslands(textArea));

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BorderLayout());
        buttonsPanel.add(resetButton, BorderLayout.EAST);
        buttonsPanel.add(solveButton, BorderLayout.WEST);

        jFrame.add(buttonsPanel, BorderLayout.SOUTH);

        solveButton.requestFocus();
    }

    private void computeIslands(final JTextArea textArea) {
        AtomicInteger currentPosition = new AtomicInteger();

        refreshUiTimer = new Timer(500, e -> {
            String initialText = textArea.getText();

            if (currentPosition.get() < initialText.length()) {
                char charAtZero = initialText.charAt(currentPosition.get());
                char charToReplace;

                if (PATTERN_FOR_LETTERS_AND_NUMBERS.matcher("" + charAtZero).matches()) {
                    if (charAtZero == CHAR_FOR_ISLAND) {
                        charToReplace = CHAR_FOUND_TOKEN;
                    } else {
                        charToReplace = CHAR_NOT_FOUND_TOKEN;
                    }
                    StringBuilder replacedInitialText = new StringBuilder(initialText);
                    replacedInitialText.setCharAt(currentPosition.get(), charToReplace);
                    textArea.setText(replacedInitialText.toString());
                }
                System.out.println("initialText = " + initialText);
                System.out.println("currentPosition = " + currentPosition.get());

                if (currentPosition.get() >= textArea.getText().length()) {
                    refreshUiTimer.stop();
                } else {
                    currentPosition.getAndIncrement();
                }
            }
        });
        refreshUiTimer.start();
    }
}

