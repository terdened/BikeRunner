package com.brutal.game.tripmanager;

import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.brutal.engine.*;
import com.brutal.game.road.*;


public class TripManagerDesert extends TripManager {

	//---------------------------------------------
    // CONSTRUCTOR
    //---------------------------------------------

	public TripManagerDesert(Road pRoad, VertexBufferObjectManager pVbom,
			ResourcesManager pResManager) {
		super(pRoad, pVbom, pResManager);
	}
	
	//---------------------------------------------
    // OVERLOADED METHODS
    //---------------------------------------------

	@Override
	public void initTripManager()
	{
		this.mScript.add(new TripCacheItem(10, mResManager.road_background_region[3], mVbom));
		this.mScript.getLast().initObject(3000, -1500, 200, 1, 300, 300);
		
		this.mScript.add(new TripCacheItem(200, mResManager.road_background_region[1], mVbom));
		this.mScript.getLast().initObject(6000, 10000, 5000, 1, 6120, 12870);
		
		this.mScript.add(new TripCacheItem(2000, mResManager.road_background_region[1], mVbom));
		this.mScript.getLast().initObject(6000, -10000, 5000, 1, 6120, 12870);
		
		this.mScript.add(new TripCacheItem(3000, mResManager.road_background_region[2], mVbom));
		this.mScript.getLast().initObject(6000, -5000, 750, 1, 1500, 3000);
		
		this.mScript.add(new TripCacheItem(4000, mResManager.road_background_region[0], mVbom));
		this.mScript.getLast().initObject(6000, 10000, 4000, 1, 4120, 12870);
		
		this.mScript.add(new TripCacheItem(5000, mResManager.road_background_region[4], mVbom));
		this.mScript.getLast().initObject(6000, -10000, 5000, 1, 6120, 12870);
		
		this.mScript.add(new TripCacheItem(10000, mResManager.road_background_region[2], mVbom));
		this.mScript.getLast().initObject(6000, 5000, 750, 1, 1500, 3000);
		
		
	}	

}
