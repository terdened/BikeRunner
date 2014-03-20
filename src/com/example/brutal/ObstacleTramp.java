package com.example.brutal;

import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class ObstacleTramp extends Obstacle {

	public ObstacleTramp(float pX, float pY,
			ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
	}
	
	@Override
	public int getObstacleHeight()
	{
		if((mZ<-1700)&&(mZ>-1700-this.pLength))
			return (int)(pHeight*((Math.abs(1700+mZ))/pLength));
		else
			return 0;
	}

}
