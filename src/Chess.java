/*
 * Name:
 * Section Leader:
 * File: Chess.java
 * ------------------
 * This program plays the game Chess.
 */

import java.awt.Color;
import java.awt.event.*;
import java.awt.event.MouseEvent;

/** The main class responsible for managing the chess game */
public class Chess extends GraphicsProgram implements MouseListener{

	/**
	 * Object responsible for handling the graphical display on the screen
	 */
	private ChessDisplay display;

	/**
	 * Object that keeps track of the locations of all pieces
	 */
	private ChessBoard board;
	private ChessPiece selectedPiece;
	private int currentColor;
	public boolean gameOver;

	/**
	 * Method called before run responsible for initializing the ChessDisplay and
	 * ChessBoard objects
	 */
	public void init() {
		display = ChessDisplay.getInstance(this);            // This line is required, don't change it
		board = new ChessBoard();

		display.useRealChessLabels(true);            // Use this method to change how the board is labeled
		                                                    // like an official chessboard; passing in false will
		display.addMouseListener(this);
		currentColor = ChessPiece.WHITE;
		gameOver = false;
		display.draw(board);
															// label the board like it is indexed in an array and
															// in ChessDisplay.
	}

	/**
	 * The main method that runs the program
	 */

	public void mousePressed(MouseEvent e) {
		if (gameOver) {
			return;
		}

		int col = display.getLocation(e.getX(), e.getY())[1];
		int row = display.getLocation(e.getX(), e.getY())[0];

		if (selectedPiece == null) {
			ChessPiece piece = board.pieceAt(row, col);
			if (piece != null && piece.getColor() == currentColor) {
				selectedPiece = piece;
				display.selectSquare(row, col, Color.YELLOW);
				display.draw(board);
				highlightAvailableMoves(selectedPiece);
			}
		} else {
			boolean validMove = board.canMoveTo(selectedPiece.getRow(), selectedPiece.getCol(), row, col);
			if (validMove) {
				board.moveTo(selectedPiece.getRow(), selectedPiece.getCol(), row, col);
				display.unselectAll();
				display.draw(board);

				if (isInCheckMate(board, currentColor)) {
					gameOver = true;
					String playerColorName = (currentColor == ChessPiece.BLACK) ? "White" : "Black";
					println("Checkmate! Player " + playerColorName + " wins!");
					return;
				}

				if (isInStalemate(board, currentColor)) {
					gameOver = true;
					println("Stalemate! It's a draw.");
					return;
				}

				if (isInCheck(board, currentColor)) {
					String playerColorName = (currentColor == ChessPiece.WHITE) ? "White" : "Black";
					println("Check! Player " + playerColorName + " is in check!");
				}

				currentColor = (currentColor == ChessPiece.WHITE) ? ChessPiece.BLACK : ChessPiece.WHITE;
			} else {
				display.unselectAll();
				display.draw(board);
			}

			selectedPiece = null;
		}
	}


	private void highlightAvailableMoves(ChessPiece piece) {
		for (int r = 0; r < ChessBoard.BOARD_SIZE; r++) {
			for (int c = 0; c < ChessBoard.BOARD_SIZE; c++) {
				if (board.canMoveTo(piece.getRow(), piece.getCol(), r, c)) {
					display.selectSquare(r, c, Color.GREEN);
				}
			}
		}
		display.draw(board);
	}

	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}


}

