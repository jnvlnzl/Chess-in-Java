/*
 * Name:
 * Section Leader:
 * File: Queen.java
 * ------------------
 * This class represents the Queen type of chess piece. This piece can move and capture
 * in any straight line (diagonally, horizontally, or vertically). For more information go 
 * here: http://en.wikipedia.org/wiki/Queen_(chess)
 */

public class Queen extends ChessPiece{

	/** Constructor for the Queen class */
	public Queen(int initialRow, int initialCol, int pieceColor)
	{
		this.row = initialRow;
		this.col = initialCol;
		this.color = pieceColor;
	}	
	
	/** Method that returns a boolean indicating whether or not the queen can legally move
	 *  to the specified location (you need to fill this one in).
	 */
	public boolean canMoveTo(int nextRow, int nextCol, ChessBoard board)
	{
		int deltaRow = nextRow - row;
		int deltaCol = nextCol - col;

		if (deltaRow == 0 || deltaCol == 0 || Math.abs(deltaRow) == Math.abs(deltaCol))
		{
			int rowStep = Integer.compare(deltaRow, 0);
			int colStep = Integer.compare(deltaCol, 0);

			int currentRow = row + rowStep;
			int currentCol = col + colStep;

			while (currentRow != nextRow || currentCol != nextCol)
			{
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

	/** Implementation of getType() method for the Pawn class. Provides a way to identify
	 *  the Pawn-type chess piece as such (you don't need to change anything here)
	 */
	public PieceType getType() 
	{
		return PieceType.QUEEN;
	}
	
}
