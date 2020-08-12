package island.recursion;

import island.swing.AppTest;
import org.junit.jupiter.api.Test;

import static island.swing.AppTest.TEST_DATA_1;
import static org.junit.jupiter.api.Assertions.assertEquals;

class IslandsRecursionTest {

    @Test
    void testNumIslands() {

        int numIslands = IslandsRecursion.numIslands(TEST_DATA_1);
        System.out.println("No of Islands: " + numIslands);
        assertEquals(1, numIslands);

        int numIslandsTestData2 = IslandsRecursion.numIslands(AppTest.TEST_DATA_2);
        assertEquals(3, numIslandsTestData2);
        System.out.println("No of Islands: " + numIslandsTestData2);
    }
}