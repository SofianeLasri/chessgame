package chess;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ChessDemo {
    public static String imagePath = "./images/";

    public static int windowWidth = 840;
    public static int windowHeight = 840;

    public static ChessGraphicTool chessGraphicTool = new ChessGraphicTool();
    public static LayerManagement mgrLayers = new LayerManagement();
    public static RenderingHints rh;
    public static ChessTable table;
    public static int titleBarHeight;

    public static void main(String[] args) {

        // Initialization
        mgrLayers.setPreferredSize(new Dimension(windowWidth, windowHeight));

        ChessGUI.showOnFrame(mgrLayers, "Comment réussir les échecs");
        titleBarHeight = ChessGUI.frame.getInsets().top;
        ChessMouseEvent chessMouseEvent = ChessGUI.getChessMouseEvent();
        rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        BufferedImage emptyLayer = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_ARGB);

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
        int xorigin = 510; // size of piece ~ 40 -> 500+(60-40)/2
        int yorigin = 210; // size of the piece ~ 40 -> 200+(60-40)/2

        // create a layer with the image file
        BufferedImage whitePawnImage = ChessGraphicTool.load(imagePath + "pawn-white.png");
        if (whitePawnImage == null) {
            System.out.println("Error image not found : " + imagePath + "pawn-white.png");
        }

        // create a dedicated layer and copy the pawn image at the right coordinates 
        BufferedImage whitePawnLayer = chessGraphicTool.createImage(whitePawnImage,
                windowWidth, windowHeight, xorigin, yorigin);

        // create a layer with the image file
        BufferedImage blackPawnImage = ChessGraphicTool.load(imagePath + "pawn-black.png");
        if (blackPawnImage == null) {
            System.out.println("Error image not found : " + imagePath + "pawn-black.png");
        }

        // create a dedicated layer and copy the new pawn image at the right coordinates 
        xorigin = 510;
        yorigin = 330;
        BufferedImage blackPawnLayer = chessGraphicTool.createImage(blackPawnImage, 80, 80, xorigin, yorigin);

//	    // add the new layers
	    // mgrLayers.addLayer(whitePawnLayer);
	    // mgrLayers.addLayer(blackPawnLayer);

        // New game !
        Game game = new Game();

        // display all the layers
        mgrLayers.repaint();

        // -- main loop waiting drag and drop user events
        int counter = 0;
        while (true) {
            // waiting in millisecond
            try {
                Thread.sleep(5);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if(chessMouseEvent.isClicked()){
                int x = chessMouseEvent.getX();
                int y = chessMouseEvent.getY();
                System.out.println("CLICK x=" + x + " y=" + y);

                // Ici on fait l'appel vers Game pour déterminer le jeu
                game.playerClicked(x, y);
                mgrLayers.repaint();
            }

            /*if (chessMouseEvent.drag()) {
                int x = chessMouseEvent.getX();
                int y = chessMouseEvent.getY();
                System.out.println("DRAG x=" + x + " y=" + y);
                //displayMoves(new Piece("knight-white.png", "white"), chessBoardGC, x, y);

                // -------
                // We have to find the right chess piece selected
                // from the coordinates.
                // -------

                // Example for moving the blackPawn piece
                // clear and copy a new ones
                blackPawnLayer.setData(emptyLayer.getRaster()); // clear
                java.awt.Graphics2D gcb = (Graphics2D) blackPawnLayer.getGraphics(); // get graphic context
                gcb.drawImage(blackPawnImage, x - 20, y - 60, null); // copy the piece in the blackPawn graphic context at the right place

                // Example with the computer 
                if (counter == 200000000) {
                    System.out.println("Computer player - please wait - analyze is running (5s)");
                    try {
                        Thread.sleep(5000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println("Computer player - Done !");
                    whitePawnLayer.setData(emptyLayer.getRaster());
                    java.awt.Graphics2D gcw = (Graphics2D) whitePawnLayer.getGraphics();
                    gcw.drawImage(whitePawnImage, 510, 210 + 2 * 60, null);

                    mgrLayers.repaint();
                    counter = 0;
                } else counter += 1;

                // refresh the display with the changes
                mgrLayers.repaint();
            }*/
        }
    }
}
