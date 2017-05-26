public final class MrDumb {
    public static String chooseMove(BoardState boardToSearch) {
        String list = MoveFinder.legalMoves(boardToSearch);
        
        int numberOfChoices = list.length() / 5;
        if (numberOfChoices == 0) {
            System.out.println("Theres no moves, this should never print :O");
        }
        int chosenMove = (int)(Math.random() * numberOfChoices);
        
        return list.substring(chosenMove * 5, chosenMove * 5 + 5);
    }
}