package com.brutal;

import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class ObstacleFency extends Obstacle {
	
	//---------------------------------------------
    // CONSTRUCTOR
    //---------------------------------------------
	
	public ObstacleFency(float pX, float pY,
			ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
		initObject(0, 100, 600, getY(), 1, getHeight(), getWidth(), 50);
	}
	
	//---------------------------------------------
    // OVERLOADED METHODS
    //---------------------------------------------
	
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
