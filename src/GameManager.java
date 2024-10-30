import java.util.Scanner;

public class GameManager {
    final private Board board = new Board();
    final private Player player = new Player();
    final private Scanner scanner = new Scanner(System.in);
    private int difficulty;
    private int chosenRow;
    private int chosenColumn;
    private boolean closeApplication = false;

    /**
     * Entry point of the program. Runs the program.
     */
    public void run() {
        System.out.println("\n\n====================== WELCOME TO MINESWEEPER! ==========================\n\n");


        // Game loop.
        while(!closeApplication) {
            this.promptGameDifficulty();
            // Game session loop.
            while (true) {
                //this.board.printVisibleBoard();
                this.promptPlayerPlaceSymbol();
                if (!this.evaluateRound()) {
                    break;
                }
            }
            promptNewGame();
        }
    }


    /**
     * Quits program if user enters stream argument.
     * @param userInput - User input.
     */
    private void checkQuitCommand(String userInput) {
        if (userInput.equalsIgnoreCase(":quit")) {
            System.out.println("Exiting program!");
            System.exit(0);
        }
    }

    /**
     * Asks user to select game difficulty.
     */
    private void promptGameDifficulty() {
        boolean isRunning = true;
        String userInput;

        System.out.println("Please enter a game difficulty:");

        while(isRunning) {
            System.out.println("1. Easy");
            System.out.println("2. Normal");
            System.out.println("3. Hard");
            System.out.println("4. Insane");

            if (scanner.hasNextLine()) {
                userInput = scanner.nextLine();
                this.checkQuitCommand(userInput);

                try {
                    this.difficulty = Integer.parseInt(userInput);
                    this.board.createBoard(this.difficulty);
                    this.board.placeBombs(this.difficulty);
                } catch (NumberFormatException e) {
                    System.out.println("Enter a valid difficulty level:");
                    continue;
                }

                if (this.difficulty < 1 || this.difficulty > 4) {
                    System.out.println("Enter a valid difficulty level: ");
                    continue;
                }

                isRunning = false;
            } else {
                System.out.println("Enter a valid difficulty level: ");
            }
        }
    }

    /**
     * Asks user which minesweeper square to unlock.
     */
    private void promptPlayerPlaceSymbol() {
        boolean rowInput = false;
        boolean columnInput = false;
        String temp;

        while(true) {
            this.board.printVisibleBoard();
            while(!rowInput) {
                System.out.println("\nChoose a row to place your mark: ");
                if (scanner.hasNextLine()) {
                    temp = scanner.nextLine();
                    this.checkQuitCommand(temp);
                    try {
                        if(Integer.parseInt(temp) <= this.difficulty * 5 && Integer.parseInt(temp) > 0) {
                            this.chosenRow = Integer.parseInt(temp) - 1;
                            rowInput = true;
                        }else{
                            System.out.println("Enter a valid number of rows: ");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Enter a valid number of rows: ");
                    }
                } else {
                    System.out.println("Enter a valid row number: ");
                }
            }

            while(!columnInput) {
                System.out.println("\nChoose a column to place your mark: ");
                if (scanner.hasNextLine()) {
                    temp = scanner.nextLine();
                    this.checkQuitCommand(temp);
                    try {
                        if((Integer.parseInt(temp) <= this.difficulty * 5 && Integer.parseInt(temp) > 0)) {
                            this.chosenColumn = Integer.parseInt(temp) - 1;
                            columnInput = true;
                        }else{
                            System.out.println("Enter a valid number of columns: ");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Enter a valid number of columns: ");
                    }
                } else {
                    System.out.println("Enter a valid column number: ");
                }
            }

            if (this.board.isSquareAvailable(this.chosenRow, this.chosenColumn)) {
                // Square is available. Break outer loop.
                break;
            } else {
                System.out.println("This spot is taken, chose another one.");
                rowInput = false;
                columnInput = false;
            }
        }
    }

    /**
     * Evaluates a round after each player move.
     * @return - Returns false if this game session is over. True if game proceeds.
     */
    private boolean evaluateRound() {
        // Check if a square is a bomb before placing player symbol.
        if (this.board.isSquareBomb(this.chosenRow, this.chosenColumn)) {
            System.out.println("You triggered a bomb. You lost.");
            scoreboard();
            return false;
        } else {
            // Place player symbol in selected square.
            this.board.placePlayerSymbol(this.chosenRow, this.chosenColumn);
        }

        // Check if there is a win condition.
        if (this.board.isWin(this.difficulty)) {
            System.out.println("You win!");
            this.player.incrementWins();
            scoreboard();
            return false;
        } else {
            this.board.adjacentHints(this.chosenRow, this.chosenColumn);
            return true;
        }


    }

    private void promptNewGame() {
        System.out.println("Do you want to start a new round or exit? (Enter 'r' to restart, 'n' for a new game, or 'x' to exit)");
        boolean validInput = false;

        while (!validInput) {
            String userInput = scanner.nextLine().toLowerCase(); // Read player's input

            if (userInput.equals("r")) {
                System.out.println("You're restarting the current game.\n");
                // Reset necessary game states here if needed
                validInput = true;
                // You can call any methods to reset the current game state
                this.board.resetBoard();
            } else if (userInput.equals("n")) {
                System.out.println("You're starting a new game.\n");
                validInput = true;
                this.promptGameDifficulty(); // Ask for game difficulty again
            } else if (userInput.equals("x")) {
                System.out.println("The game is ending. Thank you for playing!");
                validInput = true;
                closeApplication = true; // Set the flag to close the application
                System.exit(0); // Exit the application
            } else {
                System.out.println("Invalid choice. Enter 'r' to restart, 'n' to start a new game, or 'x' to exit.");
            }
        }
    }


    /**
    * Writes out amount of wins and games played
    */
    private void scoreboard(){
        this.player.incrementGamesPlayed();
        System.out.println("You have played: " + this.player.getGamesPlayed() + " games.");
        System.out.println("You've won: " + this.player.getWins() + " times.");
        System.out.println("Restarting game.");
    }
}
