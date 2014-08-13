package com.brutal;

import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class ObstacleCoin extends Obstacle {

	public int mState;
	private int pDeleteTimer;
	
	public ObstacleCoin(float pX, float pY,
			ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
		pDeleteTimer=0;
	}
	
	public void initObject(int line, int height, float z, float y, float size, float realHeight, float realWidth, float length)
	{
		mState=0;
		pHeight=height;
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
		
		final long[] PLAYER_ANIMATE = new long[] { 200, 200, 200, 200, 0, 0, 0, 0 };
		animate(PLAYER_ANIMATE, 0, 7, true);
		
		
		updateObject(0);
	}
	
	@Override
	public int getObstacleHeight()
	{
		if(mState==0)
		{
			if((mZ<-1700)&&(mZ>-1700-this.pLength))
				return pHeight;
			else
				return 0;
			}
		return 0;
	}

	public void delete()
	{
		final long[] PLAYER_ANIMATE = new long[] { 0, 0, 0, 0, 100, 100, 100, 100 };
		animate(PLAYER_ANIMATE, 0, 7, false);
		mState=1;
	}
	@Override
	public void updateObject(int speed)
	{
		if(mState==0)
		{
			if(this.getAlpha()<1)
			{
				this.setAlpha(this.getAlpha()+0.1f);
			}
			
			mZ-=speed-pSpeed;
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
			
			mZ-=speed/4-pSpeed;
			mRealHeight+=2;
			mRealWidth+=2;
			mSize=(150.0f)/(mZ+2000);
	
			this.setWidth(mSize*mRealWidth);
			this.setHeight(mSize*mRealHeight);
			
			mX=640.0f-(this.getWidth()/2)+(mSize*mRealX)+(mSize*mDislocationX);
			mY=300.0f-(this.getHeight())+((80000.0f)/(mZ+2000));
			
			this.setPosition(mX,mY);
			pDeleteTimer++;
		}
		
	}
	
	@Override
	public boolean canDelete()
	{
		if(pDeleteTimer>20)
			return true;
		else
		if(mZ<-1999)
			return true;
		else
			return false;
	}

}
