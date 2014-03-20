package com.example.brutal;

import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class TripCacheItem {
	
	public int mTimeToAppear;
	
	protected RoadObjectFar object;
	protected float mZ;
	protected float mX;
	protected float mY;
	protected float mRealX;
	protected float mRealY;
	protected float mRealHeight;
	protected float mRealWidth;
	protected float mSize;
	protected float speed;
	protected float pDeltaY;
	protected ITextureRegion pTextureRegion;
	protected final VertexBufferObjectManager pVbom; 
	
	TripCacheItem(int timeToAppear, ITextureRegion textureRegion, VertexBufferObjectManager vbom)
	{
		mTimeToAppear=timeToAppear;
		pVbom=vbom;
		pTextureRegion=textureRegion;
	}
	
	public void initObject(float z, float x, float y, float size, float realHeight, float realWidth)
	{
		mZ=z;
		mRealX=x;
		mRealY=y;
		mSize=size;
		mRealHeight=realHeight;
		mRealWidth=realWidth;
	}
	
	public boolean isAppear(int time)
	{
		if(time>mTimeToAppear)
			return true;
		else
			return false;
	}
	
	public RoadObjectFar getObject()
	{
		object = new RoadObjectFar(0,0,pTextureRegion,pVbom);
		object.initObject(mZ, mRealX, mRealY, mSize, mRealHeight, mRealWidth);
		return object;
	}
	
}
