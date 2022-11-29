package chess;

public class Player {
	String color;
	int nbPieces;
	int nbDead;
	Piece[] pieces;
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getNbPieces() {
		return nbPieces;
	}
	public void setNbPieces(int nbPieces) {
		this.nbPieces = nbPieces;
	}
	public int getNbDead() {
		return nbDead;
	}
	public void setNbDead(int nbDead) {
		this.nbDead = nbDead;
	}
	public Piece[] getPieces() {
		return pieces;
	}
	public void setPieces(Piece[] pieces) {
		this.pieces = pieces;
	}
	
	public Player(String color, int nbPieces, int nbDead, Piece[] pieces) {
		super();
		this.color = color;
		this.nbPieces = nbPieces;
		this.nbDead = nbDead;
		this.pieces = pieces;
	}
	
	
	
}
