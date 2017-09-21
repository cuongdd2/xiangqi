package game;

public class Move implements Comparable<Move> {
    public final P from;
    public final P to;
    public Chess captured;
    public Chess chess;
    public int score;// add for compatible

    public Move(P from, P to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return from + " --> " + to;
    }

    @Override
    public int compareTo(Move o) {
        return chess.value - o.chess.value;
    }
}
