package game;

import javafx.scene.image.Image;

public class General extends Piece {
    public General(boolean black) {
        this.black = black;
        String url = (black ? "black" : "red") + "/g.png";
        this.setImage(new Image(url));
    }
}
