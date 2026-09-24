// de cette classe, ni le nom de la classe.
// Vous pouvez par contre ajouter d'autres méthodes (ça devrait 
// être le cas)
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
}
