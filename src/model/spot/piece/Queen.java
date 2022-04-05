package model.spot.piece;

import model.Game;
import model.spot.Spot;

public class Queen implements Spot{
	private Piece a;

	public Queen(Piece a) {
		super();
		this.a = a;
	}

	public Queen(int x, int y, boolean color, boolean isDead, String name) {
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
			if((x - a.getX() == y - a.getY()) || (x - a.getX() == a.getY() - y) || (x == a.getX() && y != a.getY()) || (x != a.getX() && y == a.getY())) {
				canMove = true;
				if((x - a.getX() == y - a.getY()) || (x - a.getX() == a.getY() - y)) {
					int k;
					/*Kiem tra cheo chinh*/
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
						} /*Kiem tra cheo phu*/
						else {
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
					} else if(x - a.getX() == a.getY() - y) {
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
						} else {
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
				} /*Di chuyen ngang, doc*/
				else {	
					int k1, k2;
					/*Di chuyen ngang*/
					if(x != a.getX() && y == a.getY()) {	
						if(x > a.getX()) {
							k1 = a.getX();
							k2 = x;
						} else {
							k1 = x;
							k2 = a.getX();
						}
						for(int i = k1 + 1; i < k2; i++) {
							for(Spot p : Game.getWspots()) {
								if(p.getPiece().getX() == i && p.getPiece().getY() == a.getY()) {
									canMove = false;
									a.move(x, y, canMove);
									return canMove;
								}
							}
							for(Spot p : Game.getBspots()) {
								if(p.getPiece().getX() == i && p.getPiece().getY() == a.getY()) {
									canMove = false;
									a.move(x, y, canMove);
									return canMove;
								}
							}
						}
					} /*Di chuyen doc*/ 
					else {	
						if(x == a.getX() && y > a.getY()) {
							k1 = a.getY();
							k2 = y;
						} else {
							k1 = y;
							k2 = a.getY();
						}
						for(int i = k1 + 1; i < k2; i++) {
							for(Spot p : Game.getWspots()) {
								if(p.getPiece().getX() == a.getX() && p.getPiece().getY() == i) {
									canMove = false;
									a.move(x, y, canMove);
									return canMove;
								}
							}
							for(Spot p : Game.getBspots()) {
								if(p.getPiece().getX() == a.getX() && p.getPiece().getY() == i) {
									canMove = false;
									a.move(x, y, canMove);
									return canMove;
								}
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
