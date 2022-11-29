package chess;

import java.awt.image.BufferedImage;

public class Piece {
    private PieceType type;
    private BufferedImage image;
    private String color;

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

    public Piece(String image, String color) {
        super();
        this.type = new PieceType(image);
        this.color = color;
        this.image = ChessGraphicTool.load(ChessDemo.imagePath + image);
    }
}

