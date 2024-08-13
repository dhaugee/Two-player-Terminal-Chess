public class Bishop {
    private int row;
    private int col;
    private boolean isBlack;

    public Bishop (int row, int col, boolean isBlack){
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    // TODO:
    //  check if the input move for a bishop piece is legal
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack)) { // checks if piece passes base conditions
            if (board.verifyDiagonal(this.row, this.col, endRow, endCol)) { // returns true if move is legal diagonally
                return true;
            }
        }
        return false; // otherwise, false is returned
    }
}
