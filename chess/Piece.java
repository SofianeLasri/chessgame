package chess;

import java.awt.image.BufferedImage;

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
        int score = 0;
        if(type.equals("pawn")){
            score = 1;
        } else if (type.equals("bishop") || type.equals("knight")) {
            score = 3;
        } else if (type.equals("rook")) {
            score = 5;
        } else if (type.equals("queen")) {
            score = 9;
        }else{
            score = 30;
        }
        this.type = new PieceType(type, score);
        this.color = color;
        this.image = ChessGraphicTool.load(ChessDemo.imagePath + image);
    }
}

