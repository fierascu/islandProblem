package org.example;

import org.junit.jupiter.api.Test;

import static org.example.AppTest.TEST_DATA_1;
import static org.example.IslandsRecursion.numIslands;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IslandsRecursionTest {

    @Test
    void testNumIslands() {

        int numIslands = numIslands(TEST_DATA_1);
        System.out.println("No of Islands: " + numIslands);
        assertEquals(1, numIslands);

        int numIslandsTestData2 = numIslands(AppTest.TEST_DATA_2);
        assertEquals(3, numIslandsTestData2);
        System.out.println("No of Islands: " + numIslandsTestData2);
    }
}