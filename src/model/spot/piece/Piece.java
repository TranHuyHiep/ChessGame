package model.spot.piece;

import model.Game;

public class Piece {
	private int x;	
	private int y;
	private int px;
	private int py;
	private boolean color;
	private boolean isDead;
	private String name;

	public Piece(Piece piece) {
		this.x = piece.x;
		this.y = piece.y;
		this.px = piece.px;
		this.py = piece.py;
		this.color = piece.color;
		this.isDead = piece.isDead;
		this.name = piece.name;
	}

	public Piece(int x, int y, boolean color, boolean isDead, String name) {
		super();
		this.x = x;
		this.y = y;
		this.px = x*81;
		this.py = y*81;
		this.color = color;
		this.isDead = isDead;
		this.name = name;
	}
	public int getPx() {
		return px;
	}
	public void setPx(int px) {
		this.px = px;
	}
	public int getPy() {
		return py;
	}
	public void setPy(int py) {
		this.py = py;
	}
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
		this.px = x*81;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
		this.py = y*81;
	}

	public boolean isColor() {
		return color;
	}

	public void setColor(boolean color) {
		this.color = color;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean move(int xp, int yp, boolean canMove){
		if(x == xp && y == yp) canMove = false;
		if(canMove == true) {
			String stringmove = (color?"White ":"Black ") + name + ": " + 
								(char)(x + 'A') + (8 - y) + " -> " + 
								(char)(xp + 'A') + (8 - yp); 
			String text = Game.step.getText();
			Game.step.setText(text + " " + stringmove + "\n");
		}
		if(canMove == false) {
			px = this.x*81;
			py = this.y*81;
			return false;
		}
		if(Game.getSpot(xp*81, yp*81) != null){
			if(Game.getSpot(xp*81, yp*81).getPiece().isColor() != color){
				Game.getSpot(xp*81, yp*81).getPiece().kill();
			}else{
				px = this.x*81;
				py = this.y*81;
				return false;
			}
		}
		this.x = xp;
		this.y = yp;
		this.px = xp*81;
		this.py = yp*81;
		if(canMove) return true;
		else return false;
	}
	
	public void kill(){
		this.isDead = true;
	}

}
