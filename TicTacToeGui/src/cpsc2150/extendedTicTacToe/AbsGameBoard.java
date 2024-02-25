package cpsc2150.extendedTicTacToe;

public abstract class AbsGameBoard implements IGameBoard{
    /**
     * converts board to printable string
     * @return String containing entire board
     */
    public String toString(){
        String s = "   ";
        for (int i = 0; i < getNumColumns(); i++) {
            if (i < 10)
                s += " " + i + "|";
            else
                s += i + "|";
        }
        s += "\n";
        for (int r = 0; r < getNumRows(); r++){
            if (r < 10) s += " " + r + "|";
            else s += r + "|";
            for (int c = 0; c < getNumColumns(); c++){
                BoardPosition bp = new BoardPosition(r, c);
                s += whatsAtPos(bp) + " |";
            }
            s += "\n";
        }
        return s;
    }
}