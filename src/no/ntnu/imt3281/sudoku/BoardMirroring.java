package no.ntnu.imt3281.sudoku;

import java.util.Iterator;

/**
 * Includes functions for mirroring a board vertically, horizontally and diagonally
 */
public class BoardMirroring {

    /**
     * Mirrors the board horizontally.
     * <p>
     *     First column is switched with last column,
     *     second column is switch with next-last column, etc.
     * </p>
     * @param board Sudoku board array
     */
    public void mirrorLeftRight(int[] board){
        Iterator<Integer> itLeft;
        Iterator<Integer> itRight;

        // since total board columns is an odd number we can only
        // switch 4 columns
        for(int i = 0; i < 4; i++){
            itLeft = new ColumnIterator(board, i).iterator();
            itRight = new ColumnIterator(board, 8-i).iterator();
            int counterLeft = i;
            int counterRight = 8-i;

            // go through each element in both columns and swap
            while(itLeft.hasNext() || itRight.hasNext()){
                board[counterLeft] = itRight.next();
                board[counterRight] = itLeft.next();
                counterLeft += 9;
                counterRight += 9;
            }
        }
    }

    /**
     * Mirrors the board vertically.
     * <p>
     *     First row is switched with last row,
     *     second row is switch with next-last row, etc.
     * </p>
     * @param board Sudoku board array
     */
    public void mirrorTopDown(int[] board){
        Iterator<Integer> itTop;
        Iterator<Integer> itBottom;

        // since total board rows is an odd number we can only
        // switch 4 rows
        for(int i = 0; i < 4; i++) {
            itTop = new RowIterator(board, i*9).iterator();
            itBottom = new RowIterator(board, 72 - 9*i).iterator();
            int counterTop = i*9;
            int counterBottom = 72-9*i;

            // go through each element in both rows and swap
            while (itTop.hasNext() || itBottom.hasNext()) {
                board[counterTop] = itBottom.next();
                board[counterBottom] = itTop.next();
                counterTop++;
                counterBottom++;
            }
        }
    }

    /**
     * Mirrors the board diagonally top left to bottom right.
     * <p>
     *     Element[0][0] is switched with Element[8][8],
     *     ELement[0][1] is switched with ELement[7][8], etc.
     * </p>
     * @param board Sudoku board array
     */
    public void mirrorDiagonallyRed(int[] board){
        int elementsCanSwitch = 8;    // elements we can switch in one loop run

        // the elements in which the red line strike through
        // cannot be swapped. Therefore we can only loop
        // 8 times.
        for(int i = 0; i < 8; i++){
            int topLeft = 9 * i;            // moves horizontally
            int bottomRight = 80 - i;       // moves vertically
            int tempValue;

            // loop through actual boxes we can switch on each
            // side
            for(int j = 0; j < elementsCanSwitch; j++){
                tempValue = board[topLeft];
                board[topLeft] = board[bottomRight];
                board[bottomRight] = tempValue;

                // move position
                topLeft++;
                bottomRight -= 9;
            }

            // for each run, we can switch one less element
            elementsCanSwitch--;
        }
    }

    /**
     * Mirrors the board diagonally top right to bottom left
     * <p>
     *     Element[0][1] is switched with Element[1][0],
     *     ELement[0][2] is switched with ELement[2][0], etc.
     * </p>
     * @param board Sudoku board array
     */
    public void mirrorDiagonallyBlue(int[] board){
        int elementsCanSwitch = 8;    // elements we can switch in one loop run

        // the elements in which the red line strike through
        // cannot be swapped. Therefore we can only loop
        // 8 times.
        for(int i = 0; i < 8; i++){
            int topRight = 1 + 9 * i + i;       // moves horizontally
            int bottomLeft = 9 + 9 * i + i;     // moves vertically
            int tempValue;

            // loop through actual boxes we can switch on each
            // side
            for(int j = 0; j < elementsCanSwitch; j++){
                tempValue = board[topRight];
                board[topRight] = board[bottomLeft];
                board[bottomLeft] = tempValue;

                // move position
                topRight++;
                bottomLeft += 9;
            }

            // for each run, we can switch one less element
            elementsCanSwitch--;
        }
    }
}