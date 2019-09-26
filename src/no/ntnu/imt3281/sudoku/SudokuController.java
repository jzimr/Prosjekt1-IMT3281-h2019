package no.ntnu.imt3281.sudoku;

import org.json.JSONArray;

import java.io.*;

public class SudokuController {
    protected int[] boardNums = new int[81];
    protected boolean[] boardValidPlacements = new boolean[81];

    void numberPlacedIsLegit(int number, int boardPosition) throws BadNumberException {
        // do logic

        // if user did not place valid number
        // throw new BadNumberException("The number placed was not allowed");
    }

    /**
     * readFromFile()
     * <p>
     *     Reads file from filesystem and returns a string with its contents.
     * </p>
     * @param path
     * @return String
     * @throws IOException
     */
    String readFromFile(String path) throws IOException {
        //TODO:
        // Replace Stacktrace

        // read from "path"
        StringBuilder sb = new StringBuilder();
        try {
            File f = new File("./");

            FileReader reader = new FileReader(path);
            BufferedReader br = new BufferedReader(reader);
            String tmp;
            while ((tmp=br.readLine())!=null) {
                sb.append(tmp);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // return the JSON string
        return sb.toString();
    }

    /**
     * readFromJson()
     * <p>
     *     Takes a JSON string and inserts the values into the boardNums array
     *     Fills in the boardValidPlacements array.
     * </p>
     * @param json
     */
    void readFromJson(String json){
        JSONArray table = new JSONArray(json);

        for (int i = 0; i < table.length(); i++) {
            JSONArray row = new JSONArray(table.get(i).toString());

            for(int j = 0; j < table.length(); j++){
                boardNums[i*9 + j] = Integer.parseInt(row.get(j).toString());
            }
        }

        for(int i = 0; i < boardNums.length; i++){
            boardValidPlacements[i] = boardNums[i] < 0;
        }

    }

    // Trenger egen Row, Column og grid interface som implementerer iterator
    // Bruker så "getRowIterator", "getColumnIterator" og "getGridIterator"
}
