import java.util.Scanner;

public class GameManager {
    final private Board board = new Board();
    private Player player;
    final private Scanner scanner = new Scanner(System.in);
    private int difficulty;
    private int chosenRow;
    private int chosenColumn;

    public GameManager() {

    }

    public void run() {
        System.out.println("\n\n====================== WELCOME TO MINESWEEPER! ==========================\n\n");
        this.promptCreatePlayer();

        // Game loop.
        while(true) {
            this.promptGameDifficulty();
            this.promptCreateBoard();

            // Game session loop.
            while (true) {
                this.board.printVisibleBoard();
                this.promptPlayerPlaceSymbol();
                if (this.evaluateRound()) {
                    break;
                }
            }
        }
    }

    /**
     * Asks user to enter name. Creates a Player instance.
     */
    private void promptCreatePlayer() {
        System.out.println("Please enter your name:");

        boolean validNameInput = false;

        while(!validNameInput) {
            String playerName = scanner.nextLine();
            if (playerName.isEmpty()) {
                System.out.println("Enter a valid name!");
            } else {
                this.player = new Player(playerName);
                validNameInput = true;
            }
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
        int difficulty;

        System.out.println("Please enter a game difficulty:");
        System.out.println("1. Easy");
        System.out.println("2. Normal");
        System.out.println("3. Hard");
        System.out.println("4. Insane");

        while(isRunning) {
            if (scanner.hasNextInt()) {
                difficulty = scanner.nextInt();
                scanner.nextLine();

                if (difficulty < 1 || difficulty > 4) {
                    System.out.println("Enter a valid difficulty level: ");
                    System.out.println("1. Easy");
                    System.out.println("2. Normal");
                    System.out.println("3. Hard");
                    System.out.println("4. Insane");
                    continue;
                }

                this.difficulty = difficulty;
                isRunning = false;
            }
        }
    }

    /**
     * Asks user how many rows and columns the board should have.
     * Creates board based on input.
     */
    private void promptCreateBoard() {
        int rows;
        int columns;

        System.out.println("How many rows do you want for your board?");

        // Loop for rows.
        while(true) {
            if (scanner.hasNextInt()) {
                rows = scanner.nextInt();
                if (rows < 1) {
                    System.out.println("Enter a valid number of rows: ");
                    continue;
                } else {
                    scanner.nextLine();
                    break;
                }
            }
        }

        // Loop for columns.
        System.out.println("How many columns do you want for your board?");
        while(true) {
            if (scanner.hasNextInt()) {
                columns = scanner.nextInt();
                if (columns < 1) {
                    System.out.println("Enter a valid number of columns: ");
                    continue;
                } else {
                    scanner.nextLine();
                    break;
                }
            }
        }

        this.board.createBoard(difficulty, columns, rows);
        this.board.placeBombs(difficulty);
        System.out.println("Game has started!");
    }

    /**
     * Asks user which minesweeper square to unlock.
     */
    private void promptPlayerPlaceSymbol() {
        boolean rowInput = false;
        boolean columnInput = false;

        while(true) {
            while(!rowInput) {
                this.board.printVisibleBoard();
                System.out.println("\nChoose a row to place your mark: ");
                if (scanner.hasNextInt()) {
                    rowInput = true;
                    this.chosenRow = scanner.nextInt();
                } else {
                    System.out.println("Enter a valid row number: ");
                    continue;
                }
            }

            while(!columnInput) {
                this.board.printVisibleBoard();
                System.out.println("\nChoose a column to place your mark: ");
                if (scanner.hasNextInt()) {
                    columnInput = true;
                    this.chosenColumn = scanner.nextInt();
                } else {
                    System.out.println("Enter a valid column number: ");
                    continue;
                }
            }
            if (this.board.isSquareAvailable(this.chosenRow, this.chosenColumn)) {
                // Square is available. Break outer loop.
                break;
            } else {
                System.out.println("This spot is taken, chose another one.");
            }
        }
    }

    /**
     * Evaluates a round after each player move.
     * @return - Returns false if this game session is over. True if game proceeds.
     */
    private boolean evaluateRound() {
        // Check if a square is a bomb before placing player symbol.
        if (this.board.isSquareBomb(this.userInput)) {
            System.out.println("You triggered a bomb. You lost.");
            this.player.incrementGamesPlayed();
            System.out.println("You have played: " + this.player.getGamesPlayed() + " games.");
            System.out.println("You've won: " + this.player.getWins() + " times.");
            System.out.println("Restarting game.");
            return false;
        } else {
            // Place player symbol in selected square.
            this.board.placePlayerSymbol(this.userInput);
        }

        // Check if there is a win condition.
        if (this.board.isWin()) {
            System.out.println("You win!");
            this.player.incrementWins();
            this.player.incrementGamesPlayed();
            System.out.println("You have played: " + this.player.getGamesPlayed() + " games.");
            System.out.println("You've won: " + this.player.getWins() + " times.");
            System.out.println("Restarting game.");
            return false;
        } else {
            return true;
        }
    }
}
