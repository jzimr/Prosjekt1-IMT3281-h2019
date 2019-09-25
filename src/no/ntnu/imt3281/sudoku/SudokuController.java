package no.ntnu.imt3281.sudoku;

public class SudokuController {
    protected int[] boardNums = new int[81];
    protected boolean[] boardConst = new boolean[81];

    void numberPlacedIsLegit(int number, int boardPosition) throws BadNumberException {
        // do logic

        // if user did not place valid number
        // throw new BadNumberException("The number placed was not allowed");
    }

    String readFromFile(String path){
        // read from "path"

        // return the JSON string
        return "";
    }

    boolean readFromJson(String json){
        // read into board array

        // read not successful
        return false;
    }

    // Trenger egen Row, Column og grid interface som implementerer iterator
    // Bruker så "getRowIterator", "getColumnIterator" og "getGridIterator"
}
