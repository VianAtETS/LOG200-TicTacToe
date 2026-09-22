import java.util.ArrayList;
import java.util.Arrays;

class Board {
    private Mark[][] board;

    public Board() {
        board = new Mark[3][3];

        for (Mark[] cell : board) {
            Arrays.fill(cell, Mark.EMPTY);
        }
    }

    // Place la pièce 'mark' sur le plateau, à la
    // position spécifiée dans Move
    public void play(Move m, Mark mark) {
        board[m.getRow()][m.getCol()] = mark;
    }

    // retourne  100 pour une victoire
    //          -100 pour une défaite
    //           0   pour un match nul
    public int evaluate(Mark mark) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][0] == board[i][2])
                return (board[i][0] == mark) ? 100 : -100;
        }
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == board[1][i] && board[0][i] == board[2][i])
                return (board[0][i] == mark) ? 100 : -100;
        }
        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2])
                || (board[0][2] == board[1][1] && board[0][2] == board[2][0]))
            return (board[1][1] == mark) ? 100 : -100;

        return 0;
    }

    public boolean isFinal() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) if (board[i][j] == Mark.EMPTY) return false;

        return true;
    }

    public void undo(Move m) {
        board[m.getRow()][m.getCol()] = Mark.EMPTY;
    }

    public ArrayList<Move> getAvailableMoves() {
        ArrayList<Move> moves = new ArrayList<>();

        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++) if (board[r][c] == Mark.EMPTY) moves.add(new Move(r, c));

        return moves;
    }
}
