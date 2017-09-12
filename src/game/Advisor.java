package game;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Advisor extends Piece {
    public Advisor(boolean black) {
        this.black = black;
        String url = (black ? "black" : "red") + "/a.png";
        this.setImage(new Image(url));
    }

    public List<P> getMovable(){
        List<P> list = new ArrayList<>();
        if (black){
            if ((pos.x + 1) <= 5 && (pos.y + 1) <= 2){
                list.add(new P(pos.x+1, pos.y+1));
            }
            if ((pos.x + 1) <= 5 && (pos.y-1) >=0){
                list.add(new P(pos.x+1, pos.y-1));
            }
            if ((pos.x - 1) >= 3 && (pos.y+1) <=2){
                list.add(new P(pos.x-1, pos.y+1));
            }
            if ((pos.x - 1) >= 3 && (pos.y-1) >=0){
                list.add(new P(pos.x-1, pos.y-1));
            }
        }else {
            if ((pos.x + 1) <= 5 && (pos.y + 1) <= 9){
                list.add(new P(pos.x+1, pos.y+1));
            }
            if ((pos.x + 1) <= 5 && (pos.y-1) >=7){
                list.add(new P(pos.x+1, pos.y-1));
            }
            if ((pos.x - 1) >= 3 && (pos.y+1) <=9){
                list.add(new P(pos.x-1, pos.y+1));
            }
            if ((pos.x - 1) >= 3 && (pos.y-1) >=7){
                list.add(new P(pos.x-1, pos.y-1));
            }
        }

        return list;
    }
}
