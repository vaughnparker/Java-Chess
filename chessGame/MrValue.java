public final class MrValue {

    public static final int MAX_DEPTH = 2;

    public static String chooseMove(BoardState boardToSearch) {
        boolean side = boardToSearch.getSide();
        String list = MoveFinder.legalMoves(boardToSearch);
        
        int numberOfChoices = list.length() / 5;
        if (numberOfChoices == 0) {
            System.out.println("Theres no moves :O");
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
            return (side ? -100 : 100);
        }

        if (depth == 0) {
            return evaluationFunction(boardToSearch);
        }
        else if (side) {
            // if its a maximizing node/Boardstate
            // also if it's white to move
            BoardState[] childNodes = new BoardState[numberOfChoices];
            double[] childValues = new double[numberOfChoices];
            //double max = -Double.MAX_VALUE;
            double max = -1000;

            for (int i = 0; i < childNodes.length; i++) {
                childNodes[i] = boardToSearch.cloneBoard();
                childNodes[i].makeMove(list.substring(i * 5, i * 5 + 5));

                childValues[i] = minimax(childNodes[i], depth-1);
                max = Math.max(max, childValues[i]);
            }

            return max;
        }
        else {
            // if its a minimizing node/Boardstate
            // also if it's black to move
            BoardState[] childNodes = new BoardState[numberOfChoices];
            double[] childValues = new double[numberOfChoices];
            //double min = Double.MAX_VALUE;
            double min = 1000;

            for (int i = 0; i < childNodes.length; i++) {
                childNodes[i] = boardToSearch.cloneBoard();
                childNodes[i].makeMove(list.substring(i * 5, i * 5 + 5));

                childValues[i] = minimax(childNodes[i], depth-1);
                min = Math.min(min, childValues[i]);
            }

            return min;
        }
    }
    
    public static double evaluationFunction(BoardState boardToSearch) {
        char[][] position = boardToSearch.getPosition();
    	boolean side = boardToSearch.getSide();

        int wCount = 0;
        int bCount = 0;
        int x;
        int y;
    	
		for (int i = 0; i < 64; i++) {
            y = i / 8;
            x = i % 8;

            
            switch (position[y][x]) { //.toUpperCase??
                case 'P': wCount += 1;
                    break;
                case 'N': wCount += 3;
                    break;
                case 'B': wCount += 3;
                    break;
                case 'R': wCount += 5;
                    break;
                case 'Q': wCount += 9;
                    break;
                case 'p': bCount += 1;
                    break;
                case 'n': bCount += 3;
                    break;
                case 'b': bCount += 3;
                    break;
                case 'r': bCount += 5;
                    break;
                case 'q': bCount += 9;
                    break;
            }
        }
        return wCount - bCount;
    }
}