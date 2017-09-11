package game;

import javafx.scene.image.Image;

public class Elephant extends Piece {
    public Elephant(boolean black) {
        this.black = black;
        String url = (black ? "black" : "red") + "/e.png";
        this.setImage(new Image(url));
    }
}
