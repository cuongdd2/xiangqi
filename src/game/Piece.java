package game;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Piece extends ImageView {
    protected boolean black;
    private boolean selected;
    protected P pos;


//    public game.Piece(game.P pos) {
//        this.pos = pos;
//    }

    class ClickHandler implements EventHandler<MouseEvent> {

        @Override
        public void handle(MouseEvent event) {
            if (!selected) selected = true;
            else {

            }
        }
    }

    public void move(int x, int y) {}
    public boolean sameSide(Piece p) {
        if (p == null) return false;
        return black == p.black;
    }

}
