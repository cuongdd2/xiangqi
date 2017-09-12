package game;

import javafx.scene.image.Image;

import java.util.List;

public class Horse extends Piece {
    public Horse(boolean black) {
        this.black = black;
        String url = (black ? "black" : "red") + "/h.png";
        this.setImage(new Image(url));
    }

    public List<P> getMovable(Piece[][] pieces, P to) {

        return null;
    }

    public boolean canMove(Piece[][] pieces, P to) {
        int dx = to.x - pos.x;
        int dy = to.y - pos.y;
        if (pieces[pos.y + dy/2][pos.x + dx/2] != null) return false;
        dx = Math.abs(dx);
        dy = Math.abs(dy);
        return (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
    }
}
