package com.brutal.game.road;

import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class ObstaclePickup extends Obstacle {

	//---------------------------------------------
    // CONSTRUCTOR
    //---------------------------------------------
	
	public ObstaclePickup(float pX, float pY,
			ITiledTextureRegion[] pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTiledTextureRegion[(int)(Math.random()*pTiledTextureRegion.length)],
				pVertexBufferObjectManager);
		this.initObject(0, 200, 600, this.getY(), 1, this.getHeight()*1.3f,
				this.getWidth()*1.3f, 250);
	}
	
	//---------------------------------------------
    // OVERLOADED METHODS
    //---------------------------------------------
	
	@Override
	public int getObstacleHeight()
	{
		if((mZ<-1725)&&(mZ>-1725-(this.mLength*0.33)))
		{
			return (int)(mHeight/2);
		}
		else
		if((mZ<-1725)&&(mZ>-1725-(this.mLength*0.66)))
		{
			return (int)(mHeight*((Math.abs(1725+mZ))/mLength));
		}
		else
		if((mZ<-1725)&&(mZ>-1725-this.mLength))
		{
			return (int)(mHeight/2);
		}
		else
			return 0;
	}
	
	@Override
	public void initLine(int pLine)
	{
		this.initObject(pLine, mHeight, mZ, mRealY, mSize, mRealHeight, mRealWidth, mLength);
		
		if(pLine==0)
			setDislocation(50);
		if(pLine==2)
			setDislocation(-50);
		
	}

}
