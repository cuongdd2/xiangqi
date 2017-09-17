package game;

import player.PlayerProfile;

import javax.inject.Singleton;

@Singleton
public class PlayerModel {
    public boolean black;
    public PlayerProfile profile;

    public PlayerModel(PlayerProfile profile) {
        this.profile = profile;
    }

    public int getId() {
        return profile.getId();
    }


}
