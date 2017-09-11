public class Player {
    public int remainderTime;  //second
    PlayProfile playProfile;

    public Player(int remainderTime, PlayProfile playProfile) {
        this.remainderTime = remainderTime;
        this.playProfile = playProfile;
    }

    public int getRemainderTime() {
        return remainderTime;
    }

    public void setRemainderTime(int remainderTime) {
        this.remainderTime = remainderTime;
    }

    public PlayProfile getPlayProfile() {
        return playProfile;
    }

    public void setPlayProfile(PlayProfile playProfile) {
        this.playProfile = playProfile;
    }
}
