package com.example.bikerunner;

import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class WorldControllerEasy extends WorldController{

	public WorldControllerEasy(Road road, VertexBufferObjectManager vbom,
			ResourcesManager resManager) {
		super(road, vbom, resManager);
	}
	
	@Override
	public void updateWorld(int speed)
	{
		ObjectsFactory objFactory = new ObjectsFactory();
		mCounter += speed;
		
		if(mCounter%600==0)
		{
			RoadObject obj = objFactory.createSign(mVbom, mResManager);
			obj.setAlpha(0);
			mRoad.addObject(obj,"front");
			mRoad.sortChildren("front");
			
		}
		
		if(mCounter%600==0)
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
		
		if(mCounter%10==0)
		{			
			RoadObject obj = objFactory.createBlink(mVbom, mResManager);
			obj.initObject(-1000, obj.getX(), obj.getY(), 1, obj.getHeight(), obj.getWidth());
			obj.setAlpha(0);
			mRoad.addObject(obj,"middle");
		}
		
		if(mCounter>=1200)
		{
			mCounter=0;
		}
	}

}
