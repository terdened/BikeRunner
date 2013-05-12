package com.example.wildhunt;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.vbo.ISpriteVertexBufferObject;
import org.andengine.entity.sprite.vbo.ITiledSpriteVertexBufferObject;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.badlogic.gdx.math.Vector2;

public class Player extends AnimatedSprite
{

	float currentSpeedX=0;
	float currentSpeedY=0;
	float speedX=0;
	float speedY=0;
	float acceleration=0.2f;
	Boolean isMove=false;
	
	
	public Player(float pX, float pY, ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager vbom) {
		super(pX, pY, pTiledTextureRegion,
				vbom);
		// TODO Auto-generated constructor stub
	}


	public void move()
	{
		
		
		
		if(speedX!=currentSpeedX)
		{
			
			
			if(currentSpeedX<speedX)
			{
				currentSpeedX+=acceleration;
			}else
			{
				currentSpeedX-=acceleration;
			}
		}
		
		if(speedY!=currentSpeedY)
		{
			
			if(currentSpeedY<speedY)
			{
				currentSpeedY+=acceleration;
			}else
			{
				currentSpeedY-=acceleration;
			}
		}
		if(Math.abs(currentSpeedX)<acceleration)
			currentSpeedX=0;
		if(Math.abs(currentSpeedY)<acceleration)
			currentSpeedY=0;
		
		this.setX(this.getX()+currentSpeedX);
		this.setY(this.getY()+currentSpeedY);
		isMove=false;
		
		
		if(currentSpeedX!=0)
		{				
			if(currentSpeedX>0)	
			this.setRotation((float)(Math.atan(currentSpeedY/currentSpeedX)*180/3.14)+90);
			else
			this.setRotation((float)(Math.atan(currentSpeedY/currentSpeedX)*180/3.14)-90);
		}
	}
	
	public void setAcceleration(float x, float y)
	{
		speedX=x*10;
		speedY=y*10;
	}
}
