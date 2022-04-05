package model.spot.piece;

import java.util.ArrayList;
import java.util.List;

import model.Game;
import model.spot.Spot;

public class Rook implements Spot{
	private Piece a;
	private int countTurn = 0;

	public Rook(Piece a) {
		super();
		this.a = a;
	}

	public Rook(int x, int y, boolean color, boolean isDead, String name) {
		super();
		this.a = new Piece(x, y, color, isDead, name);
	}

	public int getCountTurn() {
		return countTurn;
	}

	public void setCountTurn(int countTurn) {
		this.countTurn = countTurn;
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
		Spot b = Game.getSpot(x*81, y*81);
		/*Cho phep nhap thanh*/
		if(b != null && countTurn == 0 && b.getPiece().getName() == "king") {
			King k = (King) b;
			/*Quan den*/
			if(a.isColor() == false && k.getCountTurn() == 0) {	
				int k1, k2;
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
				a.kill();
				k.getPiece().kill();
				Rook rook;
				King king;
				/*Nhap thanh ben vua*/
				if(a.getX() == 7) {
					rook = new Rook(5, 0, false, false, "rook");
					king = new King(6, 0, false, false, "king");
				} /*Nhap thanh ben hau*/ 
				else {	
					rook = new Rook(3, 0, false, false, "rook");
					king = new King(2, 0, false, false, "king");
				}
				List<Spot> l = new ArrayList<Spot>(Game.getBspots());
				l.add(rook);
				l.add(king);
				Game.setBspots(l);
				rook.setCountTurn(1);
				king.setCountTurn(1);
				a.move(x, y, canMove);
				return true;
			} /*Quan trang*/ 
			else if(a.isColor() == true && k.getCountTurn() == 0) {	
				int k1, k2;
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
				a.kill();
				k.getPiece().kill();
				Rook rook;
				King king;
				/*Nhap thanh ben vua*/
				if(a.getX() == 7) {	
					rook = new Rook(5, 7, true, false, "rook");
					king = new King(6, 7, true, false, "king");
				} /*Nhap thanh ben hau*/ 
				else {	
					rook = new Rook(3, 7, true, false, "rook");
					king = new King(2, 7, true, false, "king");
				}
				List<Spot> l = new ArrayList<Spot>(Game.getWspots());
				l.add(rook);
				l.add(king);
				Game.setWspots(l);
				rook.setCountTurn(1);
				king.setCountTurn(1);
				a.move(x, y, canMove);
				return true;
			}
		}
		/*Di chuyen theo chieu ngang, doc*/
		if(((x == a.getX() && y != a.getY()) || (x != a.getX() && y == a.getY())) 
				&& (x >= 0 && x < 8 && y >= 0 && y < 8)
				&& ((a.getX() == x && a.getY() != y) || (a.getX() != x && a.getY() == y))) {
			canMove = true;
			if(Game.getSpot(x*81, y*81) != null) {
				if(Game.getSpot(x*81, y*81).getPiece().isColor() == a.isColor()) {
					canMove = false;
					a.move(x, y, canMove);
					return canMove;
				}
			}
			int k1, k2;
			/*Di chuyen ngang*/
			if(x != a.getX()) {
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
				if(y > a.getY()) {
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
		a.move(x, y, canMove);
		if(canMove == true) countTurn++;
		return canMove;
	}

	@Override
	public Piece getPiece() {
		return this.a;
	}

}
