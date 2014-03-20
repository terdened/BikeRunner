package com.example.brutal;

import java.util.LinkedList;

import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class TripManager {
	
	protected final Road mRoad;
	protected int mCounter;
	protected final VertexBufferObjectManager mVbom;
	protected final ResourcesManager mResManager;
	protected LinkedList<TripCacheItem> mScript;
	
	TripManager(Road road, VertexBufferObjectManager vbom, ResourcesManager resManager)
	{
		mRoad=road;
		mVbom=vbom;
		mResManager=resManager;
		mCounter=0;
		mScript=new LinkedList<TripCacheItem>();
	}
	
	public void initTripManager()
	{
	}
	
	public void update(int distance)
	{
		
		LinkedList<TripCacheItem> toRemove=new LinkedList<TripCacheItem>();
		
		for(int i=0; i<mScript.size();i++)
		{
			if(mScript.get(i).isAppear(distance))
			{
				mRoad.addObject(mScript.get(i).getObject(), "back");
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
