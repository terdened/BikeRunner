package com.brutal;

import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class ObstacleBuilding extends Obstacle {

	public ObstacleBuilding(float pX, float pY,
			ITiledTextureRegion[] pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTiledTextureRegion[(int)(Math.random()*pTiledTextureRegion.length)], pVertexBufferObjectManager);
		this.initObject(0, 300, 600, this.getY(), 1.5f, this.getHeight()*1.3f, this.getWidth()*1.3f, 250);
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
