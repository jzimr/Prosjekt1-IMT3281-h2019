package no.ntnu.imt3281.sudoku;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implements an iterator that iterates through a row in the board
 */
public class RowIterator implements Iterable<Integer> {
    private final int[] rowItems;

    /**
     * RowIterator
     * <p>
     *     Takes a Sudoku board and the specified row [0-8] and create a new array that
     *     holds all values of the row.
     * </p>
     * @param board must be of length 81
     * @param boardPosition the position of an item in the board
     */
    public RowIterator(final int[] board, final int boardPosition){
        if(board.length != 81)
            throw new ArrayIndexOutOfBoundsException("Board must be of length 81");
        if(boardPosition > 80 || boardPosition < 0)
            throw new ArrayIndexOutOfBoundsException("boardPosition must be between 0 and 80");


        int[] newRow = new int[9];
        int counter = 0;

        int rowIndex = boardPosition / 9;    // (0-8)
        for(int i = rowIndex * 9; i < rowIndex*9+9; i++){
            newRow[counter++] = board[i];
        }
        rowItems = newRow;
    }

    /**
     * returns a new MyRowIterator
     * @return an iterator object
     */
    @Override
    public Iterator<Integer> iterator() {
        return new MyRowIterator();
    }

    /**
     * Goes through row left to right
     */
    private class MyRowIterator implements Iterator<Integer> {
        private int counter = 0;

        /**
         * Check if reached end of row
         * @return reached end of row
         */
        @Override
        public boolean hasNext() {
            return counter < rowItems.length;
        }

        /**
         * Get the next row element, moving iterator +1
         * @return the row element
         */
        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return rowItems[counter++];
        }
    }
}
