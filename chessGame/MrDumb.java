public final class MrDumb {
    public String chooseMove(BoardState boardToSearch) {
        String list = MoveFinder.legalMoves(boardToSearch);
        
        int numberOfChoices = list.length() / 5;
        if (numberOfChoices == 0) {
            System.out.println("Theres no moves, this should never print :O");
        }
        
        return "";
    }
}