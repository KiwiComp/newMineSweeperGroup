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
     * Returns number of wins.
     * @return - Number of wins.
     */
    public int getWins() {
        return wins;
    }

    /**
     * Returns number of played games.
     * @return - Number of games played.
     */
    public int getGamesPlayed() {
        return gamesPlayed;
    }

    /**
     * Increments number of wins by 1.
     */
    public void incrementWins() {
        this.wins++;
    }

    /**
     * Increment number of played games by 1.
     */
    public void incrementGamesPlayed() {
        this.gamesPlayed++;
    }

    
}
