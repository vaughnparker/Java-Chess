import java.util.Scanner;

public class RunnerClass
{
    public static void main (String[] args) {
        
        Scanner playerInput = new Scanner(System.in);

        /*char[][] testingPosition = {
            {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
            {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', 'K', ' ', 'N', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
            {'R', 'N', 'B', 'Q', ' ', 'B', 'N', 'R'},
            };
        BoardState gameBoard = new BoardState(testingPosition, true);*/

        BoardState gameBoard = new BoardState();
        //char[][] currentPosition = gameBoard.getPosition();

        System.out.println(gameBoard);
        
        //MoveFinder runnerFinder = new MoveFinder();
        System.out.println(MoveFinder.possibleMoves(gameBoard));
        System.out.println(gameBoard);
        
        System.out.println(MoveFinder.legalMoves(gameBoard));
        System.out.println(gameBoard);
        
        
        
        
        /*System.out.println();
        System.out.println(gameBoard.makeMove("4644 "));
        
        System.out.println();
        System.out.println(gameBoard.makeMove("211Qn"));*/





    }
}