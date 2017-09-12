package game;

import javafx.scene.image.Image;

public class General extends Piece {
    public General(boolean black) {
        this.black = black;
        String url = (black ? "black" : "red") + "/g.png";
        this.setImage(new Image(url));
    }

    @Override
    public boolean canMove(Piece[][] M, P to) {
        if (to.x < 3 || to.x > 5) return false;
        if (black && to.y > 2) return false;
        if (!black && to.y < 7) return false;
        int dx = Math.abs(to.x - pos.x);
        int dy = Math.abs(to.y - pos.y);
        return dx + dy == 1;
    }
}
