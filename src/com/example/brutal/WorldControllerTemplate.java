package com.example.brutal;

import java.util.LinkedList;

import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class WorldControllerTemplate extends WorldController{

	public WorldControllerTemplate(Road road, VertexBufferObjectManager vbom,
			ResourcesManager resManager) {
		super(road, vbom, resManager);
	}
	
	private int obstacleCounter=0;
	
	private void generateObstacle(int complexity, int speed)
	{
		int maxObstacleCounter=0;
		
		if(complexity==1)
		{
			maxObstacleCounter=90;
		}
		else
		if(complexity==2)	
		{
			maxObstacleCounter=70;
		}
		else
		{
			maxObstacleCounter=50;
		}
		
		obstacleCounter++;
		
		if(obstacleCounter==maxObstacleCounter)
		{
			LinkedList<String> ids = new LinkedList<String>();
			
			if(speed<19)
			{
				ids.add("1");
				ids.add("2");
				ids.add("3");
				ids.add("4");
				ids.add("5");
			}
			else
			if(speed<24)
			{
				ids.add("1");
				ids.add("2");
				ids.add("3");
				ids.add("4");
				ids.add("5");
				ids.add("9");
				ids.add("10");
				ids.add("11");
				ids.add("20");
				ids.add("21");
				ids.add("22");
			}
			else
			if(speed<29)
			{
				ids.add("1");
				ids.add("2");
				ids.add("3");
				ids.add("7");
				ids.add("8");
				ids.add("9");
				ids.add("10");
				ids.add("11");
				ids.add("17");
				ids.add("18");
				ids.add("19");
				ids.add("20");
				ids.add("21");
				ids.add("22");
			}
			else
			{
				ids.add("1");
				ids.add("2");
				ids.add("3");
				ids.add("4");
				ids.add("5");
				ids.add("6");
				ids.add("7");
				ids.add("8");
				ids.add("9");
				ids.add("10");
				ids.add("11");
				ids.add("20");
				ids.add("21");
				ids.add("22");
			}
			
			
			Obstacle[] obj = objFactory.createTemplate(mVbom, mResManager, ids);
			for(int i=0;i<obj.length;i++)
			{
				obj[i].setAlpha(0);
				obj[i].setAction("crash");
				mRoad.addObstacle(obj[i],"front");
			}
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
		int complexity =this.getComplexityBySpeed(speed);
		
		generateObstacle(complexity,speed);
		generateCoin();
		generateObject();
		
		if(mCounter>=100)
			mCounter=0;
	}

}
