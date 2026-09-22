import java.util.ArrayList;

// Programme de test local (non remis). Vérifie que le projet compile avec :
//   javac Mark.java Move.java Board.java CPUPlayer.java Test.java
// et sert de point d'entrée pour le débogueur (configuration « Déboguer Test »).
class Test {
    public static void main(String[] args) {
        Board board = new Board();
        CPUPlayer cpu = new CPUPlayer(Mark.X);

        ArrayList<Move> minMax = cpu.getNextMoveMinMax(board);
        System.out.println("MinMax     : " + format(minMax));
        System.out.println("  noeuds   : " + cpu.getNumOfExploredNodes());

        ArrayList<Move> alphaBeta = cpu.getNextMoveAB(board);
        System.out.println("Alpha-Beta : " + format(alphaBeta));
        System.out.println("  noeuds   : " + cpu.getNumOfExploredNodes());
    }

    private static String format(ArrayList<Move> moves) {
        StringBuilder sb = new StringBuilder();
        for (Move m : moves) {
            sb.append("(").append(m.getRow()).append(",").append(m.getCol()).append(") ");
        }
        return sb.toString().trim();
    }
}
