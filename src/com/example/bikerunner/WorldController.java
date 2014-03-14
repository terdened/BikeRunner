package com.example.bikerunner;

import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class WorldController {
	
	protected final Road mRoad;
	protected int mCounter;
	protected final VertexBufferObjectManager mVbom;
	protected final ResourcesManager mResManager;

	public WorldController(Road road, VertexBufferObjectManager vbom, ResourcesManager resManager)
	{
		mRoad = road;
		mCounter=0;
		mVbom = vbom;
		mResManager = resManager;
	}
	
	public void updateWorld(int speed)
	{
		
	}
}
