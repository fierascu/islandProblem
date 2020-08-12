package island.swing;

import island.common.Constants;
import island.common.Util;
import island.pojo.Cell;
import island.pojo.Island;
import island.recursionPojo.FindIslands;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static island.common.Util.getCells;
import static island.common.Util.setReplacedCharacter;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Frame {

    private final JFrame jFrame;
    private final JButton resetButton;
    private final JButton solveButton;
    private final JTextArea jTextArea;
    private Timer refreshUiTimer;
    private final JLabel headerLabel;

    public Frame() {
        // frame initialisation
        jFrame = new JFrame("Island Problem");
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.setSize(400, 500);
        jFrame.setVisible(true);
        jFrame.setLayout(new BorderLayout());

        // add label
        headerLabel = new JLabel();
        jFrame.add(headerLabel, BorderLayout.NORTH);

        // add testArea
        jTextArea = new JTextArea();
        jTextArea.setLineWrap(true);
        Font font = new Font("monospaced", Font.BOLD, 20);
        jTextArea.setFont(font);
        jTextArea.setForeground(Color.DARK_GRAY);
        jFrame.add(jTextArea, BorderLayout.CENTER);

        // create secondary panel SOUTH for buttons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BorderLayout());

        // add resetButton on secondary panel
        resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            setStatus(Constants.ISLAND_DEMO_VALUE, "Reseted");

        });
        buttonsPanel.add(resetButton, BorderLayout.EAST);

        // add solveButton on secondary panel
        solveButton = new JButton("Solve");
        solveButton.addActionListener(e -> computeIslands());
        buttonsPanel.add(solveButton, BorderLayout.WEST);

        // add secondary panel on main one
        jFrame.add(buttonsPanel, BorderLayout.SOUTH);

        // set focus on solve button
        solveButton.requestFocus();

        // populate with data
        setStatus(Constants.ISLAND_DEMO_VALUE, "Press Solve Button");
    }

    public String getTextAreaText() {
        if (jTextArea != null) {
            return jTextArea.getText();
        } else {
            return "";
        }
    }

    public void setTextAreaText(String text) {
        if (jTextArea != null) {
            jTextArea.setText(text);
        }
    }

    private void setStatus(String textArea, String textLabel) {
        jTextArea.setText(textArea);
        headerLabel.setText(textLabel);
    }

    private void computeIslands() {
        String initialText = jTextArea.getText();
        ArrayList<Cell> cellsList = getCells(initialText);
        System.out.println(cellsList);

        ArrayList<Island> islands = FindIslands.discoverIsland(cellsList);

        String resultText = "";
        for (int currentPosition = 0; currentPosition < initialText.length(); currentPosition++) {
            char charAtZero = initialText.charAt(currentPosition);

            if (Util.isAlphaNumericCharacter(charAtZero)) {
                char charToReplace = Util.getCharToReplace(charAtZero);
                resultText = setReplacedCharacter(initialText, charToReplace, currentPosition);
                setStatus(resultText, "Islands Found: " + islands.size());
            }

        }
        // for animation purposes, excuse the replace chars and string operations
        initialText = initialText.replaceAll("" + Constants.CHAR_FOR_LAND, "" + Constants.CHAR_FOUND_TOKEN);
        initialText = initialText.replaceAll("" + Constants.CHAR_FOR_WATTER, "" + Constants.CHAR_NOT_FOUND_TOKEN);

        setStatus(initialText, "Islands Found: " + islands.size());
    }

    public static class IslandAnimationSwingApp {
        public static void main(String[] args) {
            SwingUtilities.invokeLater(Frame::new);
        }
    }
}

