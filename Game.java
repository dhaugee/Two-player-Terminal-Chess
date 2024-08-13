import java.util.Scanner;

public class Game {

    // TODO:
    // Allow a game of chess to be played using user input
    public static void main(String[] args) {
        Board gameBoard = new Board(); // creates the game board
        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", gameBoard); // calls Fen to put the pieces in the starting position
        System.out.println(gameBoard); // prints the game board set up to start
        int i = 1; // initializes i to 1; i will be used to track who's turn it is
        Scanner gameScan = new Scanner(System.in); // opens a scanner
        while (gameBoard.isGameOver() == false) { // while the game is not over, the game will continue
            if (i == 1) { // checks if i is 1 (i begins as 1)
                System.out.println("It's currently white team's turn.");  // if i is 1, it is white's turn (white starts)
            } else if (i == -1) { // checks if i is -1
                System.out.println("It's currently black team's turn."); // if i is -1, it is black's turn
            }
            System.out.println("Where would you like to move? ([start row] [start column] [end row] [end column])"); // we ask where the user would like to move
            int startRow = gameScan.nextInt(); // scans the first int typed and assigns it to startRow
            int startCol = gameScan.nextInt(); // scans the second int typed and assigns it to startCol
            int endRow = gameScan.nextInt(); // scans the third int typed and assigns it to endRow
            int endCol = gameScan.nextInt(); // scans the fourth int typed and assigns it to endCol

            while(gameBoard.movePiece(startRow, startCol, endRow, endCol) == false){ // while the piece at the input start position cannot be moved to the ending position
                System.out.println("Illegal move, try again:"); // the user is asked to try again
                startRow = gameScan.nextInt(); // ""
                startCol = gameScan.nextInt(); // ""
                endRow = gameScan.nextInt(); // ""
                endCol = gameScan.nextInt(); // ""
            }
            gameBoard.movePiece(startRow, startCol, endRow, endCol); // the piece is moved using the user input values as parameters
            gameBoard.getPiece(endRow, endCol).pushinP(); // checks for pawn promotion
            i *= -1; // i is multiplied by -1, switching it to its previous value and changing who's turn it is
            System.out.println(gameBoard); // prints the most current, updated game board
            }
        if (i == -1){ // when we are broken out of the while loop, meaning the game is over, it checks the value of i
            System.out.println("White wins!"); // if i is -1, it means the last move occurred when i was 1, meaning white wins
        }
        else {
            System.out.println("Black wins!"); // if i isn't -1 it must be 1, meaning the last move occurred when i was -1, meaning black wins
        }
        }
    }


