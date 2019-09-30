package no.ntnu.imt3281.sudoku;

import static org.junit.Assert.*;

import org.json.JSONArray;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class SudokuControllerTest {

    final private String EXPECTED_EXCEPTION_TEXT = "java.io.FileNotFoundException: resources\\JSON\\NotFound.json (The system cannot find the file specified)";
    final private String EXPECTED_EXCEPTION_TEXT_ROW = "Value already exists in row";
    final private String EXPECTED_EXCEPTION_TEXT_COLUMN = "Value already exists in column";
    final private String EXPECTED_EXCEPTION_TEXT_LOCALBOX ="Value already exists in sub-grid";

    private SudokuController controllerTest;

    @Before
    public void init(){
        controllerTest = new SudokuController();
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

        boolean isLocked;

        isLocked = controllerTest.isCellLocked(0);

        assertFalse("Cell is locked. Wanted false got True",isLocked);

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

        assertTrue("Cell is not locked. Wanted true got false",isLocked);

    }

    @Test
    public void testInsertNumber(){
        String testJsonString = "[[5, 3, -1, -1, 7, -1, -1, -1, -1], [6, -1, -1, 1, 9, 5, -1, -1, -1],"
                + "[-1, 9, 8, -1, -1, -1, -1, 6, -1], [8, -1, -1, -1, 6, -1, -1, -1, 3],"
                +" [4, -1, -1, 8, -1, 3, -1, -1, 1], [7, -1, -1, -1, 2, -1, -1, -1, 6],"
                +" [-1, 6, -1, -1, -1, -1, 2, 8, -1], [-1, -1, -1, 4, 1, 9, -1, -1, 5], [-1, -1, -1, -1, 8, -1, -1, 7, 9]]";
        controllerTest.readFromJson(testJsonString);

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

}
