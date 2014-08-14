package com.brutal.game.road;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class RoadObject extends Sprite{
	
	//---------------------------------------------
    // VARIABLES
    //---------------------------------------------
	
	protected float mZ;
	protected float mX;
	protected float mY;
	protected float mRealX;
	protected float mRealY;
	protected float mRealHeight;
	protected float mRealWidth;
	protected float mSize;
	//protected float mSpeed;
	protected float mDeltaY;
	
	//---------------------------------------------
    // CONSTRUCTOR
    //---------------------------------------------
	
	public RoadObject(float pX, float pY, ITextureRegion pTextureRegion,
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
		mDeltaY=0;
		setAlpha(0);
	}
	
	public void initDeltaY(float pDelta)
	{
		mDeltaY=pDelta;
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
		mY=300.0f-(this.getHeight())+((80000.0f)/(mZ+2000))+(mSize*mDeltaY);
		
		this.setPosition(mX,mY);
		
	}
	
	public boolean canDelete()
	{
		if(mZ<-1999)
			return true;
		else
			return false;
	}
	
	//---------------------------------------------
    // GETTERS
    //---------------------------------------------
	
	public float getZ()
	{
		return mZ;
	}
	
}
