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

    public boolean isSquareAvailable(int row, int column) {

    }

    public void placePlayerSymbol(int row, int column) {
        boolean validInput = false;
        while (!validInput) {
            System.out.println("Choose a column to place your mark: ");

            if (chosenRow >= 0 && chosenRow < bombCollection.length &&
                    chosenColumn >= 0 && chosenColumn < bombCollection.length) {
                if (boardCollection[chosenRow][chosenColumn] == 'X') {
                    System.out.println("This spot is taken, chose another one.");
                } else if (bombCollection[chosenRow][chosenColumn] == '*') {
                    System.out.println("BOOM!");
                    //add games played code
                    gameEnd = true;
                } else if (bombCollection[chosenRow][chosenColumn] == '?') {
                    System.out.println("Safe spot!");
                    boardCollection[chosenRow][chosenColumn] = 'X';
                }
            }
        }
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

//    public boolean isWin() {
//        int totalSafeSpots = rowCollection*columnCollection-difficultyLevel;
//        int revealedSafeSpots = 0;
//        for(char[] row : boardCollection) {
//            for(char symbol : row) {
//                if(symbol == 'X') {
//                    revealedSafeSpots++;
//                }
//            }
//        }
//        if(revealedSafeSpots==totalSafeSpots) {
//            System.out.println("You have won!");
//            player.incrementGamesPlayed();
//            player.incrementWins();
//            return true;
//        }
//        return false;
//    }

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

        // printBoard-metoden
        /*public void printVisibleBoard() {
            System.out.println("Current Board:");
            for (int row = 0; row < rowCollection; row++) {
                for (int column = 0; column < columnCollection; column++) {
                    System.out.println(boardCollection[row][column] + " ");
                }
                System.out.println(); // Ny rad efter varje rad på brädet
            }
        }*/

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
