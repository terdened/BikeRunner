package com.example.brutal;

import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class WorldControllerRandom extends WorldController{

	public WorldControllerRandom(Road road, VertexBufferObjectManager vbom,
			ResourcesManager resManager) {
		super(road, vbom, resManager);
	}
	
	private int obstacleCounter=0;
	
	private void generateObstacle(int complexity)
	{
		int maxObstacleCounter=0;
		
		if(complexity==1)
		{
			maxObstacleCounter=50;
		}
		else
		if(complexity==2)	
		{
			maxObstacleCounter=30;
		}else
		{
			maxObstacleCounter=20;
		}
		
		obstacleCounter++;
		
		if(obstacleCounter==maxObstacleCounter)
		{
			Obstacle obj = objFactory.createObstacle(mVbom, mResManager);
			
			obj.setAlpha(0);
			obj.setAction("crash");
			mRoad.addObstacle(obj,"front");
			
			mRoad.sortChildren("front");
		}
		
		if(obstacleCounter==maxObstacleCounter)
		{
			obstacleCounter=0;
		}
	}
	
	private void generateCoin()
	{		
		if(mCounter%20==0)
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
			obj.initObject(1200, obj.getX(), obj.getY()*0.8f, 1, obj.getHeight()*2, obj.getWidth()*2);
			obj.setAlpha(0);
			mRoad.addObject(obj,"middle");
			mRoad.sortChildren("middle");
		}
		
		if(mCounter%10==0)
		{			
			RoadObject obj = objFactory.createBlink(mVbom, mResManager);
			obj.initObject(-1000, obj.getX(), obj.getY(), 1, obj.getHeight(), obj.getWidth());
			obj.setAlpha(0);
			mRoad.addObject(obj,"middle");
			mRoad.sortChildren("middle");
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
