default:
	javac cpsc2150/extendedTicTacToe/BoardPosition.java cpsc2150/extendedTicTacToe/IGameBoard.java cpsc2150/extendedTicTacToe/AbsGameBoard.java cpsc2150/extendedTicTacToe/GameBoard.java cpsc2150/extendedTicTacToe/GameBoardMem.java cpsc2150/extendedTicTacToe/GameScreen.java

run:
	java cpsc2150.extendedTicTacToe.GameScreen

test:
	javac -cp .:/usr/share/java/junit4.jar cpsc2150/extendedTicTacToe/TestGameBoard.java cpsc2150/extendedTicTacToe/TestGameBoardMem.java cpsc2150/extendedTicTacToe/BoardPosition.java cpsc2150/extendedTicTacToe/IGameBoard.java cpsc2150/extendedTicTacToe/AbsGameBoard.java cpsc2150/extendedTicTacToe/GameBoard.java cpsc2150/extendedTicTacToe/GameBoardMem.java

testGB:
	java -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore cpsc2150.extendedTicTacToe.TestGameBoard

testGBmem:
	java -cp .:/usr/share/java/junit4.jar org.junit.runner.JUnitCore cpsc2150.extendedTicTacToe.TestGameBoardMem

clean:
	rm -f cpsc2150/extendedTicTacToe/*.class
