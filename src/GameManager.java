import java.util.Scanner;

public class GameManager {

    private Player player;
    private Scanner scanner = new Scanner(System.in);
    private int difficulty;

    public GameManager() {

    }

    public void run() {
        System.out.println("\n\n====================== WELCOME TO MINESWEEPER! ==========================\n\n");
        this.promptCreatePlayer();
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
                player = new Player();
                player.setName(playerName);
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
}
