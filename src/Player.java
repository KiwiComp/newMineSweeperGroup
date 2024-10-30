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


    public int getWins() {
        return wins;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void incrementWins() {
        this.wins++;
    }

    public void incrementGamesPlayed() {
        this.gamesPlayed++;
    }

    
}
