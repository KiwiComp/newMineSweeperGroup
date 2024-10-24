import java.util.Scanner;

public class GameManager {

    private Player player;
    private Scanner scanner = new Scanner(System.in);

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
                player = new Player(playerName);
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
}
