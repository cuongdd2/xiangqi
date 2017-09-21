package ai;

import game.*;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

@Singleton
public class GameState {
    private static final int DEPTH = 3;
    public Chess[][] M = new Chess[10][9];
    private Stack<Move> history = new Stack<>();
    public List<P> blacks = new ArrayList<>();
    public List<P> reds = new ArrayList<>();
    public Chess current;
    public int currentId = -1;
    public boolean started = false;
    public boolean isBlack = false;
    public boolean isOnline;
    private boolean blackTurn = false;
    private PSV searchEngine = new PSV();

    public GameState() {
        init();
        searchEngine.gameState = this;
    }

    public Move bestMove() {
        return searchEngine.searchAGoodMove(M);
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

        blacks.addAll(Arrays.asList(new P(0, 0), new P(1, 0), new P(2, 0),
                new P(3, 0), new P(4, 0), new P(5, 0),
                new P(6, 0), new P(7, 0), new P(8, 0),
                new P(1, 2), new P(7, 2),
                new P(0, 3), new P(2, 3), new P(4, 3), new P(6, 3), new P(8, 3)));

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

        reds.addAll(Arrays.asList(
                new P(0, 9), new P(1, 9), new P(2, 9),
                new P(3, 9), new P(4, 9), new P(5, 9),
                new P(6, 9), new P(7, 9), new P(8, 9),
                new P(1, 7), new P(7, 7),
                new P(0, 6), new P(2, 6), new P(4, 6), new P(6, 6), new P(8, 6)));
    }

    public Chess at(P p) {
        return M[p.y][p.x];
    }

    public void setCurrent(P p) {
        current = M[p.y][p.x];
        // make sure current != null
        current.setPosition(p);
    }

    public boolean canMove(P p) {
        if (current.getPosition().equals(p)) return false;
        // validate the destination point
        if (p.x < 0 || p.x > Val.MaxX || p.y < 0 || p.y > Val.MaxY) return false;
        // the move rule is depend on each chess piece, so we pass the implementation to subclass of Chess
        return current.canMove(M, p) && !current.sameSide(M[p.y][p.x]);
    }

    public Chess move(P to) {
        System.out.println(current.getPosition() + " --> " + to);
        Chess beRemoved = M[to.y][to.x];
        if (beRemoved != null) {
            if (beRemoved.black) blacks.remove(to);
            else reds.remove(to);
        }
        P from = current.getPosition();
        blackTurn = !blackTurn;
        if (current.black) {
            blacks.remove(from);
            blacks.add(to);
        } else {
            reds.remove(from);
            reds.add(to);
        }
        M[to.y][to.x] = current;
        M[from.y][from.x] = null;
        current.toPixel(Val.InitX + to.x * Val.NextX, Val.InitY + to.y * Val.NextY);
        current = null;

        return beRemoved;
    }

    public void move(Move m) {
        m.captured = M[m.to.y][m.to.x];
        history.push(m);
        Chess p = M[m.from.y][m.from.x];
        M[m.to.y][m.to.x] = p;
        M[m.from.y][m.from.x] = null;
        if (p.black) {
            blacks.remove(m.from);
            blacks.add(m.to);
            if (m.captured != null) reds.remove(m.to);
        } else {
            reds.remove(m.from);
            reds.add(m.to);
            if (m.captured != null) blacks.remove(m.to);
        }
        blackTurn = !blackTurn;
    }

    public boolean endGame() {
        int count = 0;
        for (P p : reds) {
            if (at(p) instanceof General) {
                count++;
                break;
            }
        }
        for (P p : blacks) {
            if (at(p) instanceof General) {
                count++;
                break;
            }
        }
        return count < 2;
    }

    public void undo() {
        Move m = history.pop();
        if (m == null) return;
        // undo game move
        Chess p = M[m.to.y][m.to.x];
        M[m.from.y][m.from.x] = p;
        M[m.to.y][m.to.x] = m.captured;
        if (p.black) {
            blacks.remove(m.to);
            blacks.add(m.from);
            if (m.captured != null) reds.add(m.to);
        } else {
            reds.remove(m.to);
            reds.add(m.from);
            if (m.captured != null) blacks.add(m.to);
        }
        blackTurn = !blackTurn;
    }

}
