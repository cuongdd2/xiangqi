import javafx.scene.image.Image;

public class Chariot extends Piece {
    public Chariot(boolean black) {
        this.black = black;
        String url = (black ? "black" : "red") + "/r.png";
        this.setImage(new Image(url));
    }
}
