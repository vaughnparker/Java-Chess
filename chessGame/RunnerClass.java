import java.util.Scanner;

public class RunnerClass
{
    public static void main (String[] args) {
        
        Scanner playerInput = new Scanner(System.in);

        char[][] testingPosition = {
            {'r', 'n', 'b', 'q', 'k', 'b', 'P', 'r'},
            {'p', 'p', 'P', 'p', 'p', 'p', 'P', 'p'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', 'N', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
            {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
            };
        BoardState gameBoard = new BoardState(testingPosition, true);
        //BoardState gameBoard = new BoardState();
        char[][] currentPosition = gameBoard.getPosition();
        System.out.println(gameBoard);
        
        MoveFinder ai = new MoveFinder();
        System.out.println(ai.possibleMoves(gameBoard));
        
        
        
        
        System.out.println();
        System.out.println(gameBoard.makeMove("4644 "));
        
        System.out.println();
        System.out.println(gameBoard.makeMove("211Qn"));
    }
}