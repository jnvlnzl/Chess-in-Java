/*
 * Name:
 * Section Leader:
 * File: ChessBoard.java
 * ------------------
 * This class represents the ChessBoard. Its job is to keep track of where all
 * of the pieces are. Since we just learned about two-dimensional arrays, it 
 * might be a good idea to use one here (just a hint). Currently, it doesn't do 
 * anything, but it does have four methods for you to fill in. These are the only
 * required methods in this class. As long as these work, feel free to do whatever
 * else you want to get this class working.
 */

public class ChessBoard extends DrawableObject {

	/**
	 * Constant that sets the size of the chess board
	 */
	public static final int BOARD_SIZE = 8;

	// The constructor will need to initialize whatever data structure you’re going
	// to use to store the ChessPieces and their locations.

	private final ChessPiece[][] board;

	// The job of the constructor would be to initialize this Array to all nulls,
	// as initially, there aren't any pieces on the board.

	/**
	 * Constructor for the ChessBoard class (do whatever you want with this)
	 */
	public ChessBoard() {
		board = new ChessPiece[BOARD_SIZE][BOARD_SIZE];
		initializeBoard();

		// Note: In Java, each class variable, instance variable, or array component is
		//initialized with a default value when it is created
		//For all reference types, the default value is null.

	}

	/**
	 * Returns the ChessPiece currently residing at the specified row and
	 * column. If no piece exists at the specified location, should return
	 * null.
	 */
	public ChessPiece pieceAt(int row, int col) {
		return board[row][col];
	}

	/**
	 * Adds the specified ChessPiece to the ChessBoard (hint: ChessPieces know their
	 * own rows and columns. You can use this to figure out where to place the piece)
	 */
	public void addPiece(ChessPiece piece) {
		// If the user attempts to add a piece to a location where one already exists,
		// addPiece should overwrite the old piece with the new one.
		int row = piece.getRow();
		int col = piece.getCol();
		board[row][col] = piece;
	}


	/**
	 * Removes the piece at the specified location from the board.
	 */
	public void removePiece(int row, int col) {
		board[row][col] = null;
	}

	private void initializeBoard() {
		// Add white pieces
		addPiece(new Rook(7, 0, ChessPiece.WHITE));
		addPiece(new Knight(7, 1, ChessPiece.WHITE));
		addPiece(new Bishop(7, 2, ChessPiece.WHITE));
		addPiece(new Queen(7, 3, ChessPiece.WHITE));
		addPiece(new King(7, 4, ChessPiece.WHITE));
		addPiece(new Bishop(7, 5, ChessPiece.WHITE));
		addPiece(new Knight(7, 6, ChessPiece.WHITE));
		addPiece(new Rook(7, 7, ChessPiece.WHITE));
		for (int col = 0; col < BOARD_SIZE; col++) {
			addPiece(new Pawn(6, col, ChessPiece.WHITE));
		}

		// Add black pieces
		addPiece(new Rook(0, 0, ChessPiece.BLACK));
		addPiece(new Knight(0, 1, ChessPiece.BLACK));
		addPiece(new Bishop(0, 2, ChessPiece.BLACK));
		addPiece(new Queen(0, 3, ChessPiece.BLACK));
		addPiece(new King(0, 4, ChessPiece.BLACK));
		addPiece(new Bishop(0, 5, ChessPiece.BLACK));
		addPiece(new Knight(0, 6, ChessPiece.BLACK));
		addPiece(new Rook(0, 7, ChessPiece.BLACK));
		for (int col = 0; col < BOARD_SIZE; col++) {
			addPiece(new Pawn(1, col, ChessPiece.BLACK));
		}
	}


	public boolean canMoveTo(int startRow, int startCol, int targetRow, int targetCol) {
		ChessPiece piece = pieceAt(startRow, startCol);
		if (piece != null) {
			if (isValidPosition(targetRow, targetCol)) {
				ChessPiece targetPiece = pieceAt(targetRow, targetCol);
				if (targetPiece == null || targetPiece.getColor() != piece.getColor()) {
					return piece.canMoveTo(targetRow, targetCol, this);
				}
			}
		}
		return false;
	}

	public void moveTo(int startRow, int startCol, int targetRow, int targetCol) {
		ChessPiece piece = pieceAt(startRow, startCol);
		if (piece != null) {
			piece.moveTo(targetRow, targetCol);
			removePiece(startRow, startCol);
			addPiece(piece);

			if (piece.getType() == PieceType.PAWN && (targetRow == 0 || targetRow == 7)) {
				promotePawn(piece, targetRow, targetCol);
			}
		}
	}

	private void promotePawn(ChessPiece pawn, int row, int col) {
		ChessPiece queen = new Queen(row, col, pawn.getColor());
		removePiece(row, col);
		addPiece(queen);
	}

	private boolean isValidPosition(int row, int col) {
		return row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE;
	}

}

