package game;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.event.EventTarget;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

public class Board extends Group {

    private BoardModel model = new BoardModel();
    private ImageView selecting = new ImageView(new Image("s70.png"));
    private RotateTransition rt;
    private String musicFile = "assets/sound/select.wav";
    private Media sound = new Media(new File(musicFile).toURI().toString());

    public Board() {
        this.getChildren().add(new ImageView(new Image("bg640.jpg")));
        this.draw();
        this.getChildren().add(selecting);
        selecting.setFitWidth(60);
        selecting.setFitHeight(60);
        initCircle();
        selecting.setVisible(false);
        this.setLayoutX(150);
        this.setOnMouseClicked(event -> {
            if (model.current == null) {
                EventTarget target = event.getTarget();
                if (target instanceof Piece) {
                    P point = getP(event);
                    model.current = (Piece)event.getTarget();
                    model.current.pos = point;
                    showSelected(point);
                }
            } else {
                EventTarget target = event.getTarget();
                P p = getP(event);
                hideSelecting();
                if (model.canMove(p)) {
                    // move the chess
                    Piece chess = model.move(p);
                    // remove captured chess
                    if (chess != null) getChildren().remove(chess);

                } else model.current = null;
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

    private void initCircle() {
        rt = new RotateTransition(Duration.seconds(1), selecting);
        rt.setByAngle(360);
        rt.setCycleCount(Timeline.INDEFINITE);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.play();
    }

    private void showSelected(P p) {
        selecting.setX(Val.InitX + p.x * Val.NextX - 5);
        selecting.setY(Val.InitY + p.y * Val.NextY - 8);
        this.selecting.setVisible(true);

        // MediaPlayer need to be created after it played
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();
    }
    private void hideSelecting() {
        selecting.setVisible(false);
    }

}
