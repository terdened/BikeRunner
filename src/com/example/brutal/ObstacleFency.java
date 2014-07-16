package com.example.brutal;

import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class ObstacleFency extends Obstacle {
	
	public ObstacleFency(float pX, float pY,
			ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
		initObject(0, 100, 600, getY(), 1, getHeight(), getWidth(), 50);
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
