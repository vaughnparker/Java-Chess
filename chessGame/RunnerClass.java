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

        System.out.println(gameBoard);
        
        /*
        System.out.println(MoveFinder.possibleMoves(gameBoard));
        System.out.println(MoveFinder.legalMoves(gameBoard));
        System.out.println(Notation.convertListToAlgebraic(gameBoard, MoveFinder.legalMoves(gameBoard)));
        */
        
        
        
        
        System.out.println("Hello! Welcome to chess!");

        int gamemode;
        String player1;
        String player2;
        boolean computerSide = false;
        boolean side;
        // player1 = WHITE, player2 = black

        while(true) {
            System.out.println("Which gamemode would you like?");
            System.out.println("Enter 1 for human vs human");
            System.out.println("Enter 2 for human vs Mr. Dumb");
            System.out.println("Enter 3 for human vs Mr. Mobile");
            System.out.println("Enter 4 for human vs Mr. Value");

            gamemode = playerInput.nextInt();
            playerInput.nextLine();

            if (gamemode == 1) {
                System.out.println();
                System.out.println("Enter Player 1 name (white):");
                player1 = playerInput.nextLine();
                
                System.out.println();
                System.out.println("Enter Player 2 name (black):");
                player2 = playerInput.nextLine();

                break;
            }
            else if (gamemode == 2) {
                System.out.println("Which side is computer playing? Enter true for white or false for black.");
                computerSide = playerInput.nextBoolean();
                playerInput.nextLine();

                System.out.println("Enter Player name " + (computerSide ? "(black):" : "(white):"));
                
                if (computerSide) {
                    player1 = "Mr. Dumb";
                    player2 = playerInput.nextLine();
                }
                else {
                    player1 = playerInput.nextLine();
                    player2 = "Mr. Dumb"; 
                }

                break;
            }
            else if (gamemode == 3) {
                System.out.println("Which side is computer playing? Enter true for white or false for black.");
                computerSide = playerInput.nextBoolean();
                playerInput.nextLine();

                System.out.println("Enter Player name " + (computerSide ? "(black):" : "(white):"));

                if (computerSide) {
                    player1 = "Mr. Mobile";
                    player2 = playerInput.nextLine();
                }
                else {
                    player1 = playerInput.nextLine();
                    player2 = "Mr. Mobile"; 
                }

                break;
            }
            else if (gamemode == 4) {
                System.out.println("Which side is computer playing? Enter true for white or false for black.");
                computerSide = playerInput.nextBoolean();
                playerInput.nextLine();

                System.out.println("Enter Player name " + (computerSide ? "(black):" : "(white):"));

                if (computerSide) {
                    player1 = "Mr. Value";
                    player2 = playerInput.nextLine();
                }
                else {
                    player1 = playerInput.nextLine();
                    player2 = "Mr. Value"; 
                }

                break;
            }
            else {
                System.out.println("Invalid gamemode, please enter an integer from 1-4");
            }
        }
        
        
        
        
        
        System.out.println();
        System.out.println("Okay, we have " + player1 + " vs. " + player2 + ". Begin!");
        System.out.println();
        System.out.println(gameBoard);
        // player1 = WHITE, player2 = black

        boolean gameInProgress = true;
        String playerMove = "";
        String listOfLegalMoves = "";
        
        while(gameInProgress) {
            
            side = gameBoard.getSide();
            System.out.println((side ? player1 : player2) + " to move!");
            listOfLegalMoves = MoveFinder.legalMoves(gameBoard);
            System.out.println(listOfLegalMoves);
            System.out.println(Notation.convertListToAlgebraic(gameBoard, listOfLegalMoves));
            
            
            
            
            if ((gamemode != 1) && (side == computerSide)) {
                if (gamemode == 2) {
                    playerMove = MrDumb.chooseMove(gameBoard);
                }
                else if (gamemode == 3) {
                    playerMove = MrMobile.chooseMove(gameBoard);
                }
                else if (gamemode == 4) {
                    playerMove = MrValue.chooseMove(gameBoard);
                }
                else {
                    System.out.println("Error: There is no computer player for this gamemode");
                }
            }
            else {
                playerMove = playerInput.nextLine();
            }

            System.out.println((side ? player1 : player2) + " played " + playerMove + " or " + Notation.convertCrazyToAlgebraic(gameBoard, playerMove));





            /*int oldX = Character.getNumericValue(playerMove.charAt(0));
            int oldY = Character.getNumericValue(playerMove.charAt(1));
            int newX = Character.getNumericValue(playerMove.charAt(2));
            
            int newY;
            char promotedPiece = 'E'; // E for error
            char capturedPiece = playerMove.charAt(4);
            
            int pawnDirection = (side ? -1 : 1);
            int promotionRank = (side ? 0 : 7);




            ERROR HANDLING, REVAMP IF I HAVE TIME



            if (playerMove.length() != 5) {
                System.out.println("Invalid move, move must be 5 characters long");
                System.out.println(gameBoard);
            }
            if ( (oldX < 0 || oldX > 7) || (oldY < 0 || oldY > 7) || (newX < 0 || newX > 7) || (newY < 0 || newY > 7) ) {
                System.out.println("Invalid move, move coordinates must be in range (0 <= coordinate <= 7)");
                System.out.println(gameBoard);
            }
            if (capturedPiece != gameBoard.getPosition()[newY][newX]) {
                System.out.println("Invalid move, there is no " + capturedPiece + " at coordinates " + newX + ", " + newY);
                System.out.println(gameBoard);
            }*/

            if (listOfLegalMoves.indexOf(playerMove) == -1) {
                System.out.println((side ? player1 : player2) + " made an invalid move, " + playerMove + " is illigal by the rules of chess.");
                System.out.println(gameBoard);
            }

            else {

                gameBoard.makeMove(playerMove);

                System.out.println();
                System.out.println(gameBoard);

                if (gameBoard.winState().equals("Checkmate")) {
                    System.out.println("Checkmate! " + (side ? player1 : player2) + " is the winner!");
                    //System.out.println("Checkmate! " + side + " is the winner!");
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
}