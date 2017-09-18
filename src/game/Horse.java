package game;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Horse extends Piece {
    public Horse(boolean black) {
        this.black = black;
        value = 43;
        String url = (black ? "black" : "red") + "/h.png";
        this.setImage(new Image(url));
    }

    public boolean canMove(Piece[][] pieces, P to) {
        int dx = to.x - x;
        int dy = to.y - y;
        if (pieces[y + dy/2][x + dx/2] != null) return false;
        dx = Math.abs(dx);
        dy = Math.abs(dy);
        return (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
    }

    public List<P> getMoves(Piece[][] M) {
        List<P> moves = new ArrayList<>();
        List<P> newMoves = Arrays.asList(
                new P(x + 2, y + 1), new P(x + 2, y - 1),
                new P(x - 2, y + 1), new P(x - 2, y - 1),
                new P(x + 1, y + 2), new P(x + 1, y - 2),
                new P(x - 1, y + 2), new P(x - 1, y - 2));
        for (P p : newMoves) {
            if (!p.isValid()) continue;
            if(M[(p.y + y)/2][(p.x + x)/2] != null) continue;
            if(sameSide(M[p.y][p.x])) continue;
            moves.add(p);
        }
        return moves;
    }
}
