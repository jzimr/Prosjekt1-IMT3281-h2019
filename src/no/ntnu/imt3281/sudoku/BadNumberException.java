package no.ntnu.imt3281.sudoku;

/**
 * Should be thrown if user entered a number in a field that already exist in
 * row, column or sub-grid
 */
public class BadNumberException extends Exception {
    public BadNumberException(String message){
        super(message);
    }
}
