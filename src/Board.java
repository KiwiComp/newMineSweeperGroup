import java.util.Random;

public class Board {

    private int numberOfColumns;
    private int numberOfRows;
    private char[][] boardCollection = new char[numberOfRows][numberOfColumns];
    private char[][] bombCollection = new char[numberOfRows][numberOfColumns];

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
     * @return - True if win, else false.
     */
    public boolean isWin() {
        int totalSpots = this.numberOfRows * this.numberOfColumns;
        int occupiedSpots = 0;
        int totalBombs = 0;
        int remainingSpots;

        // Checks how many spots are occupied by player.
        for (int i = 0; i < boardCollection.length; i++) {
            for (int j = 0; j < boardCollection[i].length; j++) {
                if (Character.isDigit(boardCollection[i][j])) {
                    occupiedSpots++;
                }
            }
        }

        // Calculates number of bombs in game session.
        for (int i = 0; i < bombCollection.length; i++) {
            for (int j = 0; j < bombCollection[i].length; j++) {
                if (bombCollection[i][j] == '*') {
                    totalBombs++;
                }
            }
        }

        // Total spots, negate total bomb spots.
        return totalSpots - totalBombs == occupiedSpots;

    }

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
