/**
 * Represents a player.
 */
public class Player {
    /**
     * Player name.
     */
    private String name;

    /**
     * Number of wins.
     */
    private int wins;

    /**
     * Number of games played.
     */
    private int gamesPlayed;

    /**
     * Player square placement symbol.
     */
    private String playerSymbol = "X";

    /**
     * Creates a Player instance.
     */
    public Player(String name) {
        this.name = name;
        this.wins = 0;
        this.gamesPlayed = 0;
    }

    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public String getPlayerSymbol() {
        return playerSymbol;
    }

    public void incrementWins() {
        this.wins++;
    }

    public void incrementGamesPlayed() {
        this.gamesPlayed++;
    }

    
}
