import java.util.ArrayList;

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
    private Mark cpu;
    private Mark cpu2;
    // Le constructeur reçoit en paramètre le
    // joueur MAX (X ou O)
    public CPUPlayer(Mark cpu){
        this.numExploredNodes=0;
        this.cpu=cpu;
        if(cpu==Mark.O){
            this.cpu2=Mark.X;
        }else{
            this.cpu2=Mark.O;
        }
    }

    // Ne pas changer cette méthode
    public int  getNumOfExploredNodes(){
        return numExploredNodes;
    }

    // Retourne la liste des coups possibles.  Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveMinMax(Board board)
    {
        ArrayList<Move> nextMoveMM = new ArrayList<>();
        ArrayList<Move> moves = board.availablePos();
        int bestScore = Integer.MIN_VALUE;
        for(int i=0;i<moves.size();i++){
            Move m = moves.get(i);
            board.play(m, cpu);
            int val = minmax(board, false);
            board.undoMove(m);
            if(val>bestScore){
                bestScore=val;
                nextMoveMM.clear();
                nextMoveMM.add(m);
            }else if(val==bestScore){
                nextMoveMM.add(m);
            }
        }
        return nextMoveMM;

    }

    // Retourne la liste des coups possibles.  Cette liste contient
    // plusieurs coups possibles si et seuleument si plusieurs coups
    // ont le même score.
    public ArrayList<Move> getNextMoveAB(Board board){
        numExploredNodes++;
        ArrayList<Move> nextMoveAB = new ArrayList<>();
        return nextMoveAB;

    }

    //le minmax
    private int minmax(Board board,boolean maximizing){
        numExploredNodes++;
        int score =board.evaluate(cpu);
        ArrayList<Move> moves = board.availablePos();
        if(score==100){
            return score;
        }else if(score==-100){
            return score;
        }else if(moves.isEmpty()){
            return 0;
        }
        if(maximizing){
            int bestScore = Integer.MIN_VALUE;
            for(int i=0;i<moves.size();i++){
                Move m = moves.get(i);
                board.play(m, cpu);
                int val = minmax(board, !maximizing);
                board.undoMove(m);
                if(val>bestScore){
                    bestScore=val;
                }
            }
            return bestScore;
        }else{
            int leastScore = Integer.MAX_VALUE;
            for(int i=0;i<moves.size();i++){
                Move m = moves.get(i);
                board.play(m, cpu2);
                int val = minmax(board, !maximizing);
                board.undoMove(m);
                if(leastScore>val){
                    leastScore = val;
                }
            }
            return leastScore;
        }
    }
}
