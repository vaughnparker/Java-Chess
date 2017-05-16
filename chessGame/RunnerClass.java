public class RunnerClass
{
    public static void main (String[] args) {

        char[][] chessBoard = {
            {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
            {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
            {' ', ' ', ' ', 'P', ' ', ' ', ' ', ' '},  
            {' ', ' ', ' ', ' ', 'N', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
            {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
            };
        BoardState gameBoard = new BoardState(chessBoard, true);
        MoveFinder ai = new MoveFinder();
        
        //System.out.println(chessBoard);
        for (int i = 0; i < chessBoard.length; i++) {
            for (int j = 0; j < chessBoard[i].length; j++) {
                System.out.print(chessBoard[i][j] + " ");
            }
            System.out.println();
        }
        
        System.out.println(ai.possibleMoves(gameBoard));
    }
}   