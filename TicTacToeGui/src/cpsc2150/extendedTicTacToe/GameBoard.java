//Jack Adams
package cpsc2150.extendedTicTacToe;

/**
 * Class containing the game board, number of rows and columns, and related board methods.
 * Stores gameboard as 2D array of chars.
 */
public class GameBoard extends AbsGameBoard {
    /**
     * @Correspondence  numRows = nRows
     *                  numColumns = nCols
     *                  numMarkersToWin = markersToWin
     *                  numPlaced = nPlaced
     *                  self = board[0...NROWS][0...NCOLS]
     *
     * @Invariant       MIN_GENERAL <= nRows <= MAXROWSCOLUMNS AND
     *                  MIN_GENERAL <= nCols <= MAXROWSCOLUMNS AND
     *                  MIN_GENERAL <= markersToWin <= MAXNUMTOWIN AND
     *                  0 <= nPlaced <= (numRows * numColumns)
     */

    private char[][] board;
    private int nPlaced = 0;
    private final int nRows, nCols, markersToWin;

    /**
     * creates game board as 2D array
     * @pre     MIN_GENERAL <= r <= MAXROWSCOLUMNS AND MIN_GENERAL <= c <= MAXROWSCOLUMNS
     * @pre     MIN_GENERAL <= m <= MAXNUMTOWIN AND m <= r AND m <= c
     * @post    nRows = r, nCols = c, markersToWin = m
     * @post    nPlaced = 0
     * @post    board.length = r
     * @post    board[0...r-1].length = c
     * @post    each space holds blank space char ' '
     */
    public GameBoard(int r, int c, int m){
        nRows = r;
        nCols = c;
        markersToWin = m;
        board = new char[nRows][nCols];
        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < nCols; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public void placeMarker(BoardPosition marker, char player){
        board[marker.getRow()][marker.getColumn()] = player;
        nPlaced++;
    }

    @Override
    public boolean checkForDraw() { return nPlaced == nRows * nCols; }

    public char whatsAtPos(BoardPosition pos){ return board[pos.getRow()][pos.getColumn()]; }

    public int getNumRows() { return nRows; }

    public int getNumColumns() { return nCols; }

    public int getNumToWin() { return markersToWin; }
}