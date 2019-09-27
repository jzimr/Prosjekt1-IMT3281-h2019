package no.ntnu.imt3281.sudoku;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class RowIteratorTest {
    int[] testBoardNums;

    @Before
    public void init(){
        testBoardNums = new int[]{5, 3, -1, -1, 7, -1, -1, -1, -1, 6, -1, -1, 1, 9, 5, -1,
                -1, -1, -1, 9, 8, -1, -1, -1, -1, 6, -1, 8, -1, -1, -1, 6, -1, -1, -1, 3, 4, -1,
                -1, 8, -1, 3, -1, -1, 1, 7, -1, -1, -1, 2, -1, -1, -1, 6, -1, 6, -1, -1, -1, -1,
                2, 8, -1, -1, -1, -1, 4, 1, 9, -1, -1, 5, -1, -1, -1, -1, 8, -1, -1, 7, 9};
    }

    @Test
    public void testRowIterator_Success() {
        // checking if creation of class passes without errors
        Iterator testIterator = new RowIterator(testBoardNums, 2).iterator();
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testRowIterator_InvalidBoard() {
        // has length of 82, instead of 81
        int[] testBoardNumsWrong = new int[]{5, 3, -1, -1, 7, -1, -1, -1, -1, 6, -1, -1, 1, 9, 5, -1,
                -1, -1, -1, 9, 8, -1, -1, -1, -1, 6, -1, 8, -1, -1, -1, 6, -1, -1, -1, 3, 4, -1,
                -1, 8, -1, 3, -1, -1, 1, 7, -1, -1, -1, 2, -1, -1, -1, 6, -1, 6, -1, -1, -1, -1,
                2, 8, -1, -1, -1, -1, 4, 1, 9, -1, -1, 5, -1, -1, -1, -1, 8, -1, -1, 7, 9, 1};

        // test for invalid board length
        Iterator testIterator = new RowIterator(testBoardNumsWrong, 2).iterator();
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testRowIterator_InvalidBoardPosition1() {
        // has length of 82, instead of 81
        int[] testBoardNumsWrong = new int[]{5, 3, -1, -1, 7, -1, -1, -1, -1, 6, -1, -1, 1, 9, 5, -1,
                -1, -1, -1, 9, 8, -1, -1, -1, -1, 6, -1, 8, -1, -1, -1, 6, -1, -1, -1, 3, 4, -1,
                -1, 8, -1, 3, -1, -1, 1, 7, -1, -1, -1, 2, -1, -1, -1, 6, -1, 6, -1, -1, -1, -1,
                2, 8, -1, -1, -1, -1, 4, 1, 9, -1, -1, 5, -1, -1, -1, -1, 8, -1, -1, 7, 9, 1};

        // test for invalid boardPosition < 0
        Iterator testIterator = new RowIterator(testBoardNums, -1).iterator();
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testRowIterator_InvalidBoardPosition2() {
        // has length of 82, instead of 81
        int[] testBoardNumsWrong = new int[]{5, 3, -1, -1, 7, -1, -1, -1, -1, 6, -1, -1, 1, 9, 5, -1,
                -1, -1, -1, 9, 8, -1, -1, -1, -1, 6, -1, 8, -1, -1, -1, 6, -1, -1, -1, 3, 4, -1,
                -1, 8, -1, 3, -1, -1, 1, 7, -1, -1, -1, 2, -1, -1, -1, 6, -1, 6, -1, -1, -1, -1,
                2, 8, -1, -1, -1, -1, 4, 1, 9, -1, -1, 5, -1, -1, -1, -1, 8, -1, -1, 7, 9, 1};

        // test for invalid boardPosition > 80
        Iterator testIterator = new RowIterator(testBoardNums, 81).iterator();
    }

    @Test
    public void testHasNext(){
        Iterator testIterator = new RowIterator(testBoardNums, 4).iterator();

        // iterator has next until end of array
        for(int i = 0; i < 9; i++){
            assertTrue(testIterator.hasNext());
            testIterator.next();
        }

        // iterator does not have next anymore
        assertFalse(testIterator.hasNext());
    }

    @Test
    public void testNext_Success(){
        // boardPosition 55 is row index 6
        Iterator<Integer> testIterator = new RowIterator(testBoardNums, 55).iterator();
        int itValue;
        int[] row6 = new int[]{-1, 6, -1, -1, -1, -1, 2, 8, -1};

        // check if values  from next() matches from row in actual board
        for(int i = 0; i < 9; i++){
            itValue = testIterator.next();
            assertEquals(row6[i], itValue);
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void testNext_NoSuchElement(){
        Iterator<Integer> testIterator = new RowIterator(testBoardNums, 55).iterator();

        // loop 1 too many
        for(int i = 0; i < 10; i++){
            testIterator.next();
        }
    }
}