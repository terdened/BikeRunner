package com.example.wildhunt;

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
			RoadObject obj = objFactory.createSign(mVbom, mResManager);
			obj.initObject(600, obj.getX(), obj.getY(), 1, obj.getHeight(), obj.getWidth());
			mRoad.addObject(obj,"front");
			
		}
		
		if(mCounter%getCreationFreq(speed)==0)
		{
			
			RoadObject obj1 = objFactory.createBackgroundPart(mVbom, mResManager);
			obj1.initObject(600, obj1.getX(), obj1.getY(), 1, 156, 12800);
			mRoad.addObject(obj1,"back");
			
			RoadObject obj = objFactory.createRoadPart(mVbom, mResManager);
			obj.initObject(600, obj.getX(), obj.getY(), 1, obj.getHeight(), obj.getWidth());
			mRoad.addObject(obj,"middle");
		}
		
		
		
		if(mCounter>=1200)
		{
			mCounter=0;
		}
	}
}
