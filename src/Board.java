import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Board extends Group {

    private static int BG_H = 743;
    private static int FIRST_ROW = 25;
    private static int SECOND_ROW = 172;
    private static int THIRD_ROW = 245;

    private Piece[][] pieces = new Piece[9][10];

    public Board() {
        ImageView bg = new ImageView();
        bg.setImage(new Image("bg640.jpg"));
        this.getChildren().add(bg);
        this.initChessPieces();
        this.setLayoutY((Game.H - BG_H) / 2);
    }

    private void init() {
        // Black chess pieces
        pieces[0][0] = new Chariot(true);
        pieces[1][0] = new Horse(true);
        pieces[2][0] = new Elephant(true);
        pieces[3][0] = new Advisor(true);
        pieces[4][0] = new General(true);
        pieces[5][0] = new Advisor(true);
        pieces[6][0] = new Elephant(true);
        pieces[7][0] = new Horse(true);
        pieces[8][0] = new Chariot(true);

        pieces[1][2] = new Chariot(true);
        pieces[7][2] = new Chariot(true);

        pieces[0][3] = new Chariot(true);
        pieces[2][3] = new Elephant(true);
        pieces[4][3] = new General(true);
        pieces[6][3] = new Elephant(true);
        pieces[8][3] = new Chariot(true);

        // Red chess pieces
        pieces[0][9] = new Chariot(false);
        pieces[1][9] = new Horse(false);
        pieces[2][9] = new Elephant(false);
        pieces[3][9] = new Advisor(false);
        pieces[4][9] = new General(false);
        pieces[5][9] = new Advisor(false);
        pieces[6][9] = new Elephant(false);
        pieces[7][9] = new Horse(false);
        pieces[8][9] = new Chariot(false);

        pieces[1][7] = new Chariot(false);
        pieces[7][7] = new Chariot(false);

        pieces[0][6] = new Chariot(false);
        pieces[2][6] = new Elephant(false);
        pieces[4][6] = new General(false);
        pieces[6][6] = new Elephant(false);
        pieces[8][6] = new Chariot(false);
    }
    
    private void draw() {
        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 10; x++) {
                
            }
        }
        for (Piece[] row : pieces) {
            for (Piece p : row) {
                
            }
        }
    }


    private void initChessPieces() {
        for (int i = 0; i < 2; i++) {
            Piece r = new Chariot(true);
            r.setX(15 + i * 570);
            Piece h = new Horse(true);
            h.setX(85 + i * 430);
            Piece e = new Elephant(true);
            e.setX(155 + i * 290);
            Piece a = new Advisor(true);
            a.setX(230 + i * 140);
            r.setY(FIRST_ROW);
            h.setY(FIRST_ROW);
            e.setY(FIRST_ROW);
            a.setY(FIRST_ROW);

            Piece c = new Canon(true);
            c.setX(85 + i * 430);
            c.setY(SECOND_ROW);
            this.getChildren().addAll(c, r, h, e, a);
        }
        Piece g = new General(true);
        g.setX(300);
        g.setY(FIRST_ROW);
        this.getChildren().add(g);

        for (int i = 0; i < 5; i++) {
            Piece s = new Solider(true);
            s.setX(15 + i * 143);
            s.setY(THIRD_ROW);
            this.getChildren().add(s);
        }


    }

}
