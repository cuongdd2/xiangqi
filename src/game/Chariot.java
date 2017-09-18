package game;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Chariot extends Piece {
    public Chariot(boolean black) {
        this.black = black;
        value = 100;
        String url = (black ? "black" : "red") + "/r.png";
        this.setImage(new Image(url));
    }

    @Override
    public boolean canMove(Piece[][] M, P to) {
        int minX= 0;
        int minY = 0;
        int maxX = 8;
        int maxY = 9;
        if (to.y == y){
            for (int newX = x + 1; newX <= Val.MaxX; newX++){
                if (M[to.y][newX] != null){
                    maxX = newX;
                    break;

                }
            }
            for (int newX = x - 1; newX >= 0; newX--){
                if (M[to.y][newX] != null){
                    minX = newX;
                    break;
                }
            }
        }
        if (to.x == x){
            for (int newY = y + 1; newY <= Val.MaxY; newY++){
                if (M[newY][to.x] != null){
                    maxY = newY;
                    break;
                }
            }

            for (int newY = y - 1; newY >= 0; newY--){
                if (M[newY][to.x] != null){
                    minY = newY;
                    break;
                }
            }
        }

        return (to.x == x || to.y == y ) && to.x >= minX && to.x <= maxX && to.y >= minY && to.y <= maxY;
    }

    public List<P> getMoves(Piece[][] M) {
        List<P> moves = new ArrayList<>();
        Piece temp;
        int newX, newY;
        for (newX = x - 1; newX >= 0; newX--) {
            temp = M[y][newX];
            if (temp == null) moves.add(new P(newX, y));
            else {
                if (!sameSide(temp)) moves.add(new P(newX, y));
                break;
            }
        }
        for (newX = x + 1; newX <= Val.MaxX; newX++) {
            temp = M[y][newX];
            if (temp == null) moves.add(new P(newX, y));
            else {
                if (!sameSide(temp)) moves.add(new P(newX, y));
                break;
            }
        }
        for (newY = y - 1; newY >= 0; newY--) {
            temp = M[newY][x];
            if (temp == null) moves.add(new P(x, newY));
            else {
                if (!sameSide(temp)) moves.add(new P(x, newY));
                break;
            }
        }
        for (newY = y + 1; newY <= Val.MaxY; newY++) {
            temp = M[newY][x];
            if (temp == null) moves.add(new P(x, newY));
            else {
                if (!sameSide(temp)) moves.add(new P(x, newY));
                break;
            }
        }

        return moves;
    }
}
