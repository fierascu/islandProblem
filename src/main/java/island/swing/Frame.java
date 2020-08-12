package island.swing;

import island.common.Constants;
import island.common.Util;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static island.common.Util.setReplacedCharacter;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class Frame {

    private Timer refreshUiTimer;

    private final JTextArea textArea;

    public Frame() {
        JFrame jFrame = new JFrame("Island Problem");
        jFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        jFrame.setSize(400, 500);
        jFrame.setVisible(true);
        jFrame.setLayout(new BorderLayout());

        JLabel headerLabel = new JLabel("Press Solve button");
        jFrame.add(headerLabel, BorderLayout.NORTH);

        textArea = new JTextArea(Constants.ISLAND_DEMO_VALUE_SIMPLE_ROW);
        textArea.setLineWrap(true);
        Font font = new Font("monospaced", Font.BOLD, 20);
        textArea.setFont(font);
        textArea.setForeground(Color.DARK_GRAY);
        jFrame.add(textArea, BorderLayout.CENTER);


        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            textArea.setText(Constants.ISLAND_DEMO_VALUE_SIMPLE_ROW);
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

    public String getTextAreaText() {
        if (textArea != null) {
            return textArea.getText();
        } else {
            return "";
        }
    }

    public void setTextAreaText(String text) {
        if (textArea != null) {
            textArea.setText(text);
        }
    }

    private void computeIslands(final JTextArea textArea, JLabel headerLabel) {
        AtomicInteger currentPosition = new AtomicInteger();
        AtomicInteger timeElapsed = new AtomicInteger();
        AtomicInteger islandFound = new AtomicInteger();
        AtomicBoolean isLand = new AtomicBoolean(false);
        Integer lineLength = 4;

        refreshUiTimer = new Timer(Constants.DELAY, e -> {
            String initialText = textArea.getText();

            boolean isPrevNeighborCharAnIsland = false;

            if (currentPosition.get() < initialText.length()) {
                char charAtZero = initialText.charAt(currentPosition.get());

                if (Util.isAlphaNumericCharacter(charAtZero)) {
                    char charToReplace = Util.getCharToReplace(charAtZero);
                    textArea.setText(
                            setReplacedCharacter(initialText, charToReplace, currentPosition.get())
                    );

                    if (charToReplace == Constants.CHAR_FOUND_TOKEN) {


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
                        timeElapsed.get() > Constants.HARD_STOP_VALUE) {
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

    public static class IslandAnimationSwingApp {
        public static void main(String[] args) {
            SwingUtilities.invokeLater(Frame::new);
        }
    }
}

