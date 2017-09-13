package game;

public class P {
    public final int x;
    public final int y;

    public P(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof P)) return false;
        P p = (P)o;
        return x == p.x && y == p.y;
    }

    @Override
    public String toString() {
        return x + "," + y;
    }
}
