package game;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Chariot extends Piece {
    public Chariot(boolean black) {
        this.black = black;
        String url = (black ? "black" : "red") + "/r.png";
        this.setImage(new Image(url));
    }

    @Override
    public boolean canMove(Piece[][] M, P to) {
        int minX= 0;
        int minY = 0;
        int maxX = 8;
        int maxY = 9;
        if (to.y == pos.y){
            for (int x = pos.x + 1; x <= Val.MaxX;x++){
                if (M[to.y][x] != null){
                    maxX = x;
                    break;

                }
            }
            for (int x = pos.x -1; x >= 0; x--){
                if (M[to.y][x] != null){
                    minX = x;
                    break;
                }
            }
        }
        if (to.x == pos.x){
            for (int y= pos.y + 1; y <= Val.MaxY; y++){
                if (M[y][to.x] != null){
                    maxY = y;
                    break;
                }
            }

            for (int y= pos.y - 1; y >= 0; y--){
                if (M[y][to.x] != null){
                    minY = y;
                    break;
                }
            }
        }

        return (to.x == pos.x || to.y == pos.y ) && to.x >= minX && to.x <= maxX && to.y >= minY && to.y <= maxY;
    }
}
