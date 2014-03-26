package com.example.brutal;

import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class WorldControllerMedium extends WorldController{

	public WorldControllerMedium(Road road, VertexBufferObjectManager vbom,
			ResourcesManager resManager) {
		super(road, vbom, resManager);
	}
	
	@Override
	public void updateWorld(int speed)
	{
		ObjectsFactory objFactory = new ObjectsFactory();
		mCounter += speed;
		
		if(mCounter%560==0)
		{
			RoadObject obj = objFactory.createSign(mVbom, mResManager);
			obj.setAlpha(0);
			mRoad.addObject(obj,"middle");
			
		}
		
		if(mCounter%100==0)
		{
			ObstacleCoin obj = objFactory.createCoin(mVbom, mResManager);
			obj.setAlpha(0);
			mRoad.addCoin(obj,"front");
			mRoad.sortChildren("front");
			
		}
		
		if(mCounter%560==0)
		{
			Obstacle obj = objFactory.createObstacle(mVbom, mResManager);
			obj.setAlpha(0);
			obj.setAction("crash");
			mRoad.addObstacle(obj,"front");
			mRoad.sortChildren("front");
		}
		
		if(mCounter%20==0)
		{			
			RoadObject obj = objFactory.createGrass(mVbom, mResManager);
			obj.initObject(-1000, obj.getX(), obj.getY(), 1, obj.getHeight(), obj.getWidth());
			obj.setAlpha(0);
			mRoad.addObject(obj,"middle");
		}
		
		if(mCounter%50==0)
		{			
			RoadObject obj = objFactory.createBush(mVbom, mResManager);
			obj.initObject(-0, obj.getX(), obj.getY(), 1, obj.getHeight(), obj.getWidth());
			obj.setAlpha(0);
			mRoad.addObject(obj,"middle");
		}
		
		if(mCounter%100==0)
		{			
			RoadObject obj = objFactory.createBlink(mVbom, mResManager);
			obj.initObject(-1000, obj.getX(), obj.getY(), 1, obj.getHeight(), obj.getWidth());
			obj.setAlpha(0);
			mRoad.addObject(obj,"middle");
		}
		
		if(mCounter>=1120)
		{
			mCounter=0;
		}
	}

}
