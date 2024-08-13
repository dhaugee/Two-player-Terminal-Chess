public class Rook{
    //written and edited by: Peter and Dylan
    private int row;
    private int col;
    private boolean isBlack;

    public Rook (int row, int col, boolean isBlack){
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    // TODO:
    //  check if the input move for a rook piece is legal
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack)) { // checks if piece passes base conditions
            if (board.verifyVertical(this.row, this.col, endRow, endCol)) { // returns true if move is legal vertically
                return true;
            }
            else if (board.verifyHorizontal(this.row, this.col, endRow, endCol)) { // returns true if move is legal horizontally
                return true;
            }
        }
        return false; // if move isn't legal in either of these directions, false is returned
    }
}
