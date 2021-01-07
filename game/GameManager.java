package com.auburn.game;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import com.auburn.engine.AbstractGame;
import com.auburn.engine.GameContainer;
import com.auburn.engine.Renderer;
import com.auburn.engine.audio.SoundClip;
import com.auburn.engine.gfx.ImageTile;

public class GameManager extends AbstractGame
{
	private ArrayList<GameObject> objects = new ArrayList<GameObject>();
	
	public static int CM = 0; 
	
	public static boolean gameOver = false; 
	public GameManager() 
	{
		objects.add(new Player(1, 9));
		objects.add(new Cactus(18, 11));
		
			
	}
	
	
	@Override
	public void update(GameContainer gc, float dt)
	{
		if(!gameOver)
		{
			
			for(int i = 0; i<objects.size(); i++)
			{
				objects.get(i).update(gc, this, dt);
				if(objects.get(i).isDead())
				{
					objects.remove(i); 
					i--; 
					
				}
				
				
			}
			
			
			if (objects.get(0).posX < objects.get(1).posX + objects.get(1).width &&
					objects.get(0).posX +objects.get(0).width > objects.get(1).posX &&
					objects.get(0).posY < objects.get(1).posY + objects.get(1).height &&
					objects.get(0).posY + objects.get(0).height > objects.get(1).posY) 
				{
					
				 	gameOver = true;
					objects.clear(); 
						  
						    
				}
				
		}
		
		if(gc.getInput().isKeyDown(KeyEvent.VK_SPACE)&& gameOver)
		{
			System.exit(0);
		}
	
		
			
			
			 
		
	}
	
	public static void gameOver()
	{
		System.out.println("GAME OVER");
	}
	
	
	@Override
	public void render(GameContainer gc, Renderer r)
	{
		if(!gameOver)
		{
			for(GameObject obj : objects)
			{
				obj.render(gc, r);
			}
		}
		
		
	}
	
	
	public static void main(String[] args)
	{
		GameContainer gc  = new GameContainer(new GameManager());
		gc.setHeight(200);
		gc.setWidth(320);
		gc.setScale(3f);
		gc.start(); 
		gc.getRenderer(); 
	}

}
