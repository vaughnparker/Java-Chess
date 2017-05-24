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
        //System.out.println(gameBoard);
        
        System.out.println(MoveFinder.legalMoves(gameBoard));
        //System.out.println(gameBoard);
        
        
        
        /*System.out.println();
        System.out.println(gameBoard.makeMove("4644 "));
        
        System.out.println();
        System.out.println(gameBoard.makeMove("211Qn"));*/
        
        
        
        
        
        
        
        
        System.out.println();
        System.out.println("Enter Player 1: ");
        String player1 = playerInput.nextLine();
        //System.out.println(inputTestString);
        
        System.out.println();
        System.out.println("Enter Player 2: ");
        String player2 = playerInput.nextLine();
        
        System.out.println();
        System.out.println("Okay, we have " + player1 + " vs. " + player2 + "!");
        // for now let's just assume that its player vs player
        // player1 = WHITE, player2 = black

        boolean gameInProgress = true;
        String playerMove = "";
        
        while(gameInProgress) {
            
            boolean side = gameBoard.getSide();
            
            playerMove = playerInput.nextLine();
            gameBoard.makeMove(playerMove);
            
            System.out.println(gameBoard);
            
            if (gameBoard.winState().equals("Checkmate")) {
                System.out.println("Checkmate! " + (side ? player2 : player1) + " is the winner!");
                gameInProgress = false;
            }
            else if (gameBoard.winState().equals("Stalemate")) {
                System.out.println("Stalemate! It is a draw. :(");
                gameInProgress = false;
            }
            else {
                System.out.println("Play continues...");
            }
        }


    }
}