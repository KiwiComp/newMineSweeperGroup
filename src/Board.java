import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;

public class Board {

    private int columnCollection;
    private int rowCollection;
    private char[][] boardCollection = new char[rowCollection][columnCollection];
    private char[][] bombCollection = new char[rowCollection][columnCollection];

    public void createBoard(int difficulty) {
        System.out.println("How many rows do you want for your board?");
        rowCollection = scanner.nextInt();
        scanner.nextLine();
        System.out.println("How many columns do you want for your board?");
        columnCollection = scanner.nextInt();
        scanner.nextLine();
        rowCollection = 5 * difficulty;
        columnCollection = 5 * difficulty;
        boardCollection = new char[rowCollection][columnCollection];
        for (int row = 0; row < rowCollection; row++) {
            for (int column = 0; column < columnCollection; column++) {
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
        bombCollection = new char[rowCollection][columnCollection];
        for(int row = 0; row <rowCollection; row++) {
            for(int column = 0; column < columnCollection; column++) {
                bombCollection[row][column] = '?';
            }
        }
        int difficultyLevel = 5*difficulty;

        Random random = new Random();

        for(int i = 0; i < difficultyLevel; i++) {
            int randomRow = random.nextInt(rowCollection);
            int randomColumn = random.nextInt(columnCollection);

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
        for(int i = 0; i< columnCollection; i++) {
            System.out.print((i+1) + "     ");
        }
        System.out.println();
        for(int row = 0; row < rowCollection; row++) {
            System.out.print("   ");
            for(int i = 0; i < columnCollection; i++) {
                System.out.print("+-----");
            }
            System.out.println("+");
            System.out.print((row+1) +"  ");
            for(int column = 0; column < columnCollection; column++) {
                char cell = boardCollection[row][column];
                System.out.print("|  "+cell+"  ");
            }
            System.out.println("|");
        }
        System.out.print("   ");
        for(int i = 0; i< columnCollection; i++) {
            System.out.print("+-----");
        }
        System.out.println("+");
    }
}
