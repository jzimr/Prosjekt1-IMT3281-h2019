package no.ntnu.imt3281.sudoku;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

public class SudokuTest {

    final private String EXPECTED_EXCEPTION_TEXT = "java.io.FileNotFoundException: resources\\JSON\\NotFound.json (The system cannot find the file specified)";
    final private String EXPECTED_EXCEPTION_TEXT_ROW = "Value already exists in row";
    final private String EXPECTED_EXCEPTION_TEXT_COLUMN = "Value already exists in column";
    final private String EXPECTED_EXCEPTION_TEXT_LOCALBOX ="Value already exists in sub-grid";

    private Sudoku controllerTest;

    @Before
    public void init(){
        controllerTest = new Sudoku();
    }

    @Test
    public void testReadFromFile() throws IOException {
        String ret = controllerTest.readFromFile("resources/JSON/Board.json");
        assertFalse(ret.isEmpty());
    }

    @Test
    public void testReadFromFile_NotFound() {
        try {
            String ret = controllerTest.readFromFile("resources/JSON/NotFound.json");
        } catch (IOException e) {
            assertEquals("Exception found", EXPECTED_EXCEPTION_TEXT ,e.getMessage());
        }
    }

    @Test
    public void testReadFromJson(){
        int[] testBoardNums = new int[]{5, 3, -1, -1, 7, -1, -1, -1, -1, 6, -1, -1, 1, 9, 5, -1,
                -1, -1, -1, 9, 8, -1, -1, -1, -1, 6, -1, 8, -1, -1, -1, 6, -1, -1, -1, 3, 4, -1,
                -1, 8, -1, 3, -1, -1, 1, 7, -1, -1, -1, 2, -1, -1, -1, 6, -1, 6, -1, -1, -1, -1,
                2, 8, -1, -1, -1, -1, 4, 1, 9, -1, -1, 5, -1, -1, -1, -1, 8, -1, -1, 7, 9};

        String testJsonString = "[[5, 3, -1, -1, 7, -1, -1, -1, -1], [6, -1, -1, 1, 9, 5, -1, -1, -1],"
                + "[-1, 9, 8, -1, -1, -1, -1, 6, -1], [8, -1, -1, -1, 6, -1, -1, -1, 3],"
                +" [4, -1, -1, 8, -1, 3, -1, -1, 1], [7, -1, -1, -1, 2, -1, -1, -1, 6],"
                +" [-1, 6, -1, -1, -1, -1, 2, 8, -1], [-1, -1, -1, 4, 1, 9, -1, -1, 5], [-1, -1, -1, -1, 8, -1, -1, 7, 9]]";

        controllerTest.readFromJson(testJsonString);

        assertTrue(Arrays.equals(testBoardNums, controllerTest.boardNums));
    }

    @Test
    public void testValueExist_Success(){
        // TODO: write test if test value did NOT return any errors if input is legit
        String testJsonString = "[[5, 3, -1, -1, 7, -1, -1, -1, -1], [6, -1, -1, 1, 9, 5, -1, -1, -1],"
                + "[-1, 9, 8, -1, -1, -1, -1, 6, -1], [8, -1, -1, -1, 6, -1, -1, -1, 3],"
                +" [4, -1, -1, 8, -1, 3, -1, -1, 1], [7, -1, -1, -1, 2, -1, -1, -1, 6],"
                +" [-1, 6, -1, -1, -1, -1, 2, 8, -1], [-1, -1, -1, 4, 1, 9, -1, -1, 5], [-1, -1, -1, -1, 8, -1, -1, 7, 9]]";
        controllerTest.readFromJson(testJsonString);

        BadNumberException exception = new BadNumberException("");

        try {
            controllerTest.valueExists(8,8);
        } catch (BadNumberException e) {
            exception = new BadNumberException(e.getMessage());
        }

        assertEquals("", exception.getMessage());
    }

    @Test
    public void testValueExists_InvalidRow(){
        String testJsonString = "[[5, 3, -1, -1, 7, -1, -1, -1, -1], [6, -1, -1, 1, 9, 5, -1, -1, -1],"
                + "[-1, 9, 8, -1, -1, -1, -1, 6, -1], [8, -1, -1, -1, 6, -1, -1, -1, 3],"
                +" [4, -1, -1, 8, -1, 3, -1, -1, 1], [7, -1, -1, -1, 2, -1, -1, -1, 6],"
                +" [-1, 6, -1, -1, -1, -1, 2, 8, -1], [-1, -1, -1, 4, 1, 9, -1, -1, 5], [-1, -1, -1, -1, 8, -1, -1, 7, 9]]";
        controllerTest.readFromJson(testJsonString);

        BadNumberException exception = new BadNumberException("");

        try {
            controllerTest.valueExists(3,2);
        } catch (BadNumberException e) {
            exception = new BadNumberException(e.getMessage());
        }

        assertEquals("Exception found", EXPECTED_EXCEPTION_TEXT_ROW , exception.getMessage());

    }

    @Test
    public void testValueExists_InvalidColumn(){
        String testJsonString = "[[5, 3, -1, -1, 7, -1, -1, -1, -1], [6, -1, -1, 1, 9, 5, -1, -1, -1],"
                + "[-1, 9, 8, -1, -1, -1, -1, 6, -1], [8, -1, -1, -1, 6, -1, -1, -1, 3],"
                +" [4, -1, -1, 8, -1, 3, -1, -1, 1], [7, -1, -1, -1, 2, -1, -1, -1, 6],"
                +" [-1, 6, -1, -1, -1, -1, 2, 8, -1], [-1, -1, -1, 4, 1, 9, -1, -1, 5], [-1, -1, -1, -1, 8, -1, -1, 7, 9]]";
        controllerTest.readFromJson(testJsonString);

        BadNumberException exception = new BadNumberException("");

        try {
            controllerTest.valueExists(8,2);
        } catch (BadNumberException e) {
            exception = new BadNumberException(e.getMessage());
        }

        assertEquals("Exception found", EXPECTED_EXCEPTION_TEXT_COLUMN , exception.getMessage());

    }

    @Test
    public void testValueExists_InvalidLocalBox(){
        String testJsonString = "[[5, 3, -1, -1, 7, -1, -1, -1, -1], [6, -1, -1, 1, 9, 5, -1, -1, -1],"
                + "[-1, 9, 8, -1, -1, -1, -1, 6, -1], [8, -1, -1, -1, 6, -1, -1, -1, 3],"
                +" [4, -1, -1, 8, -1, 3, -1, -1, 1], [7, -1, -1, -1, 2, -1, -1, -1, 6],"
                +" [-1, 6, -1, -1, -1, -1, 2, 8, -1], [-1, -1, -1, 4, 1, 9, -1, -1, 5], [-1, -1, -1, -1, 8, -1, -1, 7, 9]]";
        controllerTest.readFromJson(testJsonString);

        BadNumberException exception = new BadNumberException("");

        try {
            controllerTest.valueExists(6,6);
        } catch (BadNumberException e) {
            exception = new BadNumberException(e.getMessage());
        }

        assertEquals("Exception found", EXPECTED_EXCEPTION_TEXT_LOCALBOX , exception.getMessage());

    }

    @Test
    public void testIsCellLocked_LockedCell(){
        String testJsonString = "[[5, 3, -1, -1, 7, -1, -1, -1, -1], [6, -1, -1, 1, 9, 5, -1, -1, -1],"
                + "[-1, 9, 8, -1, -1, -1, -1, 6, -1], [8, -1, -1, -1, 6, -1, -1, -1, 3],"
                +" [4, -1, -1, 8, -1, 3, -1, -1, 1], [7, -1, -1, -1, 2, -1, -1, -1, 6],"
                +" [-1, 6, -1, -1, -1, -1, 2, 8, -1], [-1, -1, -1, 4, 1, 9, -1, -1, 5], [-1, -1, -1, -1, 8, -1, -1, 7, 9]]";
        controllerTest.readFromJson(testJsonString);
        controllerTest.lockCurrentCells();

        boolean isLocked;

        isLocked = controllerTest.isCellLocked(0);

        assertTrue("Cell is locked. Wanted True got False",isLocked);

    }

    @Test
    public void testIsCellLocked_NotLockedCell(){
        String testJsonString = "[[5, 3, -1, -1, 7, -1, -1, -1, -1], [6, -1, -1, 1, 9, 5, -1, -1, -1],"
                + "[-1, 9, 8, -1, -1, -1, -1, 6, -1], [8, -1, -1, -1, 6, -1, -1, -1, 3],"
                +" [4, -1, -1, 8, -1, 3, -1, -1, 1], [7, -1, -1, -1, 2, -1, -1, -1, 6],"
                +" [-1, 6, -1, -1, -1, -1, 2, 8, -1], [-1, -1, -1, 4, 1, 9, -1, -1, 5], [-1, -1, -1, -1, 8, -1, -1, 7, 9]]";
        controllerTest.readFromJson(testJsonString);

        boolean isLocked;

        isLocked = controllerTest.isCellLocked(2);

        assertTrue("Cell is not locked. Wanted False got True",isLocked);

    }

    @Test
    public void testInsertNumber(){
        String testJsonString = "[[5, 3, -1, -1, 7, -1, -1, -1, -1], [6, -1, -1, 1, 9, 5, -1, -1, -1],"
                + "[-1, 9, 8, -1, -1, -1, -1, 6, -1], [8, -1, -1, -1, 6, -1, -1, -1, 3],"
                +" [4, -1, -1, 8, -1, 3, -1, -1, 1], [7, -1, -1, -1, 2, -1, -1, -1, 6],"
                +" [-1, 6, -1, -1, -1, -1, 2, 8, -1], [-1, -1, -1, 4, 1, 9, -1, -1, 5], [-1, -1, -1, -1, 8, -1, -1, 7, 9]]";
        controllerTest.readFromJson(testJsonString);
        Arrays.fill(controllerTest.boardValidPlacements, true);

        controllerTest.insertNumber(4, 2);

        assertEquals(controllerTest.boardNums[2],4);

    }

    @Test
    public void testGetNumber(){
        String testJsonString = "[[5, 3, -1, -1, 7, -1, -1, -1, -1], [6, -1, -1, 1, 9, 5, -1, -1, -1],"
                + "[-1, 9, 8, -1, -1, -1, -1, 6, -1], [8, -1, -1, -1, 6, -1, -1, -1, 3],"
                +" [4, -1, -1, 8, -1, 3, -1, -1, 1], [7, -1, -1, -1, 2, -1, -1, -1, 6],"
                +" [-1, 6, -1, -1, -1, -1, 2, 8, -1], [-1, -1, -1, 4, 1, 9, -1, -1, 5], [-1, -1, -1, -1, 8, -1, -1, 7, 9]]";
        controllerTest.readFromJson(testJsonString);

        int number = controllerTest.getNumber(0);

        assertEquals(number, 5);

    }

    @Test
    public void testChangeNumbersRandom(){
        /* TODO: Refactor test as the logic are kinda complicated and can easily be wrong / fail sometime later.
            As of now it works fine (01/10/19) -André
        */
        String testJsonString = "[[5, 3, -1, -1, 7, -1, -1, -1, -1], [6, -1, -1, 1, 9, 5, -1, -1, -1],"
                + "[-1, 9, 8, -1, -1, -1, -1, 6, -1], [8, -1, -1, -1, 6, -1, -1, -1, 3],"
                +" [4, -1, -1, 8, -1, 3, -1, -1, 1], [7, -1, -1, -1, 2, -1, -1, -1, 6],"
                +" [-1, 6, -1, -1, -1, -1, 2, 8, -1], [-1, -1, -1, 4, 1, 9, -1, -1, 5], [-1, -1, -1, -1, 8, -1, -1, 7, 9]]";
        controllerTest.readFromJson(testJsonString);

        int[] oldArray = controllerTest.boardNums.clone(); //Store the old values for testing.

        boolean[] checkNumbers = new boolean[10]; //Indexes are the number. True means its checked. False means its not checked.

        controllerTest.changeNumbersRandom();

        for (int i = 0; i < oldArray.length; i++) { //Loop over old array.
            if(oldArray[i] != -1 && checkNumbers[oldArray[i]] != true) { //Dont check -1 slots and dont do anything if we have check a number already.

                int tempOld = oldArray[i]; //The old value
                int tempNew = controllerTest.boardNums[i]; // The new value

                for (int j = 0; j < controllerTest.boardNums.length; j++) { //Loop over the new board array
                    if (controllerTest.boardNums[j] == tempNew && oldArray[j] != tempOld
                            || controllerTest.boardNums[j] != tempNew && oldArray[j] == tempOld){ // Mismatch between old and new numbers

                        assertTrue("Mismatch between new and old numbers",false); //Fail the test as a number has the wrong value.
                    }

                }

                checkNumbers[oldArray[i]] = true; //Set number as checked.
            }
        }

        assertTrue("Values are correct", true); //Pass test as values are correct.
    }

    @Test
    public void testLockCurrentCells() {
        String testJsonString = "[[5, 3, -1, -1, 7, -1, -1, -1, -1], [6, -1, -1, 1, 9, 5, -1, -1, -1],"
                + "[-1, 9, 8, -1, -1, -1, -1, 6, -1], [8, -1, -1, -1, 6, -1, -1, -1, 3],"
                +" [4, -1, -1, 8, -1, 3, -1, -1, 1], [7, -1, -1, -1, 2, -1, -1, -1, 6],"
                +" [-1, 6, -1, -1, -1, -1, 2, 8, -1], [-1, -1, -1, 4, 1, 9, -1, -1, 5], [-1, -1, -1, -1, 8, -1, -1, 7, 9]]";
        controllerTest.readFromJson(testJsonString);

        controllerTest.lockCurrentCells();

        // test if these cells actually are locked inside the bool array
        assertTrue(controllerTest.boardValidPlacements[0] == false &&
        controllerTest.boardValidPlacements[3] == true &&
        controllerTest.boardValidPlacements[26] == true &&
        controllerTest.boardValidPlacements[25] == false);
    }

    @Test(expected = BadNumberException.class)
    public void testExceptionChangeLockedCell(){
        String testJsonString = "[[5, 3, -1, -1, 7, -1, -1, -1, -1], [6, -1, -1, 1, 9, 5, -1, -1, -1],"
                + "[-1, 9, 8, -1, -1, -1, -1, 6, -1], [8, -1, -1, -1, 6, -1, -1, -1, 3],"
                +" [4, -1, -1, 8, -1, 3, -1, -1, 1], [7, -1, -1, -1, 2, -1, -1, -1, 6],"
                +" [-1, 6, -1, -1, -1, -1, 2, 8, -1], [-1, -1, -1, 4, 1, 9, -1, -1, 5], [-1, -1, -1, -1, 8, -1, -1, 7, 9]]";
        controllerTest.readFromJson(testJsonString);
        controllerTest.lockCurrentCells();

        controllerTest.insertNumber(2, 1);
    }

    @Test
    public void testNoExceptionChangeLockedCell(){
        String testJsonString = "[[5, 3, -1, -1, 7, -1, -1, -1, -1], [6, -1, -1, 1, 9, 5, -1, -1, -1],"
                + "[-1, 9, 8, -1, -1, -1, -1, 6, -1], [8, -1, -1, -1, 6, -1, -1, -1, 3],"
                +" [4, -1, -1, 8, -1, 3, -1, -1, 1], [7, -1, -1, -1, 2, -1, -1, -1, 6],"
                +" [-1, 6, -1, -1, -1, -1, 2, 8, -1], [-1, -1, -1, 4, 1, 9, -1, -1, 5], [-1, -1, -1, -1, 8, -1, -1, 7, 9]]";
        controllerTest.readFromJson(testJsonString);
        controllerTest.lockCurrentCells();

        controllerTest.insertNumber(2, 6);
    }


}
