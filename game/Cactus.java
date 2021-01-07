package com.auburn.game;

import com.auburn.engine.GameContainer;
import com.auburn.engine.Renderer;

public class Cactus extends GameObject
{
	
	private float fallDistance = 0; 
	private float fallSpeed = 10; 
	public static float movementSpeed = 2;
	private float speed =100 ;
	
	public Cactus(int posX, int posY)
	{
		this.posX = posX*16; 
		this.posY = posY*16; 
		this.width = 20; 
		this.height = 25; 
	}

	@Override
	public void update(GameContainer gc, GameManager gm, float dt)
	{
		fallDistance += dt * fallSpeed; 
		if(posY<gc.getHeight()-this.height) 
		{
			posY += fallDistance; 
		}
		
		if(posX < -this.width)
		{
			
			posX = gc.getWidth(); 
			movementSpeed*=1.05; 
			GameManager.CM++; 
			
			
		}
		
		posX -= movementSpeed ;
		
		
		
	}
	


	@Override
	public void render(GameContainer gc, Renderer r)
	{
		r.drawFillRect((int)posX, (int)posY, width, height, 0xffff0000);
		
	}

	public float getMovementSpeed()
	{
		return movementSpeed;
	}

	public void setMovementSpeed(float movementSpeed)
	{
		this.movementSpeed = movementSpeed;
	}

	
}
