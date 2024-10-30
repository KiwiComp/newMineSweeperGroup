/**
 * Represents a player.
 */
public class Player {
    /**
     * Number of wins.
     */
    private int wins;

    /**
     * Number of games played.
     */
    private int gamesPlayed;

    /**
     * Creates a Player instance.
     */
    public Player() {
        this.wins = 0;
        this.gamesPlayed = 0;
    }


    /**
     * Getter for wins.
     * @return wins.
     */
    public int getWins() {
        return wins;
    }

    /**
     * Getter for gamesPlayed.
     * @return gamesPlayed.
     */
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    /**
     * Method that increases wins by 1 each time it's called upon.
     */
    public void incrementWins() {
        this.wins++;
    }

    /**
     * Method that increases gamesPlayed by 1 each time it's called upon.
     */
    public void incrementGamesPlayed() {
        this.gamesPlayed++;
    }

    
}
