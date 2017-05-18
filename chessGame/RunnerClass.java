import java.util.Scanner;

public class RunnerClass
{
    public static void main (String[] args) {
        
        Scanner playerInput = new Scanner(System.in);

        /*char[][] chessBoard = {
            {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
            {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', 'N', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
            {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
            };
        BoardState gameBoard = new BoardState(chessBoard, true);*/
        BoardState gameBoard = new BoardState();
        char[][] currentPosition = gameBoard.getPosition();
        System.out.println(gameBoard);
        
        MoveFinder ai = new MoveFinder();
        System.out.println(ai.possibleMoves(gameBoard));
        
        
        
        
        System.out.println();
        
        System.out.println(gameBoard.makeMove("4644 "));
    }
}
