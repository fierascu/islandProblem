package org.example;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

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
        assertTrue(true);
    }
}
