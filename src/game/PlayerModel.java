package game;

import player.PlayerProfile;

public class PlayerModel {
    public boolean black;
    private final PlayerProfile profile;

    public PlayerModel(PlayerProfile profile) {
        this.profile = profile;
    }

    public int getId() {
        return profile.getId();
    }


}
