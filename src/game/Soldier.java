package game;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Soldier extends Piece {

    public Soldier(boolean black) {
        this.black = black;
        value = 7;
        String url = (black ? "black" : "red") + "/s.png";
        this.setImage(new Image(url));
    }

    private boolean crossRiver() {
        return (black && y > 4) || (!black && y < 5);
    }
    public boolean canMove(Piece[][] M, P to) {
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

    public List<P> getMoves(Piece[][] M) {
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
