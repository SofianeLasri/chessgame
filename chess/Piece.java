package chess;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;

public class Piece {
    private PieceType type;
    private BufferedImage image;

    public BufferedImage getLayeredImage() {
        return layeredImage;
    }

    public void setLayeredImage(BufferedImage layeredImage) {
        this.layeredImage = layeredImage;
    }

    private BufferedImage layeredImage;
    private String color;
    public static final int imageSize = 40;

    public PieceType getType() {
        return type;
    }
    public void setType(PieceType type) {
        this.type = type;
    }
    public BufferedImage getImage() {
        return image;
    }
    public String getColor(){
        return this.color;
    }
    public void setImage(BufferedImage image) {
        this.image = image;
    } 
    public void setColor(String color) {
		this.color = color;
	}

    public Piece(String image, String color) {
        super();
        this.type = new PieceType(image.split("-")[0]);
        this.color = color;
        this.image = ChessGraphicTool.load(ChessDemo.imagePath + image);
    }
}

