package cpsc2150.extendedTicTacToe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class containing the game board, number of rows and columns, and related board methods.
 * Stores gameboard as a Map - Key: Character representing a player, Value: List of BoardPositions held by player.
 */
public class GameBoardMem extends AbsGameBoard{
    /**
     * @Correspondence  numRows = nRows
     *                  numColumns = nCols
     *                  numMarkersToWin = markersToWin
     *                  numPlaced = nPlaced
     *
     * @Invariant       MIN_GENERAL <= nRows <= MAXROWSCOLUMNS AND
     *                  MIN_GENERAL <= nCols <= MAXROWSCOLUMNS AND
     *                  MIN_GENERAL <= markersToWin <= MAXNUMTOWIN AND
     *                  0 <= nPlaced <= (numRows * numColumns)
     */
    Map<Character, List<BoardPosition>> board;
    private int nPlaced = 0;
    private final int nRows, nCols, markersToWin;

    /**
     * creates game board as HashMap
     * @pre     MIN_GENERAL <= r <= MAXROWSCOLUMNS AND MIN_GENERAL <= c <= MAXROWSCOLUMNS
     * @pre     MIN_GENERAL <= m <= MAXNUMTOWIN AND m <= r AND m <= c
     * @post    nRows = r, nCols = c, markersToWin = m
     * @post    nPlaced = 0
     * @post    board.size() = 0;
     */
    public GameBoardMem(int r, int c, int m){
        nRows = r;
        nCols = c;
        markersToWin = m;
        board = new HashMap<Character, List<BoardPosition>>();
    }

    public void placeMarker(BoardPosition marker, char player){
        // if player doesn't exist in map yet, add key/value pair to map
        // initialize value as an ArrayList
        if (!board.containsKey(player)){
            board.put(player, new ArrayList<BoardPosition>());
        }
        board.get(player).add(marker); // adds marker to end of BoardPosition list associated with player
        nPlaced++;
    }

    @Override
    public boolean checkForDraw() { return nPlaced == nRows * nCols; }

    public char whatsAtPos(BoardPosition pos){
        // loop through Map using Map.Entry
        for (Map.Entry<Character, List<BoardPosition>> entry: board.entrySet()) {
            char player = (char) entry.getKey();
            List<BoardPosition> posList = (List<BoardPosition>) entry.getValue();
            if (posList.contains(pos)) return player;
        }
        return ' ';
    }

    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player){
        // contains uses .equals(), requires override in BoardPosition class
        if (board.containsKey(player)) return board.get(player).contains(pos);
        return false;
    }

    public int getNumRows() { return nRows; }

    public int getNumColumns() { return nCols; }

    public int getNumToWin() { return markersToWin; }
}
