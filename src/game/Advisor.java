package game;

import javafx.scene.image.Image;

public class Advisor extends Piece {
    public Advisor(boolean black) {
        this.black = black;
        String url = (black ? "black" : "red") + "/a.png";
        this.setImage(new Image(url));
    }
}
