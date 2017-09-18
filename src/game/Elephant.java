package game;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Elephant extends Piece {
    public Elephant(boolean black) {
        this.black = black;
        value = 20;
        String url = (black ? "black" : "red") + "/e.png";
        this.setImage(new Image(url));
    }

    @Override
    public boolean canMove(Piece[][] M, P to) {
        if (black && to.y > 4) return false;
        if (!black && to.y < 5) return false;
        int dx = to.x - x;
        int dy = to.y - y;
        // check block by other piece
        return M[y + dy / 2][x + dx / 2] == null && Math.abs(dx) == 2 && Math.abs(dy) == 2;
    }

    public List<P> getMoves(Piece[][] M) {
        List<P> moves = new ArrayList<>();
        List<P> newMoves = Arrays.asList(
                new P(x + 2, y + 2), new P(x + 2, y - 2),
                new P(x - 2, y + 2), new P(x - 2, y - 2));
        int maxY = black ? 4 : Val.MaxY;
        int minY = black ? 0 : 5;
        for (P p : newMoves) {
            if (p.x < 0 || p.x > Val.MaxX || p.y < minY || p.y > maxY) continue;
            if (M[(p.y + y)/2][(p.x + x)/2] != null) continue;
            if (sameSide(M[p.y][p.x])) continue;
            moves.add(p);
        }
        return moves;
    }

}
