package ai;

import game.*;

import java.util.ArrayList;
import java.util.List;

public class MoveGenerator {

    public List<List<Move>> moveList = new ArrayList<>();
    public int moveCount = 0;

    public MoveGenerator() {
        for (int i = 0; i < 15; i++) {
            moveList.add(new ArrayList<>());
        }
    }

    public int createPossibleMove(Chess[][] map, int depth, boolean isRedTurn) {
        moveCount = 0;
        moveList.set(depth, new ArrayList<>());
        Chess chess;
        for (int y = 0; y < 10; ++y)
            for (int x = 0; x < 9; ++x)
                if ((chess = map[y][x]) != null) {
                    if ((!isRedTurn && chess.isRed()) || (isRedTurn && !chess.isRed())) continue;
                    P from = new P(x, y);
                    chess.setPosition(from);
                    addMove(from, chess.getMoves(map), depth);
                }
        return moveCount;
    }

    public void addMove(P from, List<P> to, int depth) {
        List<Move> moves = moveList.get(depth);
        for (P p : to) {
            Move m = new Move(from, p);
            if (moveCount >= moves.size()) moves.add(m);
            else moves.set(moveCount, m);
            moveCount++;
        }
    }
}
