import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Board extends Group {

    private static int BG_H = 743;

    private Piece[][] pieces = new Piece[10][9];

    public Board() {
        this.getChildren().add(new ImageView(new Image("bg640.jpg")));
        this.init();
        this.draw();
        this.setLayoutY((Game.H - BG_H) / 2);
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

        pieces[3][0] = new Chariot(true);
        pieces[3][2] = new Elephant(true);
        pieces[3][4] = new General(true);
        pieces[3][6] = new Elephant(true);
        pieces[3][8] = new Chariot(true);

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

        pieces[6][0] = new Chariot(false);
        pieces[6][2] = new Elephant(false);
        pieces[6][4] = new General(false);
        pieces[6][6] = new Elephant(false);
        pieces[6][8] = new Chariot(false);
    }
    
    private void draw() {
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 9; x++) {
                Piece n = pieces[y][x];
                if (n != null) {
                    n.setX(15 + x * 71);
                    n.setY(20 + y * 73);
                    this.getChildren().add(n);
                }
            }
        }
    }

}
