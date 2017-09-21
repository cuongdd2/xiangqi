package ai;

import game.Chess;
import game.Move;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class TranspositionTable {
    private int hashKey32 = 0;
    private StringBuffer hashCheck = new StringBuffer();
    private int[][][] hashKey32Table = new int[15][10][9];
    private Hash[][] TT = new Hash[2][1 << 20];
    private static List<String> types = Arrays.asList("", "B_General", "B_Chariot", "B_Horse", "B_Canon", "B_Advisor", "B_Elephant", "B_Soldier",
            "R_General", "R_Chariot", "R_Horse", "R_Canon", "R_Advisor", "R_Elephant", "R_Soldier");

    private static int rnd32() {
        return ((int) (Math.random() * (~(1 << 31)))) ^ ((~(1 << 31)) & (((int) (Math.random() * (~(1 << 31)))) << 15)) ^ ((~(1 << 31)) & (((int) (Math.random() * (~(1 << 31)))) << 30));
    }

    private static int convertChessToNumber(Chess chess) {
        if (chess == null) return 0;
        return types.indexOf((chess.black ? "B_" : "R_") + chess.getClass().getSimpleName());
    }

    public TranspositionTable() {
        initializeHashKey();
    }

    private void initializeHashKey() {
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 10; k++) {
                    hashKey32Table[i][k][j] = rnd32();
                }
            }
        }
        for (int i = 0; i < 2; ++i)
            for (int j = 0; j < (1 << 20); ++j) {
                this.TT[i][j] = new Hash();
            }
    }

    public void calculateInitHashKey(Chess[][] map) {
        for (int k = 0; k < 10; k++) {
            for (int j = 0; j < 9; j++) {
                int chessType = convertChessToNumber(map[k][j]);
                if (chessType != 0) hashKey32 = hashKey32 ^ hashKey32Table[chessType][k][j];
                hashCheck.append((char) (chessType + 64));
            }
        }
    }

    public void hashMakeMove(Move move, Chess[][] map) {
        int fromId = convertChessToNumber(map[move.from.y][move.from.x]);
        int toId = this.convertChessToNumber(map[move.to.y][move.to.x]);
        this.hashKey32 = this.hashKey32 ^ this.hashKey32Table[fromId][move.from.y][move.from.x];
        if (toId != 0) this.hashKey32 = this.hashKey32 ^ this.hashKey32Table[toId][move.to.y][move.to.x];
        this.hashCheck.setCharAt(move.to.y * 9 + move.to.x, this.hashCheck.charAt(move.from.y * 9 + move.from.x));
        this.hashKey32 = this.hashKey32 ^ this.hashKey32Table[fromId][move.to.y][move.to.x];
        this.hashCheck.setCharAt(move.from.y * 9 + move.from.x, '0');
    }

    public void hashUnMakeMove(Move move, Chess chess, Chess[][] map) {
        int toId = this.convertChessToNumber(map[move.to.y][move.to.x]);
        int chessId = this.convertChessToNumber(chess);
        this.hashKey32 = this.hashKey32 ^ this.hashKey32Table[toId][move.from.y][move.from.x];
        this.hashCheck.setCharAt(move.from.y * 9 + move.from.x, this.hashCheck.charAt(move.to.y * 9 + move.to.x));
        this.hashKey32 = this.hashKey32 ^ this.hashKey32Table[toId][move.to.y][move.to.x];
        if (chessId != 0) this.hashKey32 = this.hashKey32 ^ this.hashKey32Table[chessId][move.to.y][move.to.x];
        this.hashCheck.setCharAt(move.from.y * 9 + move.from.x, (char) (toId + 56));
    }

    public int lookUpHashTable(int alpha, int beta, int depth, int isRedTurn) {
        int x = this.hashKey32 & 0xFFFFF;
        Hash pht = this.TT[isRedTurn][x];

        if (pht.depth >= depth && Objects.equals(pht.checkSum, this.hashCheck.toString())) {
            switch (pht.entryType) {
                case "exact":
                    return pht.eval;
                case "lowerBound":
                    if (pht.eval >= beta)
                        return (pht.eval);
                    else
                        break;
                case "upperBound":
                    if (pht.eval <= alpha)
                        return (pht.eval);
                    else
                        break;
            }
        }
        return 66666;
    }

    public void enterHashTable(String entryType, int eval, int depth, int isRedTurn) {
        int x = this.hashKey32 & 0xFFFFF;
        this.TT[isRedTurn][x].checkSum = this.hashCheck.toString();
        this.TT[isRedTurn][x].entryType = entryType;
        this.TT[isRedTurn][x].eval = eval;
        this.TT[isRedTurn][x].depth = depth;
    }
}

class Hash {
    public String checkSum = "";
    public String entryType = "";
    public int eval = 0;
    public int depth = 0;
}
