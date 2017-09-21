package game;

import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public abstract class Chess extends ImageView implements Cloneable {
    public boolean black = true;
    private P pos;
    public String type;
    public int x;
    public int y;
    public int value;
//    public int mobility;

    public Chess(boolean black) {
        this.black = black;
    }

    public boolean isRed() { return !black; }
    abstract public int posValue(P pos);
    abstract public int getMobility();

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

    public boolean crossRiver() {
        return (black && y > 4) || (!black && y < 5);
    }
    public int threat() {
        return 0;
    }

    public boolean sameSide(Chess p) {
        return p != null && black == p.black;
    }

    public boolean canMove(Chess[][] M, P to) {
        return false;
    }

    public List<P> getMoves(Chess[][] M) {
        return new ArrayList<>();
    }

    public Chess clone() {
        Chess c = null;
        try {
            c = (Chess)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return c;
    }
}
