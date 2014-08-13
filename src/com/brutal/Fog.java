package com.brutal;

import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Fog extends RoadObject {

	public Fog(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
		// TODO Auto-generated constructor stub
	}
	
	public void updateObject(int speed)
	{
		if(this.getAlpha()<1)
		{
			this.setAlpha(this.getAlpha()+0.1f);
		}
		
		mSize=(150.0f)/(mZ+2000);
		
		mX=640.0f-(this.getWidth()/2)+(mSize*mRealX);
		mY=300.0f-(this.getHeight())+((80000.0f)/(mZ+2000))+pDeltaY;
		
		this.setPosition(mX,mY);
		
	}
}
