public class Pawn extends ChessPiece{

	/** Constructor for the Pawn class */
	public Pawn(int initialRow, int initialCol, int pieceColor) {
		this.row = initialRow;
		this.col = initialCol;
		this.color = pieceColor;
	}

	/** Method that returns a boolean indicating whether or not the pawn can legally move
	 *  to the specified location (you need to fill this one in).
	 */
	public boolean canMoveTo(int nextRow, int nextCol, ChessBoard board) {
		int deltaRow = nextRow - row;
		int deltaCol = nextCol - col;

		int direction = (color == ChessPiece.WHITE) ? -1 : 1;

		if (deltaCol == 0) {
			if (deltaRow == direction) {
				ChessPiece destinationPiece = board.pieceAt(nextRow, nextCol);
				return destinationPiece == null;
			} else if (deltaRow == 2 * direction && isFirstMove()) {
				int intermediateRow = row + direction;
				return board.pieceAt(intermediateRow, col) == null && board.pieceAt(nextRow, nextCol) == null;
			}
		} else if (Math.abs(deltaCol) == 1 && deltaRow == direction) {
			ChessPiece destinationPiece = board.pieceAt(nextRow, nextCol);
			return destinationPiece != null && destinationPiece.getColor() != color;
		}

		return false;
	}

	public boolean isFirstMove() {
		return (color == ChessPiece.WHITE && row == 6) || (color == ChessPiece.BLACK && row == 1);
	}

	/** Implementation of getType() method for the Pawn class. Provides a way to identify
	 *  the Pawn-type chess piece as such (you don't need to change anything here)
	 */
	public PieceType getType() {
		return PieceType.PAWN;
	}
}
