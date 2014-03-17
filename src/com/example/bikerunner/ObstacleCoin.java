package com.example.bikerunner;

import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class ObstacleCoin extends Obstacle {

	public int mState;
	
	public ObstacleCoin(float pX, float pY,
			ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
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
		final long[] PLAYER_ANIMATE = new long[] { 0, 0, 0, 0, 50, 50, 50, 50 };
		animate(PLAYER_ANIMATE, 0, 7, false);
		mState=1;
	}

}
