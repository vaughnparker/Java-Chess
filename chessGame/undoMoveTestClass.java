public class undoMoveTestClass
{
    public static void main(String[] args) {
        char[][] testingPosition = {
            {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
            {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', 'K', ' ', 'N', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
            {'R', 'N', 'B', 'Q', ' ', 'B', 'N', 'R'},
            };
        BoardState gameBoard = new BoardState(testingPosition, true);
        
        System.out.println(gameBoard);
        
        String testMoves = MoveFinder.legalMoves(gameBoard);
        System.out.println(testMoves);
        
        for (int i = 5; i <= testMoves.length(); i +=5) {
            String moveToMake = testMoves.substring(i-5, i);
            
            gameBoard.makeMove(moveToMake);
            System.out.println(gameBoard);
            gameBoard.undoMove(moveToMake);
            System.out.println(gameBoard);
        }
    }
}
