package model.spot;

import model.spot.piece.Piece;

public interface Spot {
	public boolean move(int x, int y);
	public Piece getPiece();
}
