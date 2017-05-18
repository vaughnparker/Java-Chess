public class Game {

    BoardState gameBoard;
    char[][] position;
    boolean side;
    MoveFinder myAi;

    public Game() {
        gameBoard = new BoardState();
        position = boardToSearch.getPosition();
        side = boardToSearch.getSide();
        myAi = new MoveFinder();
    }

    public Game(BoardState board) {
        gameBoard = board;
        position = gameBoard.getPosition();
        side = gameBaord.getSide();
        myAi = new MoveFinder();
    }



    public static void makeMove(BoardState oldBoard, String move) {

        listOfValidMoves = myAi.possibleMoves;

        if (listOfValidMoves.indexOf(move) == -1) {
            return "INVALID MOVE";
        }



    }

}