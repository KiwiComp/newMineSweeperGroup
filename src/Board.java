import java.util.Random;

public class Board {

    private int numberOfColumns;
    private int numberOfRows;
    private char[][] boardCollection = new char[numberOfRows][numberOfColumns];
    private char[][] bombCollection = new char[numberOfRows][numberOfColumns];

    public void createBoard(int difficulty, int columns, int rows) {
        this.numberOfRows = 5 * difficulty;
        this.numberOfColumns = 5 * difficulty;
        this.boardCollection = new char[rows][columns];

        // Adds question marks to empty board squares.
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                boardCollection[row][column] = '?';
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
        return boardCollection[chosenRow][chosenColumn] != 'X';
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
        boardCollection[chosenRow][chosenColumn] = 'X';
    }



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
                if(symbol == 'X') {
                    revealedSafeSpots++;
                }
            }
        }
        return revealedSafeSpots == totalSafeSpots;
    }

    public int placeBombAdjacentHints(int columnSpot, int rowSpot){
        int bombAmount = 0;
        for (int i = -1; i < 1; i++) {
            if (columnSpot - 1 >= 0 && rowSpot + i >= 0) {
                /*
                if(checkBomb(columnSpot - 1, rowSpot + i)){
                    bombAmount++;
                }
                 */
            }
        }
        for (int i = -1; i < 1; i++) {
            if (rowSpot + i >= 0) {
                /*
                if(checkBomb(columnSpot, rowSpot + i)){
                    bombAmount++;
                }
                 */
            }
        }
        for (int i = -1; i < 1; i++) {
            if (columnSpot + 1 >= 0 && rowSpot + i >= 0) {
                /*
                if(checkBomb(columnSpot + 1, rowSpot + i)){
                    bombAmount++;
                }
                 */
            }
        }
        return bombAmount;
    }

    /**
     * Prints the minesweeper board to stdout.
     */
    public void printVisibleBoard() {
        System.out.print("      ");
        for(int i = 0; i< numberOfColumns; i++) {
            System.out.print((i+1) + "     ");
        }
        System.out.println();
        for(int row = 0; row < numberOfRows; row++) {
            System.out.print("   ");
            for(int i = 0; i < numberOfColumns; i++) {
                System.out.print("+-----");
            }
            System.out.println("+");
            System.out.print((row+1) +"  ");
            for(int column = 0; column < numberOfColumns; column++) {
                char cell = boardCollection[row][column];
                System.out.print("|  "+cell+"  ");
            }
            System.out.println("|");
        }
        System.out.print("   ");
        for(int i = 0; i< numberOfColumns; i++) {
            System.out.print("+-----");
        }
        System.out.println("+");
    }
}
