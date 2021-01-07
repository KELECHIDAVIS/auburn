package com.auburn.game;

import java.awt.event.KeyEvent;

import com.auburn.engine.GameContainer;
import com.auburn.engine.Renderer;

public class Player extends GameObject
{
	// jump speed
	
	
	private float fallDistance = 0; 
	private float fallSpeed = 10; 
	private float gravity= 15; 
	
	private float speed =150;
	private float jump = -5; 
	private boolean ground = false; 
	
	
	
	
	public Player(int posX, int posY)
	{
		this.tag = "player"; 
		this.posX = posX*16; 
		this.posY = posY*16; 
		this.width = 20; 
		this.height = 40; 
	}

	@Override
	public void update(GameContainer gc, GameManager gm , float dt)
	{
		fallDistance += dt * fallSpeed; 
		if(gc.getInput().isKeyDown(KeyEvent.VK_W)&& ground)
		{
			fallDistance = jump; 
			ground = false; 
		}
		posY += fallDistance; 
		if(!(posY<gc.getHeight()-this.height)) 
		{
			
			ground = true; 
		}
		if(ground)
		{
			fallDistance = 0;
			fallSpeed = 0; 
		}else
		{
			fallSpeed = gravity; 
		}
		
		
		
	}
	
	public void jump() 
	{
		
	}

	@Override
	public void render(GameContainer gc, Renderer r)
	{
		
		r.drawFillRect((int)posX, (int)posY, width, height, 0xff00ff00);
	}
}
