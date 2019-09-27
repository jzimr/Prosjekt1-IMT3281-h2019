/*
TODO:
 * Funksjon for å sette inn et element(tall) på rad/kolonne. (Andre)
 * Hente og sette et element i arrayen (brukes internt, defineres som protected) (Andre)
 DONE * Fikse iterator for valueExists funksjonen for å sjekke om nummer er lov på rad,kolonne eller lokal boks. (Jan)
 * Når et brett blir speilet om en av aksene (horisontalt, vertikalt, på skrå så vil det fortsatt være det samme brettet og altså gyldig, men for en bruker så vil det fremstå som et helt nytt brett.
 * Lag test + funksjonlitet for speiling vertikalt og horisontalt. (Jan)
 * Lag så tester og funksjonalitet for å speile brettet rundt henholdsvis den røde og den blå linjen i bildet på wikien (Diagonalene). (Jan)
 * Lag funksjonalitet for å tilfeldig bytte ut alle tall på brettet. Lag så en test for å sjekke at dette ble riktig, testen må da sjekke at overalt hvor de tidligere sto (Andre)
 * Når en har laget et brett (manuelt satt tall i aktuelle elementer eller lest det inn fra en JSON struktur og så rotert/flippet og randomisert det) så må en kunne låse de elementene
 * I tillegg til at en ikke skal kunne endre disse elementene trengs en metode som kan brukes for å finne ut om et gitt element (rad/kolonne) er en låst celle. Lag denne metoden og testen for denne.
 * Dersom du ikke tidligere har laget en metode for å hente verdien på en gitt rad/kolonne så lager du denne og en enkelt test for dette.
 * Nå er logikken for å la en bruker spille Sudoku klar, det skal nå lages et grafisk grensesnitt for å la brukere spille Sudoku.





 */

package no.ntnu.imt3281.sudoku;

import org.json.JSONArray;

import java.io.*;
import java.util.Iterator;

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
        Iterator<Integer> iterator;
        int currValue;

        // check for row
        iterator = new RowIterator(boardNums, boardPosition).iterator();
        while(iterator.hasNext()){
            currValue = iterator.next();
            if(currValue == number)
                throw new BadNumberException("Value already exists in row");
        }

        // check for column
        iterator = new ColumnIterator(boardNums, boardPosition).iterator();
        while(iterator.hasNext()){
            currValue = iterator.next();
            if(currValue == number)
                throw new BadNumberException("Value already exists in column");
        }

        // check for sub-grid
        iterator = new SubgridIterator(boardNums, boardPosition).iterator();
        while(iterator.hasNext()){
            currValue = iterator.next();
            if(currValue == number)
                throw new BadNumberException("Value already exists in sub-grid");
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
