package game;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Piece extends ImageView {
    protected boolean black;
    private boolean selected;
    private P pos;


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

    public void draw() {
    }
    public void move(int x, int y) {}

}
