package game;

import javafx.scene.image.Image;

public class Horse extends Piece {
    public Horse(boolean black) {
        this.black = black;
        String url = (black ? "black" : "red") + "/h.png";
        this.setImage(new Image(url));
    }
}
