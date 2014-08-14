package com.brutal.game.road;

import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class RoadObjectFar extends RoadObject {

	//---------------------------------------------
    // CONSTRUCTOR
    //---------------------------------------------
	
	public RoadObjectFar(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
	}
	
	//---------------------------------------------
    // PUBLILC METHODS
    //---------------------------------------------
		
	public void initObject(float pZ, float pX, float pY, float pSize, 
			float pRealHeight, float pRealWidth)
	{
		mZ=pZ;
		mRealX=pX;
		mRealY=pY;
		mSize=pSize;
		mRealHeight=pRealHeight;
		mRealWidth=pRealWidth;
		updateObject(0);
		mDeltaY=10;
		setAlpha(0);
	}
	
	public void updateObject(int pSpeed)
	{
		if(this.getAlpha()<1)
		{
			this.setAlpha(this.getAlpha()+0.02f);
		}
		if(this.getAlpha()>1)
		{
			this.setAlpha(1);
		}
		
		mZ-=pSpeed;
		mSize=(150.0f)/(mZ+2000);

		this.setWidth(mSize*mRealWidth);
		this.setHeight(mSize*mRealHeight);
		
		mX=640.0f-(this.getWidth()/2)+(mSize*mRealX);
		mY=300.0f-(this.getHeight())+((80000.0f)/(mZ+2000))+mDeltaY;
		
		this.setPosition(mX,mY);
	}

}
