package game;

public class Move {
    public final P from;
    public final P to;
    public Piece captured;

    public Move(P from, P to) {
        this.from = from;
        this.to = to;
    }
}
