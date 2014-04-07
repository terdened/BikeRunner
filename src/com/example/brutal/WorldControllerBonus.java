package com.example.brutal;

import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class WorldControllerBonus extends WorldController{

	public WorldControllerBonus(Road road, VertexBufferObjectManager vbom,
			ResourcesManager resManager) {
		super(road, vbom, resManager);
	}
	
	private int obstacleCounter=0;
	
	private void generateObstacle(int complexity)
	{
		
	}
	
	private void generateCoin()
	{		
		if(mCounter%5==0)
		{
			ObstacleCoin obj = objFactory.createCoin(mVbom, mResManager);
			
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
			RoadObject obj = objFactory.createBackgroundObject(mVbom, mResManager);
			obj.initObject(-0, obj.getX(), obj.getY(), 1, obj.getHeight(), obj.getWidth());
			obj.setAlpha(0);
			mRoad.addObject(obj,"middle");
		}
		
		if(mCounter%10==0)
		{			
			RoadObject obj = objFactory.createBlink(mVbom, mResManager);
			obj.initObject(-1000, obj.getX(), obj.getY(), 1, obj.getHeight(), obj.getWidth());
			obj.setAlpha(0);
			mRoad.addObject(obj,"middle");
		}
	}
	
	@Override
	public void updateWorld(int speed)
	{
		mCounter++;
		int complexity = this.getComplexityBySpeed(speed);
		generateObstacle(complexity);
		generateCoin();
		generateObject();
		
		if(mCounter>=100)
			mCounter=0;
	}

}
