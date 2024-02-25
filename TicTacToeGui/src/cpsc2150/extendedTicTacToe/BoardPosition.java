//Jack Adams
package cpsc2150.extendedTicTacToe;

/**
 * keeps track of individual cells for the board
 * holds row and column positions
 */
public class BoardPosition {
    /**
     *
     * @Invariant
     *
     */
    private final int row;
    private final int column;

    /**
     * creates board position
     * @param r is row position
     * @param c is column position
     * @post    row = r, column = c
     */
    public BoardPosition(int r, int c){
        this.row = r;
        this.column = c;
    }

    /**
     * returns the row
     * @return row
     */
    public int getRow(){
        return row;
    }

    /**
     * returns the column
     * @return column
     */
    public int getColumn(){
        return column;
    }

    /**
     * checks if contents of two BoardPosition objects are equal
     * @param obj can technically be any java Object, should be a BoardPosition object
     * @return true if same row & column values, false otherwise or if obj isn't BoardPosition type
     */
    @Override
    public boolean equals(Object obj){
        //check type of param
        if (!(obj instanceof BoardPosition)) return false;
        //typecast
        BoardPosition pos = (BoardPosition) obj;
        //compare contents
        return (this.row == pos.row) && (this.column == pos.column);
    }

    /**
     * creates String in format "<row>,<column>"
     * @return String holding row and column in proper format
     */
    @Override
    public String toString(){
        return "<" + row + ", " + column + ">";
    }
}
