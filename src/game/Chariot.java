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

    public List<P> getMovable(){
        List<P> list = new ArrayList<>();
        for (int x = 0; x <= Val.MaxX;x++){
            for (int y=0;y<Val.MaxY;y++){
                list.add(new P(x,y));
            }
        }

        return list;
    }


}
