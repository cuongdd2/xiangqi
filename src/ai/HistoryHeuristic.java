package ai;

import game.Move;

public class HistoryHeuristic {
    private int[][] historyTable = new int[90][90];
    private Move[] targetBuff = new Move[90];

    {
        for (int f = 0; f < 90; ++f)
            for (int t = 0; t < 90; ++t)
                historyTable[f][t] = 0;
    }

    public void resetHistoryTable() {
        for (int f = 0; f < 90; ++f)
            for (int t = 0; t < 90; ++t)
                this.historyTable[f][t] = 0;
    }

    public int getHistoryScore(Move move) {
        int from = move.from.y * 9 + move.from.x,
                to = move.to.y * 9 + move.to.x;
        return this.historyTable[from][to];
    }

    public void enterHistoryScore(Move move, int depth) {
        int from = move.from.y * 9 + move.from.x,
                to = move.to.y * 9 + move.to.x;
        this.historyTable[from][to] += 2 << depth;
    }

    public Move[] mergeSort(Move[] source, int n, int direction) {
        int s = 1;
        while (s < n) {
            this.mergeAll(source, targetBuff, s, n, direction);
            s += s;
            this.mergeAll(targetBuff, source, s, n, direction);
            s += s;
        }
        return source;
    }

    private void merge(Move[] source, Move[] target, int l, int m, int r, int direction) {
        int i = l,
                j = m + 1,
                k = l;
        while ((i <= m) && (j <= r))
            if ((direction ^ (source[i].score >= source[j].score ? 1 : 0)) > 0)
                target[k++] = source[i++];
            else
                target[k++] = source[j++];
        if (i > m)
            for (int q = j; q <= r; ++q) target[k++] = source[q];
        else
            for (int q = i; q <= m; ++q) target[k++] = source[q];
    }

    private void mergeAll(Move[] source, Move[] target, int s, int n, int direction) {
        int i = 0;
        while (i <= n - 2 * s) {
            this.merge(source, target, i, i + s - 1, i + 2 * s - 1, direction);
            i = i + 2 * s;
        }
        if (i + s < n) this.merge(source, target, i, i + s - 1, n - 1, direction);
        else for (int j = i; j <= n - 1; ++j) target[j] = source[j];
    }

}
