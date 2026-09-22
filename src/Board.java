import java.util.ArrayList;
import java.util.Arrays;

// Classe qui contient les informations du plateau.
// IMPORTANT: Il ne faut pas changer la signature des méthodes
// de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait 
// être le cas)
class Board
{
    private Mark[][] board;

    // === Constructor ===

    // Ne pas changer la signature de cette méthode
    public Board() {
        this.board = new Mark[][] {
            { Mark.EMPTY, Mark.EMPTY, Mark.EMPTY },
            { Mark.EMPTY, Mark.EMPTY, Mark.EMPTY },
            { Mark.EMPTY, Mark.EMPTY, Mark.EMPTY }
        };
    }

    // === Functions ===

    // Place la pièce 'mark' sur le plateau, à la
    // position spécifiée dans Move
    //
    // Ne pas changer la signature de cette méthode
    public void play(Move m, Mark mark){
        if(this.isValidMove(m)) {
            this.board[m.getRow()][m.getCol()] = mark;
        }
    }

    // Retire la pièce `mark` du plateau, à la
    // position spécifiée dans Move
    public void undo(Move m) {
        board[m.getRow()][m.getCol()] = Mark.EMPTY;
    }


    // Évalue si le joueur correspondant à la `mark` est gagnant
    // retourne  100 pour une victoire
    //          -100 pour une défaite
    //           0   pour un match nul
    // Ne pas changer la signature de cette méthode
    public int evaluate(Mark mark){
        Mark winner = getWinner();

        if (winner == Mark.EMPTY) return 0;
        
        return (winner == mark) ? 100 : -100;
    }

    // Détermine si la partie est terminée (si un joueur à gagner ou si le plateau est plein).
    public boolean isGameOver() {
        return getWinner() != Mark.EMPTY || getAvailableMoves().isEmpty();
    }

    // Retourne le Mark gagnant, ou Mark.EMPTY s'il n'y en a pas encore.
    private Mark getWinner() {
        for (int i = 0; i < 3; i++) {
            if (isWinningLine(board[i][0], board[i][1], board[i][2])) return board[i][0]; // rangées
            if (isWinningLine(board[0][i], board[1][i], board[2][i])) return board[0][i]; // colonnes
        }
        if (isWinningLine(board[0][0], board[1][1], board[2][2])) return board[0][0]; // diagonale \
        if (isWinningLine(board[0][2], board[1][1], board[2][0])) return board[0][2]; // diagonale /

        return Mark.EMPTY;
    }

    private boolean isWinningLine(Mark a, Mark b, Mark c) {
        return a != Mark.EMPTY && a == b && b == c;
    }
    
    // Évalue si un coup est légal et s'il peut être joué
    public boolean isValidMove(Move move) {
        if(
            (move.getRow() >= 0 && move.getCol() >= 0)
            &&
            (move.getRow() < this.board.length && move.getCol() < this.board[0].length)
        ) {
            return this.board[move.getRow()][move.getCol()] == Mark.EMPTY;
        }

        return false;
    }

    public ArrayList<Move> getAvailableMoves() {
        ArrayList<Move> moves = new ArrayList<Move>();

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                Move move = new Move(r, c);
                if (this.isValidMove(move)) {
                    moves.add(move);
                }
            }
        }

        return moves;
    }
}
