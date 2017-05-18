public class BoardState
{
    private char[][] savedPosition;
    private boolean sideToMove;
    
    public BoardState() {
        char[][] emptyBoard = {
            {'r', 'n', 'b', 'q', 'k', 'b', 'n', 'r'},
            {'p', 'p', 'p', 'p', 'p', 'p', 'p', 'p'},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {'P', 'P', 'P', 'P', 'P', 'P', 'P', 'P'},
            {'R', 'N', 'B', 'Q', 'K', 'B', 'N', 'R'},
        };
        
        savedPosition = emptyBoard;
        sideToMove = true; // true = WHITE, false = black
    }
    
    public BoardState(char[][] inputBoard) {
        savedPosition = inputBoard;
        sideToMove = true;
    }

    public BoardState(char[][] inputBoard, boolean inputSide) {
        savedPosition = inputBoard;
        sideToMove = inputSide;
    }
    
    public char[][] getPosition() {
        return savedPosition;
    }

    public boolean getSide() {
        return sideToMove;
    }
    
    
    
    
    
    
    
    public String makeMove(String move) {
        
        if (move.length() != 5) {
            return "Invalid move, move must be 5 characters long";
        }
        
        
        int oldX = Character.getNumericValue(move.charAt(0));
        int oldY = Character.getNumericValue(move.charAt(1));
        int newX = Character.getNumericValue(move.charAt(2));
        int newY = Character.getNumericValue(move.charAt(3));
        char capturedPiece = move.charAt(4);
        
        if ( (oldX < 0 || oldX > 7) || (oldY < 0 || oldY > 7) || (newX < 0 || newX > 7) || (newY < 0 || newY > 7) ) {
            return "Invalid move, move coordinates must be in range (0 <= coordinate <= 7)";
        }
        if (capturedPiece != savedPosition[newY][newX]) {
            return "Invalid move, there is no " + capturedPiece + " at coordinates " + newX + ", " + newY;
        }
        
        
        savedPosition[newY][newX] = savedPosition[oldY][newX];
        savedPosition[oldY][newX] = ' ';
        
        System.out.println("TESTOZESTO " + savedPosition[newX][newY]);
        return this.toString();
        
        
        //System.out.println("" + oldX + oldY + newX + newY);
        
        //return move;
        //maybe return this.getAlgebraicNotation(move);
    }
    
    public String toString() {
        String textBoard = "";
        
        for (int i = 0; i < savedPosition.length; i++) {
            for (int j = 0; j < savedPosition[i].length; j++) {
                    textBoard += savedPosition[i][j] + " ";
                }
            textBoard += "\n";
            }
            
        return textBoard;
    }
}
