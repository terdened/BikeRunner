package com.brutal.game.worldmanager;

import java.util.LinkedList;

import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.brutal.engine.*;
import com.brutal.game.road.*;


public class ObjectsFactory {
	
	//---------------------------------------------
    // VARIABLES
    //---------------------------------------------
	
	private int mLastLine;
	private int[] mCoinLessTimes;
	private int mCoinLessCounter;
	
	//---------------------------------------------
    // CONSTRUCTOR
    //---------------------------------------------
	
	public ObjectsFactory()
	{
		mLastLine=(int) (Math.random()*3);
		mCoinLessTimes = new int[10];
		mCoinLessTimes[0]=2;
		mCoinLessTimes[1]=4;
		mCoinLessTimes[2]=6;
		mCoinLessTimes[3]=8;
		mCoinLessTimes[4]=10;
		mCoinLessTimes[5]=12;
		mCoinLessTimes[6]=14;
		mCoinLessTimes[7]=16;
		mCoinLessTimes[8]=18;
		mCoinLessTimes[9]=20;
		mCoinLessCounter=0;
	}

	//---------------------------------------------
    // PUBLIC METHODS
    //---------------------------------------------
	
	public RoadObject createBlink(VertexBufferObjectManager pVbom, ResourcesManager pResManager)
	{
		int rand=(int)(Math.random()*pResManager.road_background_blink_region.length);
		RoadObject result= new RoadObject((float)Math.random()*1000-500, 40, pResManager.road_background_blink_region[rand], pVbom);
		
		return result;
	}
	
	public RoadObject createBackgroundObject(VertexBufferObjectManager pVbom, ResourcesManager pResManager)
	{
		int sign=1;
		if(Math.random()*2>1)
		{
			sign=-1;
		}
		
		int rand=(int)(Math.random()*pResManager.road_background_object.length);
		RoadObject result= new RoadObject(((float)Math.random()*5000+1000)*sign, 40, pResManager.road_background_object[rand], pVbom);
		
		return result;
	}
	
	public RoadObject createBackgroundPart(VertexBufferObjectManager pVbom, ResourcesManager pResManager)
	{
		RoadObject obj = new RoadObject(0, 156, pResManager.road_background_fon_region, pVbom);
		return obj;
	}
	
	public RoadObject createSign(VertexBufferObjectManager pVbom, ResourcesManager pResManager)
	{
		RoadObject obj;
		
		if(Math.random()*2>1)
			obj = new RoadObject(700, 480, pResManager.sign_speed_region, pVbom);
		else 
			obj = new RoadObject(-700, 480, pResManager.sign_speed_region, pVbom);
	
		obj.initObject(600, obj.getX(), obj.getY(), 1, obj.getHeight(), obj.getWidth());
		return obj; 
	}
	
	public Obstacle createObstacle(VertexBufferObjectManager pVbom, ResourcesManager pResManager)
	{
		Obstacle obj;
		
		
		float randomValue=(float)Math.random()*12f;
		
		if(randomValue>8)
		{
			obj = new ObstacleTramp(-380, 128, pResManager.tramp_a_region, pVbom);
			obj.initLine(0);
		}
		else
		if(randomValue>7)
		{
			obj = new ObstacleTramp(0, 128, pResManager.tramp_a_region, pVbom);
			obj.initLine(1);
		}
		else
		if(randomValue>6)
		{
			obj = new ObstacleTramp(380, 128, pResManager.tramp_a_region, pVbom);
			obj.initLine(2);
		}
		else
		if(randomValue>5)
		{
			obj = new ObstacleBus(-380, 128, pResManager.bus_region, pVbom);
			obj.initLine(0);
		}
		else
		if(randomValue>4)
		{
			obj = new ObstacleBus(0, 128, pResManager.bus_region, pVbom);
			obj.initLine(1);
		}
		else 
		if(randomValue>3)
		{
			obj = new ObstacleBus(380, 128, pResManager.bus_region, pVbom);
			obj.initLine(2);
		}
		else 
		if(randomValue>2)
		{
			obj = new ObstacleFency(-380, 128, pResManager.fence_region, pVbom);
			obj.initLine(0);
		}
		else 
		if(randomValue>1)
		{
			obj = new ObstacleFency(0, 128, pResManager.fence_region, pVbom);
			obj.initLine(1);
		}
		else
		{
			obj = new ObstacleFency(380, 128, pResManager.fence_region, pVbom);
			obj.initLine(2);
		}
		
		return obj; 
	}
	
	public Obstacle[] createTemplate(VertexBufferObjectManager pVbom, ResourcesManager pResManager,
			LinkedList<String> pIds)
	{
		
		int randomValue=(int)((float)Math.random()*pIds.size());
		
		Template template =  new Template(Integer.parseInt(pIds.get(randomValue)),pResManager,pVbom);
		Obstacle[] obj=new Obstacle[template.mObjects.size()];
		
		for(int i=0;i<template.mObjects.size();i++)
		{
			obj[i] = template.mObjects.get(i);
		}
		
		return obj; 
	}
	
	private void changeCoinsLine()
	{
		if(Math.random()*20>18)
		{
			if(mLastLine==0)
				mLastLine++;
			else
			if(mLastLine==2)
				mLastLine--;
			else
			{
				if(Math.random()*2>1)
				{
					mLastLine++;
				}
				else
				{
					mLastLine--;
				}
			}
		}
	}
	
	public ObstacleCoin createCoin(VertexBufferObjectManager pVbom, ResourcesManager pResManager)
	{
		
		if(mCoinLessCounter<=0)
		{
			mCoinLessCounter++;
			ObstacleCoin obj;
			changeCoinsLine();
			
			if(mCoinLessCounter>0)
			{
				mCoinLessCounter=mCoinLessTimes[(int) (mCoinLessTimes.length*Math.random())];
			}
			
			
			if(mLastLine==0)
			{
				obj = new ObstacleCoin(-380, 128, pResManager.coin_region, pVbom);
				obj.initObject(0, 100, 600, obj.getY(), 1, obj.getHeight()*3, obj.getWidth()*3, 50);
			}
			else
			if(mLastLine==1)
			{
				obj = new ObstacleCoin(0, 128, pResManager.coin_region, pVbom);
				obj.initObject(1, 100, 600, obj.getY(), 1, obj.getHeight()*3, obj.getWidth()*3, 50);
			}
			else
			{
				obj = new ObstacleCoin(380, 128, pResManager.coin_region, pVbom);
				obj.initObject(2, 100, 600, obj.getY(), 1, obj.getHeight()*3, obj.getWidth()*3, 50);
			}
			
			return obj;
		}
		
		mCoinLessCounter--;
		if(mCoinLessCounter<=0)
		{
			mCoinLessCounter=-mCoinLessTimes[(int) (mCoinLessTimes.length*Math.random())];
		}
		
		return null;
	}
}



