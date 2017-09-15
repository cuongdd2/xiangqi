package game;

import javafx.scene.image.ImageView;

public class Piece extends ImageView {
    protected boolean black;
    protected P pos;
    protected int value;

    public void toPixel(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    public boolean sameSide(Piece p) {
        return p != null && black == p.black;
    }

    public boolean canMove(Piece[][] M, P to) {
        return false;
    }

}
