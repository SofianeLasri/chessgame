package chess;

import java.util.ArrayList;

public class PieceType {
    String type;

	int score;

    public String getType() {
        return type;
    }

    public PieceType(String type, int score) {
        super();
        this.type = type;
		this.score = score;
    }

    public void setType(String type) {
        this.type = type;
    }


    public ArrayList<int[]> PossibleMove() {
    	ArrayList<int[]> moves = new ArrayList<int[]>(); //0 = x; 1 = y;
		System.out.println(this.type);
		switch (this.type) {
            case "pawn":
            	moves.add(new int[] {0,1});
            	moves.add(new int[] {0,2});
            	moves.add(new int[] {1,1});
            	moves.add(new int[] {-1,1});
                break;
            case "rook":
            	for(int i = 1; i < 8; i++) {
					moves.add(new int[] {0,i});
	                moves.add(new int[] {i,0});
	                moves.add(new int[] {-i,0});
	                moves.add(new int[] {0,-i});
            	}
                break;
            case "bishop":
            	for(int i = 1; i < 8; i++) {
	            	moves.add(new int[] {i,i});
	                moves.add(new int[] {i,-i});
	                moves.add(new int[] {-i,i});
	                moves.add(new int[] {-i,-i});
            	}
                break;
            case "knight":
	            moves.add(new int[] {1,2});
	            moves.add(new int[] {2,1});
	            moves.add(new int[] {-1,-2});
	            moves.add(new int[] {-2,-1});
	            moves.add(new int[] {-2,1});
	            moves.add(new int[] {-1,2});
	            moves.add(new int[] {2,-1});
	            moves.add(new int[] {1,-2});
                break;
            case "queen":
            	for(int i = 1; i < 8; i++) {
	            	moves.add(new int[] {i,i});
	                moves.add(new int[] {i,-i});
	                moves.add(new int[] {-i,-i});
	                moves.add(new int[] {-i,i});
	                moves.add(new int[] {0,i});
	                moves.add(new int[] {0,-i});
	                moves.add(new int[] {-i,0});
	                moves.add(new int[] {i,0});
            	}
                break;
            case "king":
	            moves.add(new int[] {0,1});
	            moves.add(new int[] {1,1});
	            moves.add(new int[] {1,0});
	            moves.add(new int[] {0,-1});
	            moves.add(new int[] {1,-1});
	            moves.add(new int[] {-1,-1});
	            moves.add(new int[] {-1,0});
	            moves.add(new int[] {-1,1});
                break;
        }
        return moves;
    }
}
