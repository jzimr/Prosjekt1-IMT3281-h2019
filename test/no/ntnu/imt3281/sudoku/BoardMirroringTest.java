package no.ntnu.imt3281.sudoku;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class BoardMirroringTest {
    int[] testBoardNums;

    @Before
    public void init(){
        testBoardNums = new int[]{
                5, 3, -1, -1, 7, -1, -1, -1, -1,
                6, -1, -1, 1, 9, 5, -1, -1, -1,
                -1, 9, 8, -1, -1, -1, -1, 6, -1,
                8, -1, -1, -1, 6, -1, -1, -1, 3,
                4, -1, -1, 8, -1, 3, -1, -1, 1,
                7, -1, -1, -1, 2, -1, -1, -1, 6,
                -1, 6, -1, -1, -1, -1, 2, 8, -1,
                -1, -1, -1, 4, 1, 9, -1, -1, 5,
                -1, -1, -1, -1, 8, -1, -1, 7, 9};
    }

    @Test
    public void testMirrorLeftRight(){
        BoardMirroring boardMirroring = new BoardMirroring();
        int[] mirroredLeftRight = new int[]{
                -1, -1, -1, -1, 7, -1, -1, 3, 5,
                -1, -1, -1, 5, 9, 1, -1, -1, 6,
                -1, 6, -1, -1, -1, -1, 8, 9, -1,
                3, -1, -1, -1, 6, -1, -1, -1, 8,
                1, -1, -1, 3, -1, 8, -1, -1, 4,
                6, -1, -1, -1, 2, -1, -1, -1, 7,
                -1, 8, 2, -1, -1, -1, -1, 6, -1,
                5, -1, -1, 9, 1, 4, -1, -1, -1,
                9, 7, -1, -1, 8, -1, -1, -1, -1
        };
        // mirror the board
        boardMirroring.mirrorLeftRight(testBoardNums);

        assertTrue(Arrays.equals(mirroredLeftRight, testBoardNums));
    }

    @Test
    public void testMirrorTopDown(){
        BoardMirroring boardMirroring = new BoardMirroring();
        int[] mirroredTopDown = new int[]{
                -1, -1, -1, -1, 8, -1, -1, 7, 9,
                -1, -1, -1, 4, 1, 9, -1, -1, 5,
                -1, 6, -1, -1, -1, -1, 2, 8, -1,
                7, -1, -1, -1, 2, -1, -1, -1, 6,
                4, -1, -1, 8, -1, 3, -1, -1, 1,
                8, -1, -1, -1, 6, -1, -1, -1, 3,
                -1, 9, 8, -1, -1, -1, -1, 6, -1,
                6, -1, -1, 1, 9, 5, -1, -1, -1,
                5, 3, -1, -1, 7, -1, -1, -1, -1,
        };
        // mirror the board
        boardMirroring.mirrorTopDown(testBoardNums);

        assertTrue(Arrays.equals(mirroredTopDown, testBoardNums));
    }

    @Test
    public void testMirrorDiagonallyRed(){
        BoardMirroring boardMirroring = new BoardMirroring();
        int[] mirroredDiagonallyRed = new int[]{
                9, 5, -1, 6, 1, 3, -1, -1, -1,
                7, -1, 8, -1, -1, -1, 6, -1, -1,
                -1, -1, 2, -1, -1, -1, -1, -1, -1,
                -1, 9, -1, -1, 3, -1, -1, 5, -1,
                8, 1, -1, 2, -1, 6, -1, 9, 7,
                -1, 4, -1, -1, 8, -1, -1, 1, -1,
                -1, -1, -1, -1, -1, -1, 8, -1, -1,
                -1, -1, 6, -1, -1, -1, 9, -1, 3,
                -1, -1, -1, 7, 4, 8, -1, 6, 5
        };
        // mirror the board
        boardMirroring.mirrorDiagonallyRed(testBoardNums);

        assertTrue(Arrays.equals(mirroredDiagonallyRed, testBoardNums));
    }

    @Test
    public void testMirrorDiagonallyBlue(){
        BoardMirroring boardMirroring = new BoardMirroring();
        int[] mirroredDiagonallyBlue = new int[]{
                5, 6, -1, 8, 4, 7, -1, -1, -1,
                3, -1, 9, -1, -1, -1, 6, -1, -1,
                -1, -1, 8, -1, -1, -1, -1, -1, -1,
                -1, 1, -1, -1, 8, -1, -1, 4, -1,
                7, 9, -1, 6, -1, 2, -1, 1, 8,
                -1, 5, -1, -1, 3, -1, -1, 9, -1,
                -1, -1, -1, -1, -1, -1, 2, -1, -1,
                -1, -1, 6, -1, -1, -1, 8, -1, 7,
                -1, -1, -1, 3, 1, 6, -1, 5, 9
        };
        // mirror the board
        boardMirroring.mirrorDiagonallyBlue(testBoardNums);

        assertTrue(Arrays.equals(mirroredDiagonallyBlue, testBoardNums));
    }


}