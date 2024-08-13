import java.util.Scanner;

public class Piece {

    // Instance variables
    private char character;
    private int row;
    private int col;
    private boolean isBlack;

    /**
     * Constructor.
     * @param character     The character representing the piece.
     * @param row           The row on the board the piece occupies.
     * @param col           The column on the board the piece occupies.
     * @param isBlack       The color of the piece.
     */
    public Piece(char character, int row, int col, boolean isBlack) {
        this.character = character;
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    /**
     * Determines if moving this piece is legal.
     * @param board     The current state of the board.
     * @param endRow    The destination row of the move.
     * @param endCol    The destination column of the move.
     * @return If the piece can legally move to the provided destination on the board.
     */
    public boolean isMoveLegal(Board board, int endRow, int endCol) {
        switch (this.character) {
            // Pawn chars
            case '\u2659':
            case '\u265f':
                Pawn pawn = new Pawn(row, col, isBlack);
                return pawn.isMoveLegal(board, endRow, endCol);
            // Rook chars
            case '\u2656':
            case '\u265c':
                Rook rook = new Rook(row, col, isBlack);
                return rook.isMoveLegal(board, endRow, endCol);
            // Knight chars
            case '\u265e':
            case '\u2658':
                Knight knight = new Knight(row, col, isBlack);
                return knight.isMoveLegal(board, endRow, endCol);
            // Bishop chars
            case '\u265d':
            case '\u2657':
                Bishop bishop = new Bishop(row, col, isBlack);
                return bishop.isMoveLegal(board, endRow, endCol);
            // Queen chars
            case '\u265b':
            case '\u2655':
                Queen queen = new Queen(row, col, isBlack);
                return queen.isMoveLegal(board, endRow, endCol);
            // King chars
            case '\u265a':
            case '\u2654':
                King king = new King(row, col, isBlack);
                return king.isMoveLegal(board, endRow, endCol);
            default:
                return false;
        }
    }

    // TODO:
    //  check and see if a pawn has reached the opposite side of the board, and promotes it to whatever the user would like if so
    public void pushinP(){
        if (character == '\u2659' || character == '\u265f'){ // checks if the given piece is a pawn of either color
            if (row == 0) { // only white pawns can reach row zero
                System.out.println("your pawn has crossed the board! what piece would you like? enter 'K' for a knight, 'Q' for a queen, 'B' for a bishop, or 'R' for a rook."); // asks for user input
                Scanner myScan = new Scanner(System.in); // makes a scanner
                String input = myScan.nextLine(); // takes in whatever they typed as input
                if (input.equals("Q")){ // checks if user typed "Q"
                    this.character = '\u265b'; // if so, the character is changed to that of a queen
                }
                else if (input.equals("K")){ // checks if user typed "K"
                    this.character = '\u265e'; // if so, the character is changed to that of a knight
                }
                else if (input.equals("B")){ // checks if user typed "B"
                    this.character = '\u265d'; // if so, the character is changed to that of a bishop
                }
                else if (input.equals("R")){ // checks if user typed "R"
                    this.character = '\u265c'; // if so, the character is changed to that of a rook
                }
                else {
                    System.out.println("Enter a valid input"); // gets to this else case if and only if user typed in something that wasn't any of the letters
                    this.pushinP(); // recalls method to re-ask user input
                }
            }
            else if (row == 7) {
                System.out.println("your pawn has crossed the board! what piece would you like? enter 'K' for a knight, 'Q' for a queen, 'B' for a bishop, or 'R' for a rook.");
                Scanner myScan = new Scanner(System.in); //creates a scanner
                String input = myScan.nextLine(); // takes in whatever they typed as input
                if (input.equals('Q')){ // checks if user typed "Q"
                    this.character = '\u2655'; // if so, the character is changed to that of a queen
                }
                else if (input.equals('K')){ // checks if user typed "K"
                    this.character = '\u2658'; // if so, the character is changed to that of a knight
                }
                else if (input.equals('B')){ // checks if user typed "B"
                    this.character = '\u2657'; // if so, the character is changed to that of a bishop
                }
                else if (input.equals('R')){ // checks if user typed "R"
                    this.character = '\u2656'; // if so, the character is changed to that of a rook
                }
                else {
                    System.out.println("Enter a valid input"); // gets to this else case if and only if user typed in something that wasn't an option
                    this.pushinP(); // re-calls method to re-ask user input
                }
            }
        }
    }

    /**
     * Sets the position of the piece.
     * @param row   The row to move the piece to.
     * @param col   The column to move the piece to.
     */
    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    /**
     * Returns the current chess unicode character.
     * @return Unicode character.
     */
    public char getCharacter(){
        return this.character;
    }

    /**
     * Return the color of the piece.
     * @return  The color of the piece.
     */
    public boolean getIsBlack() {
        return this.isBlack;
    }

    /**
     * Tests the equality of two Piece objects based on
     * their character parameter.
     * @param other An instance of Piece to compare with this
     *              instance.
     * @return Boolean value representing equality result.
     */
    public boolean equals(Piece other){
        return this.character == other.character;
    }

    /**
     * Returns a string representation of the piece.
     * @return  A string representation of the piece.
     */
    public String toString() {
        return "" + this.character;
    }


}
