package game;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.List;

public class Elephant extends Piece {
    public Elephant(boolean black) {
        this.black = black;
        String url = (black ? "black" : "red") + "/e.png";
        this.setImage(new Image(url));
    }


}
