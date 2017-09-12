package game;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Soldier extends Piece {
    public Soldier(boolean black) {
        this.black = black;
        String url = (black ? "black" : "red") + "/s.png";
        this.setImage(new Image(url));
    }

    public boolean crossRiver() {
        return (black && pos.y > 4) || pos.y < 5;
    }

    public List<P> getMovable() {
        List<P> list = new ArrayList<>();
        if (black) {
            if(pos.y < Val.MaxY) list.add(new P(pos.x, pos.y + 1));
        } else {
            if (pos.y > 0) list.add(new P(pos.x, pos.y - 1));
        }
        if (crossRiver()) {
            if (pos.x > 0) list.add(new P(pos.x - 1, pos.y));
            if (pos.x < Val.MaxX) list.add(new P(pos.x + 1, pos.y));
        }

        return list;
    }
}
