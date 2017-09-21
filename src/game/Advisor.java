package game;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Advisor extends Chess {
    private static final int[][] POS_VALUES = {
            { 0,  0,  0,  0,  0,  0,  0,  0,  0},
            { 0,  0,  0,  0,  0,  0,  0,  0,  0},
            { 0,  0,  0,  0,  0,  0,  0,  0,  0},
            { 0,  0,  0,  0,  0,  0,  0,  0,  0},
            { 0,  0,  0,  0,  0,  0,  0,  0,  0},
            { 0,  0, 20,  0,  0,  0, 20,  0,  0},
            { 0,  0,  0,  0,  0,  0,  0,  0,  0},
            {18,  0,  0, 20, 23, 20,  0,  0, 18},
            { 0,  0,  0,  0, 23,  0,  0,  0,  0},
            { 0,  0, 20, 20,  0, 20, 20,  0,  0}
    };
//    private static final int VALUE = 110;
    private static final int VALUE = 250;
    public int mobility = 1;

    public int posValue(P pos) {
        if (black) pos = pos.mirror();
        return POS_VALUES[pos.y][pos.x];
    }

    @Override
    public int getMobility() {
        return mobility;
    }

    public Advisor(boolean black) {
        super(black);
        value = VALUE;
        String url = (black ? "black" : "red") + "/a.png";
        this.setImage(new Image(url));
    }

    @Override
    public boolean canMove(Chess[][] M, P to) {
        int dx = to.x - x;
        int dy = to.y - y;
        return Math.abs(dx) == 1 && Math.abs(dy) == 1
                && to.x <= 5 && to.x >= 3
                && (black && to.y <= 2 && to.y >= 0
                || !black && to.y >= 7 && to.y <= 9);
    }

    public List<P> getMoves(Chess[][] M) {
        List<P> moves = new ArrayList<>();
        List<P> newMoves = Arrays.asList(
                new P(x + 1, y + 1), new P(x + 1, y - 1),
                new P(x - 1, y + 1), new P(x - 1, y - 1));
        int maxY = black ? 2 : Val.MaxY;
        int minY = black ? 0 : (Val.MaxY - 2);
        for (P p : newMoves) {
            if (p.x < 3 || p.x > 5 || p.y > maxY || p.y < minY) continue;
            if (sameSide(M[p.y][p.x])) continue;
            moves.add(p);
        }
        return moves;
    }
}
