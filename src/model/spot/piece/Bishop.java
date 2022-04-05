package model.spot.piece;

import model.Game;
import model.spot.Spot;

public class Bishop implements Spot{
	private Piece a;

	public Bishop(Piece a) {
		super();
		this.a = a;
	}

	public Bishop(int x, int y, boolean color, boolean isDead, String name) {
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
		if((x >= 0 && x < 8 && y >= 0 && y < 8)
				&& ((x - a.getX() == y - a.getY()) || (x - a.getX() == a.getY() - y))) {
			canMove = true;
			int k;
			/* Kiem tra duong cheo chinh */
			if((x - a.getX() == y - a.getY())) {
				if(x > a.getX()) {
					k = x - a.getX();
					for(int i = 1; i < k; i++) {
						for(Spot p : Game.getWspots()) {
							if(p.getPiece().getX() == a.getX() + i && p.getPiece().getY() == a.getY() + i) {
								canMove = false;
								a.move(x, y, canMove);
								return canMove;
							}
						}
						for(Spot p : Game.getBspots()) {
							if(p.getPiece().getX() == a.getX() + i && p.getPiece().getY() == a.getY() + i) {
								canMove = false;
								a.move(x, y, canMove);
								return canMove;
							}
						}
					}
				} else {
					k = a.getX() - x;
					for(int i = 1; i < k; i++) {
						for(Spot p : Game.getWspots()) {
							if(p.getPiece().getX() == x + i && p.getPiece().getY() == y + i) {
								canMove = false;
								a.move(x, y, canMove);
								return canMove;
							}
						}
						for(Spot p : Game.getBspots()) {
							if(p.getPiece().getX() == x + i && p.getPiece().getY() == y + i) {
								canMove = false;
								a.move(x, y, canMove);
								return canMove;
							}
						}
					}
				}
			} /*Kiem tra duong cheo phu*/
			else if(x - a.getX() == a.getY() - y) { 
				if(x > a.getX()) {
					k = x - a.getX();
					for(int i = 1; i < k; i++) {
						for(Spot p : Game.getWspots()) {
							if(p.getPiece().getX() == a.getX() + i && p.getPiece().getY() == a.getY() - i) {
								canMove = false;
								a.move(x, y, canMove);
								return canMove;
							}
						}
						for(Spot p : Game.getBspots()) {
							if(p.getPiece().getX() == a.getX() + i && p.getPiece().getY() == a.getY() - i) {
								canMove = false;
								a.move(x, y, canMove);
								return canMove;
							}
						}
					}
				} else if(x < a.getX()){
					k = a.getX() - x;
					for(int i = 1; i < k; i++) {
						for(Spot p : Game.getWspots()) {
							if(p.getPiece().getX() == x + i && p.getPiece().getY() == y - i) {
								canMove = false;
								a.move(x, y, canMove);
								return canMove;
							}
						}
						for(Spot p : Game.getBspots()) {
							if(p.getPiece().getX() == x + i && p.getPiece().getY() == y - i) {
								canMove = false;
								a.move(x, y, canMove);
								return canMove;
							}
						}
					}
				}
			}
		}

		if(canMove == true) {
			if(Game.getSpot(x*81, y*81) != null && Game.getSpot(x*81, y*81).getPiece().isColor() == a.isColor()) {
				canMove = false;
			}
		}
		a.move(x, y, canMove);
		return canMove;
	}

	@Override
	public Piece getPiece() {
		return this.a;
	}
}
