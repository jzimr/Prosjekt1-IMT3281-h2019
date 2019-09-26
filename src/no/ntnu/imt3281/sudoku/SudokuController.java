/*
TODO:
 * Funksjon for å sette inn et element(tall) på rad/kolonne.
 * Hente og sette et element i arrayen (brukes internt, defineres som protected)
 * Fikse iterator for valueExists funksjonen for å sjekke om nummer er lov på rad,kolonne eller lokal boks.
 * Når et brett blir speilet om en av aksene (horisontalt, vertikalt, på skrå så vil det fortsatt være det samme brettet og altså gyldig, men for en bruker så vil det fremstå som et helt nytt brett.
 * Lag test + funksjonlitet for speiling vertikalt og horisontalt
 * Lag så tester og funksjonalitet for å speile brettet rundt henholdsvis den røde og den blå linjen i bildet på wikien (Diagonalene)
 * Lag funksjonalitet for å tilfeldig bytte ut alle tall på brettet. Lag så en test for å sjekke at dette ble riktig, testen må da sjekke at overalt hvor de tidligere sto
 * Når en har laget et brett (manuelt satt tall i aktuelle elementer eller lest det inn fra en JSON struktur og så rotert/flippet og randomisert det) så må en kunne låse de elementene
 * I tillegg til at en ikke skal kunne endre disse elementene trengs en metode som kan brukes for å finne ut om et gitt element (rad/kolonne) er en låst celle. Lag denne metoden og testen for denne.
 * Dersom du ikke tidligere har laget en metode for å hente verdien på en gitt rad/kolonne så lager du denne og en enkelt test for dette.
 * Nå er logikken for å la en bruker spille Sudoku klar, det skal nå lages et grafisk grensesnitt for å la brukere spille Sudoku.





 */

package no.ntnu.imt3281.sudoku;

import org.json.JSONArray;

import java.io.*;

public class SudokuController {
    protected int[] boardNums = new int[81];
    protected boolean[] boardValidPlacements = new boolean[81];

    /**
     * valueExists
     * <p>
     *      Takes a number and a position and check the number exists in the same row,
     *      column or localbox. If not return a BadNumberException with an error message
     *      to why it failed.
     * </p>
     * @param number
     * @param boardPosition
     * @throws BadNumberException
     */
    void valueExists(int number, int boardPosition) throws BadNumberException {

        //Check for row
        int row = boardPosition / 9;    // (0-8)
        for(int i = row * 9; i < row*9+9; i++){
            if(boardNums[i] == number)
                throw new BadNumberException("Value already exists in row");
        }

        // check for column
        int column = boardPosition % 9; // (0-8)
        for(int i = column; i < 81; i += 9){
            if(boardNums[i] == number)
                throw new BadNumberException("Value already exists in column");
        }

        //Check for localbox
        int localBox = ((row/3)*3)+ (column/3)*3; // top-left position of local box
        int position; // our position in table
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                position = localBox+(i*9)+j;
                if (boardNums[position] == number)
                    throw new BadNumberException("Value already exists in local box");
            }
        }

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
