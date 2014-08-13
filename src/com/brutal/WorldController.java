package com.brutal;

import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class WorldController {
	
	protected final Road mRoad;
	protected int mCounter;
	protected final VertexBufferObjectManager mVbom;
	protected final ResourcesManager mResManager;
	protected ObjectsFactory objFactory;
	
	public WorldController(Road road, VertexBufferObjectManager vbom, ResourcesManager resManager)
	{
		mRoad = road;
		mCounter=0;
		mVbom = vbom;
		mResManager = resManager;
		objFactory = new ObjectsFactory();
	}
	
	public void updateWorld(int speed)
	{
		
	}
	
	protected int getComplexityBySpeed(int speed)
	{
		int result=0;
		
		if(speed<20)
		{
			result=1;
		}else
		if(speed<25)
		{
			result=2;
		}else
		{
			result=3;
		}
		
		return result;
	}
}
