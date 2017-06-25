public class SmartEval
{
	public static int pawnBoard[][]={//mainly copied from LogicCrazyChess and the chessprogramming wiki
        { 0,  0,  0,  0,  0,  0,  0,  0},
        {50, 50, 50, 50, 50, 50, 50, 50},
        {10, 10, 20, 30, 30, 20, 10, 10},
        { 5,  5, 10, 25, 25, 10,  5,  5},
        { 0,  0,  0, 20, 20,  0,  0,  0},
        { 5, -5,-10,  0,  0,-10, -5,  5},
        { 5, 10, 10,-20,-20, 10, 10,  5},
        { 0,  0,  0,  0,  0,  0,  0,  0}};
    public static int rookBoard[][]={
        { 0,  0,  0,  0,  0,  0,  0,  0},
        { 5, 10, 10, 10, 10, 10, 10,  5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        {-5,  0,  0,  0,  0,  0,  0, -5},
        { 0,  0,  0,  5,  5,  0,  0,  0}};
    public static int knightBoard[][]={
        {-50,-40,-30,-30,-30,-30,-40,-50},
        {-40,-20,  0,  0,  0,  0,-20,-40},
        {-30,  0, 10, 15, 15, 10,  0,-30},
        {-30,  5, 15, 20, 20, 15,  5,-30},
        {-30,  0, 15, 20, 20, 15,  0,-30},
        {-30,  5, 10, 15, 15, 10,  5,-30},
        {-40,-20,  0,  5,  5,  0,-20,-40},
        {-50,-40,-30,-30,-30,-30,-40,-50}};
    public static int bishopBoard[][]={
        {-20,-10,-10,-10,-10,-10,-10,-20},
        {-10,  0,  0,  0,  0,  0,  0,-10},
        {-10,  0,  5, 10, 10,  5,  0,-10},
        {-10,  5,  5, 10, 10,  5,  5,-10},
        {-10,  0, 10, 10, 10, 10,  0,-10},
        {-10, 10, 10, 10, 10, 10, 10,-10},
        {-10,  5,  0,  0,  0,  0,  5,-10},
        {-20,-10,-10,-10,-10,-10,-10,-20}};
    public static int queenBoard[][]={
        {-20,-10,-10, -5, -5,-10,-10,-20},
        {-10,  0,  0,  0,  0,  0,  0,-10},
        {-10,  0,  5,  5,  5,  5,  0,-10},
        { -5,  0,  5,  5,  5,  5,  0, -5},
        {  0,  0,  5,  5,  5,  5,  0, -5},
        {-10,  5,  5,  5,  5,  5,  0,-10},
        {-10,  0,  5,  0,  0,  0,  0,-10},
        {-20,-10,-10, -5, -5,-10,-10,-20}};
    public static int kingMidBoard[][]={
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-30,-40,-40,-50,-50,-40,-40,-30},
        {-20,-30,-30,-40,-40,-30,-30,-20},
        {-10,-20,-20,-20,-20,-20,-20,-10},
        { 20, 20,  0,  0,  0,  0, 20, 20},
        { 20, 30, 10,  0,  0, 10, 30, 20}};
    public static int kingEndBoard[][]={
        {-50,-40,-30,-20,-20,-30,-40,-50},
        {-30,-20,-10,  0,  0,-10,-20,-30},
        {-30,-10, 20, 30, 30, 20,-10,-30},
        {-30,-10, 30, 40, 40, 30,-10,-30},
        {-30,-10, 30, 40, 40, 30,-10,-30},
        {-30,-10, 20, 30, 30, 20,-10,-30},
        {-30,-30,  0,  0,  0,  0,-30,-30},
        {-50,-30,-30,-30,-30,-30,-30,-50}};

    public static int rating(BoardState boardToSearch) {
        int counter = 0;
        int material = 0;
        int depth = 0;

        //counter += rateAttack();
        material = rateMaterial(boardToSearch);
        counter += material;
        counter += rateMoveability(boardToSearch, depth, material);
        counter += ratePositional(boardToSearch, material);
        boardToSearch.flipBoard();

        //counter -= rateAttack();
        material = rateMaterial(boardToSearch);
        counter -= material;
        counter -= rateMoveability(boardToSearch, depth, material);
        counter -= ratePositional(boardToSearch, material);
        boardToSearch.flipBoard();

        return -(counter + (depth * 50));
        //return counter;
    }

    /*public static int rateAttack() {
        int counter=0;
        int tempPositionC=AlphaBetaChess.kingPositionC;
        for (int i=0;i<64;i++) {
            switch (AlphaBetaChess.chessBoard[i/8][i%8]) {
                case "P": {AlphaBetaChess.kingPositionC=i; if (!AlphaBetaChess.kingSafe()) {counter-=64;}}
                    break;
                case "R": {AlphaBetaChess.kingPositionC=i; if (!AlphaBetaChess.kingSafe()) {counter-=500;}}
                    break;
                case "K": {AlphaBetaChess.kingPositionC=i; if (!AlphaBetaChess.kingSafe()) {counter-=300;}}
                    break;
                case "B": {AlphaBetaChess.kingPositionC=i; if (!AlphaBetaChess.kingSafe()) {counter-=300;}}
                    break;
                case "Q": {AlphaBetaChess.kingPositionC=i; if (!AlphaBetaChess.kingSafe()) {counter-=900;}}
                    break;
            }
        }
        AlphaBetaChess.kingPositionC=tempPositionC;
        if (!AlphaBetaChess.kingSafe()) {counter-=200;}
        return counter/2;
    }*/

    public static int rateMaterial(BoardState boardToSearch) {
    	char[][] position = boardToSearch.getPosition();
    	boolean side = boardToSearch.getSide();

        int counter = 0;
        int bishopCounter = 0;

        if (side) {
        	for (int i = 0; i < 64; i++) {
	            switch (position[i/8][i%8]) {
	                case 'P': counter+=100;
	                    break;
	                case 'R': counter+=500;
	                    break;
	                case 'N': counter+=300;
	                    break;
	                case 'B': bishopCounter+=1;
	                    break;
	                case 'Q': counter+=900;
	                    break;
	            }
	        }
        }
        else {
        	for (int i = 0; i < 64; i++) {
	            switch (position[i/8][i%8]) {
	                case 'p': counter+=100;
	                    break;
	                case 'r': counter+=500;
	                    break;
	                case 'n': counter+=300;
	                    break;
	                case 'b': bishopCounter+=1;
	                    break;
	                case 'q': counter+=900;
	                    break;
	            }
	        }
        }
	        
        if (bishopCounter >= 2) {
            counter += 300 * bishopCounter;
        }
        else if (bishopCounter == 1) {
        	counter += 250;
        }
        
        return counter;
    }

    public static int rateMoveability(BoardState boardToSearch, int depth, int material) {
        int counter = 0;

        int listLength = MoveFinder.legalMoves(boardToSearch).length();
        counter += listLength; //5 pointer per valid move

        if (listLength == 0) {//current side is in checkmate or stalemate
            if (boardToSearch.winState().equals("Checkmate")) {
            	//if checkmate
                counter += -200000 * depth;
            }
            else if (boardToSearch.winState().equals("Stalemate")) {
            	//if stalemate
                counter += -150000 * depth;
            }
        }
        return counter;
    }

    public static int ratePositional(BoardState boardToSearch, int material) {
    	char[][] position = boardToSearch.getPosition();
    	boolean side = boardToSearch.getSide();

        int counter = 0;

        if (side) {
        	for (int i=0;i<64;i++) {
	            switch (position[i/8][i%8]) {
	                case 'P': counter += pawnBoard[i/8][i%8];
	                    break;
	                case 'R': counter += rookBoard[i/8][i%8];
	                    break;
	                case 'N': counter += knightBoard[i/8][i%8];
	                    break;
	                case 'B': counter += bishopBoard[i/8][i%8];
	                    break;
	                case 'Q': counter += queenBoard[i/8][i%8];
	                    break;
	                case 'K':
	                	if (material>=1750) {
	                		counter += kingMidBoard[i/8][i%8];
	                		counter += MoveFinder.possibleK(boardToSearch, i/8, i%8).length() * 10;
	                	}
	                	else {
	                		counter += kingEndBoard[i/8][i%8];
	                		counter += MoveFinder.possibleK(boardToSearch, i/8, i%8).length() * 30;
	                	}
	                    break;
            	}
        	}
        }
        else {
        	for (int i=0;i<64;i++) {
	            switch (position[i/8][i%8]) {
	                case 'p': counter += pawnBoard[i/8][i%8];
	                    break;
	                case 'r': counter += rookBoard[i/8][i%8];
	                    break;
	                case 'n': counter += knightBoard[i/8][i%8];
	                    break;
	                case 'b': counter += bishopBoard[i/8][i%8];
	                    break;
	                case 'q': counter += queenBoard[i/8][i%8];
	                    break;
	                case 'k':
	                	if (material>=1750) {
	                		counter += kingMidBoard[i/8][i%8];
	                		counter += MoveFinder.possibleK(boardToSearch, i/8, i%8).length() * 10;
	                	}
	                	else {
	                		counter += kingEndBoard[i/8][i%8];
	                		counter += MoveFinder.possibleK(boardToSearch, i/8, i%8).length() * 30;
	                	}
	                    break;
            	}
        	}
        }
        
        return counter;
    }
}
