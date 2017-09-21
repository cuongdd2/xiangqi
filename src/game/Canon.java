package game;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Canon extends Chess {

    private static final int[][] POS_VALUES = {
            {100,100, 96, 91, 90, 91, 96,100,100},
            { 98, 98, 96, 92, 89, 92, 96, 98, 98},
            { 97, 97, 96, 91, 92, 91, 96, 97, 97},
            { 96, 99, 99, 98,100, 98, 99, 99, 96},
            { 96, 96, 96, 96,100, 96, 96, 96, 96},
            { 95, 96, 99, 96,100, 96, 99, 96, 95},
            { 96, 96, 96, 96, 96, 96, 96, 96, 96},
            { 97, 96,100, 99,101, 99,100, 96, 97},
            { 96, 97, 98, 98, 98, 98, 98, 97, 96},
            { 96, 96, 97, 99, 99, 99, 97, 96, 96}
    };
//    private static final int VALUE = 500;
    private static final int VALUE = 350;
    public int mobility = 7;

    public int posValue(P pos) {
        if (black) pos = pos.mirror();
        return POS_VALUES[pos.y][pos.x];
    }

    @Override
    public int getMobility() {
        return mobility;
    }

    public Canon(boolean black) {
        super(black);
        value = VALUE;
        String url = (black ? "black" : "red") + "/c.png";
        this.setImage(new Image(url));
    }
    /** canon at the bottom is more threat than cross river */
    public int threat() {
        return 30 * (black ? (9 - y) : y);
    }

    @Override
    public boolean canMove(Chess[][] M, P to) {
        int dx = to.x - x;
        int dy = to.y - y;
        if (dx != 0 && dy != 0) return false;
        int bullet = 0;
        int step;
        boolean needBullet = M[to.y][to.x] != null;
        if (dy == 0) {
            step = dx / Math.abs(dx);
            for (int newX = x + step; newX - to.x != 0; newX += step) {
                if (M[to.y][newX] != null) bullet++;
            }
        } else {
            step = dy / Math.abs(dy);
            for (int newY = y + step; newY - to.y != 0; newY += step) {
                if (M[newY][to.x] != null) bullet++;
            }
        }
        return (needBullet && bullet == 1) || (!needBullet && bullet == 0);
    }

    public List<P> getMoves(Chess[][] M) {
        List<P> moves = new ArrayList<>();
        Chess temp;
        int newX, newY;
        boolean hasBullet = false;
        for (newX = x - 1; newX >= 0; newX--) {
            temp = M[y][newX];
            if (hasBullet) {
                if (temp != null) {
                    if (!sameSide(temp)) moves.add(new P(newX, y));
                    break;
                }
            } else {
                if (temp == null) moves.add(new P(newX, y));
                else hasBullet = true;
            }
        }
        hasBullet = false;
        for (newX = x + 1; newX <= Val.MaxX; newX++) {
            temp = M[y][newX];
            if (hasBullet) {
                if (temp != null) {
                    if (!sameSide(temp)) moves.add(new P(newX, y));
                    break;
                }
            } else {
                if (temp == null) moves.add(new P(newX, y));
                else hasBullet = true;
            }
        }
        hasBullet = false;
        for (newY = y - 1; newY >= 0; newY--) {
            temp = M[newY][x];
            if (hasBullet) {
                if (temp != null) {
                    if (!sameSide(temp)) moves.add(new P(x, newY));
                    break;
                }
            } else {
                if (temp == null) moves.add(new P(x, newY));
                else hasBullet = true;
            }
        }
        hasBullet = false;
        for (newY = y + 1; newY <= Val.MaxY; newY++) {
            temp = M[newY][x];
            if (hasBullet) {
                if (temp != null) {
                    if (!sameSide(temp)) moves.add(new P(x, newY));
                    break;
                }
            } else {
                if (temp == null) moves.add(new P(x, newY));
                else hasBullet = true;
            }
        }

        return moves;
    }
 }
