package com.brutal.game.worldmanager;

import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.brutal.engine.*;
import com.brutal.game.road.*;


public abstract class WorldController {
	
	//---------------------------------------------
    // VARIABLES
    //---------------------------------------------

	protected final Road mRoad;
	protected int mCounter;
	protected final VertexBufferObjectManager mVbom;
	protected final ResourcesManager mResManager;
	protected ObjectsFactory mObjFactory;
	
	//---------------------------------------------
    // CONSTRUCTOR
    //---------------------------------------------

	public WorldController(Road pRoad, VertexBufferObjectManager pVbom, 
			ResourcesManager pResManager)
	{
		mRoad = pRoad;
		mCounter=0;
		mVbom = pVbom;
		mResManager = pResManager;
		mObjFactory = new ObjectsFactory();
	}
	
	//---------------------------------------------
    // PUBLIC METHODS
    //---------------------------------------------

	abstract public void updateWorld(int pSpeed);

	//---------------------------------------------
    // GETTERS
    //---------------------------------------------

	protected int getComplexityBySpeed(int pSpeed)
	{
		int result=0;
		
		if(pSpeed<20)
		{
			result=1;
		}else
		if(pSpeed<25)
		{
			result=2;
		}else
		{
			result=3;
		}
		
		return result;
	}
}
