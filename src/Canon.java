import javafx.scene.image.Image;

public class Canon extends Piece {
    public Canon(boolean black) {
        this.black = black;
        String url = (black ? "black" : "red") + "/c.png";
        this.setImage(new Image(url));
    }
}
