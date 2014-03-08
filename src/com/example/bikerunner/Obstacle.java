package com.example.bikerunner;

import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Obstacle extends RoadObject {

	private int pLine;
	private float pLength;
	
	public Obstacle(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTextureRegion, pVertexBufferObjectManager);
	}
	
	public void initObject(int line, float z, float y, float size, float realHeight, float realWidth, float length)
	{
		mZ=z;
		mRealY=y;
		mSize=size;
		mRealHeight=realHeight;
		mRealWidth=realWidth;
		pLine=line;
		pLength=length;
		
		if(pLine==0)
		{
			mRealX=-380;
		}
		else
		if(pLine==1)
		{
			mRealX=0;
		}
		else
		if(pLine==2)
		{
			mRealX=380;
		}
		
		updateObject(0);
	}
	
	public float getZ()
	{
		return this.mZ;
	}
	
	public float getLength()
	{
		return this.pLength;
	}
	
	public int getLine()
	{
		return this.pLine;
	}
}
