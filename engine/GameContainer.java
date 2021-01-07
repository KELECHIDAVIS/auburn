package com.auburn.engine;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.auburn.game.GameManager;

public class GameContainer implements Runnable
{
	private Window window; 
	private Input input;
	private Renderer renderer;
	private Thread thread; 
	private AbstractGame game; 
	
	private boolean running  = false; 
	private final double UPDATE_CAP  = 1.0/60.0; 
	private int width = 320, height = 240; 
	private float scale =  3f; 
	private String title = "Auburn v 1.0"; 	
	public GameContainer(AbstractGame game) 
	{
		this.game = game;
	}
	public void start() 
	{
		
		window = new Window(this); 
		renderer = new Renderer(this);
		input = new Input(this);
		thread  = new Thread(this);
		thread.run(); 
		
	}
	public void stop()
	{
		
	}
	public void run() 
	{
	
		boolean render = false; 
		double firstTime = 0; 
		double lastTime = System.nanoTime() / 1000000000.0; 
		double passedTime=0; 
		double unprocessedTime = 0; 
		
		double frameTime  = 0;
		int frames  = 0; 
		int fps  = 0;  
		
		running = true;
		
	
		
		while(running)
		{
			render = true;
			firstTime = System.nanoTime() / 1000000000.0; 
			passedTime = firstTime - lastTime; 
			lastTime = firstTime; 
			unprocessedTime += passedTime; 
			frameTime += passedTime; 
			while(unprocessedTime >= UPDATE_CAP)
			{
				
				unprocessedTime -= UPDATE_CAP;
				render = true; 
				
				game.update(this,  (float)UPDATE_CAP);
				
				
				
				input.update();
				
				
				if (frameTime>= 1.0) 
				{
					frameTime = 0; 
					fps = frames; 
					frames= 0; 
					
				}
				 
			}
			if(render)
			{
				renderer.clear(); 
				game.render(this, renderer);
				renderer.drawText("SCORE:" + GameManager.CM, 287 , 0, 0xff00ffff);
				if(GameManager.gameOver == false)
				{
					renderer.drawText("FPS:" + fps, 0, 0, 0xff00ffff);
					
					switch(GameManager.CM)
					{
					case 8:
						System.out.println("Speed Is Increasing");
						GameManager.CM++; 
						break;
					case 15: 
						System.out.println("SPEED IS INCREASING");
						GameManager.CM++; 
						break;
						
					case 20:
						System.out.println("good luck");
						GameManager.CM++; 
						break; 
						
					case 40: 
						System.out.println("guess you're just goated");
						GameManager.CM++; 
					default: 
						
						break;
						
					}
				}else
				{
					renderer.drawText("GAMEOVER",135 , 80, 0xffff0000);
					renderer.drawText("PRESS SPACE TO CLOSE THE GAME",100 , 100, 0xffff0000);
					
				}
				window.update(); 
				frames++;
				
			}
			else
			{
				try 
				{
					Thread.sleep(1);
				} catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
		}
		dispose(); 
	}
	
	private void dispose() 
	{
		
	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public float getScale() {
		return scale;
	}
	public void setScale(float scale) {
		this.scale = scale;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Window getWindow() {
		return window;
	}
	public Input getInput()
	{
		return input;
	}
	public Renderer getRenderer()
	{
		return renderer;
	}
	public void setRenderer(Renderer renderer)
	{
		this.renderer = renderer;
	}
}
