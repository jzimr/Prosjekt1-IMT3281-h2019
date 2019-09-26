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
    public void testReadFromFile_notFound() {
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

}
