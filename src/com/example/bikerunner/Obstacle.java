package com.example.bikerunner;

import java.util.LinkedList;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Obstacle extends AnimatedSprite {

	public Obstacle(float pX, float pY,ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
	}

	private int pLine;
	private boolean[][] pCollision;
	
	private float pLength;
	protected float mZ;
	protected float mX;
	protected float mY;
	protected float mRealX;
	protected float mDislocationX;
	protected float mRealY;
	protected float mRealHeight;
	protected float mRealWidth;
	protected float mSize;
	protected float speed;
	
	public void initCollision(boolean[][] line)
	{
		this.pCollision=line;
	}
	
	public void initObject(int line, float z, float y, float size, float realHeight, float realWidth, float length)
	{
		mDislocationX=0;
		mZ=z;
		mRealY=y;
		mSize=size;
		mRealHeight=realHeight;
		mRealWidth=realWidth;
		pLine=line;
		pLength=length;
		
		if(pLine==0)
		{
			mRealX=-380;
		}
		else
		if(pLine==1)
		{
			mRealX=0;
		}
		else
		if(pLine==2)
		{
			mRealX=380;
		}
		
		if(pLine==0)
		{
			final long[] PLAYER_ANIMATE = new long[] { 200, 0, 0 };
			animate(PLAYER_ANIMATE, 0, 2, true);
		}
		else
		if(pLine==1)
		{
			final long[] PLAYER_ANIMATE = new long[] { 0, 200, 0 };
			animate(PLAYER_ANIMATE, 0, 2, true);
		}
		else
		if(pLine==2)
		{
			final long[] PLAYER_ANIMATE = new long[] { 0, 0, 200 };
			animate(PLAYER_ANIMATE, 0, 2, true);
		}
		
		updateObject(0);
	}
	
	
	public void setDislocation(float dislocation)
	{
		mDislocationX = dislocation;
	}
	
	public float getZ()
	{
		return this.mZ;
	}
	
	public float getLength()
	{
		return this.pLength;
	}
	
	public int getLine()
	{
		return this.pLine;
	}
	
	public boolean[][] getCollisionGrid()
	{
		if((mZ<-1800+this.pLength)&&(mZ>-1800))
			return pCollision;
		else
			return new boolean[][]{{false,false,false},
								   {false,false,false},
								   {false,false,false}};
	}
	
	public void updateObject(int speed)
	{
		if(this.getAlpha()<1)
		{
			this.setAlpha(this.getAlpha()+0.1f);
		}
		
		mZ-=speed;
		mSize=(150.0f)/(mZ+2000);

		this.setWidth(mSize*mRealWidth);
		this.setHeight(mSize*mRealHeight);
		
		mX=640.0f-(this.getWidth()/2)+(mSize*mRealX)+(mSize*mDislocationX);
		mY=300.0f-(this.getHeight())+((80000.0f)/(mZ+2000));
		
		this.setPosition(mX,mY);
		
	}
	
	public boolean canDelete()
	{
		if(mZ<-1999)
			return true;
		else
			return false;
	}
}
