package chess;

import java.awt.image.BufferedImage;

public class Piece {
	PieceType type;
	BufferedImage image;
	
	public PieceType getType() {
		return type;
	}
	public void setType(PieceType type) {
		this.type = type;
	}
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public Piece(String image) {
		super();
		this.type = new PieceType(image);
		this.image = ChessGraphicTool.load(ChessDemo.imagePath + image);
	}
}

