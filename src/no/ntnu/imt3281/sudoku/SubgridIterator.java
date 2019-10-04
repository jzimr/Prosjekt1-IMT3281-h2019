package no.ntnu.imt3281.sudoku;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implements an iterator that iterates through a sub-grid in the board
 */
public class SubgridIterator implements Iterable<Integer> {
    private final int[] subgridItems;

    /**
     * SubgridIterator
     * <p>
     *     Takes a Sudoku board and the specified sub-grid  [0-8] and create a new array that
     *     holds all values of the sub-grid .
     * </p>
     * @param board must be of length 81
     * @param boardPosition the position of an item in the board
     */
    public SubgridIterator(final int[] board, final int boardPosition){
        if(board.length != 81)
            throw new ArrayIndexOutOfBoundsException("Board must be of length 81");
        if(boardPosition > 80 || boardPosition < 0)
            throw new ArrayIndexOutOfBoundsException("boardPosition must be between 0 and 80");

        int[] newSubgrid = new int[9];
        int counter = 0;

        // check for sub-grid
        int row = boardPosition / 9;    // which row? (0-8)
        int column = boardPosition % 9; // which column? (0-8)
        int subgridIndex = ((row/3)*3)*9 + (column/3)*3;

        // loop through whole sub-grid and add to array
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                newSubgrid[counter++] = board[subgridIndex+(i*9)+j];
            }
        }
        subgridItems = newSubgrid;
    }

    /**
     * returns a new MySubgridIterator
     * @return an iterator object
     */
    @Override
    public Iterator<Integer> iterator() {
        return new MySubgridIterator();
    }

    /**
     * Goes through sub-grid  left to right
     */
    private class MySubgridIterator implements Iterator<Integer> {
        private int counter = 0;

        /**
         * Check if reached end of sub-grid
         * @return reached end of sub-grid
         */
        @Override
        public boolean hasNext() {
            return counter < subgridItems.length;
        }

        /**
         * Get the next sub-grid  element, moving iterator +1
         * @return the sub-grid  element
         */
        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return subgridItems[counter++];
        }
    }
}

