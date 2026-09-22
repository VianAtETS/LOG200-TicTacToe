import java.util.ArrayList;

// Classe qui contient les méthodes qui implémentent l’agent intelligent.
// IMPORTANT: Il ne faut pas changer la signature des méthodes
// de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait 
// être le cas)
class CPUPlayer
{

    // Contient le nombre de noeuds visités (le nombre
    // d'appel à la fonction MinMax ou Alpha Beta)
    // Normalement, la variable devrait être incrémentée
    // au début de votre MinMax ou Alpha Beta.
    private int numExploredNodes;

    private Mark agent, opponent;

    // === Constructor ===

    // Le constructeur reçoit en paramètre le
    // joueur MAX (X ou O)
    public CPUPlayer(Mark cpu){
        this.agent = cpu;
        this.opponent = (cpu == Mark.X) ? Mark.O : Mark.X;
    }

    // === Getters & Setters ===

    // Ne pas changer cette méthode
    public int  getNumOfExploredNodes(){
        return numExploredNodes;
    }

    // === Functions ===

    // Retourne la liste des coups possibles.  Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveMinMax(Board board)
    {
        numExploredNodes = 0;

        ArrayList<Move> bestMoves = new ArrayList<>();
        int bestScore = Integer.MIN_VALUE;

        // Racine : c'est au CPU (Max) de jouer
        for (Move move : board.getAvailableMoves()) {
            board.play(move, agent);
            int score = minimax(board, this.opponent); // Explorer les coups
            board.undo(move);
            
            if (score > bestScore) {
                bestScore = score;
                bestMoves.clear();
                bestMoves.add(move);
            } else if (score == bestScore) {
                bestMoves.add(move);
            }
        }

        return bestMoves;
    }

    
    // Évalue le meilleur coup possible à l'aide de l'algorithme Minimax
    // Documentation : https://fr.wikipedia.org/wiki/Algorithme_minimax
    private int minimax(Board board, Mark player) {
        int best = (player == agent) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        numExploredNodes++;

        // La partie est finie ?
        if (board.isGameOver()) {
            return board.evaluate(this.agent);
        }
        
        for (Move move : board.getAvailableMoves()) {
            board.play(move, player);
            int score = minimax(board, (player == agent) ? opponent : agent);
            board.undo(move);

            best = (player == agent) ? Math.max(best, score) : Math.min(best, score);
        }

        return best;
    }

    // Retourne la liste des coups possibles.  Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveAB(Board board){
        numExploredNodes = 0;

        ArrayList<Move> bestMoves = new ArrayList<>();
        int bestScore = Integer.MIN_VALUE;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        for(Move move : board.getAvailableMoves()) {
            board.play(move, this.agent);
            int score = alphaBeta(board, this.opponent, alpha, beta);
            board.undo(move);

           if (score > bestScore) {
                bestScore = score;
                bestMoves.clear();
                bestMoves.add(move);
                // alpha = Math.max(alpha, bestScore); Ignorer pour le moment ! Sinon les coups ex aequo trouvés après le premier seraient élagués.
            } else if (score == bestScore) {
                bestMoves.add(move);
            }
        }

        return bestMoves;
    }


    // Évalue le meilleur coup possible à l'aide de l'algorithme Alpha-beta
    // Documentation : https://fr.wikipedia.org/wiki/Algorithme_alpha-bêta
    private int alphaBeta(Board board, Mark player, int alpha, int beta) {
        int best = (player == agent) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        numExploredNodes++;

        // La partie est finie ?
        if (board.isGameOver()) {
            return board.evaluate(this.agent);
        }

        for (Move move : board.getAvailableMoves()) {
            board.play(move, player);
            int score = alphaBeta(board, (player == this.agent) ? this.opponent : this.agent, alpha, beta);
            board.undo(move);
            
            if (player == agent) {
                best = Math.max(best, score);
                alpha = Math.max(alpha, best);
            } else {
                best = Math.min(best, score);
                beta = Math.min(beta, best);
            }
            
            if (beta <= alpha) {
                break;
            }
        }


        return best;
    }

}
