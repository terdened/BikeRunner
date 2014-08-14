package com.brutal.game.road;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Obstacle extends AnimatedSprite {

	//---------------------------------------------
    // VARIABLES
    //---------------------------------------------
	
	protected int mLine;
	protected int mHeight;
	protected String mAction;
	protected float mLength;
	protected float mZ;
	protected float mX;
	protected float mY;
	protected float mRealX;
	protected float mDislocationX;
	protected float mRealY;
	protected float mRealHeight;
	protected float mRealWidth;
	protected float mSize;
	protected float mSpeed;
	
	//---------------------------------------------
    // CONSTRUCTOR
    //---------------------------------------------
	
	public Obstacle(float pX, float pY,ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
	}

	//---------------------------------------------
    // PUBLIC METHODS
    //---------------------------------------------
	
	public void initObject(int pLine, int pHeight, float pZ, float pY, float pSize, 
			float pRealHeight, float pRealWidth, float pLength)
	{
		mHeight=pHeight;
		mDislocationX=0;
		mSpeed=0;
		mZ=pZ;
		mRealY=pY;
		mSize=pSize;
		mRealHeight=pRealHeight;
		mRealWidth=pRealWidth;
		mLine=pLine;
		mLength=pLength;
		
		if(mLine==0)
		{
			mRealX=-380;
		}
		else
		if(mLine==1)
		{
			mRealX=0;
		}
		else
		if(mLine==2)
		{
			mRealX=380;
		}
		
		if(mLine==0)
		{
			final long[] PLAYER_ANIMATE = new long[] { 200, 0, 0 };
			animate(PLAYER_ANIMATE, 0, 2, true);
		}
		else
		if(mLine==1)
		{
			final long[] PLAYER_ANIMATE = new long[] { 0, 200, 0 };
			animate(PLAYER_ANIMATE, 0, 2, true);
		}
		else
		if(mLine==2)
		{
			final long[] PLAYER_ANIMATE = new long[] { 0, 0, 200 };
			animate(PLAYER_ANIMATE, 0, 2, true);
		}
		
		updateObject(0);
	}
	
	public void initLine(int pLine)
	{
		this.mLine=pLine;
	}
	
	public boolean canDelete()
	{
		if(mZ<-1999)
			return true;
		else
			return false;
	}
	
	public void updateObject(int pSpeed)
	{
		if(this.getAlpha()<1)
		{
			this.setAlpha(this.getAlpha()+0.1f);
		}
		
		mZ-=pSpeed-mSpeed;
		mSize=(150.0f)/(mZ+2000);

		this.setWidth(mSize*mRealWidth);
		this.setHeight(mSize*mRealHeight);
		
		mX=640.0f-(this.getWidth()/2)+(mSize*mRealX)+(mSize*mDislocationX);
		mY=300.0f-(this.getHeight())+((80000.0f)/(mZ+2000));
		
		this.setPosition(mX,mY);
		
	}
	
	//---------------------------------------------
    // SETTERS
    //---------------------------------------------
	
	public void setRealHeight(int pHeight)
	{
		mHeight += pHeight;
		mRealY += pHeight;
	}
	
	public void setDislocation(float pDislocation)
	{
		mDislocationX = pDislocation;
	}
	
	public void setSpeed(float pSpeed)
	{
		mSpeed = pSpeed;
	}
	
	public void setAction(String pAction)
	{
		mAction=pAction;
	}
	
	public void setDepth(int pZ)
	{
		mZ=pZ;
	}
	
	//---------------------------------------------
    // GETTERS
    //---------------------------------------------
	
	public String getAction()
	{
		return mAction;
	}
	
	public float getZ()
	{
		return this.mZ;
	}
	
	public float getLength()
	{
		return this.mLength;
	}
	
	public int getLine()
	{
		return this.mLine;
	}
	
	public int getObstacleHeight()
	{
		if((mZ<-1700-25)&&(mZ>-1700-this.mLength-25))
			return mHeight;
		else
			return 0;
	}
	
	public int getObstacleHeightByPosition(int pPosition)
	{
		if((mZ<-1700+pPosition-25)&&(mZ>-1700+pPosition-this.mLength-25))
			return mHeight;
		else
			return 0;
	}
	
	public int getObstacleHeightByDeep(float pDeep)
	{
		if((mZ<pDeep)&&(mZ>pDeep-this.mLength))
			return mHeight;
		else
			return 0;
	}
	
}
