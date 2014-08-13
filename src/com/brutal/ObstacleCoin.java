package com.brutal;

import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class ObstacleCoin extends Obstacle {

	//---------------------------------------------
    // VARIABLES
    //---------------------------------------------
	
	public int mState;
	private int mDeleteTimer;
	
	//---------------------------------------------
    // CONSTRUCTOR
    //---------------------------------------------
	
	public ObstacleCoin(float pX, float pY,
			ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
		mDeleteTimer=0;
	}
	
	
	//---------------------------------------------
    // OVERLOADED METHODS
    //---------------------------------------------
	
	@Override
	public int getObstacleHeight()
	{
		if(mState==0)
		{
			if((mZ<-1700)&&(mZ>-1700-this.mLength))
				return mHeight;
			else
				return 0;
			}
		return 0;
	}

	@Override
	public void updateObject(int pSpeed)
	{
		if(mState==0)
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
		else
		if(mState==1)
		{
			if(this.getAlpha()<1)
			{
				this.setAlpha(this.getAlpha()+0.1f);
			}
			
			mZ-=pSpeed/4-mSpeed;
			mRealHeight+=2;
			mRealWidth+=2;
			mSize=(150.0f)/(mZ+2000);
	
			this.setWidth(mSize*mRealWidth);
			this.setHeight(mSize*mRealHeight);
			
			mX=640.0f-(this.getWidth()/2)+(mSize*mRealX)+(mSize*mDislocationX);
			mY=300.0f-(this.getHeight())+((80000.0f)/(mZ+2000));
			
			this.setPosition(mX,mY);
			mDeleteTimer++;
		}
		
	}
	
	@Override
	public boolean canDelete()
	{
		if(mDeleteTimer>20)
			return true;
		else
		if(mZ<-1999)
			return true;
		else
			return false;
	}

	//---------------------------------------------
    // PUBLIC METHODS
    //---------------------------------------------
	
	public void initObject(int pLine, int pHeight, float pZ, float pY, 
			float pSize, float pRealHeight, float pRealWidth, float pLength)
	{
		mState=0;
		mHeight=pHeight;
		mDislocationX=0;
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
		
		final long[] PLAYER_ANIMATE = new long[] { 200, 200, 200, 200, 0, 0, 0, 0 };
		animate(PLAYER_ANIMATE, 0, 7, true);
		
		
		updateObject(0);
	}
	
	public void delete()
	{
		final long[] PLAYER_ANIMATE = new long[] { 0, 0, 0, 0, 100, 100, 100, 100 };
		animate(PLAYER_ANIMATE, 0, 7, false);
		mState=1;
	}

}
