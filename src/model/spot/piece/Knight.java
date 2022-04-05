package model.spot.piece;

import model.spot.Spot;

public class Knight implements Spot{
	private Piece a;

	public Knight(Piece a) {
		super();
		this.a = a;
	}

	public Knight(int x, int y, boolean color, boolean isDead, String name) {
		super();
		this.a = new Piece(x, y, color, isDead, name);
	}

	public Piece getA() {
		return a;
	}

	public void setA(Piece a) {
		this.a = a;
	}

	@Override
	public boolean move(int x, int y) {
		boolean canMove = false; 
		if(x >= 0 && x < 8 && y >= 0 && y < 8) {
			if((Math.abs(x - a.getX()) == 2 
					&& Math.abs(y - a.getY()) == 1) 
					|| (Math.abs(x - a.getX()) == 1 
					&& Math.abs(y - a.getY()) == 2)) 
				canMove = true;
		}
		a.move(x, y, canMove);
		return canMove;
	}

	@Override
	public Piece getPiece() {
		return this.a;
	}

}
