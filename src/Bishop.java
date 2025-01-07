/*
 * Name:
 * Section Leader:
 * File: Bishop.java
 * ------------------
 * This class represents the Bishop type of chess piece. This piece can move and capture
 * pieces along diagonals. For more information visit: http://en.wikipedia.org/wiki/Bishop_(chess)
 */

public class Bishop extends ChessPiece{

	/** Constructor for the Bishop class */
	public Bishop(int initialRow, int initialCol, int pieceColor)
	{
		this.row = initialRow;
		this.col = initialCol;
		this.color = pieceColor;
	}

	/** Method that returns a boolean indicating whether or not the bishop can legally move
	 *  to the specified location (you need to fill this one in).
	 */
	public boolean canMoveTo(int nextRow, int nextCol, ChessBoard board)
	{
		int deltaRow = Math.abs(nextRow - row);
		int deltaCol = Math.abs(nextCol - col);

		if (deltaRow == deltaCol) {
			int rowStep = (nextRow > row) ? 1 : -1;
			int colStep = (nextCol > col) ? 1 : -1;

			int currentRow = row + rowStep;
			int currentCol = col + colStep;
			while (currentRow != nextRow && currentCol != nextCol) {
				if (board.pieceAt(currentRow, currentCol) != null) {
					return false;
				}
				currentRow += rowStep;
				currentCol += colStep;
			}

			ChessPiece destinationPiece = board.pieceAt(nextRow, nextCol);
			return destinationPiece == null || destinationPiece.getColor() != color;
		}

		return false;
	}

	/** Implementation of getType() method for the Bishop class. Provides a way to identify
	 *  the Bishop-type chess piece as such (you don't need to change anything here)
	 */
	public PieceType getType()
	{
		return PieceType.BISHOP;
	}
}
