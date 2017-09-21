package ai;

import game.Chess;
import game.General;
import game.Move;

import java.util.Arrays;

/**
 * Principal variation search with History Heuristic and Transposition Table
 * */
public class PSV {
    public GameState gameState;
    private Chess[][] currentMap = new Chess[10][9];
    private int searchDepth = 6;
    private int maxDepth;
    private long timeCount;
    private static int TIME_LIMIT = 3000;
    private MoveGenerator moveGen = new MoveGenerator();
    private EvaluationV2 evaluator = new EvaluationV2();
    private Move bestMove;
    private TranspositionTable TT = new TranspositionTable();
    private HistoryHeuristic HH = new HistoryHeuristic();

    public Move searchAGoodMove(Chess[][] map) {
        Move backupBestMove = null;
        int stopDepth = 0;
        this.mapCopy(map);
        this.TT.calculateInitHashKey(this.currentMap);
        this.HH.resetHistoryTable();
        this.timeCount = System.currentTimeMillis();
        for (maxDepth = 1; maxDepth <= this.searchDepth; maxDepth++) {
            if (this.negaScout(maxDepth, -20000, 20000) != 66666) {
                backupBestMove = new Move(bestMove.from, bestMove.to);
                backupBestMove.score = this.bestMove.score;
                stopDepth = maxDepth;
                System.out.println("level: " + maxDepth + ", " + bestMove);
            }
        }
        System.out.format("time: %dms%n", System.currentTimeMillis() - timeCount);
        System.out.format("depth: %d%n", stopDepth - 1);
        return backupBestMove;

    }

    private void mapCopy(Chess[][] map) {
        currentMap = new Chess[10][9];
        for (int y = 0; y < 10; ++y)
            for (int x = 0; x < 9; ++x) {
                Chess chess = map[y][x];
                if (chess != null) this.currentMap[y][x] = chess.clone();
            }
    }

    private int negaScout(int depth, int alpha, int beta) {
        int score = this.isGameOver(this.currentMap, depth);
        if (score != 0) return score;
        int isRedTurn = (this.maxDepth - depth) % 2;
        score = this.TT.lookUpHashTable(alpha, beta, depth, isRedTurn);
        if (score != 66666) return score;
        if (depth <= 0) {
            score = evaluator.evaluate(gameState, isRedTurn == 1);
            this.TT.enterHashTable("exact", score, depth, isRedTurn);
            return score;
        }
        if (depth == this.maxDepth) {
            if ((System.currentTimeMillis() - this.timeCount) >= this.TIME_LIMIT)
                return 66666;
        }
        int count = moveGen.createPossibleMove(this.currentMap, depth, isRedTurn == 1);
        for (int i = 0; i < count; i++)
            moveGen.moveList.get(depth).get(i).score = this.HH.getHistoryScore(moveGen.moveList.get(depth).get(i));
        moveGen.moveList.set(depth, Arrays.asList(this.HH.mergeSort(moveGen.moveList.get(depth).toArray(new Move[0]), count, 0)));
        int bestmove = -1;
        int a = alpha,
                b = beta;
        int isEvalExact = 0;
        for (int i = 0; i < count; ++i) {
            this.TT.hashMakeMove(moveGen.moveList.get(depth).get(i), this.currentMap);
            Chess target = this.makeMove(moveGen.moveList.get(depth).get(i));
            int t = -this.negaScout(depth - 1, -b, -a);
            if (t > a && t < beta && i > 0) {
                a = -this.negaScout(depth - 1, -beta, -t);
                isEvalExact = 1;
                if (depth == this.maxDepth)
                    this.bestMove = moveGen.moveList.get(depth).get(i);
                bestmove = i;
            }

            this.TT.hashUnMakeMove(moveGen.moveList.get(depth).get(i), target, this.currentMap);
            this.unMakeMove(moveGen.moveList.get(depth).get(i), target);
            if (a < t) {
                isEvalExact = 1;
                a = t;
                if (depth == this.maxDepth)
                    this.bestMove = moveGen.moveList.get(depth).get(i);
            }
            if (a >= beta) {
                this.TT.enterHashTable("lowerBound", a, depth, isRedTurn);
                this.HH.enterHistoryScore(moveGen.moveList.get(depth).get(i), depth);
                return a;
            }
            b = a + 1;
        }
        if (bestmove != -1)
            this.HH.enterHistoryScore(moveGen.moveList.get(depth).get(bestmove), depth);
        if (isEvalExact == 1)
            this.TT.enterHashTable("exact", a, depth, isRedTurn);
        else
            this.TT.enterHashTable("upperBound", a, depth, isRedTurn);
        return a;
    }

    private Chess makeMove(Move move) {
        Chess chess = this.currentMap[move.to.y][move.to.x];
        this.currentMap[move.to.y][move.to.x] = this.currentMap[move.from.y][move.from.x];
        this.currentMap[move.from.y][move.from.x] = null;
        return chess;
    }

    private void unMakeMove(Move move, Chess chess) {
        this.currentMap[move.from.y][move.from.x] = this.currentMap[move.to.y][move.to.x];
        this.currentMap[move.to.y][move.to.x] = chess;
    }

    private int isGameOver(Chess[][] map, int depth) {
        boolean red = false, black = false;
        for (int y = 7; y < 10; ++y)
            for (int x = 3; x < 6; ++x)
                if (map[y][x] != null && map[y][x] instanceof General) {
                    red = true;
                    break;
                }
        for (int y = 0; y < 3; ++y)
            for (int x = 3; x < 6; x++)
                if (map[y][x] != null && map[y][x] instanceof General) {
                    black = true;
                    break;
                }
        boolean isBlackTurn = (this.maxDepth - depth + 1) % 2 == 1;
        return (red && black) ? 0 : (black ^ isBlackTurn ? -1 : 1) * (19990 + depth);
    }
}
