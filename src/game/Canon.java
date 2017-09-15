package game;

import javafx.scene.image.Image;

public class Canon extends Piece {
    public Canon(boolean black) {
        this.black = black;
        value = 32;
        String url = (black ? "black" : "red") + "/c.png";
        this.setImage(new Image(url));
    }

    @Override
    public boolean canMove(Piece[][] M, P to) {
        int dx = to.x - pos.x;
        int dy = to.y - pos.y;
        if (dx != 0 && dy != 0) return false;
        int bullet = 0;
        int step;
        boolean needBullet = M[to.y][to.x] != null;
        if (dy == 0) {
            step = dx / Math.abs(dx);
            for (int x = pos.x + step; x - to.x != 0; x += step) {
                if (M[to.y][x] != null) bullet++;
            }
        } else {
            step = dy / Math.abs(dy);
            for (int y = pos.y + step; y - to.y != 0; y += step) {
                if (M[y][to.x] != null) bullet++;
            }
        }
        return (needBullet && bullet == 1) || (!needBullet && bullet == 0);
    }
}
