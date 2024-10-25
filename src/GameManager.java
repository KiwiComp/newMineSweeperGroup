import java.util.Scanner;

public class GameManager {
    private Board board = new Board();
    private Player player;
    private Scanner scanner = new Scanner(System.in);
    private int difficulty;
    private boolean closeApplication = false;

    public GameManager() {

    }

    public void run() {
        System.out.println("\n\n====================== WELCOME TO MINESWEEPER! ==========================\n\n");
        this.promptCreatePlayer();
        while(!closeApplication) {
            this.board.createBoard();
            board.printVisibleBoard();
            System.out.println("How many bombs do you want?");
            board.placeBombs(scanner.nextInt());
            scanner.nextLine();

            while (!board.getGameEnd() && !board.getWeHaveAWinner()) {
                board.placePlayerSymbol(player);
            }
            this.scoreboard();

            newGameOption();
        }

    }

    private void newGameOption() {
        System.out.println("Do you want to play again? (y/n)");
        boolean validInput = false;
        String again;
        while(!validInput) {
            again = scanner.nextLine();
            if (again.trim().equalsIgnoreCase("n")) {
                validInput=true;
                closeApplication = true;
            } else if (again.trim().equalsIgnoreCase("y")) {
                validInput = true;
                board.setGameEnd(false);
                board.setWeHaveAWinner(false);
            } else {
                System.out.println("You have neither chosen y nor n. Try again.");
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
                this.player = new Player();
                this.player.setName(playerName);
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
     * Creates the minesweeper game boards.
     * @param difficulty - Difficulty of game session.
     */
    private void createGame(int difficulty) {
        board.createBoard();
        board.placeBombs(difficulty);
        System.out.println("Game has started!");
    }

    /**
     * Evaluates a round after each player move.
     * @return - Returns false if this game session is over. True if game proceeds.
     */
    /*private boolean evaluateRound() {
        // Check if a square is a bomb before placing player symbol.
        if (this.board.isSquareBomb(this.userInput)) {
            System.out.println("You triggered a bomb. You lost.");
            this.board.incrementGamesPlayed();
            System.out.println("You have played: " + this.board.getGamesPlayed() + " games.");
            System.out.println("You've won: " + this.getWins() + " times.");
            System.out.println("Restarting game.");
            this.resetGame();
            return false;
        } else {
            // Place player symbol in selected square.
            this.board.placePlayerSymbol(this.userInput);
        }

        // Check if there is a win condition.
        if (this.board.isWin()) {
            System.out.println("You win!");
            this.board.incrementWins();
            this.board.incrementGamesPlayed();
            System.out.println("You have played: " + this.board.getGamesPlayed() + " games.");
            System.out.println("You've won: " + this.getWins() + " times.");
            System.out.println("Restarting game.");
            this.resetGame();
            return false;
        } else {
            return true;
        }
    }*/

    public void scoreboard() {
        System.out.println("Number of games played: " + this.player.getGamesPlayed()+"\n"+
                "Number of games won: " + this.player.getWins()+"\n"+
                "Number of games lost: " + (this.player.getGamesPlayed()-this.player.getWins()));

    }

}
