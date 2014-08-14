package com.brutal.game.road;

import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class ObstacleBuilding extends Obstacle {

	//---------------------------------------------
    // CONSTRUCTOR
    //---------------------------------------------
	
	public ObstacleBuilding(float pX, float pY,
			ITiledTextureRegion[] pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTiledTextureRegion[(int)(Math.random()*pTiledTextureRegion.length)],
				pVertexBufferObjectManager);
		this.initObject(0, 300, 600, this.getY(), 1.5f, this.getHeight()*1.3f,
				this.getWidth()*1.3f, 250);
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
