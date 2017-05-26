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
        String newMove = "";

        if (crazyMove.length() != 5) {
           System.out.println("Notation error, crazyMove is the wrong length");
           return "";
        }
        
        // if castling
        if (crazyMove.charAt(0) == 'O') {
            return crazyMove;
        }

        // assuming standard move
        int oldX = Character.getNumericValue(crazyMove.charAt(0));
        int oldY = Character.getNumericValue(crazyMove.charAt(1));
        int newX = Character.getNumericValue(crazyMove.charAt(2));
        int newY = Character.getNumericValue(crazyMove.charAt(3));
        char capturedPiece = crazyMove.charAt(4);

        if (position[oldY][oldX] == 'P' || position[oldY][oldX] == 'p') {
            newMove += " " + convertNumberToRank(newX) + (8 - newY) + "  ";
        }
        else if (capturedPiece == ' ') {
            newMove += ("" + position[oldY][oldX]).toUpperCase() + convertNumberToRank(newX) + (8 - newY) + "  ";
        }
        else {
            newMove += ("" + position[oldY][oldX]).toUpperCase() + "x" + convertNumberToRank(newX) + (8 - newY) + " ";
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