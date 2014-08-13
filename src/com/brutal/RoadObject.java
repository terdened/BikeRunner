package com.brutal;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class RoadObject extends Sprite{
	
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
	
	
	public RoadObject(float pX, float pY, ITextureRegion pTextureRegion,
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
		updateObject(0);
		pDeltaY=0;
		setAlpha(0);
	}
	
	public void initDeltaY(float delta)
	{
		pDeltaY=delta;
	}
	
	public void updateObject(int speed)
	{
		if(this.getAlpha()<1)
		{
			this.setAlpha(this.getAlpha()+0.02f);
		}
		if(this.getAlpha()>1)
		{
			this.setAlpha(1);
		}
		
		mZ-=speed;
		mSize=(150.0f)/(mZ+2000);

		this.setWidth(mSize*mRealWidth);
		this.setHeight(mSize*mRealHeight);
		
		mX=640.0f-(this.getWidth()/2)+(mSize*mRealX);
		mY=300.0f-(this.getHeight())+((80000.0f)/(mZ+2000))+(mSize*pDeltaY);
		
		this.setPosition(mX,mY);
		
	}
	
	public boolean canDelete()
	{
		if(mZ<-1999)
			return true;
		else
			return false;
	}
	
	public float getZ()
	{
		return mZ;
	}
	
}
