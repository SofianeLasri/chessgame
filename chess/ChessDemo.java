package chess;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class ChessDemo {

    private static int positions(int x) {
        System.out.println(x);
        return x * 80 + 40;
    }

    public static String imagePath = "./images/";

    public static List<String> piecesList = new ArrayList<>() {
        {
            add("rook");
            add("knight");
            add("bishop");
            add("queen");
            add("king");
            add("bishop");
            add("knight");
            add("rook");
            add("pawn");
            add("pawn");
            add("pawn");
            add("pawn");
            add("pawn");
            add("pawn");
            add("pawn");
            add("pawn");
        }
    };

    public static int windowWidth = 840;
    public static int windowHeight = 840;

    public static ChessGraphicTool chessGraphicTool = new ChessGraphicTool();
    public static LayerManagement mgrLayers = new LayerManagement();

    public static void main(String[] args) {

        // Initialization
        mgrLayers.setPreferredSize(new Dimension(windowWidth, windowHeight));

        ChessGUI.showOnFrame(mgrLayers, "Comment r�ussir les �checs");
        ChessMouseEvent chessMouseEvent = ChessGUI.getChessMouseEvent();
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
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
        BufferedImage chessBoard = new BufferedImage(windowWidth, windowHeight, BufferedImage.TYPE_INT_ARGB);
        mgrLayers.addLayer(chessBoard);
        java.awt.Graphics2D chessBoardGC = (Graphics2D) chessBoard.getGraphics();
        chessBoardGC.setRenderingHints(rh);

        boolean pair_impair = false; // v�rifie si la case est paire ou impaire pour savoir comment la colorier
        int line = 0; // v�rifie sur quelle ligne on est pour le d�calage des couleurs
        for (int y = 100; y < 740; y += 80) { // lines
            for (int x = 100; x < 740; x += 80) { // columns
                // chessBoardGC.draw3DRect(x,y,80,80,true); // xcenter, ycenter, windowWidth=80 , windowHeight=80
                if (pair_impair) {
                    pair_impair = false;
                    chessBoardGC.setColor(java.awt.Color.WHITE); // remplis la couleur du rectangle dessin�
                } else {
                    pair_impair = true;
                    chessBoardGC.setColor(java.awt.Color.BLACK); // remplis la couleur du rectangle dessin�
                }
                line++;
                if (line == 8) {
                    pair_impair = !pair_impair;
                    line = 0;
                }
                chessBoardGC.fillRect(x, y, 80, 80);
            }
        }

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
        BufferedImage blackPawnLayer = chessGraphicTool.createImage(blackPawnImage,80, 80, xorigin, yorigin);

//	    // add the new layers
//	    mgrLayers.addLayer(whitePawnLayer);
//	    mgrLayers.addLayer(blackPawnLayer);

        // adding pieces to its list
        ArrayList<String> blackChessPiecesImages = new ArrayList<>();
        ArrayList<String> whiteChessPiecesImages = new ArrayList<>();

        blackChessPiecesImages.add("rook-black.png");
        blackChessPiecesImages.add("knight-black.png");
        blackChessPiecesImages.add("bishop-black.png");
        blackChessPiecesImages.add("queen-black.png");
        blackChessPiecesImages.add("king-black.png");
        blackChessPiecesImages.add("bishop-black.png");
        blackChessPiecesImages.add("knight-black.png");
        blackChessPiecesImages.add("rook-black.png");

        whiteChessPiecesImages.add("rook-white.png");
        whiteChessPiecesImages.add("knight-white.png");
        whiteChessPiecesImages.add("bishop-white.png");
        whiteChessPiecesImages.add("queen-white.png");
        whiteChessPiecesImages.add("king-white.png");
        whiteChessPiecesImages.add("bishop-white.png");
        whiteChessPiecesImages.add("knight-white.png");
        whiteChessPiecesImages.add("rook-white.png");

        for (int i = 8; i > 0; i--) {
            blackChessPiecesImages.add("pawn-black.png");
        }

        for (int i = 8; i > 0; i--) {
            whiteChessPiecesImages.add("pawn-white.png");
        }

        Piece pieces_white[] = new Piece[90];
        Piece pieces_black[] = new Piece[90];
        
        Player p1 = new Player("black", 16, 0, pieces_white);
        Player p2 = new Player("white", 16, 0, pieces_black);

        // display pieces for black player
        createPieces("black", p1);

        // display pieces for white player
        createPieces("white", p2);

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
            if (chessMouseEvent.drag()) {
                int x = chessMouseEvent.getX();
                int y = chessMouseEvent.getY();
                System.out.println("CLICK x=" + x + " y=" + y);

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
            }
        }
    }

    private static void createPieces(String color, Player player) {
        List<Piece> pieces = new ArrayList<>();
        int posx, posy;
        if (color.equals("black")) {
            posx = posy = 1;
        } else {
            posx = 1;
            posy = 8;
        }

        for (String pieceName : piecesList) {
            Piece p = new Piece(pieceName + "-" + color + ".png", color);
            pieces.add(p);
            player.pieces[posx+(10*posy)] = p;
            BufferedImage piece = chessGraphicTool.createImage(p.getImage(), windowWidth, windowHeight, positions(posx), positions(posy));
            mgrLayers.addLayer(piece);

            posx++;
            if (posx == 9) {
                if (color.equals("black")) {
                    posy++;
                } else {
                    posy--;
                }
                posx = 1;
            }
        }
    }
}
