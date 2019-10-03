/*
TODO:
 DONE * Funksjon for å sette inn et element(tall) på rad/kolonne. (Andre)
 DONE * Hente og sette et element i arrayen (brukes internt, defineres som protected) (Andre)
 DONE * Fikse iterator for valueExists funksjonen for å sjekke om nummer er lov på rad,kolonne eller lokal boks. (Jan)
 DONE * Når et brett blir speilet om en av aksene (horisontalt, vertikalt, på skrå så vil det fortsatt være det samme brettet og altså gyldig, men for en bruker så vil det fremstå som et helt nytt brett.
 DONE * Lag test + funksjonlitet for speiling vertikalt og horisontalt. (Jan)
 DONE * Lag så tester og funksjonalitet for å speile brettet rundt henholdsvis den røde og den blå linjen i bildet på wikien (Diagonalene). (Jan)
 * Lag funksjonalitet for å tilfeldig bytte ut alle tall på brettet. Lag så en test for å sjekke at dette ble riktig, testen må da sjekke at overalt hvor de tidligere sto (Andre)
 * Når en har laget et brett (manuelt satt tall i aktuelle elementer eller lest det inn fra en JSON struktur og så rotert/flippet og randomisert det) så må en kunne låse de elementene
 DONE * I tillegg til at en ikke skal kunne endre disse elementene trengs en metode som kan brukes for å finne ut om et gitt element (rad/kolonne) er en låst celle. Lag denne metoden og testen for denne.
 DONE * Dersom du ikke tidligere har laget en metode for å hente verdien på en gitt rad/kolonne så lager du denne og en enkelt test for dette.
 * Nå er logikken for å la en bruker spille Sudoku klar, det skal nå lages et grafisk grensesnitt for å la brukere spille Sudoku.
 */

package no.ntnu.imt3281.sudoku;

import org.json.JSONArray;

import java.io.*;
import java.util.*;

public class Sudoku {
    protected int[] boardNums = new int[81];
    protected boolean[] boardValidPlacements = new boolean[81];     // true = mutable, false = locked


    public Sudoku(){
        resetGame();
    }

    public void resetGame(){
        Arrays.fill(boardValidPlacements, true);
        Arrays.fill(boardNums, -1);
    }

    /**
     * Creates a new Sudoku board board from the Board.json file
     */
    public void createNewBoard(String path) throws BadNumberException {
        // read from JSON file
        try{
            readFromJson(readFromFile(path));
        } catch(Exception e){
            e.printStackTrace();
        }

        // check if values are valid
        for(int i = 0; i < boardNums.length; i++) {
            checkIfValidBoard(boardNums);
        }

        // change all numbers first, then mirror the board in different ways
        changeNumbersRandom();
        mirrorBoardRandom();

        // then lock the cells to restrict further edit
        lockCurrentCells();
    }

    /**
     * Load a custom Sudoku board from the user
     * @param board the board to be passed in
     */
    public void createNewBoard(int[] board) throws BadNumberException {
        // paste the custom board into our Sudoku board
        boardNums = board;

        // check if values are valid
        for(int i = 0; i < boardNums.length; i++) {
            checkIfValidBoard(boardNums);
        }

        // change all numbers first, then mirror the board in different ways
        //changeNumbersRandom();
        //mirrorBoardRandom();

        // then lock the cells to restrict further edit
        lockCurrentCells();
    }

    /**
     * Locks all cells that currently have a value
     * <p>
     *     Loops through the whole board and checks if it has a value that is not -1.
     *     If yes, set the index in array to "false".
     * </p>
     */
     void lockCurrentCells(){
        // reset array, i.e. set all cells to mutable
        Arrays.fill(boardValidPlacements, true);

        for(int i = 0; i < 81; i++){
            if(boardNums[i] > 0){
                boardValidPlacements[i] = false;
            }
        }
    }

    /**
     * Randomizes the board by mirroring it different ways
     * <p>
     *     Creates first a random int (1-20) for how often it should mirror,
     *     then loops those amount of times and randomly chooses what type
     *     of mirroring will be applied.
     * </p>
     */
    void mirrorBoardRandom(){
        Random rand = new Random();
        BoardMirroring boardMirroring = new BoardMirroring();
        int mirrorHowOften = rand.nextInt(20)+1;
        int mirrorHow;

        for(int i = 0; i < mirrorHowOften; i++){
            mirrorHow = rand.nextInt(4);
            switch(mirrorHow){
                case 0:
                    boardMirroring.mirrorLeftRight(boardNums); break;
                case 1:
                    boardMirroring.mirrorTopDown(boardNums); break;
                case 2:
                    boardMirroring.mirrorDiagonallyRed(boardNums); break;
                case 3:
                    boardMirroring.mirrorDiagonallyBlue(boardNums); break;
                default:
                    continue;
            }
        }
    }

    /**
     * changeNumbersRandom
     * <p>
     *     Creates an array of 9 random unique numbers.
     *     Puts the 9 numbers into a hashMap to map the old value to the new random value
     *     Replace all occurences of i with the new random number from the hashMap.
     * </p>
     */
    void changeNumbersRandom(){
        Random rand = new Random();
        ArrayList<Integer> randomValues = new ArrayList<>();
        HashMap<Integer, Integer> ValueMap = new HashMap<>();

        //Create a array with random unique numbers.
        //TODO: Rework this since it might take multiple iterations before
        // the array is filled with unique numbers.
        while(randomValues.size() < 9) {
            int number = rand.nextInt(9) +1;
            if (!randomValues.contains(number)) {
                randomValues.add(number);
            }
        }

        //Put the random numbers in the hashMap
        for (int i = 1; i <= 9; i++) {
            ValueMap.put(i,randomValues.get(i-1));
        }

        //Change all values on the board using the hashMap key value pairs
        for(int i = 0; i < boardNums.length; i++) {
            int boardValue = boardNums[i];
            if(boardValue != -1) {
                boardNums[i] = ValueMap.get(boardValue);
            }
        }
    }

    /**
     * insertNumber
     * <p>
     *     Inserts number into the boardNums array based on boardPosition parameter.
     * </p>
     * @param number
     * @param boardPosition
     */
    void insertNumber(int number, int boardPosition) throws BadNumberException {
        if(isCellLocked(boardPosition)){
            throw new BadNumberException("This cell is locked");
        }

        boardNums[boardPosition] = number;
    }


    /**
     * getNumber
     * <p>
     *     Returns the number at boardPosition from the boardNums array.
     * </p>
     * @param boardPosition
     * @return Integer
     */
    int getNumber(int boardPosition){
        int ret = -1;
        for (int i = 0; i <= boardPosition; i++) {
            if (i == boardPosition) {
                ret = boardNums[i];
            }
        }

        return ret;
    }

    /**
     * isCellLocked
     * <p>
     *     Returns true if the cell is mutable and false if it is locked.
     * </p>
     * @param boardPosition
     * @return boolean
     */
    boolean isCellLocked(int boardPosition) {
        return !boardValidPlacements[boardPosition];
    }

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
                throw new BadNumberException("Value " + number + " in row " +
                        (boardPosition / 9) + ", column " + (boardPosition % 9) +
                        " is already placed in the same row.");
        }

        // check for column
        iterator = new ColumnIterator(boardNums, boardPosition).iterator();
        while(iterator.hasNext()){
            currValue = iterator.next();
            if(currValue == number)
                throw new BadNumberException("Value " + number + " in row " +
                        (boardPosition / 9) + ", column " + (boardPosition % 9) +
                        " is already placed in the same column.");
        }

        // check for sub-grid
        iterator = new SubgridIterator(boardNums, boardPosition).iterator();
        while(iterator.hasNext()){
            currValue = iterator.next();

            if(currValue == number)
                throw new BadNumberException("Value " + number + " in row " +
                        (boardPosition / 9) + ", column " + (boardPosition % 9) +
                        " is already placed in the same sub-grid.");
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
     * If the board is completed
     * @return if board completed = true
     */
    boolean isBoardCompleted(){
        return Arrays.stream(boardNums).allMatch(n -> n != -1);
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
    }

    /**
     * Checks if a board is valid, i.e. follows the rules of sudoku.
     * <p>
     * Will throw exception if row, column or sub-grid contain
     * duplicate numbers.
     * </p>
     * @param board the board to check
     * @throws BadNumberException thrown if it breaks Sudoku rules
     */
    void checkIfValidBoard(int[] board) throws BadNumberException {
        Iterator<Integer> iterator;
        int currValue;
        int[] occurences = new int[10];
        Arrays.fill(occurences, 0);

        // loop through the amount of rows/columns/sub-grids to check all
        for(int i = 0; i < 9; i++){
            // check for row
            Arrays.fill(occurences, 0);
            iterator = new RowIterator(board, i * 9).iterator();

            // count the number of occurrences
            while(iterator.hasNext()){
                currValue = iterator.next();
                if(currValue > 0){
                    occurences[currValue]++;
                }
            }

            // loop through list and check if duplicates exist
            for(int number = 1; number < 10; number++){
                // a duplicate exists
                if(occurences[number] > 1){
                    throw new BadNumberException("Value " + number + " in row " +
                            (i / 9) + ", column " + (i % 9) +
                            " is already placed in the same row.");
                }
            }

            // check for column
            Arrays.fill(occurences, 0);
            int subgridPos = (((i / 9)/3)*3)+ ((i%9)/3)*3; // top-left position of local box
            iterator = new ColumnIterator(board, subgridPos).iterator();

            // count the number of occurrences
            while(iterator.hasNext()){
                currValue = iterator.next();
                if(currValue > 0){
                    occurences[currValue]++;
                }
            }

            // loop through list and check if duplicates exist
            for(int number = 1; number < 10; number++){
                // a duplicate exists
                if(occurences[number] > 1){
                    throw new BadNumberException("Value " + number + " in row " +
                            (i / 9) + ", column " + (i % 9) +
                            " is already placed in the same column.");
                }
            }

            // check for sub-grid
            Arrays.fill(occurences, 0);
            iterator = new SubgridIterator(board, i * 9).iterator();

            // count the number of occurrences
            while(iterator.hasNext()){
                currValue = iterator.next();
                if(currValue > 0){
                    occurences[currValue]++;
                }
            }

            // loop through list and check if duplicates exist
            for(int number = 1; number < 10; number++){
                // a duplicate exists
                if(occurences[number] > 1){
                    throw new BadNumberException("Value " + number + " in row " +
                            (i / 9) + ", column " + (i % 9) +
                            " is already placed in the same sub-grid.");
                }
            }
        }
    }
}
