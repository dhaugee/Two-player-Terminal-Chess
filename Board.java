// EDITED BY: PETER YI,DYLAN HAUGEE

import java.lang.Math; // used for absolute values

public class Board {
    private Piece[][] board;

    public Board() {
        board = new Piece[8][8]; // constructor initializes an 8x8 2D array Piece, representing the board
    }

    public Piece getPiece(int row, int col) {return (board[row][col]);} // returns the Piece object stored at a given row and column
    public void setPiece(int row, int col, Piece piece) {board[row][col] = piece;} // adds a Piece object to a spot on the board

    //TODO:
    // moves a piece, provided it's legal. returns a boolean representing if the move took place
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        if (this.board[startRow][startCol] != null && this.board[startRow][startCol].isMoveLegal(this, endRow, endCol)) { // tests that the starting position has a piece on it and that the move is legal
            this.board[endRow][endCol] = this.board[startRow][startCol]; // if the requirements are met, the ending spot is set to represent whatever Piece object was at the starting spot
            this.board[startRow][startCol].setPosition(endRow, endCol); // the actual position of the piece as stored by Java is changed to the end position
            this.board[startRow][startCol] = null; // the starting spot is set to represent null
            return true; // since the piece was moved, true is returned
        }
        return false; // if the base case isn't met, false is returned
    }

    //TODO:
    // test if the game is over by counting the amount of kings
    public boolean isGameOver() {
        int kings = 0; // variable to track the amount of kings on the board
        int nulls = 0; // variable only here to help with the first if case
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){ // nested for loops to cycle through each position on the board
                if (board[i][j] != null){ // isGameOver was having some bugs, so this if loop has been implemented as a precaution
                    if (board[i][j].getCharacter() == '\u265a' || board[i][j].getCharacter() == '\u2654') { // tests if a board position's character is that of a king
                        kings ++; // we should end up with 2
                    }
                }
            }
        }
        if (kings != 2){ // if there are any amount of kings besides 2,
            System.out.println("game over:"); // the game is over.
            return true;
        }
        return false; // if true is not returned, there are two kings remaining and the game is not yet over
    }

    //TODO:
    // return a string representing the board and its pieces
    public String toString() {
        String[] string = {"\uFF10", "\uFF11", "\uFF12", "\uFF13", "\uFF14", "\uFF15", "\uFF16", "\uFF17", "\u2001"}; // an array consisting of Unicode digits 0-7 (matched with their indexes) and the Unicode space at the end
        String nums = "   \uFF10\uFF11\uFF12\uFF13\uFF14\uFF15\uFF16\uFF17\n"; // the upper number line (may appear misaligned)
        String lines = ""; // an empty string that will represent the rest of the board
        for (int i = 0; i < 8; i++) { // loops through the rows of the board
            lines += string[i]; // every time a row is beginning to be looped through, the Unicode digit of that row's number is added to the string
            for (int z = 0; z < 8; z++) { // loops through the columns of the board
                if (board[i][z] == null){
                    lines += "|" + string[8]; // if there is no Piece object on a spot, a divider and Unicode space is added
                }
                else {
                    lines += "|" + board[i][z].getCharacter(); // otherwise, a divider and the Unicode representation of the piece is added
                }
                if (z == 7){
                    lines += "|\n"; // if the loop has reached the last column, a new line is added to the string so we can proceed to the next row
                }
            }
        }
        return nums + lines; // the upper line and rest of the board is returned
    }

    //TODO:
    // clear the board
    public void clear() {
        for (int i = 0; i < 8; i++) {
            for (int g = 0; g < 8; g++) {
                board[i][g] = null; // loops through the board Piece array, setting each position to null
            }
        }
    }

    //TODO:
    // test several conditions related to the legality of a piece's move
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {
        if (startRow < 8 && startRow > -1 && startCol < 8 && startCol > -1 && endRow < 8 && endRow > -1 && endCol < 8 && endCol > -1) { // checks if the starting and ending position are within the bounds of the board
            if (getPiece(startRow, startCol) != null) { // checks for a piece on the starting spot
                if (board[startRow][startCol].getIsBlack() == isBlack) { // checks if the color of the piece on the starting spot matches that of the input color
                    if (getPiece(endRow, endCol) == null || board[endRow][endCol].getIsBlack() != isBlack) { // ensures that the ending spot is either null or has a piece of the opposite color on it (aka capturing)
                        return true; // returns true if each of these conditions is met, giving a green light to move forward with the move
                    }
                }
            }
        }
        return false; // returns false if any condition is not met
    }

    //TODO:
    // check whether a start and end position are adjacent
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        if (Math.abs(startRow - endRow) <= 1 && Math.abs(startCol - endCol) <= 1) { // tests if the absolute value of the difference of the column and row start/end positions is no larger than 1
            return true; // if so, they must be adjacent, and the move can proceed
        }
        return false; // returns false if the positions are not directly adjacent
    }

    //TODO:
    // check whether a start and end position are a valid horizontal move
    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        int max = Math.max(startCol,endCol); // takes the larger of the two columns from which the move would take place
        int min = Math.min(startCol,endCol); // takes the smaller of the two columns from which the move would take place
        if (startRow == endRow) { // checks if the move will take place on one row (a horizontal move)
            for (int i = min + 1; i < max; i++) { // loops through each position on the row between the smaller and larger column
                if (getPiece(startRow, i) != null){ // checks if the position is null or not
                    return false; // if there's a position between the start and ending spots with a piece on it, the move cannot happen
                }
            }
            return true; // if the loop ends without returning false, the two conditions are met and the horizontal move can proceed

        }
        return false; // if the move would change rows, its no longer a horizontal move and cannot proceed
    }

        //TODO:
        // check whether a start and end position are a valid vertical move
        public boolean verifyVertical ( int startRow, int startCol, int endRow, int endCol){
            int max = Math.max(startRow,endRow); // takes the larger of the two rows from which the move would take place
            int min = Math.min(startRow,endRow); // takes the smaller of the two rows from which the move would take place
                if (startCol == endCol) { // checks if the move will take place on one column (a vertical move)
                    for (int i = min+1; i < max; i++) { // loops through each position on the column between the smaller and larger row
                        if (getPiece(i, startCol) != null) { // checks if the position is null or not
                            return false; // if there's a position between the start and ending spots with a piece on it, the move cannot happen
                        }
                    }
                    return true; // if the loop ends without returning false, the two conditions are met and the vertical move can proceed

                }
                return false;// if the move would change columns, its no longer a vertical move and cannot proceed
        }



        //TODO:
        // check whether a start and end position are a valid diagonal move
        public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {
            boolean b = Math.abs(startRow - endRow) == Math.abs(startCol - endCol); // checks if the horizontal and vertical differences are equal
            if (startRow > endRow && startCol < endCol) { // checks if diagonal starts in the upper left
                if (b) { // checks if the boolean is true, meaning it is a diagonal move
                    for(int i = startRow - 1; i >= endRow; i--) {
                        for (int j = startCol + 1; i <= endCol; i++) {
                            if (getPiece(i, j) == null) { // checks if the positions between the start and end are null
                                return true; // if so, returns true and the move can proceed
                            }
                        }
                    }
                }
            }
            if (startRow < endRow && startCol < endCol) { // checks if diagonal starts in the bottom left
                if (b) { // checks if the boolean is true, meaning it is a diagonal move
                    for(int i = startRow + 1; i <= endRow; i++) {
                        for (int j = startCol + 1; i <= endCol; i++) {
                            if (getPiece(i, j) == null) { // checks if the positions between the start and end are null
                                return true; // if so, returns true and the move can proceed
                            }
                        }
                    }
                }
            }
            if (startRow < endRow && startCol > endCol) { // checks if diagonal starts in the bottom right
                if (b) { // checks if the boolean is true, meaning it is a diagonal move
                    for(int i = startRow + 1; i <= endRow; i++) {
                        for (int j = startCol - 1; i >= endRow; i--) {
                            if (getPiece(i, j) == null) { // checks if the positions between the start and end are null
                                return true; // if so, returns true and the move can proceed
                            }
                        }
                    }
                }
            }
            if (startRow > endRow && startCol > endCol) { // checks if diagonal starts in the upper right
                if (b) { // checks if the boolean is true, meaning it is a diagonal move
                    for(int i = startRow - 1; i >= endRow; i--) {
                        for (int j = startCol - 1; i >= endRow; i--) {
                            if (getPiece(i, j) == null) { // checks if the positions between the start and end are null
                                return true; // if so, returns true and the move can proceed
                            }
                        }
                    }
                }
            }
            return false; // if not conditions are met, returns false and the move cannot proceed
        }
    }


