package player;

public class PlayerProfile {
    private int id;
    private String name;
    private double winRate;
    private int eloResult;

    public PlayerProfile(int id, String name, double winRate, int eloResult) {
        this.id = id;
        this.name = name;
        this.winRate = winRate;
        this.eloResult = eloResult;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getWinRate() {
        return winRate;
    }

    public void setWinRate(double winRate) {
        this.winRate = winRate;
    }

    public int getEloResult() {
        return eloResult;
    }

    public void setEloResult(int eloResult) {
        this.eloResult = eloResult;
    }
}
