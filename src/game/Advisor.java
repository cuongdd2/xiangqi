package game;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Advisor extends Piece {
    public Advisor(boolean black) {
        this.black = black;
        value = 11;
        String url = (black ? "black" : "red") + "/a.png";
        this.setImage(new Image(url));
    }

    public List<P> getMovable(){
        List<P> list = new ArrayList<>();
        if (black){
            if ((x + 1) <= 5 && (y + 1) <= 2){
                list.add(new P(x+1, y+1));
            }
            if ((x + 1) <= 5 && (y-1) >=0){
                list.add(new P(x+1, y-1));
            }
            if ((x - 1) >= 3 && (y+1) <=2){
                list.add(new P(x-1, y+1));
            }
            if ((x - 1) >= 3 && (y-1) >=0){
                list.add(new P(x-1, y-1));
            }
        }else {
            if ((x + 1) <= 5 && (y + 1) <= 9){
                list.add(new P(x+1, y+1));
            }
            if ((x + 1) <= 5 && (y-1) >=7){
                list.add(new P(x+1, y-1));
            }
            if ((x - 1) >= 3 && (y+1) <=9){
                list.add(new P(x-1, y+1));
            }
            if ((x - 1) >= 3 && (y-1) >=7){
                list.add(new P(x-1, y-1));
            }
        }

        return list;
    }

    @Override
    public boolean canMove(Piece[][] M, P to) {
        int dx = to.x - x;
        int dy = to.y - y;
        return Math.abs(dx) == 1 && Math.abs(dy) == 1
                && to.x <= 5 && to.x >= 3
                && (black && to.y <= 2 && to.y >= 0
                || !black && to.y >= 7 && to.y <= 9);
    }

    public List<P> getMoves(Piece[][] M) {
        List<P> moves = new ArrayList<>();
        List<P> newMoves = Arrays.asList(
                new P(x + 1, y + 1), new P(x + 1, y - 1),
                new P(x - 1, y + 1), new P(x - 1, y - 1));
        int maxY = black ? 2 : Val.MaxY;
        int minY = black ? 0 : (Val.MaxY - 2);
        for (P p : newMoves) {
            if (p.x < 3 || p.x > 5 || p.y > maxY || p.y < minY) continue;
            if (sameSide(M[p.y][p.x])) continue;
            moves.add(p);
        }
        return moves;
    }
}
