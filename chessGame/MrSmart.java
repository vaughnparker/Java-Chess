public final class MrSmart {

    public static final int MAX_DEPTH = 2;
    public static int evalnumber = 0;

    public static String chooseMove(BoardState boardToSearch) {
        boolean side = boardToSearch.getSide();
        String list = MoveFinder.legalMoves(boardToSearch);
        
        int numberOfChoices = list.length() / 5;
        if (numberOfChoices == 0) {
            System.out.println("Theres no moves, this should never print :O");
        }
        
        int chosenMove = 0;
        

        BoardState[] immidiateChoices = new BoardState[numberOfChoices];
        double[] choiceValues = new double[numberOfChoices];
        //int chosenIndex = 0;

        if (side) {

            //double max = -Double.MAX_VALUE;
            double max = -1000;

            for (int i = 0; i < numberOfChoices; i++) {
                immidiateChoices[i] = boardToSearch.cloneBoard();
                immidiateChoices[i].makeMove(list.substring(i * 5, i * 5 + 5));

                choiceValues[i] = minimax(immidiateChoices[i], MAX_DEPTH);
                System.out.println(choiceValues[i]);

                if (max < choiceValues[i]) {
                    max = choiceValues[i];
                    chosenMove = i;
                }
            }
        }
        else {

            //double min = Double.MAX_VALUE;
            double min = 1000;

            for (int i = 0; i < numberOfChoices; i++) {
                immidiateChoices[i] = boardToSearch.cloneBoard();
                immidiateChoices[i].makeMove(list.substring(i * 5, i * 5 + 5));

                choiceValues[i] = minimax(immidiateChoices[i], MAX_DEPTH);
                System.out.println(choiceValues[i]);

                if (min > choiceValues[i]) {
                    min = choiceValues[i];
                    chosenMove = i;
                }
            }
        }


        return list.substring(chosenMove * 5, chosenMove * 5 + 5);
    }

    public static double minimax(BoardState boardToSearch, int depth) {
        boolean side = boardToSearch.getSide();
        String list  = MoveFinder.legalMoves(boardToSearch);

        int numberOfChoices = list.length() / 5;

        if (numberOfChoices == 0) {
            System.out.println("Theres no moves :O");
            return (side ? -1000 : 1000);
        }

        if (depth == 0) {
            return SmartEval.rating(boardToSearch);
        }
        else if (side) {
            // if its a maximizing node/Boardstate
            // also if it's white to move

            //first make move
            //then find its largest child
            //then undo move


            double[] childValues = new double[numberOfChoices];
            double max = -1000;

            for (int i = 0; i < numberOfChoices; i++) {

                boardToSearch.makeMove(list.substring(i * 5, i * 5 + 5));

                childValues[i] = minimax(boardToSearch, depth-1);
                max = Math.max(max, childValues[i]);

                boardToSearch.undoMove(list.substring(i * 5, i * 5 + 5));
            }

            return max;
        }
        else {
            // if its a minimizing node/Boardstate
            // also if it's black to move

            double[] childValues = new double[numberOfChoices];
            double min = 1000;

            for (int i = 0; i < numberOfChoices; i++) {

                boardToSearch.makeMove(list.substring(i * 5, i * 5 + 5));

                childValues[i] = minimax(boardToSearch, depth-1);
                min = Math.min(min, childValues[i]);

                boardToSearch.undoMove(list.substring(i * 5, i * 5 + 5));
            }

            return min;
        }
    }
    
    /*public static double evaluationFunction(BoardState boardToSearch) {
    	boolean side = boardToSearch.getSide();

        
    }*/
}