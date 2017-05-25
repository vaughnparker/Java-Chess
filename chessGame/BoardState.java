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

        //capital letters are WHITE
        //lowercase letters are black
        
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
    
    
    
    
    
    
    
    public BoardState makeMove(String move) {
        
        if (move.length() != 5) {
            System.out.println("Invalid move, move must be 5 characters long");
            return this;
        }
        
        int pawnDirection = (sideToMove ? -1 : 1);
        int promotionRank = (sideToMove ? 0 : 7);
        
        String typeOfMove = "Standard";
        
        int oldX = Character.getNumericValue(move.charAt(0));
        int oldY = Character.getNumericValue(move.charAt(1));
        int newX = Character.getNumericValue(move.charAt(2));
        
        int newY;
        char promotedPiece = 'E'; // E for error
        
        char capturedPiece = move.charAt(4);
        
        //if its a pawn promotion
        if (move.charAt(3) == 'Q' || move.charAt(3) == 'R' || move.charAt(3) == 'B' || move.charAt(3) == 'N'
           || move.charAt(3) == 'q' || move.charAt(3) == 'r' || move.charAt(3) == 'b' || move.charAt(3) == 'n')
        {
            if (oldY != promotionRank - pawnDirection) {
                System.out.println("Invalid move, pawn must be on 2nd or 7th rank to promote");
                return this;
            }
            
            
            
            newY = promotionRank;
            
            promotedPiece = move.charAt(3);
            typeOfMove = "Promotion";
        }
        else {
            newY = Character.getNumericValue(move.charAt(3));
        }
        
        
        
        
        if ( (oldX < 0 || oldX > 7) || (oldY < 0 || oldY > 7) || (newX < 0 || newX > 7) || (newY < 0 || newY > 7) ) {
            System.out.println("Invalid move, move coordinates must be in range (0 <= coordinate <= 7)");
            return this;
        }
        if (capturedPiece != savedPosition[newY][newX]) {
            System.out.println("Invalid move, there is no " + capturedPiece + " at coordinates " + newX + ", " + newY);
            return this;
        }


        // makeMove() cannot check if it's a legal move, because the legalMoves method relies on removeAllSelfChecks, which
        // relies on the makeMove method
        /*if (MoveFinder.legalMoves(this).indexOf(move) == -1) {
            System.out.println("Invalid move, " + move + " is not a legal move.");
            return this;
        }*/



        
        if (typeOfMove.equals("Standard")) {
            savedPosition[newY][newX] = savedPosition[oldY][oldX];
            savedPosition[oldY][oldX] = ' ';
        }
        else if (typeOfMove.equals("Promotion")) {
            savedPosition[newY][newX] = promotedPiece;
            savedPosition[oldY][oldX] = ' ';
        }
        
        //System.out.println("TESTOZESTO " + savedPosition[newX][newY]);
        //System.out.println(this.toString());

        sideToMove = !sideToMove; //this is SUPER IMPORTANT, changes whose turn it is
        return this;
        
        
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

    public boolean isKingTakeable() {
        // if it's WHITE's turn to move it sees whether or not the BLACK king is in check
        // if it's BLACK's turn to move it sees whether or not the WHITE king is in check (can be taken)
        boolean side = this.getSide();

        String listOfMoves = MoveFinder.possibleMoves(this);

        String kingString = (side ? "k" : "K");

        //System.out.println(listOfMoves.indexOf(kingString) != -1);
        return (listOfMoves.indexOf(kingString) != -1);
        // true = YES, the king IS in check
        // this ONLY returns true in illigal board states, if someone makes an illigal move
        // false = NO, the king is NOT in check
    }
    
    public boolean cannotMove() {
        // returns TRUE if the player whose turn it is CAN'T MOVE
        // returns FALSE if play continues
        
        String listOfMoves = MoveFinder.possibleMoves(this);
        return (listOfMoves.length() == 0);
    }
    
    
    public String winState() {
        if (this.cannotMove()) { // if the player has no moves 
            char[][] swapSidePosition = new char[8][8];

            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    swapSidePosition[row][col] = savedPosition[row][col];
                }
            }
        
            BoardState swapSideBoard = new BoardState(swapSidePosition, !sideToMove);
            
            if (swapSideBoard.isKingTakeable()) {
                //and if we make a new board
                // where side = !side and do .isKingInCheck and it returns TRUE, meaning
                // that the king IS in check, then its checkmate
                //return ("Checkmate," + !side + " wins!");
                return ("Checkmate");
            }
            else {
                // it's stalemate
                return ("Stalemate");
            }
        }
        
        return "Play continues";
    }
}