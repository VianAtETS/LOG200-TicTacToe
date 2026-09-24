// de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait
// être le cas)

import java.util.ArrayList;

class Board
{
    private Mark[][] board;

    // Ne pas changer la signature de cette méthode
    public Board() {
        initBoard();
    }

    // Place la pièce 'mark' sur le plateau, à la
    // position spécifiée dans Move
    //
    // Ne pas changer la signature de cette méthode
    public void play(Move m, Mark mark){
        //check if the position is empty or taken already, fill it with the mark enum passed if empty
        int x=m.getRow();
        int y=m.getCol();
        if(this.board[x][y]!=Mark.EMPTY){
            return;
        }else{
            this.board[x][y]=mark;
        }

    }


    // retourne  100 pour une victoire
    //          -100 pour une défaite
    //           0   pour un match nul
    // Ne pas changer la signature de cette méthode
    public int evaluate(Mark mark){
        if(hasWon(mark)){
            return 100;
        }else if(hasLost(mark)){
            return -100;
        }
        return 0;
    }
    //to initialize board
    private void initBoard(){
        this.board = new Mark[3][3];
            for(int i=0;i<board.length;i++){
                for(int j=0;j<board[i].length;j++){
                    board[i][j]=Mark.EMPTY;
                }
            }
    }
    //to find possible moves
    public ArrayList<Move> availablePos(){
        ArrayList<Move>movesPossible = new ArrayList<>();
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                if(board[i][j]==Mark.EMPTY){
                    int x=i;
                    int y=j;
                    Move m = new Move(x,y);
                    movesPossible.add(m);
                }
            }
        }
        return movesPossible;
    }
    //to see if our mark won
    private boolean hasWon(Mark mark){
        boolean answer = false;
        for(int i=0;i<board.length;i++){
            if(board[i][0]==mark && board[i][1]==mark && board[i][2]==mark){
                answer=true;
            }
            if(board[0][i]==mark && board[1][i]==mark && board[2][i]==mark){
                answer=true;
            }
            if(i==0){
                if((board[i][i]==mark && board[i+1][i+1]==mark && board[i+2][i+2]==mark)){
                answer=true;
            }
            }
            if(i==2){
                if((board[i][0]==mark && board[i-1][1]==mark && board[i-2][2]==mark)){
                    answer=true;
                }
            }
        }
        return answer;
    }
    //to see if enemy mark won
    private boolean hasLost(Mark mark){
        boolean answer = false;
        if(mark==Mark.O){
            mark=Mark.X;
        }else{
            mark=Mark.O;
        }
        answer = hasWon(mark);
        return answer;
    }
    //to undo a move during the evaluation
    public void undoMove(Move m){
        int x=m.getRow();
        int y=m.getCol();
        if(board[x][y]!=Mark.EMPTY){
            board[x][y]=Mark.EMPTY;
        }
    }
}
