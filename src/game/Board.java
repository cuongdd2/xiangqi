package game;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.event.MouseEvent;

public class Board extends Group {

    private static int BG_H = 743;
    private static int InitX = 15;
    private static int InitY = 20;
    private static int NextX = 71;
    private static int NextY = 73;

    private Piece[][] pieces = new Piece[10][9];
    private Piece current;

    public Board() {
        this.getChildren().add(new ImageView(new Image("bg640.jpg")));
        this.init();
        this.draw();
        this.setLayoutX(150);
        this.setOnMouseClicked(event -> {
            if (current == null) {
                current = (Piece)event.getTarget();
            }
        });
    }

    private void init() {
        // Black chess pieces
        pieces[0][0] = new Chariot(true);
        pieces[0][1] = new Horse(true);
        pieces[0][2] = new Elephant(true);
        pieces[0][3] = new Advisor(true);
        pieces[0][4] = new General(true);
        pieces[0][5] = new Advisor(true);
        pieces[0][6] = new Elephant(true);
        pieces[0][7] = new Horse(true);
        pieces[0][8] = new Chariot(true);

        pieces[2][1] = new Chariot(true);
        pieces[2][7] = new Chariot(true);

        pieces[3][0] = new Soldier(true);
        pieces[3][2] = new Soldier(true);
        pieces[3][4] = new Soldier(true);
        pieces[3][6] = new Soldier(true);
        pieces[3][8] = new Soldier(true);

        // Red chess pieces
        pieces[9][0] = new Chariot(false);
        pieces[9][1] = new Horse(false);
        pieces[9][2] = new Elephant(false);
        pieces[9][3] = new Advisor(false);
        pieces[9][4] = new General(false);
        pieces[9][5] = new Advisor(false);
        pieces[9][6] = new Elephant(false);
        pieces[9][7] = new Horse(false);
        pieces[9][8] = new Chariot(false);

        pieces[7][1] = new Chariot(false);
        pieces[7][7] = new Chariot(false);

        pieces[6][0] = new Soldier(false);
        pieces[6][2] = new Soldier(false);
        pieces[6][4] = new Soldier(false);
        pieces[6][6] = new Soldier(false);
        pieces[6][8] = new Soldier(false);
    }
    
    private void draw() {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 9; x++) {
                Piece n = pieces[y][x];
                if (n != null) {
                    n.setX(InitX + x * NextX);
                    n.setY(InitY + y * NextY);
                    this.getChildren().add(n);
                }
            }
        }
    }

    private P getP(MouseEvent event) {
        int x = (event.getX() - InitX)/NextX;
        int y = (event.getY() - InitY)/NextY;
        return new P(x, y);
    }

}
