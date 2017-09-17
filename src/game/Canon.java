package game;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Canon extends Piece {
    public Canon(boolean black) {
        this.black = black;
        value = 32;
        String url = (black ? "black" : "red") + "/c.png";
        this.setImage(new Image(url));
    }

    @Override
    public boolean canMove(Piece[][] M, P to) {
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

    public List<P> getMoves(Piece[][] M) {
        List<P> moves = new ArrayList<>();
        Piece temp;
        int newX, newY;
        boolean hasBullet = false;
        for (newX = x - 1; newX >= 0; newX--) {
            temp = M[y][newX];
            if (hasBullet) {
                if (!sameSide(temp)) moves.add(new P(newX, y));
                break;
            } else {
                if (temp == null) moves.add(new P(newX, y));
                else hasBullet = true;
            }
        }
        hasBullet = false;
        for (newX = x + 1; newX <= Val.MaxX; newX++) {
            temp = M[y][newX];
            if (hasBullet) {
                if (!sameSide(temp)) moves.add(new P(newX, y));
                break;
            } else {
                if (temp == null) moves.add(new P(newX, y));
                else hasBullet = true;
            }
        }
        hasBullet = false;
        for (newY = y - 1; newY >= 0; newY--) {
            temp = M[newY][x];
            if (hasBullet) {
                if (!sameSide(temp)) moves.add(new P(x, newY));
                break;
            } else {
                if (temp == null) moves.add(new P(x, newY));
                else hasBullet = true;
            }
        }
        hasBullet = false;
        for (newY = y + 1; newY <= Val.MaxY; newY++) {
            temp = M[newY][x];
            if (hasBullet) {
                if (!sameSide(temp)) moves.add(new P(x, newY));
                break;
            } else {
                if (temp == null) moves.add(new P(x, newY));
                else hasBullet = true;
            }
        }

        return moves;
    }
 }
