package chess;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The main class
 */
public class ChessDemo {
    /**
     * The path to the images
     */
    public static final String imagePath = "./images/";

    /**
     * The window width and height
     */
    public static final int windowWidth = 840;

    /**
     * The window width and height
     */
    public static final int windowHeight = 840;

    /**
     * The chess graphic tool
     */
    public static final ChessGraphicTool chessGraphicTool = new ChessGraphicTool();

    /**
     * The layer management
     */
    public static final LayerManagement mgrLayers = new LayerManagement();

    /**
     * The rendering hints
     */
    public static RenderingHints rh;

    /**
     * The chess table
     */
    public static ChessTable table;

    /**
     * The height of the title bar
     */
    public static int titleBarHeight;

    /**
     * The main function
     * @param args the arguments
     */
    public static void main(String[] args) {

        // Initialization
        mgrLayers.setPreferredSize(new Dimension(windowWidth, windowHeight));

        ChessGUI.showOnFrame(mgrLayers, "Comment réussir les échecs");
        titleBarHeight = ChessGUI.frame.getInsets().top;
        ChessMouseEvent chessMouseEvent = ChessGUI.getChessMouseEvent();
        rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // ===============
        // YOUR CODE HERE - HERE AFTER PLEASE FIND  BASIC MATERIAL FOR THE PLAY
        // ===============

        // create a black background		
        BufferedImage foreground = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_ARGB);
        mgrLayers.addLayer(foreground);
        java.awt.Graphics2D foregroundGC = (Graphics2D) foreground.getGraphics();
        foregroundGC.setRenderingHints(rh);
        foregroundGC.setColor(java.awt.Color.BLACK);
        foregroundGC.fill3DRect(0, 0, windowWidth, windowHeight, true);

        //drawing the chess board
        table = new ChessTable(mgrLayers);

        // create a builder layer for the chess piece
        // and load two images pawns.
        // size of piece ~ 40 -> 500+(60-40)/2
        // size of the piece ~ 40 -> 200+(60-40)/2

        // create a layer with the image file
        BufferedImage whitePawnImage = ChessGraphicTool.load(imagePath + "pawn-white.png");
        if (whitePawnImage == null) {
            System.out.println("Error image not found : " + imagePath + "pawn-white.png");
        }

        // create a dedicated layer and copy the pawn image at the right coordinates

        // create a layer with the image file
        BufferedImage blackPawnImage = ChessGraphicTool.load(imagePath + "pawn-black.png");
        if (blackPawnImage == null) {
            System.out.println("Error image not found : " + imagePath + "pawn-black.png");
        }

        // create a dedicated layer and copy the new pawn image at the right coordinates

        // New game !
        Game game = new Game();

        // display all the layers
        mgrLayers.repaint();

        // -- main loop waiting drag and drop user events
        //noinspection InfiniteLoopStatement
        while (true) {
            // waiting in millisecond
            try {
                Thread.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (chessMouseEvent.isClicked()) {
                int x = chessMouseEvent.getX();
                int y = chessMouseEvent.getY();
                System.out.println("CLICK x=" + x + " y=" + y);

                // Ici on fait l'appel vers Game pour déterminer le jeu
                game.playerClicked(x, y);
                mgrLayers.repaint();
            }
        }
    }
}
