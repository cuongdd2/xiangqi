package player;

public class Player {
    public int remainderTime;  //second
    PlayerProfile playProfile;

    public Player(int remainderTime, PlayerProfile playProfile) {
        this.remainderTime = remainderTime;
        this.playProfile = playProfile;
    }

    public int getRemainderTime() {
        return remainderTime;
    }

    public void setRemainderTime(int remainderTime) {
        this.remainderTime = remainderTime;
    }

    public PlayerProfile getPlayProfile() {
        return playProfile;
    }

    public void setPlayProfile(PlayerProfile playProfile) {
        this.playProfile = playProfile;
    }

    public int calculateEloResult(Player winPlayer, Player losePlayer){
        /**
         * Calculate rating for 2 players
         *
         * @param player1Rating
         *            The rating of Player1
         * @param player2Rating
         *            The rating of Player2
         * @return New rating for winPlayer
         */

        int player1Rating =  winPlayer.playProfile.getEloResult();
        int player2Rating =  losePlayer.playProfile.getEloResult();

            double actualScore;

            // winner
            actualScore = 1.0;

            // calculate expected outcome
            double exponent = (double) (player2Rating - player1Rating) / 400;
            double expectedOutcome = (1 / (1 + (Math.pow(10, exponent))));

            // K-factor
            int K = 32;

            // calculate new rating
            int newRating = (int) Math.round(player1Rating + K * (actualScore - expectedOutcome));

            return newRating;
    }
}
