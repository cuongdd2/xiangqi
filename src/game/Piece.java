package game;

import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Piece extends ImageView {
    protected boolean black;
    private P pos;
    protected int x;
    protected int y;
    protected int value;

    public void setPosition(P p) {
        pos = p;
        x = p.x;
        y = p.y;
    }

    public P getPosition() {
        return pos;
    }

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

    public List<P> getMoves(Piece[][] M) {
        return new ArrayList<>();
    }
}
