public class Queen {
    private int row;
    private int col;
    private boolean isBlack;

    public Queen (int row, int col, boolean isBlack){
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    // TODO:
    //  check if the input move for a queen piece is legal
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack)) { // checks if piece passes base conditions
            if (board.verifyVertical(this.row, this.col, endRow, endCol)) {return true;} // returns true if move is legal vertically
            else if (board.verifyHorizontal(this.row, this.col, endRow, endCol)) {return true;} // returns true if move is legal horizontally
            else if (board.verifyDiagonal(this.row, this.col, endRow, endCol)) {return true;} // returns true if move is legal diagonally
        }
        return false; // if move isn't legal in any of these directions, false is returned
    }
}
