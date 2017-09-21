package game;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Horse extends Chess {
    private static final int[][] POS_VALUES = {
            {90, 90, 90, 96, 90, 96, 90, 90, 90},
            {90, 96,103, 97, 94, 97,103, 96, 90},
            {92, 98, 99,103, 99,103, 99, 98, 92},
            {93,108,100,107,100,107,100,108, 93},
            {90,100, 99,103,104,103, 99,100, 90},
            {90, 98,101,102,103,102,101, 98, 90},
            {92, 94, 98, 95, 98, 95, 98, 94, 92},
            {93, 92, 94, 95, 92, 95, 94, 92, 93},
            {85, 90, 92, 93, 78, 93, 92, 90, 85},
            {88, 85, 90, 88, 90, 88, 90, 85, 88}
    };
//    private static final int VALUE = 300;
    private static final int VALUE = 350;
    public int mobility = 13;

    public Horse(boolean black) {
        super(black);
        value = VALUE;
        String url = (black ? "black" : "red") + "/h.png";
        this.setImage(new Image(url));
    }
    public int threat() {
        return crossRiver() ? 40 : 0;
    }

    public int posValue(P pos) {
        if (black) pos = pos.mirror();
        return POS_VALUES[pos.y][pos.x];
    }

    @Override
    public int getMobility() {
        return mobility;
    }

    public boolean canMove(Chess[][] chess, P to) {
        int dx = to.x - x;
        int dy = to.y - y;
        if (chess[y + dy/2][x + dx/2] != null) return false;
        dx = Math.abs(dx);
        dy = Math.abs(dy);
        return (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
    }

    public List<P> getMoves(Chess[][] M) {
        List<P> moves = new ArrayList<>();
        List<P> newMoves = Arrays.asList(
                new P(x + 2, y + 1), new P(x + 2, y - 1),
                new P(x - 2, y + 1), new P(x - 2, y - 1),
                new P(x + 1, y + 2), new P(x + 1, y - 2),
                new P(x - 1, y + 2), new P(x - 1, y - 2));
        for (P p : newMoves) {
            if (!p.isValid()) continue;
            if(sameSide(M[p.y][p.x])) continue;
            if(M[y + (p.y - y)/2][x + (p.x - x)/2] != null) continue;
            moves.add(p);
        }
        return moves;
    }
}
