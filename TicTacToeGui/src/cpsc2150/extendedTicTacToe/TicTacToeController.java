package cpsc2150.extendedTicTacToe;

import java.util.ArrayList;
import java.util.List;

/**
 * The TicTacToe controller class will handle communication between our TicTacToeView and our Model (IGameBoard and BoardPosition)
 * <p>
 * This is where you will write code
 * <p>
 * You will need to include your BoardPosition class, the IGameBoard interface
 * and the implementations from previous homeworks
 * If your code was correct you will not need to make any changes to your IGameBoard classes
 */
public class TicTacToeController {

    //our current game that is being played
    private IGameBoard curGame;

    //The screen that provides our view
    private TicTacToeView screen;

    //Other private variables
    private int curPlayerIndex = 0;
    private char curPlayer;
    private List<Character> players;
    private boolean gameOver = false;

    public static final int MAX_PLAYERS = 10;

    /**
     * @param model the board implementation
     * @param view  the screen that is shown
     * @param np    The number of players for the game
     *
     * @post the controller will respond to actions on the view using the model.
     */
    public TicTacToeController(IGameBoard model, TicTacToeView view, int np) {
        this.curGame = model;
        this.screen = view;
        players = new ArrayList<>();
        //create players deque (no longer from user input)
        for (int i = 0; i < np; i++) players.add((char)(i + 65));
        curPlayer = players.get(0);
        screen.setMessage("It's player " + curPlayer + "'s turn.");
    }

    /**
     * @param row the row of the activated button
     * @param col the column of the activated button
     *
     * @pre row and col are in the bounds of the game represented by the view
     * @post The button pressed will show the right token and check if a player has won.
     */
    public void processButtonClick(int row, int col) {
        if (gameOver){
            newGame();
            return;
        }
        BoardPosition lastPos = new BoardPosition(row, col);
        if (curGame.checkSpace(lastPos)) { // check if selection is available
            curGame.placeMarker(lastPos, curPlayer); //place selection in IGameBoard
            screen.setMarker(row, col, curPlayer); //place selection in screen
            if (curGame.checkForWinner(lastPos)){ //check for winner at lastPos
                screen.setMessage("Player " + curPlayer + " wins! Press any button to start a new game.");
                gameOver = true;
            }
            else if (curGame.checkForDraw()){ //check for draw
                screen.setMessage("Draw! Press any button to start a new game.");
                gameOver = true;
            }
            else { //no game over and acceptable placement
                nextPlayer();
                screen.setMessage("It's player " + curPlayer + "'s turn."); //update turn display
            }
        }
        else { //handle invalid selection
            screen.setMessage("Player " + curPlayer + ": that space is unavailable, please pick again");
        }
    }

    private void newGame() {
        // You do not need to make any changes to this code.
        screen.dispose();
        GameSetupScreen screen = new GameSetupScreen();
        GameSetupController controller = new GameSetupController(screen); //calls new TicTacToe controller
        screen.registerObserver(controller);
    }

    /**
     * @post curPlayerIndex and curPlayer updated to next index/player in list
     */
    private void nextPlayer(){
        if (curPlayerIndex == players.size() - 1) curPlayerIndex = 0;
        else curPlayerIndex++;
        curPlayer = players.get(curPlayerIndex);
    }
}