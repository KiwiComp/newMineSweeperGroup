import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;

public class Board {

    private int columnCollection;
    private int rowCollection;
    private char[][] boardCollection = new char[rowCollection][columnCollection];
    private char[][] bombCollection = new char[rowCollection][columnCollection];
    Scanner scanner = new Scanner(System.in);
    Player player = new Player();
    private boolean gameEnd = false;
    private boolean weHaveAWinner = false;
    private int difficultyLevel;
    private boolean closeApplication = false;



    public Board() {

    }


    public boolean getGameEnd() {
        return gameEnd;
    }

    public boolean getWeHaveAWinner() {
        return weHaveAWinner;
    }

    public void setGameEnd(boolean gameEnd) {
        this.gameEnd = gameEnd;
    }

    public void setWeHaveAWinner(boolean weHaveAWinner) {
        this.weHaveAWinner = weHaveAWinner;
    }


    public void createBoard() {
        System.out.println("How many rows do you want for your board?");
        rowCollection = scanner.nextInt();
        scanner.nextLine();
        System.out.println("How many columns do you want for your board?");
        columnCollection = scanner.nextInt();
        scanner.nextLine();
        boardCollection = new char[rowCollection][columnCollection];
        for (int row = 0; row < rowCollection; row++) {
            for (int column = 0; column < columnCollection; column++) {
                boardCollection[row][column] = '?';
            }
        }
    }


    public void placePlayerSymbol() {
        boolean validInput = false;
        while(!validInput) {

            System.out.println("\nChoose a row to place your mark: ");
            int chosenRow = scanner.nextInt()-1;
            scanner.nextLine();
            System.out.println("Choose a column to place your mark: ");
            int chosenColumn = scanner.nextInt()-1;
            scanner.nextLine();

            if (chosenRow >=0 && chosenRow < bombCollection.length &&
            chosenColumn >=0 && chosenColumn < bombCollection.length) {
                if (boardCollection[chosenRow][chosenColumn] == 'X') {
                    System.out.println("This spot is taken, chose another one.");
                }
                else if (bombCollection[chosenRow][chosenColumn] == '*') {
                    System.out.println("BOOM!");
                    //add games played code
                    gameEnd = true;
                }
                else if (bombCollection[chosenRow][chosenColumn] == '?') {
                System.out.println("Safe spot!");
                boardCollection[chosenRow][chosenColumn] = 'X';
                printBoard();
                weHaveAWinner=isWin();
                validInput = true;
                }
            }
        }



    public void placeBombs(int bombAmount) {
        bombCollection = new char[rowCollection][columnCollection];
        for(int row = 0; row <rowCollection; row++) {
            for(int column = 0; column < columnCollection; column++) {
                bombCollection[row][column] = '?';
            }
        }
        difficultyLevel = bombAmount;

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






    public boolean isWin() {
        int totalSafeSpots = rowCollection*columnCollection-difficultyLevel;
        int revealedSafeSpots = 0;
        for(char[] row : boardCollection) {
            for(char symbol : row) {
                if(symbol == 'X') {
                    revealedSafeSpots++;
                }
            }
        }
        if(revealedSafeSpots==totalSafeSpots) {
            System.out.println("You have won!");
            player.incrementGamesPlayed();
            player.incrementWins();
            return true;
        }
        return false;
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

        // printBoard-metoden
        public void printBoard() {
            System.out.println("Current Board:");
            for (int row = 0; row < rowCollection; row++) {
                for (int column = 0; colmun < columnCollection; colmun++) {
                    System.out.println(boardCollection[row][colmun] + " ");
                }
                System.out.println(); // Ny rad efter varje rad på brädet
            }
        }
}
