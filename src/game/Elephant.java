package game;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Elephant extends Chess {
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

    public Elephant(boolean black) {
        super(black);
        value = VALUE;
        String url = (black ? "black" : "red") + "/e.png";
        this.setImage(new Image(url));
    }

    @Override
    public boolean canMove(Chess[][] M, P to) {
        if (black && to.y > 4) return false;
        if (!black && to.y < 5) return false;
        int dx = to.x - x;
        int dy = to.y - y;
        // check block by other piece
        return M[y + dy / 2][x + dx / 2] == null && Math.abs(dx) == 2 && Math.abs(dy) == 2;
    }

    public List<P> getMoves(Chess[][] M) {
        List<P> moves = new ArrayList<>();
        List<P> newMoves = Arrays.asList(
                new P(x + 2, y + 2), new P(x + 2, y - 2),
                new P(x - 2, y + 2), new P(x - 2, y - 2));
        int maxY = black ? 4 : Val.MaxY;
        int minY = black ? 0 : 5;
        for (P p : newMoves) {
            if (p.x < 0 || p.x > Val.MaxX || p.y < minY || p.y > maxY) continue;
            if (M[(p.y + y)/2][(p.x + x)/2] != null) continue;
            if (sameSide(M[p.y][p.x])) continue;
            moves.add(p);
        }
        return moves;
    }

}
