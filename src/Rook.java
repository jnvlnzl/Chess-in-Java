/*
 * Name:
 * Section Leader:
 * File: Rook.java
 * ------------------
 * This class represents the Rook type of chess piece. This piece can move and capture 
 * pieces along rows and columns. It is also known as a castle. For more information visit: 
 * http://en.wikipedia.org/wiki/Rook_(chess)
 */

public class Rook extends ChessPiece{

	/** Constructor for the Rook class */
	public Rook(int initialRow, int initialCol, int pieceColor)
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
		int deltaRow = nextRow - row;
		int deltaCol = nextCol - col;

		if (deltaRow == 0 || deltaCol == 0)
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

	/** Implementation of getType() method for the Rook class. Provides a way to identify
	 *  the Rook-type chess piece as such (you don't need to change anything here)
	 */
	public PieceType getType() 
	{
		return PieceType.ROOK;
	}

}
