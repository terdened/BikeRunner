package com.brutal.game.worldmanager;

import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.brutal.engine.*;
import com.brutal.game.road.*;


public class WorldControllerBonus extends WorldController{

	//---------------------------------------------
    // CONSTRUCTOR
    //---------------------------------------------

	public WorldControllerBonus(Road pRoad, VertexBufferObjectManager pVbom,
			ResourcesManager pResManager) {
		super(pRoad, pVbom, pResManager);
	}
	
	//---------------------------------------------
    // OVERLOADED METHODS
    //---------------------------------------------
	
	@Override
	public void updateWorld(int pSpeed)
	{
		mCounter++;
		int complexity = this.getComplexityBySpeed(pSpeed);
		generateObstacle(complexity);
		generateCoin();
		generateObject();
		
		if(mCounter>=100)
			mCounter=0;
	}
	
	//---------------------------------------------
    // PRIVATE METHODS
    //---------------------------------------------

	private void generateObstacle(int pComplexity)
	{
		
	}
	
	private void generateCoin()
	{		
		if(mCounter%5==0)
		{
			ObstacleCoin obj = mObjFactory.createCoin(mVbom, mResManager);
			
			if(obj!=null)
			{
				obj.setAlpha(0);
				
				if(mRoad.getLinesHeight()[obj.getLine()]>0)
					obj.setRealHeight(mRoad.getLinesHeight()[obj.getLine()]);
				
				mRoad.addCoin(obj,"front");
				mRoad.sortChildren("front");
			}
			
		}
	}
	
	private void generateObject()
	{		
		if(mCounter%25==0)
		{			
			RoadObject obj = mObjFactory.createBackgroundObject(mVbom, mResManager);
			obj.initObject(1200, obj.getX(), obj.getY()*0.8f, 1, obj.getHeight()*2, obj.getWidth()*2);
			obj.setAlpha(0);
			mRoad.addObject(obj,"middle");
			mRoad.sortChildren("middle");
		}
		
		if(mCounter%10==0)
		{			
			RoadObject obj = mObjFactory.createBlink(mVbom, mResManager);
			obj.initObject(-1000, obj.getX(), obj.getY(), 1, obj.getHeight(), obj.getWidth());
			obj.setAlpha(0);
			mRoad.addObject(obj,"middle");
			mRoad.sortChildren("middle");
		}
	}
	
}
