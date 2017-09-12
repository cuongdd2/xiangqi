package game;

import javafx.event.EventTarget;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Board extends Group {

    private BoardModel model = new BoardModel();

    public Board() {
        this.getChildren().add(new ImageView(new Image("bg640.jpg")));
//        this.init();
        this.draw();
        this.setLayoutX(150);
        this.setOnMouseClicked(event -> {
            if (model.current == null) {
                EventTarget target = event.getTarget();
                if (target instanceof Piece) {
                    P point = getP(event);
                    model.current = (Piece)event.getTarget();
                    model.current.pos = point;
                }
            } else {
                EventTarget target = event.getTarget();
                P p = getP(event);
                if (model.canMove(p)) {
                    // move the chess
                    Piece chess = model.move(p);
                    // remove captured chess
                    if (chess != null) getChildren().remove(chess);

                } else model.current = null;
                // check if target is blank or not
                // check if target is alliance or enemy
                // check if this point is movable
            }
        });
    }
    
    private void draw() {
        for (int y = 0; y <= Val.MaxY; y++) {
            for (int x = 0; x <= Val.MaxX; x++) {
                Piece n = model.M[y][x];
                if (n != null) {
                    n.setX(Val.InitX + x * Val.NextX);
                    n.setY(Val.InitY + y * Val.NextY);
                    this.getChildren().add(n);
                }
            }
        }
    }

    private P getP(MouseEvent event) {
        int x = (int)Math.round((event.getX() - Val.InitX - Val.ChessW/2)/Val.NextX);
        int y = (int)Math.round((event.getY() - Val.InitY - Val.ChessW/2)/Val.NextY);
        return new P(x, y);
    }

}
