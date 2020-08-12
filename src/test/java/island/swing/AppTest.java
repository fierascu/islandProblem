package island.swing;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AppTest {

    public static char[][] TEST_DATA_1 = new char[][]{
            {'1', '1', '1', '1', '0'},
            {'1', '1', '0', '1', '0'},
            {'1', '1', '0', '1', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '0', '0', '0'}};
    public static char[][] TEST_DATA_2 = new char[][]{
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}};

    @Test
    public void shouldAnswerWithTrue() {
        Frame frame = new Frame();
        String existingText = frame.getTextAreaText();
        assertEquals(7, existingText.length());

        frame.setTextAreaText("123");
        existingText = frame.getTextAreaText();
        assertEquals(3, existingText.length());
    }
}
