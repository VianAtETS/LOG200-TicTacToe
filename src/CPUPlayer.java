import java.util.ArrayList;

// IMPORTANT: Il ne faut pas changer la signature des méthodes
// de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait
// être le cas)
class CPUPlayer {
    // Contient le nombre de noeuds visités (le nombre
    // d'appel à la fonction MinMax ou Alpha Beta)
    // Normalement, la variable devrait être incrémentée
    // au début de votre MinMax ou Alpha Beta.
    private int numExploredNodes;
    private Mark max;
    private Mark min;

    // Le constructeur reçoit en paramètre le
    // joueur MAX (X ou O)
    public CPUPlayer(Mark cpu) {
        this.max = cpu;
        this.min = (cpu == Mark.X) ? Mark.O : Mark.X;
    }

    public int getNumOfExploredNodes() {
        return this.numExploredNodes;
    }

    // Retourne la liste des coups possibles.  Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveMinMax(Board board) {
        this.numExploredNodes = 0;
        ArrayList<Move> bestMoves = new ArrayList<>();
        int bestScore = Integer.MIN_VALUE;

        for (Move m : board.getAvailableMoves()) {
            board.play(m, this.max);
            int score = minimax(board, this.min);
            board.undo(m);

            if (score > bestScore) {
                bestScore = score;
                bestMoves.clear();
                bestMoves.add(m);
            } else if (score == bestScore) {
                bestMoves.add(m);
            }
        }
        return bestMoves;
    }

    private int minimax(Board board, Mark currentPlayer) {
        numExploredNodes++;

        if (board.isFinal()) return board.evaluate(this.max);

        boolean isMax = (currentPlayer == this.max);
        int best = isMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        Mark next = isMax ? this.min : this.max;

        for (Move m : board.getAvailableMoves()) {
            board.play(m, currentPlayer);
            int score = minimax(board, next);
            board.undo(m);

            best = isMax ? Math.max(best, score) : Math.min(best, score);
        }
        return best;
    }

    // Retourne la liste des coups possibles.  Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveAB(Board board) {
        this.numExploredNodes = 0;
        return new ArrayList<>();
    }

    private int alphaBeta(Board board, Mark currentPlayer, int alpha, int beta) {
        return minimax(board, (currentPlayer == Mark.X) ? Mark.O : Mark.X);
    }
}
