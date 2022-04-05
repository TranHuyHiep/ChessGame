package model.spot.piece;

import java.util.ArrayList;
import java.util.List;

import model.Game;
import model.spot.Spot;

public class Pawn implements Spot{
	private Piece a;

	public Pawn(int x, int y, boolean color, boolean isDead, String name) {
		super();
		this.a = new Piece(x, y, color, isDead, name);
	}

	public Piece getA() {
		return a;
	}

	public void setA(Piece a) {
		this.a = a;
	}

	public Pawn(Piece a) {
		super();
		this.a = a;
	}
	@Override
	public boolean move(int x, int y) {
		boolean canMove = false; 
		if(x != a.getX() || y != a.getY())
			/*Quan den*/
			if(a.isColor() == false) {
				/*Kiem tra co giet duoc khong*/
				if(Math.abs(x - a.getX()) == 1 && y == a.getY() + 1) {
					for(Spot p : Game.getWspots()) {
						if(p.getPiece().getX() == x && p.getPiece().getY() == y) {
							/*Phong hau*/
							if(a.getY() == 6 && y == 7) {
								canMove = true;
								a.move(x, y, canMove);
								Queen b = new Queen(x, y, false, false, "queen");
								List<Spot> l = new ArrayList<Spot>(Game.getBspots());
								l.add(b);
								l.remove(this);
								a.kill();
								Game.setBspots(l);
								return canMove;
							}
							canMove = true;
							a.move(x, y, canMove);
							return canMove;
						}
					}
				} /*Neu o vi tri ban dau*/ 
				else if(a.getY() == 1) {	
					/*Cho phep di 2 nuoc*/
					if(y - a.getY() <= 2 && x == a.getX()) {
						canMove = true;
						for(Spot p : Game.getWspots()) {
							if(p.getPiece().getX() == x && p.getPiece().getY() == y) {
								canMove = false;
								a.move(x, y, canMove);
								return canMove;
							}
						}
						a.move(x, y, canMove);
						return canMove;
					} 
				} /*Cac buoc sau*/ 
				else {	
					/*Phong hau*/
					if(a.getY() == 6 && y == 7 && Game.getSpot(x*81, y*81) == null) { 
						canMove = true;
						a.move(x, y, canMove);
						a.kill();
						Queen b = new Queen(x, y, false, false, "queen");
						List<Spot> l = new ArrayList<Spot>(Game.getWspots());
						l.add(b);
						Game.setWspots(l);
						return canMove;
					}
					/*Chi cho phep di 1 nuoc*/
					if(y - a.getY() == 1 && x == a.getX()) {	
						canMove = true;
						for(Spot p : Game.getWspots()) {
							if(p.getPiece().getX() == x && p.getPiece().getY() == y) {
								canMove = false;
								a.move(x, y, canMove);
								return canMove;
							}
						}
						a.move(x, y, canMove);
						return canMove;
					}
				}
			} /*Ben trang*/ 
			else {	
				/*Kiem tra giet*/
				if(Math.abs(x - a.getX()) == 1 && a.getY() == y + 1) {	
					for(Spot p : Game.getBspots()) {
						if(p.getPiece().getX() == x && p.getPiece().getY() == y) {
							/*Phong hau*/
							if(a.getY() == 1 && y == 0) { 
								canMove = true;
								a.move(x, y, canMove);
								Queen b = new Queen(x, y, true, false, "queen");
								List<Spot> l = new ArrayList<Spot>(Game.getWspots());
								l.add(b);
								l.remove(this);
								a.kill();
								Game.setWspots(l);
								return canMove;
							}
							canMove = true;
							a.move(x, y, canMove);
							return canMove;
						}
					}
				} /*O vi tri xuat phat*/
				else if(a.getY() == 6) {	
					/*Cho phep di 2 nuoc*/
					if(a.getY() - y <= 2 && x == a.getX()) {	
						canMove = true;
						for(Spot p : Game.getBspots()) {
							if(p.getPiece().getX() == x && p.getPiece().getY() == y) {
								canMove = false;
								a.move(x, y, canMove);
								return canMove;
							}
						}
						a.move(x, y, canMove);
						return canMove;
					} 
				} /*Cac buoc con lai*/
				else {	
					/*Cho phep di 1 nuoc*/
					if(a.getY() - y == 1 && x == a.getX()) {	
						/*Phong hau*/
						if(a.getY() == 1 && y == 0 && Game.getSpot(x*81, y*81) == null) { 
							canMove = true;
							a.move(x, y, canMove);
							Queen b = new Queen(x, y, true, false, "queen");
							List<Spot> l = new ArrayList<Spot>(Game.getWspots());
							l.add(b);
							l.remove(this);
							a.kill();
							Game.setWspots(l);
							return canMove;
						}
						canMove = true;
						for(Spot p : Game.getBspots()) {
							if(p.getPiece().getX() == x && p.getPiece().getY() == y) {
								canMove = false;
								a.move(x, y, canMove);
								return canMove;
							}
						}
						a.move(x, y, canMove);
						return canMove;
					} 
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
