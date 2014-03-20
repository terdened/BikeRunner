package com.example.brutal;

import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class RoadObjectFar extends RoadObject {

	public RoadObjectFar(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
	}
	
	public void initObject(float z, float x, float y, float size, float realHeight, float realWidth)
	{
		mZ=z;
		mRealX=x;
		mRealY=y;
		mSize=size;
		mRealHeight=realHeight;
		mRealWidth=realWidth;
		pDeltaY=10;
		updateObject(0);
	}
	
	public void updateObject(int speed)
	{
		if(this.getAlpha()<1)
		{
			this.setAlpha(this.getAlpha()+0.1f);
		}
		
		mZ-=speed/4;
		mSize=(150.0f)/(mZ+2000);

		this.setWidth(mSize*mRealWidth);
		this.setHeight(mSize*mRealHeight);
		
		mX=640.0f-(this.getWidth()/2)+(mSize*mRealX);
		mY=300.0f-(this.getHeight())+((80000.0f)/(mZ+2000))+pDeltaY;
		
		this.setPosition(mX,mY);
		
	}

}
