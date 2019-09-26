package no.ntnu.imt3281.sudoku;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

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

    }

}
