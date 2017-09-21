package game;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Soldier extends Chess {
//    private static final int[][] POS_VALUES = {
//            { 9,  9,  9, 11, 13, 11,  9,  9,  9},
//            {19, 24, 34, 42, 44, 42, 34, 24, 19},
//            {19, 24, 32, 37, 37, 37, 32, 24, 19},
//            {19, 23, 27, 29, 30, 29, 27, 23, 19},
//            {14, 18, 20, 27, 29, 27, 20, 18, 14},
//            { 7,  0, 13,  0, 16,  0, 13,  0,  7},
//            { 7,  0,  7,  0, 15,  0,  7,  0,  7},
//            { 0,  0,  0,  1,  1,  1,  0,  0,  0},
//            { 0,  0,  0,  2,  2,  2,  0,  0,  0},
//            { 0,  0,  0, 11, 15, 11,  0,  0,  0}
//    };
    private static final int[][] POS_VALUES = {
            { 0,  0,  0,  0,  0,  0,  0,  0,  0},
            {90, 90,110,120,120,120,110, 90, 90},
            {90, 90,110,120,120,120,110, 90, 90},
            {70, 90,110,110,110,110,110, 90, 70},
            {70, 70, 70, 70, 70, 70, 70, 70, 70},
            { 0,  0,  0,  0,  0,  0,  0,  0,  0},
            { 0,  0,  0,  0,  0,  0,  0,  0,  0},
            { 0,  0,  0,  0,  0,  0,  0,  0,  0},
            { 0,  0,  0,  0,  0,  0,  0,  0,  0},
            { 0,  0,  0,  0,  0,  0,  0,  0,  0}
    };
//    private static final int VALUE = 70;
    private static final int VALUE = 70;
    public int mobility = 15;

    public int posValue(P pos) {
        if (black) pos = pos.mirror();
        return POS_VALUES[pos.y][pos.x];
    }

    @Override
    public int getMobility() {
        return mobility;
    }

    public Soldier(boolean black) {
        super(black);
        value = VALUE;
        String url = (black ? "black" : "red") + "/s.png";
        this.setImage(new Image(url));
    }

    public int threat() {
        return crossRiver() ? 10 : 0;
    }

    public boolean canMove(Chess[][] M, P to) {
        int dx = Math.abs(to.x - x);
        int dy = to.y - y;
        if (crossRiver()) {
            if (dx > 1) return false;
        } else {
            if (dx != 0) return false;
        }
        if (dx == 0) {
            if (black) return dy == 1;
            else return dy == -1;
        } else return dy == 0;
    }

    public List<P> getMoves(Chess[][] M) {
        List<P> moves = new ArrayList<>();
        if (black) {
            if (y < Val.MaxY && !sameSide(M[y + 1][x])) moves.add(new P(x, y + 1));
        } else {
            if (y > 0 && !sameSide(M[y - 1][x])) moves.add(new P(x, y - 1));
        }
        if (crossRiver()) {
            if (x < Val.MaxX && !sameSide(M[y][x + 1])) moves.add(new P(x + 1, y));
            if (x > 0 && !sameSide(M[y][x - 1])) moves.add(new P(x - 1, y));
        }
        return moves;
    }
}
