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

        // Adds question marks to empty board squares.
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
        if(boardCollection[chosenRow][chosenColumn] <= 9){
           isAvailable = false;
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
        for(int row = 0; row < numberOfRows; row++) {
            for(int column = 0; column < numberOfColumns; column++) {
                bombCollection[row][column] = '?';
            }
        }
        int difficultyLevel = 5*difficulty;

        Random random = new Random();

        for(int i = 0; i < difficultyLevel; i++) {
            int randomRow = random.nextInt(numberOfRows);
            int randomColumn = random.nextInt(numberOfColumns);

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
        int totalSafeSpots = this.numberOfRows*this.numberOfColumns-difficulty * 5;
        int revealedSafeSpots = 0;
        for(char[] row : boardCollection) {
            for(char symbol : row) {
                if(symbol <= (char) 9) {
                    revealedSafeSpots++;
                }
            }
        }
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
        for (int i = -1; i < 2; i++) {
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
        return (char) (bombAmount + '0');
    }



    /**
     * Prints the minesweeper board to stdout.
     */
    public void printVisibleBoard() {
        System.out.print("      ");
        for(int i = 0; i< numberOfColumns; i++) {
            System.out.print(String.format("%2d", i+1) + "    ");
        }
        System.out.println();
        for(int row = 0; row < numberOfRows; row++) {
            System.out.print("    ");
            for(int i = 0; i < numberOfColumns; i++) {
                System.out.print("+-----");
            }
            System.out.println("+");
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
