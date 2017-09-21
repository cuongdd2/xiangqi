package game;

import ai.GameState;
import ai.Search;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.event.EventTarget;
import javafx.scene.Group;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Board extends Group {

    GameState gameState = new GameState();
    @Inject PlayerModel playerModel;
    private ImageView selecting = new ImageView(new Image("s70.png"));
    private RotateTransition rt;
    private Media clickSound = new Media(new File("assets/sound/select.wav").toURI().toString());
    private Media moveSound = new Media(new File("assets/sound/move.wav").toURI().toString());
    private Media captureSound = new Media(new File("assets/sound/captured.wav").toURI().toString());
    private Media illegalSound = new Media(new File("assets/sound/illegal.wav").toURI().toString());
    private Media loseSound = new Media(new File("assets/sound/lose.mp3").toURI().toString());
    private Media winSound = new Media(new File("assets/sound/win.mp3").toURI().toString());
    private Media canonSound = new Media(new File("assets/sound/canon.mp3").toURI().toString());
    private Media horseSound = new Media(new File("assets/sound/horse.mp3").toURI().toString());
    private Media bgSound = new Media(new File("assets/sound/bg.mp3").toURI().toString());
    private Media timerSound = new Media(new File("assets/sound/timer.mp3").toURI().toString());
    private ChessSocket socket;
    private boolean thinking = false;

    public Board() throws IOException {
        int core = Runtime.getRuntime().availableProcessors();
        System.out.println("CPU : " + core);
        gameState = new GameState();
        this.getChildren().add(new ImageView(new Image("bg640.jpg")));
        this.draw();
        initCircle();
        selecting.setVisible(false);
//        this.setLayoutX(150);
        this.setOnMouseClicked(event -> {
            if (thinking) return;
            if (gameState.current == null) {
                EventTarget target = event.getTarget();
                if (target instanceof Chess) {
                    Chess chess = (Chess)target;
                    if (chess.black == gameState.isBlack) {
                        P point = getP(event);
                        gameState.setCurrent(point);
                        showSelected(point);

                    }
                }
            } else {
                P p = getP(event);
                hideSelecting();
                if (gameState.canMove(p)) {
                    // move the chess
                    if (gameState.isOnline)
                        socket.sendMsg("move:" + playerModel.getId() + ":" + gameState.current.x + ":" + p);
                    else {
                        move(p);
                        // generate move for black side
                        execAI();
                    }
                } else {
                    gameState.current = null;
                    playSound(illegalSound);
                }
            }
        });
        if (gameState.isOnline) {
            socket.sendMsg("join:" + playerModel.getId());
        }
        playSound(bgSound);
    }


    final Move[] foundMove = new Move[1];
    private void execAI() {
        thinking = true;
//        new Thread("AI") {
//            public void run() {
                long time = System.currentTimeMillis();
//                Move m = Search.bestMove(gameState);
                foundMove[0] = gameState.bestMove();
                thinking = false;
                moveByAI();
//            };
//
//        }.start();

    }

    private void moveByAI() {
        gameState.setCurrent(foundMove[0].from);
        move(foundMove[0].to);
    }


    private void move(P p) {
        boolean isCanon = gameState.current instanceof Canon;
        boolean isHorse = gameState.current instanceof Horse;
        Chess chess = gameState.move(p);
        // remove captured chess
        if (chess != null) {
            if (isCanon) playSound(canonSound);
            else if (isHorse) playSound(horseSound);
            else playSound(captureSound);
            if (chess instanceof General) {
                // Game over
                boolean win = chess.black;
                Dialog dialog = new Dialog();
                dialog.setContentText(win ? "You Win" : "You Lose");
                dialog.show();
                playSound(win ? winSound : loseSound);
            } else getChildren().remove(chess);
        } else playSound(moveSound);
    }

    private void draw() {
        for (int y = 0; y <= Val.MaxY; y++) {
            for (int x = 0; x <= Val.MaxX; x++) {
                Chess n = gameState.M[y][x];
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
        this.getChildren().add(selecting);
        selecting.setFitWidth(44);
        selecting.setFitHeight(44);
        rt = new RotateTransition(Duration.seconds(1), selecting);
        rt.setByAngle(-360);
        rt.setCycleCount(Timeline.INDEFINITE);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.play();
    }

    private void showSelected(P p) {
        selecting.setX(Val.InitX + p.x * Val.NextX - 2);
        selecting.setY(Val.InitY + p.y * Val.NextY - 2);
        this.selecting.setVisible(true);
        playSound(clickSound);
        showMoves(p);
    }
    private List<ImageView> dots = new ArrayList<>();
    private void showMoves(P p) {
        Chess chess = gameState.at(p);
        List<P> moves = chess.getMoves(gameState.M);
        for (P to : moves) {
            ImageView img = new ImageView(new Image("dot.png"));
            getChildren().add(img);
            img.setX(Val.InitX + to.x * Val.NextX + 18);
            img.setY(Val.InitY + to.y * Val.NextY + 16);
            dots.add(img);
        }
    }
    private void hideMoves() {
        for (ImageView img : dots) {
            getChildren().remove(img);
        }
        dots = new ArrayList<>();
    }
    private void hideSelecting() {
        selecting.setVisible(false);
        hideMoves();
    }

    private void playSound(Media snd) {
        MediaPlayer mediaPlayer = new MediaPlayer(snd);
        if (snd == bgSound) {
            mediaPlayer.setVolume(0.1);
            mediaPlayer.setCycleCount(1000);
        }
        mediaPlayer.play();
    }
}
