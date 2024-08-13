public class Knight {
    private int row;
    private int col;
    private boolean isBlack;

    public Knight (int row, int col, boolean isBlack){
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    // TODO:
    //  check if the input move for a knight piece is legal
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
         if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack)) { // checks if piece passes base conditions
             if (Math.abs(this.row - endRow) == 2 && Math.abs(this.col - endCol) == 1){ // checks for the L-shaped move along the row
                 return true; // knight can jump over pieces, so true is returned if this case is met
             }
             else if(Math.abs(this.row - endRow) == 1 && Math.abs(this.col - endCol) == 2){ // checks for the L-shaped move along the column
                 return true; // knight can jump over pieces, so true is returned if this case is met
             }
         }
         return false; // returns false in any other case
    }
}

