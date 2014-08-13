package com.brutal;

import java.util.LinkedList;

import org.andengine.opengl.vbo.VertexBufferObjectManager;

public abstract class TripManager {
	
	//---------------------------------------------
    // VARIABLES
    //---------------------------------------------

	protected final Road mRoad;
	protected int mCounter;
	protected final VertexBufferObjectManager mVbom;
	protected final ResourcesManager mResManager;
	protected LinkedList<TripCacheItem> mScript;
	
	//---------------------------------------------
    // CONSTRUCTOR
    //---------------------------------------------

	TripManager(Road pRoad, VertexBufferObjectManager pVbom, ResourcesManager pResManager)
	{
		mRoad=pRoad;
		mVbom=pVbom;
		mResManager=pResManager;
		mCounter=0;
		mScript=new LinkedList<TripCacheItem>();
	}
	
	//---------------------------------------------
    // PUBLIC METHODS
    //---------------------------------------------

	abstract public void initTripManager();
	
	public void update(int pDistance)
	{
		
		LinkedList<TripCacheItem> toRemove=new LinkedList<TripCacheItem>();
		
		for(int i=0; i<mScript.size();i++)
		{
			if(mScript.get(i).isAppear(pDistance))
			{
				mRoad.addObject(mScript.get(i).getObject(), "middle");
				mScript.get(i).getObject().setAlpha(0);
				toRemove.add(mScript.get(i));
			}
		}
		
		for(int i=0;i<toRemove.size();i++)
		{
			mScript.remove(toRemove.get(i));
		}
		
		toRemove.clear();
	}

}
