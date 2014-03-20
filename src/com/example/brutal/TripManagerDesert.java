package com.example.brutal;

import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class TripManagerDesert extends TripManager {

	TripManagerDesert(Road road, VertexBufferObjectManager vbom,
			ResourcesManager resManager) {
		super(road, vbom, resManager);
	}
	
	@Override
	public void initTripManager()
	{
		this.mScript.add(new TripCacheItem(200, mResManager.road_background_region[0], mVbom));
		this.mScript.getLast().initObject(6000, 10000, 2120, 1, 2120, 6870);
		this.mScript.add(new TripCacheItem(2000, mResManager.road_background_region[0], mVbom));
		this.mScript.getLast().initObject(6000, 12000, 2120, 1, 2120, 6870);
		this.mScript.add(new TripCacheItem(4000, mResManager.road_background_region[0], mVbom));
		this.mScript.getLast().initObject(6000, 14000, 2120, 1, 2120, 6870);
	}	

}
