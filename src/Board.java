import java.util.Random;

public class Board {

    private int numberOfColumns;
    private int numberOfRows;
    private char[][] boardCollection = new char[numberOfRows][numberOfColumns];
    private char[][] bombCollection = new char[numberOfRows][numberOfColumns];



    /**
     * Method to create the current playing board based on user's chosen difficulty level.
     * @param difficulty - the user's chosen difficulty level.
     */

    public void createBoard(int difficulty) {
        this.numberOfRows = 5 * difficulty;
        this.numberOfColumns = 5 * difficulty;
        this.boardCollection = new char[this.numberOfRows][this.numberOfColumns];

        // Adds 'space' to empty board squares.
        for (int row = 0; row < this.numberOfRows; row++) {
            for (int column = 0; column < this.numberOfColumns; column++) {
                boardCollection[row][column] = ' ';
            }
        }
    }

    /**
     * Checks if a square is available.
     * @param chosenRow - User selected row.
     * @param chosenColumn - User selected column.
     * @return - True if available, otherwise false.
     */
    public boolean isSquareAvailable(int chosenRow, int chosenColumn) {
        boolean isAvailable = false;
        // checks if the position contains a number 9 or smaller, spot is taken = set boolean to false.
        if(boardCollection[chosenRow][chosenColumn] <= 9){
           isAvailable = false;
       // checks if the position contains 'space', spot is available = set boolean to true;
        }else if (boardCollection[chosenRow][chosenColumn] == ' '){
            isAvailable = true;
        }
        return isAvailable;
    }

    /**
     * Checks if a square is a bomb.
     * @param chosenRow - User selected row.
     * @param chosenColumn - User selected column.
     * @return - True if is a bomb, otherwise false.
     */
    public boolean isSquareBomb(int chosenRow, int chosenColumn) {
        return this.bombCollection[chosenRow][chosenColumn] == '*';
    }

    /**
     * Places player symbol.
     * @param chosenRow - User selected row.
     * @param chosenColumn - User selected column.
     */
    public void placePlayerSymbol(int chosenRow, int chosenColumn) {
        boardCollection[chosenRow][chosenColumn] = adjacentHints(chosenRow,chosenColumn);
    }



    /**
     * Set bombs on board based on user's chosen difficulty level.
     * @param difficulty - user selected difficulty level.
     */

    public void placeBombs(int difficulty) {
        bombCollection = new char[numberOfRows][numberOfColumns];
        // Fills hidden bomb board with question marks.
        for(int row = 0; row < numberOfRows; row++) {
            for(int column = 0; column < numberOfColumns; column++) {
                bombCollection[row][column] = '?';
            }
        }
        int difficultyLevel = 5*difficulty;

        Random random = new Random();

        // randomises where to place bombs, bombs are symbolised by *.
        for(int i = 0; i < difficultyLevel; i++) {
            int randomRow = random.nextInt(numberOfRows);
            int randomColumn = random.nextInt(numberOfColumns);

            // Checks if random position is a question mark, if not, go back one iteration and try again.
            if(bombCollection[randomRow][randomColumn]=='?') {
                bombCollection[randomRow][randomColumn]='*';
            } else {
                i--;
            }
        }
    }

    /**
     * Checks if user has won a session of minesweeper.
     * @param difficulty - Game difficulty.
     * @return - True if win, else false.
     */
    public boolean isWin(int difficulty) {
        int totalSafeSpots = this.numberOfRows * this.numberOfColumns - difficulty * 5;
        int revealedSafeSpots = 0;

        // Check position symbols.
        for (int row = 0; row < numberOfRows; row++) {
            for (int col = 0; col < numberOfColumns; col++) {
                // Check if the cell is revealed and not a bomb, if revealed add 1 to revealedSafeSpots.
                if (boardCollection[row][col] != ' ' && bombCollection[row][col] != '*') {
                    revealedSafeSpots++;
                }
            }
        }
        // As soon as revealedSafeSpots equals totalSafeSpots, return true.
        return revealedSafeSpots == totalSafeSpots;
    }



    /**
     * Method to print number of bombs adjacent to chosen square.
     * @param rowSpot - user's chosen row position.
     * @param columnSpot - user's chosen column position.
     * @return - the number of bombs adjacent to chosen position.
     */

    public char adjacentHints(int rowSpot, int columnSpot){
        int bombAmount = 0;
        //This for loops checks from the spot left of the chosen spot to the one on the right
        for (int i = -1; i < 2; i++) {
            //This checks the row on top of the chosen spot and so forth
            //It also only checks the row/column if the spot is within the set board size
            if (rowSpot - 1 >= 0 && columnSpot + i >= 0 && columnSpot + i < numberOfColumns) {
                if(isSquareBomb(rowSpot - 1, columnSpot + i)){
                    bombAmount++;
                }
            }
            if (columnSpot + i >= 0 && columnSpot + i < numberOfColumns) {
                if(isSquareBomb(rowSpot, columnSpot + i)){
                    bombAmount++;
                }
            }
            if (rowSpot + 1 < numberOfRows && columnSpot + i >= 0 && columnSpot + i < numberOfColumns) {
                if(isSquareBomb(rowSpot + 1, columnSpot + i)){
                    bombAmount++;
                }
            }
        }
        //It then makes the int into a char
        return (char) (bombAmount + '0');
    }



    /**
     * Prints the minesweeper board to stdout.
     */
    public void printVisibleBoard() {
        System.out.print("      ");
        for(int i = 0; i< numberOfColumns; i++) {
            // Format column positioning numbers so they take up the same space (2 digits).
            System.out.print(String.format("%2d", i+1) + "    ");
        }
        System.out.println();
        for(int row = 0; row < numberOfRows; row++) {
            System.out.print("    ");
            for(int i = 0; i < numberOfColumns; i++) {
                System.out.print("+-----");
            }
            System.out.println("+");
            // Format row positioning numbers so they take up the same space (2 digits).
            System.out.print(String.format("%2d", row + 1) + "  ");
            for(int column = 0; column < numberOfColumns; column++) {
                char cell = boardCollection[row][column];
                System.out.print("|  "+cell+"  ");

            }
            System.out.println("|");
        }
        System.out.print("    ");
        for(int i = 0; i< numberOfColumns; i++) {
            System.out.print("+-----");
        }
        System.out.println("+");
    }
}
