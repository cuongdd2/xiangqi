package ai;

import game.Chess;
import game.General;
import game.P;

import java.util.List;

public class EvaluationV2 {
    private int[][] attackPos = new int[10][9];
    private int[][] guardPos = new int[10][9];
    private int[][] flexibilityPos = new int[10][9];
    private int[][] chessValue = new int[10][9];

    public int evaluate(GameState game, boolean isRedTurn) {
        Chess[][] map = game.M;
        for (int i = 0; i < 10; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.chessValue[i][j] = 0;
                this.attackPos[i][j] = 0;
                this.guardPos[i][j] = 0;
                this.flexibilityPos[i][j] = 0;
            }
        }
        for (int y = 0; y < 10; ++y)
            for (int x = 0; x < 9; ++x) {
                if (map[y][x] != null) {
                    Chess chess = map[y][x];
                    chess.setPosition(new P(x, y));
                    List<P> moves = chess.getMoves(map);
                    for (P to : moves) {
                        Chess target = map[to.y][to.x];
                        if (target == null) {
                            ++this.flexibilityPos[y][x];
                        } else {
                            if (chess.black == target.black) {
                                ++this.guardPos[to.y][to.y];
                            } else {
                                ++this.flexibilityPos[y][x];
                                this.attackPos[to.y][to.x] += 3 + Math.floor((target.value - chess.value) * 0.01);
                                if (target instanceof General && game.isBlack != target.black) return 18888;//失败
                            }
                        }
                    }
                }
            }
        for (int y = 0; y < 10; ++y)
            for (int x = 0; x < 9; ++x) {
                if (map[y][x] != null) {
                    Chess chess = map[y][x];
                    int halfValue = chess.value / 16;
                    this.chessValue[y][x] += chess.value;
                    this.chessValue[y][x] += chess.getMobility() * this.flexibilityPos[y][x];
                    this.chessValue[y][x] += chess.posValue(new P(x, y));

                    if (this.attackPos[y][x] != 0) {
                        if ((chess.isRed() && isRedTurn) || (!chess.isRed() && !isRedTurn)) {
                            if (chess instanceof General) {
                                this.chessValue[y][x] -= 20;
                            } else {
                                this.chessValue[y][x] -= halfValue * 2;
                                if (this.guardPos[y][x] != 0) this.chessValue[y][x] += halfValue;
                            }
                        } else {
                            if (chess instanceof General)
                                return 18888;
                            this.chessValue[y][x] -= halfValue * 10;
                            if (this.guardPos[y][x] != 0) this.chessValue[y][x] += halfValue * 9;
                        }
                        this.chessValue[y][x] -= this.attackPos[y][x];
                    } else {
                        if (this.guardPos[y][x] != 0) this.chessValue[y][x] += 5;
                    }
                }
            }
        int redValue = 0, blackValue = 0;
        for (int y = 0; y < 10; ++y)
            for (int x = 0; x < 9; ++x) {
                Chess chess = map[y][x];
                if (chess != null) {
                    if (chess.black) blackValue += this.chessValue[y][x];
                    else redValue += chessValue[y][x];
                }
            }
        return isRedTurn ? redValue - blackValue : blackValue - redValue;
    }
}
