package chess;

import java.awt.image.BufferedImage;

/**
 * The chess piece
 */
public class Piece {
    /**
     * The type of the piece
     */
    private PieceType type;

    /**
     * The image of the piece
     */
    private final BufferedImage image;

    /**
     * Get the layer image
     * @return the layer image
     */
    public BufferedImage getLayeredImage() {
        return layeredImage;
    }

    /**
     * Set the layer image
     * @param layeredImage the layer image
     */
    public void setLayeredImage(BufferedImage layeredImage) {
        this.layeredImage = layeredImage;
    }

    /**
     * The layer image
     */
    private BufferedImage layeredImage;

    /**
     * The color of the piece
     */
    private final String color;

    /**
     * The size of the image
     */
    public static final int imageSize = 40;

    /**
     * The position x of the piece
     */
    private int posX = 0;

    /**
     * The position y of the piece
     */
    private int posY = 0;

    /**
     * If the piece is down
     */
    private boolean isDown = false;

    /**
     * Get the type of the piece
     * @return the type of the piece
     */
    public PieceType getType() {
        return type;
    }

    /**
     * Set the type of the piece
     * @param type the type of the piece
     */
    public void setType(PieceType type) {
        this.type = type;
    }

    /**
     * Get the image of the piece
     * @return the image of the piece
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Get the color of the piece
     * @return the color of the piece
     */
    public String getColor() {
        return this.color;
    }

    /**
     * Get the position x of the piece
     * @return the position x of the piece
     */
    public int getPosX() {
        return posX;
    }

    /**
     * Get the position y of the piece
     * @return the position y of the piece
     */
    public int getPosY() {
        return posY;
    }

    /**
     * Set the position of the piece
     * @param posX the position x of the piece
     * @param posY the position y of the piece
     */
    public void setPos(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
    }

    /**
     * Set the piece down
     */
    public void isNowDown() {
        isDown = true;
    }

    /**
     * Check if the piece is down
     * @return true if the piece is down otherwise false
     */
    public boolean isDown() {
        return isDown;
    }

    /**
     * Create a piece
     * @param image the image of the piece
     * @param color the color of the piece
     */
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

