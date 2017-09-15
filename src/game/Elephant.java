package game;

import javafx.scene.image.Image;

public class Elephant extends Piece {
    public Elephant(boolean black) {
        this.black = black;
        value = 11;
        String url = (black ? "black" : "red") + "/e.png";
        this.setImage(new Image(url));
    }

    @Override
    public boolean canMove(Piece[][] M, P to) {
        if (black && to.y > 4) return false;
        if (!black && to.y < 5) return false;
        int dx = to.x - pos.x;
        int dy = to.y - pos.y;
        // check block by other piece
        return M[pos.y + dy / 2][pos.x + dx / 2] == null && Math.abs(dx) == 2 && Math.abs(dy) == 2;
    }

}
