import javafx.scene.image.Image;

public class Soldier extends Piece {
    public Soldier(boolean black) {
        this.black = black;
        String url = (black ? "black" : "red") + "/s.png";
        this.setImage(new Image(url));
    }
}
