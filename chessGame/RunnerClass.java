public class RunnerClass
{
    public static void main (String[] args) {

        char[][] chessBoard = {
            {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
            {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', 'R', ' ', ' ', ' '},
            {' ', 'r', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
            {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
            };
        BoardState gameBoard = new BoardState(chessBoard, true);
        MoveFinder ai = new MoveFinder();

        System.out.println(ai.possibleMoves(gameBoard));
    }
}
