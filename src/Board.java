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
        return 100;
    }
}
