/*
 * Name:
 * Section Leader:
 * File: Knight.java
 * ------------------
 * This class represents the Knight type of chess piece. This piece can move only in 
 * L-shapes. That is it moves two spaces in one direction and one space in an orthogonal
 * direction. It is the only piece in chess that can jump over other pieces. For
 * more information go here: http://en.wikipedia.org/wiki/Knight_(chess)
 */

public class Knight extends ChessPiece{

	/** Constructor for the Knight class */
	public Knight(int initialRow, int initialCol, int pieceColor)
	{
		this.row = initialRow;
		this.col = initialCol;
		this.color = pieceColor;
	}		
	
	/** Method that returns a boolean indicating whether or not the king can legally move
	 *  to the specified location (you need to fill this one in).
	 */
	public boolean canMoveTo(int nextRow, int nextCol, ChessBoard board)
	{
		int deltaRow = Math.abs(nextRow - row);
		int deltaCol = Math.abs(nextCol - col);

		if ((deltaRow == 2 && deltaCol == 1) || (deltaRow == 1 && deltaCol == 2))
		{
			ChessPiece destinationPiece = board.pieceAt(nextRow, nextCol);

			return destinationPiece == null || destinationPiece.getColor() != color;
		}

		return false;
	}
	
	/** Implementation of getType() method for the Knight class. Provides a way to identify
	 *  the Knight-type chess piece as such (you don't need to change anything here)
	 */
	public PieceType getType() 
	{
		return PieceType.KNIGHT;
	}
	
}
