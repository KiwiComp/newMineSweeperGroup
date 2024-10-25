import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;

public class Board {

    private int columnCollection;
    private int rowCollection;
    private char[][] boardCollection = new char[rowCollection][columnCollection];
    private char[][] bombCollection = new char[rowCollection][columnCollection];
    Scanner scanner = new Scanner(System.in);
//    Player player;
    private boolean gameEnd = false;
    private boolean weHaveAWinner = false;
    private int difficultyLevel;
    private boolean closeApplication = false;
    private int bombs;



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
                boardCollection[row][column] = ' ';
            }
        }
    }


    public void placePlayerSymbol(Player player) {
        boolean validInput = false;
        while (!validInput) {

            System.out.println("\nChoose a row to place your mark: ");
            int chosenRow = scanner.nextInt() - 1;
            scanner.nextLine();
            System.out.println("Choose a column to place your mark: ");
            int chosenColumn = scanner.nextInt() - 1;
            scanner.nextLine();


            if (chosenRow >= 0 && chosenRow < bombCollection.length &&
                    chosenColumn >= 0 && chosenColumn < bombCollection.length) {
                if (boardCollection[chosenRow][chosenColumn] <= (char)bombs) {
                    System.out.println("This spot is taken, chose another one.");
                } else if (bombCollection[chosenRow][chosenColumn] == '*') {
                    System.out.println("BOOM!");
                    //add games played code
                    printVisibleBoard();
                    player.incrementGamesPlayed();
                    validInput = true;
                    setGameEnd(true);
                } else if (bombCollection[chosenRow][chosenColumn] == '?') {
                    System.out.println("Safe spot!");
                    boardCollection[chosenRow][chosenColumn] = AdjacentHints(chosenRow, chosenColumn);
                    this.printVisibleBoard();
                    weHaveAWinner = isWin(player);
                    validInput = true;
                }
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
        this.bombs = bombAmount;
//        difficultyLevel = bombAmount;

        Random random = new Random();

        for(int i = 0; i < bombAmount; i++) {
            int randomRow = random.nextInt(rowCollection);
            int randomColumn = random.nextInt(columnCollection);

            if(bombCollection[randomRow][randomColumn]=='?') {
                bombCollection[randomRow][randomColumn]='*';
                boardCollection[randomRow][randomColumn] = '*';
            } else {
                i--;
            }
        }
    }






    public boolean isWin(Player player) {
        int totalSafeSpots = rowCollection*columnCollection-difficultyLevel;
        int revealedSafeSpots = 0;
        for(char[] row : boardCollection) {
            for(char symbol : row) {
                if(symbol <= (char)bombs) {
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

    public char AdjacentHints(int rowSpot, int columnSpot){
        int bombAmount = 0;
        for (int i = -1; i < 2; i++) {
            if (rowSpot - 1 >= 0 && columnSpot + i >= 0 && columnSpot + i < columnCollection) {
                if(bombCollection[rowSpot - 1][columnSpot + i] == '*'){
                    bombAmount++;
                }
            }
        }
        for (int i = -1; i < 2; i++) {
            if (columnSpot + i >= 0 && columnSpot + i < columnCollection) {
                if(bombCollection[rowSpot][columnSpot + i] =='*'){
                    bombAmount++;
                }
            }
        }
        for (int i = -1; i < 2; i++) {
            if (rowSpot + 1 < rowCollection && columnSpot + i >= 0 && columnSpot + i < columnCollection) {
                if(bombCollection[rowSpot + 1][columnSpot + i] == '*' ){
                    bombAmount++;
                }
            }
        }
        return (char) (bombAmount + '0');
    }

       /* // printBoard-metoden
        public void printVisibleBoard() {
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
            System.out.print((row+1)+"  ");
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
