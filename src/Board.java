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


    public void fillBoard() {
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
    }

}
