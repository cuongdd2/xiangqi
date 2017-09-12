package game;

import java.util.List;

public class BoardModel {

    public Piece[][] M = new Piece[10][9];
    public Piece current;

    public BoardModel() {
        init();
    }

    private void init() {
        // Black chess pieces
        M[0][0] = new Chariot(true);
        M[0][1] = new Horse(true);
        M[0][2] = new Elephant(true);
        M[0][3] = new Advisor(true);
        M[0][4] = new General(true);
        M[0][5] = new Advisor(true);
        M[0][6] = new Elephant(true);
        M[0][7] = new Horse(true);
        M[0][8] = new Chariot(true);

        M[2][1] = new Canon(true);
        M[2][7] = new Canon(true);

        M[3][0] = new Soldier(true);
        M[3][2] = new Soldier(true);
        M[3][4] = new Soldier(true);
        M[3][6] = new Soldier(true);
        M[3][8] = new Soldier(true);

        // Red chess pieces
        M[9][0] = new Chariot(false);
        M[9][1] = new Horse(false);
        M[9][2] = new Elephant(false);
        M[9][3] = new Advisor(false);
        M[9][4] = new General(false);
        M[9][5] = new Advisor(false);
        M[9][6] = new Elephant(false);
        M[9][7] = new Horse(false);
        M[9][8] = new Chariot(false);

        M[7][1] = new Canon(false);
        M[7][7] = new Canon(false);

        M[6][0] = new Soldier(false);
        M[6][2] = new Soldier(false);
        M[6][4] = new Soldier(false);
        M[6][6] = new Soldier(false);
        M[6][8] = new Soldier(false);
    }


    public boolean canMove(P p) {
        if (current instanceof Soldier) {
            List<P> ps = ((Soldier) current).getMovable();
            if (!ps.contains(p)) return false;
            return !current.sameSide(M[p.y][p.x]);
        }

        return false;
    }

    public Piece move(P to) {
        Piece beRemoved = M[to.y][to.x];
        P from = current.pos;
        M[to.y][to.x] = current;
        M[from.y][from.x] = null;
        current.setX(Val.InitX + to.x * Val.NextX);
        current.setY(Val.InitY + to.y * Val.NextY);
        current = null;

        return beRemoved;
    }
}
