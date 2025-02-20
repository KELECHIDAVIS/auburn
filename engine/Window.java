package com.auburn.engine;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Window 
{
	
	private JFrame frame ; 
	private BufferedImage image ; 
	private Canvas canvas ; 
	private Graphics g; 
	private BufferStrategy bs; 
	
	public Window(GameContainer gc)
	{
		image = new BufferedImage(gc.getWidth(), gc.getHeight(), BufferedImage.TYPE_INT_RGB); 
		canvas = new Canvas();
		Dimension s = new Dimension((int)(gc.getWidth()*gc.getScale()), (int)(gc.getHeight()*gc.getScale()));
		canvas.setPreferredSize(s);
		canvas.setMaximumSize(s);
		canvas.setMinimumSize(s);
		canvas.setFocusable(true);
		
		
        
		frame  =new JFrame(gc.getTitle());
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(canvas, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		 
		frame.setLocationRelativeTo(null);
		
		
		canvas.createBufferStrategy(2);
		bs = canvas.getBufferStrategy(); 
		g = bs.getDrawGraphics(); 
		
	}
	
	
	
	
	public void update() 
	{
		g.drawImage(image, 0, 0, canvas.getWidth(),canvas.getHeight(), null);
		bs.show(); 
		
	}




	public Canvas getCanvas() {
		return canvas;
	}




	public BufferedImage getImage() {
		return image;
	}




	public JFrame getFrame() {
		return frame;
	}
}
