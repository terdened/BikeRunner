package com.brutal;

import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class ObstacleTramp extends Obstacle {

	public ObstacleTramp(float pX, float pY,
			ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
		this.initObject(0, 200, 600, this.getY(), 1, this.getHeight()*1.3f, this.getWidth()*1.3f, 250);
	}
	
	@Override
	public int getObstacleHeight()
	{
		if((mZ<-1725)&&(mZ>-1725-this.pLength))
			return (int)(pHeight*((Math.abs(1725+mZ))/pLength));
		else
			return 0;
	}
	
	@Override
	public void initLine(int line)
	{
		this.initObject(line, pHeight, mZ, mRealY, mSize, mRealHeight, mRealWidth, pLength);
		
		if(line==0)
			setDislocation(50);
		if(line==2)
			setDislocation(-50);
		
	}

}
