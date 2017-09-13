package game;

import player.PlayerProfile;

public class PlayerModel {
    public final boolean black;
    private final PlayerProfile profile;

    public PlayerModel(boolean black, PlayerProfile profile) {
        this.black = black;
        this.profile = profile;
    }

    public int getId() {
        return profile.getId();
    }


}
