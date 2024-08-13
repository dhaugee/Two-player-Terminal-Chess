public class King { //creates class King
    private int row;
    private int col;
    private boolean isBlack;

    public King (int row, int col, boolean isBlack){
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    // TODO:
    //  check if the input move for a king piece is legal
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack)) { // checks if piece passes base conditions
            if (board.verifyAdjacent(this.row, this.col, endRow, endCol)) { // returns true if the move is legal adjacently
                return true;
            }
        }
        return false; // otherwise, false is returned
    }
}
