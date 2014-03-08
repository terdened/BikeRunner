package com.example.bikerunner;

import java.util.LinkedList;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.badlogic.gdx.math.Vector2;

public class Biker extends AnimatedSprite {



	private int mLine;
	private LinkedList<Animation> pAnimationList;
	
	public Biker(float pX, float pY, ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		
		super(pX, pY,pTiledTextureRegion, pVertexBufferObjectManager);
		
		mLine = 1;
		pAnimationList = new LinkedList<Animation>();
		final long[] PLAYER_ANIMATE = new long[] { 200, 0, 0 };
		animate(PLAYER_ANIMATE, 0, 2, true);
		updateObject(0);
	}
	
	public void updateObject(int speed)
	{
		if((mLine==0)&&(pAnimationList.size()==0))
			this.setPosition(350-128,400);
		if((mLine==1)&&(pAnimationList.size()==0))
			this.setPosition(640-128,400);
		if((mLine==2)&&(pAnimationList.size()==0))
			this.setPosition(910-128,400);
		
		if(pAnimationList.size()>0)
		{
			pAnimationList.getFirst().update();
			if(pAnimationList.getFirst().canDelete())
			{
				pAnimationList.removeFirst();
			}
		}
		
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
	
	public int getLine()
	{
		return mLine;
	}
	
	public void resetGame()
	{
		mLine = 1;
		pAnimationList = new LinkedList<Animation>();
		final long[] PLAYER_ANIMATE = new long[] { 200, 0, 0 };
		animate(PLAYER_ANIMATE, 0, 2, true);
		updateObject(0);
	}
	
	private void moveBiker(String direction)
	{
		if(direction=="left")
		{
			if((mLine>0)&&(pAnimationList.size()==0))
			{
				mLine--;
				pAnimationList.add(new MoveLeftAnimation(this));
				pAnimationList.getLast().initAnimation("left", 10);
			}
		}else
		if(direction=="right")
		{
			if((mLine<2)&&(pAnimationList.size()==0))
			{
				mLine++;
				pAnimationList.add(new MoveRightAnimation(this));
				pAnimationList.getLast().initAnimation("right", 10);
			}
		}
	}

}
