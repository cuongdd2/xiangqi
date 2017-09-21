package game;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Chariot extends Chess {
    private static final int[][] POS_VALUES = {
            {206,208,207,213,214,213,207,208,206},
            {206,212,209,216,233,216,209,212,206},
            {206,208,207,214,216,214,207,208,206},
            {206,213,213,216,216,216,213,213,206},
            {208,211,211,214,215,214,211,211,208},
            {208,212,212,214,215,214,212,212,208},
            {204,209,204,212,214,212,204,209,204},
            {198,208,204,212,212,212,204,208,198},
            {200,208,206,212,200,212,206,208,200},
            {194,206,204,212,200,212,204,206,194}
    };
//    private static final int VALUE = 600;
    private static final int VALUE = 500;
    public int mobility = 7;

    public Chariot(boolean black) {
        super(black);
        value = VALUE;
        String url = (black ? "black" : "red") + "/r.png";
        this.setImage(new Image(url));
    }
    public int threat() {
        return crossRiver() ? 30 : 0;
    }

    public int posValue(P pos) {
        if (black) pos = pos.mirror();
        return POS_VALUES[pos.y][pos.x];
    }

    @Override
    public int getMobility() {
        return mobility;
    }

    @Override
    public boolean canMove(Chess[][] M, P to) {
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

    public List<P> getMoves(Chess[][] M) {
        List<P> moves = new ArrayList<>();
        Chess temp;
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
