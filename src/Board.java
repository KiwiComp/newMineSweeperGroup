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

}
