package no.ntnu.imt3281.sudoku;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implements an iterator that iterates through a column in the board
 */
public class ColumnIterator implements Iterable<Integer> {
    private final int[] columnItems;

    /**
     * ColuumnIterator
     * <p>
     *     Takes a Sudoku board and the specified column [0-8] and create a new array that
     *     holds all values of the column.
     * </p>
     * @param board must be of length 81
     * @param boardPosition the position of an item in the board
     */
    public ColumnIterator(final int[] board, final int boardPosition){
        if(board.length != 81)
            throw new ArrayIndexOutOfBoundsException("Board must be of length 81");
        if(boardPosition > 80 || boardPosition < 0)
            throw new ArrayIndexOutOfBoundsException("boardPosition must be between 0 and 80");

        int[] newColumn = new int[9];
        int counter = 0;

        // check for column
        int columnIndex = boardPosition % 9; // (0-8)
        for(int i = columnIndex; i < 81; i += 9){
            newColumn[counter++] = board[i];
        }
        columnItems = newColumn;
    }

    /**
     * returns a new MyColumnIterator
     * @return an iterator object
     */
    @Override
    public Iterator<Integer> iterator() {
        return new MyColumnIterator();
    }

    /**
     * Goes through column left to right
     */
    private class MyColumnIterator implements Iterator<Integer> {
        private int counter = 0;

        /**
         * Check if reached end of column
         * @return reached end of column
         */
        @Override
        public boolean hasNext() {
            return counter < columnItems.length;
        }

        /**
         * Get the next column element, moving iterator +1
         * @return the column element
         */
        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return columnItems[counter++];
        }
    }
}

