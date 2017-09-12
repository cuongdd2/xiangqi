package game;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class Piece extends ImageView {
    protected boolean black;
    private boolean selected;
    protected P pos;

    public Piece() {
        setOnMouseClicked(event -> {

        });
    }

    public void toPixel(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    public boolean sameSide(Piece p) {
        if (p == null) return false;
        return black == p.black;
    }

    public boolean canMove(Piece[][] M, P to) {
        return false;
    }

}
