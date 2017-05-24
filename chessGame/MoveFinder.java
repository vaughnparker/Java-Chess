/**
 * Write a description of class MoveFinder here.
 * 
 * @author Vaughn 
 * @version 4.11.17
 */
public class MoveFinder
{

    //capital letters are white
    
    /*public MoveFinder() {
        
    }*/

    public static String legalMoves(BoardState boardToSearch) {

        String possibleList = MoveFinder.possibleMoves(boardToSearch);
        String legalList = MoveFinder.removeSelfChecks(boardToSearch, possibleList);

        return legalList;
    }

    public static String possibleMoves(BoardState boardToSearch) {
        String list = "";
        char[][] position = boardToSearch.getPosition();
        int y;
        int x;
        boolean sideToMove = boardToSearch.getSide();

        for (int i = 0; i < 64; i++) {
            y = i / 8;
            x = i % 8;
            //top row has y = 0, bottom row has y == 7
            //left 'column' has x == 0
            //a piece on e4 = position[4][5]

            if(sideToMove) {
                // if it is WHITE's move
                switch (position[y][x]) { //.toUpperCase??
                    case 'P': list+=possibleP(boardToSearch, y, x);
                        break;
                    case 'N': list+=possibleN(boardToSearch, y, x);
                        break;
                    case 'B': list+=possibleB(boardToSearch, y, x);
                        break;
                    case 'R': list+=possibleR(boardToSearch, y, x);
                        break;
                    case 'Q': list+=possibleQ(boardToSearch, y, x);
                        break;
                    case 'K': list+=possibleK(boardToSearch, y, x);
                        break;
                }
            }
            else {
                // if it is black's move
                switch (position[y][x]) { //.toUpperCase??
                    case 'p': list+=possibleP(boardToSearch, y, x);
                        break;
                    case 'n': list+=possibleN(boardToSearch, y, x);
                        break;
                    case 'b': list+=possibleB(boardToSearch, y, x);
                        break;
                    case 'r': list+=possibleR(boardToSearch, y, x);
                        break;
                    case 'q': list+=possibleQ(boardToSearch, y, x);
                        break;
                    case 'k': list+=possibleK(boardToSearch, y, x);
                        break;
                }
            }
        }

        return list; //x1, y1, x2, y2, captured piece
    }


    public static String possibleMovesInDirection(BoardState boardToSearch, int yCoord, int xCoord, int yDir, int xDir) {
        String list = "";
        // start with empty list

        char[][] position = boardToSearch.getPosition();
        boolean side = boardToSearch.getSide();

        int yToCheck = yCoord + yDir;
        int xToCheck = xCoord + xDir;
        // check space in direction

        while (!(yToCheck < 0 || yToCheck > 7 || xToCheck < 0 || xToCheck > 7)) {

            if (position[yToCheck][xToCheck] == ' ') {
                list = list + xCoord + yCoord + xToCheck + yToCheck + " ";
                // if that square is empty then add it and repeat
            }
            else if (Character.isUpperCase(position[yToCheck][xToCheck]) != side) {
                list = list + xCoord + yCoord + xToCheck + yToCheck + position[yToCheck][xToCheck];
                return list;
                // if that square has an enemy piece then add it and return list
            }
            else {
                return list;
                // if that square has a friendly piece then don't add it and return list
            }

            yToCheck += yDir;
            xToCheck += xDir;
        }
        
        return list;
        // if either tempX or tempY are out of bounds then return list (we are done)
        
    }

    public static String possibleR(BoardState boardToSearch, int yCoord, int xCoord) {
        char[][] position = boardToSearch.getPosition();

        String list = "";

        list += possibleMovesInDirection(boardToSearch, yCoord, xCoord, -1, 0);
        list += possibleMovesInDirection(boardToSearch, yCoord, xCoord, 0, -1);
        list += possibleMovesInDirection(boardToSearch, yCoord, xCoord, 0, 1);
        list += possibleMovesInDirection(boardToSearch, yCoord, xCoord, 1, 0);

        return list;

        /*
        // this one is for looking "up" (towards rank 8, towards position[0])
        for(int tempy = yCoord - 1; tempy >= 0; tempy--) {
            if(position[tempy][xCoord] == ' ') {
                list = list + xCoord + yCoord + xCoord + tempy + " ";
            }
            else {
                break; //alternatively, tempy = -1; or restructure into while loop
            }
        }
        // this one is for looking down
        for(int tempy = yCoord + 1; tempy <= 7; tempy++) {
            if(position[tempy][xCoord] == ' ') {
                list = list + xCoord + yCoord + xCoord + tempy + " ";
            }
            else {
                break; //alternatively, tempy = -1; or restructure into while loop
            }
        }
        // this for loop looks left
        for(int tempx = xCoord - 1; tempx >= 0; tempx--) {
            if(position[yCoord][tempx] == ' ') {
                list = list + xCoord + yCoord + tempx + yCoord + " ";
            }
            else {
                break; //alternatively, tempx = -1; or restructure into while loop
            }
        }
        // and this one looks right (towards the h file)
        for(int tempx = xCoord + 1; tempx <= 7; tempx++) {
            if(position[yCoord][tempx] == ' ') {
                list = list + xCoord + yCoord + tempx + yCoord + " ";
            }
            else {
                break; //alternatively, tempx = -1; or restructure into while loop
            }
        }
        */

        // I could re-order these for loops to make it SEEM like it is "scanning" up--> left --> right --> down -->

        // I could also make a method "return all unblocked spaces in this direction"
        // this could take in currentSquare parameters (x and y) and also a direction int or pair of ints to show direction of movement
        // this could compactify code for ROOK, BISHOP, and QUEEN piece types
    }

    public static String possibleB(BoardState boardToSearch, int yCoord, int xCoord) {
        char[][] position = boardToSearch.getPosition();

        String list = "";

        list += possibleMovesInDirection(boardToSearch, yCoord, xCoord, -1, -1); //checks up-left diagonal
        list += possibleMovesInDirection(boardToSearch, yCoord, xCoord, -1, 1); //checks up-right diagonal
        list += possibleMovesInDirection(boardToSearch, yCoord, xCoord, 1, -1); //checks down-left diagonal
        list += possibleMovesInDirection(boardToSearch, yCoord, xCoord, 1, 1); //checks down-right diagonal

        return list;
    }

    public static String possibleQ(BoardState boardToSearch, int yCoord, int xCoord) {
        //char[][] position = boardToSearch.getPosition();

        String list = "";

        list += possibleR(boardToSearch, yCoord, xCoord); //checks rook moves
        list += possibleB(boardToSearch, yCoord, xCoord); //checks bishop moves
        // this is a neat solution as a queen is really just a "compound piece"
        // I could use this exact type of method combination to implement fairy chess pieces

        return list;
    }

    public static boolean isValidSquare(BoardState boardToSearch, int yCoord, int xCoord) {
        char[][] position = boardToSearch.getPosition();
        boolean side = boardToSearch.getSide();

        return !((yCoord < 0 || yCoord > 7 || xCoord < 0 || xCoord > 7) || ((position[yCoord][xCoord] != ' ') && Character.isUpperCase(position[yCoord][xCoord]) == side));
    }

    public static String moveToSquare(BoardState boardToSearch, int yCoord, int xCoord, int yToAdd, int xToAdd) {
        char[][] position = boardToSearch.getPosition();

        int yToCheck = yCoord + yToAdd;
        int xToCheck = xCoord + xToAdd;

        if(isValidSquare(boardToSearch, yToCheck, xToCheck)) {
            return "" + xCoord + yCoord + xToCheck + yToCheck + position[yToCheck][xToCheck];
        }
        else {
            return "";
        }
    }

    public static String possibleN(BoardState boardToSearch, int yCoord, int xCoord) {
        char[][] position = boardToSearch.getPosition();
        boolean side = boardToSearch.getSide();

        String list = "";

        list += moveToSquare(boardToSearch, yCoord, xCoord, -2, -1);
        list += moveToSquare(boardToSearch, yCoord, xCoord, -2, 1);

        list += moveToSquare(boardToSearch, yCoord, xCoord, -1, 2);
        list += moveToSquare(boardToSearch, yCoord, xCoord, 1, 2);

        list += moveToSquare(boardToSearch, yCoord, xCoord, 2, 1);
        list += moveToSquare(boardToSearch, yCoord, xCoord, 2, -1);

        list += moveToSquare(boardToSearch, yCoord, xCoord, 1, -2);
        list += moveToSquare(boardToSearch, yCoord, xCoord, -1, -2);

        return list;
    }

    public static String possibleP(BoardState boardToSearch, int yCoord, int xCoord) {
        char[][] position = boardToSearch.getPosition();
        boolean side = boardToSearch.getSide();
        int pawnDirection = side ? -1 : 1;
        int promotionRank = side ? 0 : 7;
        
        String list = "";

        //if (yCoord != promotionRank - pawnDirection) {
            //movement type A: regular 1 square forward
            if (isValidSquare(boardToSearch, yCoord + pawnDirection, xCoord) && position[yCoord + pawnDirection][xCoord] == ' ') {
                list = list + xCoord + yCoord +  xCoord + (yCoord + pawnDirection) + " ";
            }
    
            //movement type B: starting 2 squares forward
            if (yCoord == 7 -  promotionRank + pawnDirection) {
                //System.out.println("double space working");
                if (isValidSquare(boardToSearch, yCoord + (2 * pawnDirection), xCoord) && position[yCoord + pawnDirection][xCoord] == ' ') {
                    list = list + xCoord + yCoord +  xCoord + (yCoord + (2 * pawnDirection)) + " ";
                }
            }
            
            //movement type C: diagonal capture
            if (isValidSquare(boardToSearch, yCoord + pawnDirection, xCoord + 1)
            && (Character.isUpperCase(position[yCoord + pawnDirection][xCoord + 1]) != side)
            && (position[yCoord + pawnDirection][xCoord + 1] != ' ')) {
                list = list + xCoord + yCoord +  (xCoord + 1) + (yCoord + pawnDirection) + position[yCoord + pawnDirection][xCoord + 1];
            }
    
            if (isValidSquare(boardToSearch, yCoord + pawnDirection, xCoord - 1)
            && (Character.isUpperCase(position[yCoord + pawnDirection][xCoord - 1]) != side)
            && (position[yCoord + pawnDirection][xCoord - 1] != ' ')) {
                list = list + xCoord + yCoord +  (xCoord - 1) + (yCoord + pawnDirection) + position[yCoord + pawnDirection][xCoord - 1];
            }
        //}
        //else {
            //movement type D: promotion
            
            for (int i = 0; i < list.length(); i += 5) {
                
                String moveToEdit = list.substring(i, i+5); //print stuff if it doesnt work
                //THIS COULD CAUSE AN OOB BABY ERROR LATER SO I GOTTA FIX THIS SUBSTRING
                
                if (Character.getNumericValue(moveToEdit.charAt(3)) == promotionRank) {
                    // this turns moves that look like "4140 " into "414Q " and "7667N" into "766Nq"
                    
                    if (side) {
                        list = list.substring(0, i) + moveToEdit.substring(0, 3) + "Q" + moveToEdit.substring(4)
                        + moveToEdit.substring(0, 3) + "R" + moveToEdit.substring(4)
                        + moveToEdit.substring(0, 3) + "B" + moveToEdit.substring(4)
                        + moveToEdit.substring(0, 3) + "N" + moveToEdit.substring(4)
                        + list.substring(i+5);
                    }
                    else {
                       list = list.substring(0, i) + moveToEdit.substring(0, 3) + "q" + moveToEdit.substring(4)
                        + moveToEdit.substring(0, 3) + "r" + moveToEdit.substring(4)
                        + moveToEdit.substring(0, 3) + "b" + moveToEdit.substring(4)
                        + moveToEdit.substring(0, 3) + "n" + moveToEdit.substring(4)
                        + list.substring(i+5);
                    }
                }
            }
        
            
        
        //}

        //movement type E: en passant

        return list;
    }

    public static String possibleK(BoardState boardToSearch, int yCoord, int xCoord) {
        char[][] position = boardToSearch.getPosition();
        boolean side = boardToSearch.getSide();

        String list = "";

        list += moveToSquare(boardToSearch, yCoord, xCoord, -1, -1);
        list += moveToSquare(boardToSearch, yCoord, xCoord, -1, 0);
        list += moveToSquare(boardToSearch, yCoord, xCoord, -1, 1);

        list += moveToSquare(boardToSearch, yCoord, xCoord, 0, -1);
        list += moveToSquare(boardToSearch, yCoord, xCoord, 0, 1);
        
        list += moveToSquare(boardToSearch, yCoord, xCoord, 1, -1);
        list += moveToSquare(boardToSearch, yCoord, xCoord, 1, 0);
        list += moveToSquare(boardToSearch, yCoord, xCoord, 1, 1);

        return list;
    }

    public static String removeSelfChecks(BoardState boardToSearch, String list) {

        String shortenedList = "";

        for (int i = 5; i <= list.length(); i += 5) { //should this be < or <= ? fix this later
            
            //BoardState tempKingBoard = new BoardState(boardToSearch.getPosition(), boardToSearch.getSide());
            //this is a shallow copy, doesn't work!

            char[][] tempKingPosition = new char[8][8];

            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    tempKingPosition[row][col] = boardToSearch.getPosition()[row][col];
                }
            }

            //char[][] tempKingPosition = boardToSearch.getPosition().clone();

            BoardState tempKingBoard = new BoardState(tempKingPosition, boardToSearch.getSide());
            //this one is a deep copy, let's see if this works


            String tempMove = list.substring(i-5, i);
            tempKingBoard.makeMove(tempMove);
            
            //System.out.println("WTF my guy gameBoard\n" + boardToSearch);
            // this was for the shallow copy error

            if (!tempKingBoard.isKingTakeable()) {
                shortenedList += tempMove;
            }

        }


        return shortenedList;


    }
}