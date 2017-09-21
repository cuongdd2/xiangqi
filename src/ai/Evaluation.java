package ai;

import game.*;

public class Evaluation {
    // TODO: evaluate by position
    // TODO: re-order move
    // TODO: implement check validation

    public static int evaluate(GameState b, boolean black) {
        int redValue, blackValue;
        int[] strength = strength(b);
        int[] position = position(b);
        int[] mobility = {0, 0};//mobility(b);
        int[] threat = threat(b);
        int[] control = {0, 0};//control(b);
        // Final step: assign all evaluated values to red and black players
        // Weighting of strength, position, mobility and treats are assigned in 4:3:2:1
        redValue = 6 * strength[0] + 4 * position[0] + 4 * threat[0] + mobility[0] + control[0];
        blackValue = 6 * strength[1] + 4 * position[1] + 4 * threat[1] + mobility[1] + control[1];
//        redValue = 6 * strength[0];
//        blackValue = 6 * strength[1];
        return black ? (blackValue - redValue) : (redValue - blackValue);
    }

    private static int[] strength(GameState b) {
        int[] strength = new int[2];
        for (P p : b.reds) strength[0] += b.M[p.y][p.x].value;
        for (P p : b.blacks) strength[1] += b.M[p.y][p.x].value;
        return strength;
    }

    private static int[] position(GameState b) {
        int[] pos = new int[2];
        for (P p : b.reds) pos[0] += b.M[p.y][p.x].posValue(p);
        for (P p : b.blacks) pos[1] += b.M[p.y][p.x].posValue(p);
        return pos;
    }

    private static int[] mobility(GameState b) {
        int[] mobility = new int[2];
        Chess chess;
        for (P p : b.reds) {
            chess = b.M[p.y][p.x];
            if (chess instanceof General || chess instanceof Advisor || chess instanceof Elephant) continue;
            mobility[0] += chess.getMobility() * chess.getMoves(b.M).size();
        }
        for (P p : b.blacks) {
            chess = b.M[p.y][p.x];
            if (chess instanceof General || chess instanceof Advisor || chess instanceof Elephant) continue;
            mobility[1] += chess.getMobility() * chess.getMoves(b.M).size();
        }
        return mobility;
    }

    private static int[] threat(GameState b) {
        int[] threat = new int[2];
        Chess chess;
        for (P p : b.reds) {
            chess = b.at(p);
            chess.setPosition(p);
            threat[0] += chess.threat();
        }
        for (P p : b.blacks) {
            chess = b.at(p);
            chess.setPosition(p);
            threat[1] += chess.threat();
        }
        return threat;
    }

    private static int[] control(GameState b) {
        int[] control = new int[2];
        Chess chess;
        for (P p : b.reds) {
            chess = b.at(p);
            if (chess instanceof Chariot || chess instanceof Canon || chess instanceof Horse) {
                chess.getMoves(b.M).forEach(p1 -> {
                    if(b.at(p1) != null && b.at(p1).black) {
                        control[0] += b.at(p1).value / 10;
                    }
                });
            }
        }
        for (P p : b.blacks) {
            chess = b.at(p);
            if (chess instanceof Chariot || chess instanceof Canon || chess instanceof Horse) {
                chess.getMoves(b.M).forEach(p1 -> {
                    if(b.at(p1) != null && !b.at(p1).black) {
                        control[1] += b.at(p1).value / 10;
                    }
                });
            }
        }
        return control;
    }
}
