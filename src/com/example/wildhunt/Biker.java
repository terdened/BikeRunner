package com.example.wildhunt;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.badlogic.gdx.math.Vector2;

public class Biker extends AnimatedSprite {



	private int mLine;
	
	public Biker(float pX, float pY, ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY,pTiledTextureRegion, pVertexBufferObjectManager);
		mLine = 1;
	}
	
	public void updateObject(int speed)
	{
		if(mLine==0)
			this.setPosition(320-128,400);
		if(mLine==1)
			this.setPosition(640-128,400);
		if(mLine==2)
			this.setPosition(960-128,400);
		
	}
	
	public void setAction(float xStart, float yStart, float xStop, float yStop)
	{
		Vector2 direction = new Vector2(xStop-xStart, yStop-yStart);
		if(Math.abs(xStart-xStop)>Math.abs(yStart-yStop))
		{
			if(xStart>xStop)
			{
				moveBiker("left");
			}
			else
			{
				moveBiker("right");
			}
		}else
		{
			
		}
	}
	
	private void moveBiker(String direction)
	{
		if(direction=="left")
		{
			if(mLine>0)
				mLine--;
		}else
		if(direction=="right")
		{
			if(mLine<2)
				mLine++;
		}
	}

}
