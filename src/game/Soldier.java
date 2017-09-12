package game;

import javafx.scene.image.Image;

public class Soldier extends Piece {
    public Soldier(boolean black) {
        this.black = black;
        String url = (black ? "black" : "red") + "/s.png";
        this.setImage(new Image(url));
    }

    private boolean crossRiver() {
        return (black && pos.y > 4) || (!black && pos.y < 5);
    }

    public boolean canMove(Piece[][] M, P to) {
        int dx = Math.abs(to.x - pos.x);
        int dy = to.y - pos.y;
        if (crossRiver()) {
            if (dx != 0) return false;
            if (black && dy != 1) return false;
            if (!black && dy != -1) return false;
        } else {
            if (dx != 0) return false;
        }

        return dx + Math.abs(dy) == 1;
    }
}
