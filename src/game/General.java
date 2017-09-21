package game;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class General extends Chess {
    private static final int[][] POS_VALUES = {
            { 9,  9,  9, 11, 13, 11,  9,  9,  9},
            {19, 24, 34, 42, 44, 42, 34, 24, 19},
            {19, 24, 32, 37, 37, 37, 32, 24, 19},
            {19, 23, 27, 29, 30, 29, 27, 23, 19},
            {14, 18, 20, 27, 29, 27, 20, 18, 14},
            { 7,  0, 13,  0, 16,  0, 13,  0,  7},
            { 7,  0,  7,  0, 15,  0,  7,  0,  7},
            { 0,  0,  0,  1,  1,  1,  0,  0,  0},
            { 0,  0,  0,  2,  2,  2,  0,  0,  0},
            { 0,  0,  0,  5, 15,  5,  0,  0,  0}
    };
//    private static final int VALUE = 1000000;
    private static final int VALUE = 10000;
    public int mobility = 0;

    public int posValue(P pos) {
        if (black) pos = pos.mirror();
        return POS_VALUES[pos.y][pos.x];
    }

    @Override
    public int getMobility() {
        return mobility;
    }

    public General(boolean black) {
        super(black);
        value = VALUE;
        String url = (black ? "black" : "red") + "/g.png";
        this.setImage(new Image(url));
    }

    @Override
    public boolean canMove(Chess[][] M, P to) {
        if (to.x < 3 || to.x > 5) return false;
        // TODO: make General can move vertical to kill enemy's General
        if (black && to.y > 2) return false;
        if (!black && to.y < 7) return false;
        int dx = Math.abs(to.x - x);
        int dy = Math.abs(to.y - y);
        return dx + dy == 1;
    }

    public List<P> getMoves(Chess[][] M) {
        List<P> moves = new ArrayList<>();
        List<P> newMoves = Arrays.asList(
                new P(x + 1, y), new P(x - 1, y),
                new P(x, y + 1), new P(x, y - 1));
        int maxY = black ? 2 : Val.MaxY;
        int minY = black ? 0 : (Val.MaxY - 2);
        for (P p : newMoves) {
            if (p.x < 3 || p.x > 5 || p.y > maxY || p.y < minY) continue;
            if (sameSide(M[p.y][p.x])) continue;
            moves.add(p);
        }
//        int fromY = y < 4 ? (y + 1) : (y - 1);
//        int toY = y < 4 ? Val.MaxY : 0;
//        for (int i = fromY; i != toY; i += fromY - y) {
//            if (M[i][x] != null) {
//                if((M[i][x] instanceof General)) moves.add(new P(x, i));
//                break;
//            }
//        }
        return moves;
    }
}
