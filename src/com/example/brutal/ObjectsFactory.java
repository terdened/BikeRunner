package com.example.brutal;

import java.util.LinkedList;

import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class ObjectsFactory {
	
	private int pLastLine;
	private int[] pCoinLessTimes;
	private int pCoinLessCounter;
	
	public ObjectsFactory(ResourcesManager resManager)
	{
		pLastLine=(int) (Math.random()*3);
		pCoinLessTimes = new int[10];
		pCoinLessTimes[0]=2;
		pCoinLessTimes[1]=4;
		pCoinLessTimes[2]=6;
		pCoinLessTimes[3]=8;
		pCoinLessTimes[4]=10;
		pCoinLessTimes[5]=12;
		pCoinLessTimes[6]=14;
		pCoinLessTimes[7]=16;
		pCoinLessTimes[8]=18;
		pCoinLessTimes[9]=20;
		pCoinLessCounter=0;
	}

	public RoadObject createBlink(VertexBufferObjectManager vbom, ResourcesManager resManager)
	{
		int rand=(int)(Math.random()*resManager.road_background_blink_region.length);
		RoadObject result= new RoadObject((float)Math.random()*1000-500, 40, resManager.road_background_blink_region[rand], vbom);
		
		return result;
	}
	
	public RoadObject createBackgroundObject(VertexBufferObjectManager vbom, ResourcesManager resManager)
	{
		int sign=1;
		if(Math.random()*2>1)
		{
			sign=-1;
		}
		
		int rand=(int)(Math.random()*resManager.road_background_object.length);
		RoadObject result= new RoadObject(((float)Math.random()*5000+1000)*sign, 40, resManager.road_background_object[rand], vbom);
		
		return result;
	}
	
	public RoadObject createBackgroundPart(VertexBufferObjectManager vbom, ResourcesManager resManager)
	{
		RoadObject obj = new RoadObject(0, 156, resManager.road_background_fon_region, vbom);
		return obj;
	}
	
	public RoadObject createSign(VertexBufferObjectManager vbom, ResourcesManager resManager)
	{
		RoadObject obj;
		
		if(Math.random()*2>1)
			obj = new RoadObject(700, 480, resManager.sign_speed_region, vbom);
		else 
			obj = new RoadObject(-700, 480, resManager.sign_speed_region, vbom);
	
		obj.initObject(600, obj.getX(), obj.getY(), 1, obj.getHeight(), obj.getWidth());
		return obj; 
	}
	
	public Obstacle createObstacle(VertexBufferObjectManager vbom, ResourcesManager resManager)
	{
		Obstacle obj;
		
		
		float randomValue=(float)Math.random()*12f;
		
		if(randomValue>8)
		{
			obj = new ObstacleTramp(-380, 128, resManager.tramp_a_region, vbom);
			obj.initLine(0);
		}
		else
		if(randomValue>7)
		{
			obj = new ObstacleTramp(0, 128, resManager.tramp_a_region, vbom);
			obj.initLine(1);
		}
		else
		if(randomValue>6)
		{
			obj = new ObstacleTramp(380, 128, resManager.tramp_a_region, vbom);
			obj.initLine(2);
		}
		else
		if(randomValue>5)
		{
			obj = new ObstacleBus(-380, 128, resManager.bus_region, vbom);
			obj.initLine(0);
		}
		else
		if(randomValue>4)
		{
			obj = new ObstacleBus(0, 128, resManager.bus_region, vbom);
			obj.initLine(1);
		}
		else 
		if(randomValue>3)
		{
			obj = new ObstacleBus(380, 128, resManager.bus_region, vbom);
			obj.initLine(2);
		}
		else 
		if(randomValue>2)
		{
			obj = new ObstacleFency(-380, 128, resManager.fence_region, vbom);
			obj.initLine(0);
		}
		else 
		if(randomValue>1)
		{
			obj = new ObstacleFency(0, 128, resManager.fence_region, vbom);
			obj.initLine(1);
		}
		else
		{
			obj = new ObstacleFency(380, 128, resManager.fence_region, vbom);
			obj.initLine(2);
		}
		
		return obj; 
	}
	
	public Obstacle[] createTemplate(VertexBufferObjectManager vbom, ResourcesManager resManager,
			LinkedList<String> ids)
	{
		
		int randomValue=(int)((float)Math.random()*ids.size());
		
		Template template =  new Template(Integer.parseInt(ids.get(randomValue)),resManager,vbom);
		Obstacle[] obj=new Obstacle[template.objects.size()];
		
		for(int i=0;i<template.objects.size();i++)
		{
			obj[i] = template.objects.get(i);
		}
		
		return obj; 
	}
	
	private void changeCoinsLine()
	{
		if(Math.random()*20>18)
		{
			if(pLastLine==0)
				pLastLine++;
			else
			if(pLastLine==2)
				pLastLine--;
			else
			{
				if(Math.random()*2>1)
				{
					pLastLine++;
				}
				else
				{
					pLastLine--;
				}
			}
		}
	}
	
	public ObstacleCoin createCoin(VertexBufferObjectManager vbom, ResourcesManager resManager)
	{
		
		if(pCoinLessCounter<=0)
		{
			pCoinLessCounter++;
			ObstacleCoin obj;
			changeCoinsLine();
			
			if(pCoinLessCounter>0)
			{
				pCoinLessCounter=pCoinLessTimes[(int) (pCoinLessTimes.length*Math.random())];
			}
			
			
			if(pLastLine==0)
			{
				obj = new ObstacleCoin(-380, 128, resManager.coin_region, vbom);
				obj.initObject(0, 100, 600, obj.getY(), 1, obj.getHeight()*3, obj.getWidth()*3, 50);
			}
			else
			if(pLastLine==1)
			{
				obj = new ObstacleCoin(0, 128, resManager.coin_region, vbom);
				obj.initObject(1, 100, 600, obj.getY(), 1, obj.getHeight()*3, obj.getWidth()*3, 50);
			}
			else
			{
				obj = new ObstacleCoin(380, 128, resManager.coin_region, vbom);
				obj.initObject(2, 100, 600, obj.getY(), 1, obj.getHeight()*3, obj.getWidth()*3, 50);
			}
			
			return obj;
		}
		
		pCoinLessCounter--;
		if(pCoinLessCounter<=0)
		{
			pCoinLessCounter=-pCoinLessTimes[(int) (pCoinLessTimes.length*Math.random())];
		}
		
		return null;
	}
	
	
	/*public Obstacle createLift(VertexBufferObjectManager vbom, ResourcesManager resManager)
	{
		Obstacle obj= new Obstacle(-380, 128, resManager.bus_region[0], vbom);
		obj.initObject(0, 0, 400, obj.getY(), 1, obj.getHeight(), obj.getWidth(), 110);
		obj.setDislocation(50);
		
		return obj; 
	}*/
}



