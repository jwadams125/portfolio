package cpsc2150.extendedTicTacToe;
/**
 * A Tic-Tac-Toe gameboard interface with methods allowing the user to view,
 * update, and test positions in the board.
 */

/**
 *  Initialization ensures:
 *      Board is created with acceptable number of rows, columns, and markers
 *      to win, AND all board positions are blank
 *  Defines:
 *      numRows: number of rows on board
 *      numColumns: number of columns on board
 *      numMarkersToWin: number of markers in a row needed to win a game
 *      numPlaced: number of tokens placed on board
 *  Constraints:
 *      MIN_GENERAL <= numRows <= MAXROWSCOLUMNS
 *      MIN_GENERAL <= numColumns <= MAXROWSCOLUMNS
 *      MIN_GENERAL <= numMarkersToWin <= MAXNUMTOWIN
 *      0 <= numPlaced <= (numRows * numColumns)
 */

public interface IGameBoard {
    int MAXROWSCOLUMNS = 100;
    int MAXNUMTOWIN = 25;
    int MINROWSCOLS = 3;
    int MINTOWIN = 3;

    /**
     * checks if space is blank
     * @param   pos represents cell to be checked
     * @return  true if space is ' ', false otherwise
     * @post    self = #self
     */
    default boolean checkSpace(BoardPosition pos){
        if (0 <= pos.getRow() && pos.getRow() < getNumRows()
                && 0 <= pos.getColumn() && pos.getColumn() < getNumColumns()) {
            return whatsAtPos(pos) == ' ';
        }
        return false;
    }

    /**
     * places the character in marker on the position specified by
     * marker and should not be called if the space is unavailable.
     * @param   marker the location of player selection
     * @param   player is the char representing a player
     * @pre     0 <= marker.getRow() < numRows
     * @pre     0 <= marker.getColumn() < numColumns
     * @pre     checkSpace(marker) = true;
     * @post    board updated with char of player selection
     */
    public void placeMarker(BoardPosition marker, char player);

    /**
     * checks if game has been won using the three check win methods
     * @param   lastPos is the position last entered by a player
     * @return  true if one of three win checkers returns true, false otherwise
     * @pre     0 <= lastPos.getRow() < numRows
     * @pre     0 <= lastPos.getColumn() < numColumns
     * @pre     checkSpace(lastPos) = false [space is not empty]
     * @post    checkForWinner = [true if win found, false otherwise]
     * @post    self = #self
     */
    default boolean checkForWinner(BoardPosition lastPos){
        char player = whatsAtPos(lastPos);
        return checkHorizontalWin(lastPos, player)
                || checkVerticalWin(lastPos, player)
                || checkDiagonalWin(lastPos, player);
    }

    /**
     * checks if no available spaces remain on the board
     * @return  true if no free board positions remaining, false otherwise
     * @post    checkForDraw = [true if full board, false otherwise
     * @post    self = #self
     */
    default boolean checkForDraw(){
        for (int r = 0; r < getNumRows(); r++){
            for (int c = 0; c < getNumColumns(); c++){
                BoardPosition bp = new BoardPosition(r, c);
                if (checkSpace(bp)) return false;
            }
        }
        return true;
    }

    /**
     * checks at player position for horizontal win
     * @param   lastPos is the base position for horizontal search
     * @param   player is the char to examine for horizontal win
     * @return  true if game won horizontally by player, false otherwise
     * @pre     0 <= lastPos.getRow() < numRows
     * @pre     0 <= lastPos.getColumn() < numColumns
     * @post    checkHorizontalWin = [true if horizontal win found by player at lastPos, false o/w]
     * @post    self = #self
     */
    default boolean checkHorizontalWin(BoardPosition lastPos, char player){
        // Check matching token at lastPos
        if (!isPlayerAtPos(lastPos, player)) return false;
        int sequenceCount = 1;
        // Search for matching tokens right
        int r = lastPos.getRow();
        int c = lastPos.getColumn() + 1;
        BoardPosition tempBP = new BoardPosition(r, c);
        while (c < getNumColumns() && isPlayerAtPos(tempBP, player)){
            sequenceCount++;
            tempBP = new BoardPosition(r, ++c);
        }
        // Search for matching tokens left (starting one position left of lastPos)
        c = lastPos.getColumn() - 1;
        tempBP = new BoardPosition(r, c);
        while (c >= 0 && isPlayerAtPos(tempBP, player)){
            sequenceCount++;
            tempBP = new BoardPosition(r, --c);
        }

        return sequenceCount >= getNumToWin();
    }

    /**
     * checks at player position for vertical win
     * @param   lastPos is the base position for vertical search
     * @param   player is the char to examine for diagonal win
     * @return  true if game won vertically by player, false otherwise
     * @pre     0 <= lastPos.getRow() < numRows
     * @pre     0 <= lastPos.getColumn() < numColumns
     * @post    checkVerticalWin = [true if vertical win found by player at lastPos, false o/w]
     * @post    self = #self
     */
    default boolean checkVerticalWin(BoardPosition lastPos, char player){
        // Check matching token at lastPos
        if (!isPlayerAtPos(lastPos, player)) return false;
        int sequenceCount = 1;
        // Search for matching tokens down
        int r = lastPos.getRow() + 1;
        int c = lastPos.getColumn();
        BoardPosition tempBP = new BoardPosition(r, c);
        while (r < getNumRows() && isPlayerAtPos(tempBP, player)){
            sequenceCount++;
            tempBP = new BoardPosition(++r, c);
        }
        // Search for matching tokens up (starting one position above lastPos)
        r = lastPos.getRow() - 1;
        tempBP = new BoardPosition(r, c);
        while (r >= 0 && isPlayerAtPos(tempBP, player)){
            sequenceCount++;
            tempBP = new BoardPosition(--r, c);
        }

        return sequenceCount >= getNumToWin();
    }

    /**
     * checks for diagonal win in both directions
     * @param   lastPos is the base position for diagonal search
     * @param   player is the player to examine for diagonal win
     * @return  true if game won diagonally by player, false otherwise
     * @pre     0 <= lastPos.getRow() < numRows
     * @pre     0 <= lastPos.getColumn() < numColumns
     * @post    checkVerticalWin = [true if vertical win found by player at lastPos, false o/w]
     * @post    self = #self
     */
    default boolean checkDiagonalWin(BoardPosition lastPos, char player){
        boolean stdDiagonal, antiDiagonal;
        //Check matching token at lastPos
        if (!isPlayerAtPos(lastPos, player)) return false;
        // STANDARD (RIGHT-DOWN) DIAGONAL
        int sequenceCount = 1;
        // Right-down
        int r = lastPos.getRow() + 1;
        int c = lastPos.getColumn() + 1;
        BoardPosition tempBP = new BoardPosition(r, c);
        while (r < getNumRows() && c < getNumColumns() && isPlayerAtPos(tempBP, player)) {
            sequenceCount++;
            tempBP = new BoardPosition(++r, ++c);
        }
        // Left-up
        r = lastPos.getRow() - 1;
        c = lastPos.getColumn() - 1;
        tempBP = new BoardPosition(r, c);
        while (r >= 0 && c >= 0 && isPlayerAtPos(tempBP, player)) {
            sequenceCount++;
            tempBP = new BoardPosition(--r, --c);
        }
        stdDiagonal = sequenceCount >= getNumToWin();

        // ANTI (LEFT-DOWN) DIAGONAL
        sequenceCount = 1;
        // Left-down
        r = lastPos.getRow() + 1;
        c = lastPos.getColumn() - 1;
        tempBP = new BoardPosition(r, c);
        while (r < getNumRows() && c >= 0 && isPlayerAtPos(tempBP, player)) {
            sequenceCount++;
            tempBP = new BoardPosition(++r, --c);
        }
        // Right-up
        r = lastPos.getRow() - 1;
        c = lastPos.getColumn() + 1;
        tempBP = new BoardPosition(r, c);
        while (r >= 0 && c < getNumColumns() && isPlayerAtPos(tempBP, player)) {
            sequenceCount++;
            tempBP = new BoardPosition(--r, ++c);
        }
        antiDiagonal = sequenceCount >= getNumToWin();

        return stdDiagonal || antiDiagonal;
    }

    /**
     * returns what is in the GameBoard at BoardPosition pos
     * @param   pos is position of what we want to identify in board
     * @return  the char at specified position in board
     * @pre     0 <= pos.getRow() < numRows
     * @pre     0 <= pos.getColumn() < numColumns
     * @post    whatsAtPos = [player char at lastPos, or ' ' if no player at lastPos]
     * @post    self = #self
     */
    public char whatsAtPos(BoardPosition pos);

    /**
     * determines if specified player is at given position
     * @param   pos is position being examined
     * @param   player is player to be looked for at pos
     * @return  true if player is at pos, false o/w
     * @pre     0 <= pos.getRow() < numRows
     * @pre     0 <= pos.getColumn() < numColumns
     * @post    isPlayerAtPos = [true if player is found at pos, false o/w]
     * @post    self = #self
     */
    default boolean isPlayerAtPos(BoardPosition pos, char player){
        return whatsAtPos(pos) == player;
    }

    /**
     * returns the number of rows in the GameBoard
     * @return  numRows
     * @post    getNumRows = numRows
     * @post    self = #self
     */
    public int getNumRows();

    /**
     * returns the number of columns in the GameBoard
     * @return  numColumns
     * @post    getNumColumns = numColumns
     * @post    self = #self
     */
    public int getNumColumns();

    /**
     * returns the number of tokens in a row needed to win the game
     * @return  numMarkersToWin
     * @post    getNumToWin = numMarkersToWin
     * @post    self = #self
     */
    public int getNumToWin();
}