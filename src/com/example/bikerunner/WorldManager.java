package com.example.bikerunner;

import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class WorldManager {
	
	private final Road mRoad;
	private int mCounter;
	private final VertexBufferObjectManager mVbom;
	private final ResourcesManager mResManager;
	
	public WorldManager(Road road, VertexBufferObjectManager vbom, ResourcesManager resManager)
	{
		mRoad = road;
		mCounter=0;
		mVbom = vbom;
		mResManager = resManager;
	}
	
	private int getCreationFreq(int speed)
	{
		if(speed<=10)
			return 60;
		else
		if(speed<=11)
			return 55;
		if(speed<=11)
			return 48;
		if(speed<=13)
			return 39;
		if(speed<=14)
			return 28;
		else
			return 20;
	}
	
	public void updateWorld(int speed)
	{
		ObjectsFactory objFactory = new ObjectsFactory();
		mCounter += speed;
		
		
		
		if(mCounter%600==0)
		{
			if(Math.random()*2>1)
			{
				Obstacle obj1 = objFactory.createLift(mVbom, mResManager);
				obj1.setAlpha(0);
				obj1.setAction("jump");
				mRoad.addObstacle(obj1,"front");
				mRoad.sortChildren("front");
			}
			
			Obstacle obj = objFactory.createObstacle(mVbom, mResManager);
			obj.setAlpha(0);
			obj.setAction("crash");
			mRoad.addObstacle(obj,"front");
			mRoad.sortChildren("front");
		}
		
		if(mCounter%600==0)
		{
			RoadObject obj = objFactory.createSign(mVbom, mResManager);
			obj.setAlpha(0);
			mRoad.addObject(obj,"front");
			mRoad.sortChildren("front");
			
		}
		
		/*if(mCounter%getCreationFreq(speed)==0)
		{			
			RoadObject obj = objFactory.createRoadPart(mVbom, mResManager);
			obj.initObject(600, obj.getX(), obj.getY(), 1, obj.getHeight(), obj.getWidth());
			mRoad.addObject(obj,"middle");
		}
		*/
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
