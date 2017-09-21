package ai;

import game.Chess;
import game.Move;
import game.P;

import java.util.ArrayList;
import java.util.List;

public class Search {

    private static final int DEPTH_LIMIT = 9;
    private static final int DEPTH = 3;


    private static int searchDepth(GameState b) {
        int depth = (int) (-0.11 * (b.reds.size() + b.blacks.size()) + 7.3);
        return Math.min(DEPTH_LIMIT, depth);
    }


    public static List<Move> allMoves(GameState b, boolean black) {
        List<Move> moves = new ArrayList<>();
        List<P> pList = black ? b.blacks : b.reds;
        for (P p : pList) {
            Chess chess = b.at(p);
            chess.setPosition(p);
            List<P> nextP = chess.getMoves(b.M);
            for (P to : nextP) {
                Move move = new Move(p, to);
                move.chess = chess;
                moves.add(move);
            }
        }
        shellSort(moves);
        return moves;
    }

    private static void shellSort(List<Move> a) {
        for (int gap = a.size() / 2; gap > 0;
             gap = gap == 2 ? 1 : (int) (gap / 2.2))
            for (int i = gap; i < a.size(); i++) {
                Move tmp = a.get(i);
                int j = i;
                for (; j >= gap && tmp.compareTo(a.get(j - gap)) < 0; j -= gap)
                    a.set(j, a.get(j - gap));
                a.set(j, tmp);
            }
    }

    public static Move bestMove(GameState b) {
        List<Move> moves = allMoves(b, true);
        int bestValue = -9999999;
        int depth = DEPTH;//searchDepth(b);
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        Move bMove = null;
        for (Move m : moves) {
            b.move(m);
            // Use MiniMax technique with Alpha Beta pruning
            int v = Search.miniMaxAB(depth, b, alpha, beta, false);
            b.undo();
            if (v > bestValue) {
                bestValue = v;
                bMove = m;
                System.out.println(v + ": " + m);
            }
        }
        return bMove;
    }

    public static int miniMaxAB(int depth, GameState bm, int alpha, int beta, boolean black) {
        if (depth == 0 || bm.endGame()) return Evaluation.evaluate(bm, !black);
        List<Move> moves = allMoves(bm, black);
        int value;
        for (Move m : moves) {
            bm.move(m);
            if (black) {
                value = miniMaxAB(depth - 1, bm, alpha, beta, !black);
                if (value > alpha) {
                    log(value, m);
                    alpha = value;
                }
            }
            else {
                value = miniMaxAB(depth - 1, bm, alpha, beta, black);
                if (value < beta) {
                    log(value, m);
                    beta = value;
                }
            }
            bm.undo();
            // Alpha-Beta Pruning
            if (alpha > beta) {
                log(value, m);
                break;
            }
        }
        return black ? alpha : beta;
    }

    private static void log(int value, Move m) {
//        System.out.println(value + ": " + m);
    }


}
