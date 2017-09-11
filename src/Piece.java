import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Piece extends ImageView {
    protected boolean black;


    public Piece() {
        this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ((Piece)event.getTarget()).draw();
            }
        });
    }

    public void draw() {
    }
    public void move(int x, int y) {}

}
