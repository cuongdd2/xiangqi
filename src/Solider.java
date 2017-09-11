import javafx.scene.image.Image;

public class Solider extends Piece {
    public Solider(boolean black) {
        this.black = black;
        String url = (black ? "black" : "red") + "/s.png";
        this.setImage(new Image(url));
    }
}
