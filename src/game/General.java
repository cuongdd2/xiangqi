package game;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class General extends Piece {
    public General(boolean black) {
        this.black = black;
        value = 9999;
        String url = (black ? "black" : "red") + "/g.png";
        this.setImage(new Image(url));
    }

    @Override
    public boolean canMove(Piece[][] M, P to) {
        if (to.x < 3 || to.x > 5) return false;
        // TODO: make General can move vertical to kill enemy's General
        if (black && to.y > 2) return false;
        if (!black && to.y < 7) return false;
        int dx = Math.abs(to.x - x);
        int dy = Math.abs(to.y - y);
        return dx + dy == 1;
    }

    public List<P> getMoves(Piece[][] M) {
        List<P> moves = new ArrayList<>();
        List<P> newMoves = Arrays.asList(
                new P(x + 1, y), new P(x - 1, y),
                new P(x, y + 1), new P(x, y - 1));
        int maxY = black ? 2 : Val.MaxY;
        int minY = black ? 0 : (Val.MaxY - 2);
        for (P p : newMoves) {
            if (p.x < 3 || p.x > 5 || p.y > maxY || p.y < minY) continue;
            if (sameSide(M[p.y][p.x])) continue;
            moves.add(p);
        }
        P p = canFly(M);
        if (p != null) moves.add(p);
        return moves;
    }

    private P canFly(Piece[][] M) {
        int fromY = black ? y + 1 : y - 1;
        int toY = black ? Val.MaxY : 0;
        int count = 0;
        for (int i = fromY; i != toY; i += fromY - y) {
            if (M[i][x] != null) {
                if((M[i][x] instanceof General)) return new P(x, i);
                else return null;
            }
        }
        return null;
    }
}
