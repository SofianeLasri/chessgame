package chess;

import java.awt.image.BufferedImage;

public class Piece {
    private PieceType type;
    private final BufferedImage image;

    public BufferedImage getLayeredImage() {
        return layeredImage;
    }

    public void setLayeredImage(BufferedImage layeredImage) {
        this.layeredImage = layeredImage;
    }

    private BufferedImage layeredImage;
    private final String color;
    public static final int imageSize = 40;
    private int posX = 0;
    private int posY = 0;
    private boolean isDown = false;

    public PieceType getType() {
        return type;
    }

    public void setType(PieceType type) {
        this.type = type;
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getColor() {
        return this.color;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPos(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    public void isNowDown() {
        isDown = true;
    }

    public boolean isDown() {
        return isDown;
    }

    public Piece(String image, String color) {
        super();
        String type = image.split("-")[0];
        int score = switch (type) {
            case "pawn" -> 1;
            case "bishop", "knight" -> 3;
            case "rook" -> 5;
            case "queen" -> 9;
            default -> 30;
        };
        this.type = new PieceType(type, score);
        this.color = color;
        this.image = ChessGraphicTool.load(ChessDemo.imagePath + image);
    }
}

