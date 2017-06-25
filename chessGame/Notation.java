public class Notation
{

    public static String convertListToAlgebraic(BoardState boardToSearch, String oldList) {
        char[][] position = boardToSearch.getPosition();
        String newList = "";

        for (int i = 5; i <= oldList.length(); i +=5) {
            newList += convertCrazyToAlgebraic(boardToSearch, oldList.substring(i-5, i));
        }
        
        return newList;
    }

    public static String convertCrazyToAlgebraic(BoardState boardToSearch, String crazyMove) {
        char[][] position = boardToSearch.getPosition();
        boolean side = boardToSearch.getSide();
        String newMove = "";

        if (crazyMove.length() != 5) {
           System.out.println("Notation error, crazyMove is the wrong length");
           return "";
        }
        
        // if castling
        if (BoardState.determineTypeOfMove(crazyMove).equals("Castling")) {
            return crazyMove;
        }
        // else if promotion
        else if (BoardState.determineTypeOfMove(crazyMove).equals("Promotion")) {
            int promotionRank = (side ? 0 : 7);

            int oldX = Character.getNumericValue(crazyMove.charAt(0));
            int oldY = Character.getNumericValue(crazyMove.charAt(1));
            int newX = Character.getNumericValue(crazyMove.charAt(2));
            int newY = promotionRank;
            
            char promotedPiece = crazyMove.charAt(3);
            char capturedPiece = crazyMove.charAt(4);

            if (capturedPiece == ' ') {
                newMove += "" + convertNumberToRank(newX) + (8 - newY) + "=" + promotedPiece + " ";
            }
            else {
                newMove += "x" + convertNumberToRank(newX) + (8 - newY) + "=" + promotedPiece + " ";
            }

        }

        // assuming standard move
        int oldX = Character.getNumericValue(crazyMove.charAt(0));
        int oldY = Character.getNumericValue(crazyMove.charAt(1));
        int newX = Character.getNumericValue(crazyMove.charAt(2));
        int newY = Character.getNumericValue(crazyMove.charAt(3));
        char capturedPiece = crazyMove.charAt(4);




        // this thing needs to work, promotion AND captures are not accounted for
        if (capturedPiece == ' ') {
            if (position[oldY][oldX] == 'P' || position[oldY][oldX] == 'p') {
                newMove += "" + convertNumberToRank(newX) + (8 - newY) + "   ";
            }
            else {
                newMove += ("" + position[oldY][oldX]).toUpperCase() + convertNumberToRank(newX) + (8 - newY) + "  ";
            }
        }

        else {
            if (position[oldY][oldX] == 'P' || position[oldY][oldX] == 'p') {
                newMove += "" + convertNumberToRank(oldX) + "x" + convertNumberToRank(newX) + (8 - newY) + " ";
            }
            else {
                newMove += ("" + position[oldY][oldX]).toUpperCase() + "x" + convertNumberToRank(newX) + (8 - newY) + " ";
            }
            
        }

        

        return newMove;
    }

    public static char convertNumberToRank(int xCoord) {
        switch (xCoord) {
            case 0: return 'a';
            case 1: return 'b';
            case 2: return 'c';
            case 3: return 'd';
            case 4: return 'e';
            case 5: return 'f';
            case 6: return 'g';
            case 7: return 'h';
        }
        return 'E';
    }
    
    public static String convertAlgebraicToCrazy(BoardState boardToSearch, String crazyMove) {
        return "";
    }
}