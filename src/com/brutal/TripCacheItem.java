package com.brutal;

import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class TripCacheItem {

	//---------------------------------------------
    // VARIABLES
    //---------------------------------------------

	protected RoadObjectFar mObject;
	protected float mZ;
	protected float mX;
	protected float mY;
	protected float mRealX;
	protected float mRealY;
	protected float mRealHeight;
	protected float mRealWidth;
	protected float mSize;
	protected float mSpeed;
	protected float mDeltaY;
	protected ITextureRegion mTextureRegion;
	protected final VertexBufferObjectManager mVbom; 
	
	public int mTimeToAppear;
	
	//---------------------------------------------
    // CONSTRUCTOR
    //---------------------------------------------

	TripCacheItem(int pTimeToAppear, ITextureRegion pTextureRegion, 
			VertexBufferObjectManager pVbom)
	{
		mTimeToAppear=pTimeToAppear;
		mVbom=pVbom;
		mTextureRegion=pTextureRegion;
	}
	
	//---------------------------------------------
    // PUBLIC METHODS
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
	}
	
	public boolean isAppear(int pTime)
	{
		if(pTime>mTimeToAppear)
			return true;
		else
			return false;
	}
	
	//---------------------------------------------
    // GETTERS
    //---------------------------------------------

	public RoadObjectFar getObject()
	{
		mObject = new RoadObjectFar(0,0,mTextureRegion,mVbom);
		mObject.initObject(mZ, mRealX, mRealY, mSize, mRealHeight, mRealWidth);
		return mObject;
	}
	
}
