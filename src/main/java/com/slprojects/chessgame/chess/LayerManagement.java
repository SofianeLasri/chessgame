package com.slprojects.chessgame.chess;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

import java.util.ArrayList;

public class LayerManagement extends JPanel {
	
	ArrayList<BufferedImage> layers;
	
	public LayerManagement() {
        layers = new ArrayList<BufferedImage>();
    }

    public void addLayer(BufferedImage layer) {
        layers.add(layer);
    }
    
    public void removeLayer(BufferedImage layer) {
    	for(java.awt.image.BufferedImage buf : layers) { 
    		if (buf == layer) {
    			System.out.println("REMOVED");
    			layers.remove(layer);
    		}
    	}
    }

    @Override
    public void paintComponent(java.awt.Graphics g){
        super.paintComponent(g);
        for(java.awt.image.BufferedImage buf : layers) { 
        	//render all layers
            g.drawImage(buf, 0, 0, buf.getWidth(), buf.getHeight(), null);
        }
    }

}
