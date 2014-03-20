package com.example.brutal;

import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class ObjectsFactory {
	
	public RoadObject createGrass(VertexBufferObjectManager vbom, ResourcesManager resManager)
	{
		int sign=1;
		if(Math.random()*2>1)
		{
			sign=-1;
		}
		return new RoadObject(((float)Math.random()*5000+1000)*sign, 40, resManager.road_background_desert_a_region, vbom);
	}
	
	public RoadObject createBlink(VertexBufferObjectManager vbom, ResourcesManager resManager)
	{
		return new RoadObject((float)Math.random()*1000-500, 40, resManager.road_background_blink_region, vbom);
	}
	
	public RoadObject createBush(VertexBufferObjectManager vbom, ResourcesManager resManager)
	{
		int sign=1;
		if(Math.random()*2>1)
		{
			sign=-1;
		}
		return new RoadObject(((float)Math.random()*5000+1000)*sign, 40, resManager.road_background_desert_b_region, vbom);
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
			obj.initObject(0, 200, 600, obj.getY(), 1, obj.getHeight(), obj.getWidth(), 250);
			obj.setDislocation(50);
			obj.setSpeed(5);
		}
		else
		if(randomValue>7)
		{
			obj = new ObstacleTramp(0, 128, resManager.tramp_a_region, vbom);
			obj.initObject(1, 200, 600, obj.getY(), 1, obj.getHeight(), obj.getWidth(), 250);
			obj.setDislocation(50);
		}
		else
		if(randomValue>6)
		{
			obj = new ObstacleTramp(380, 128, resManager.tramp_a_region, vbom);
			obj.initObject(2, 200, 600, obj.getY(), 1, obj.getHeight(), obj.getWidth(), 250);
			obj.setDislocation(50);
			obj.setSpeed(-5);
		}
		else
		if(randomValue>5)
		{
			obj = new Obstacle(-380, 128, resManager.bus_a_region, vbom);
			obj.initObject(0, 200, 600, obj.getY(), 1, obj.getHeight(), obj.getWidth(), 250);
			obj.setDislocation(50);
		}
		else
		if(randomValue>4)
		{
			obj = new Obstacle(0, 128, resManager.bus_a_region, vbom);
			obj.initObject(1, 200, 600, obj.getY(), 1, obj.getHeight(), obj.getWidth(), 250);
			
		}
		else 
		if(randomValue>3)
		{
			obj = new Obstacle(380, 128, resManager.bus_a_region, vbom);
			obj.initObject(2, 200, 600, obj.getY(), 1, obj.getHeight(), obj.getWidth(), 250);
			obj.setDislocation(-50);
		}
		else 
		if(randomValue>2)
		{
			obj = new Obstacle(-380, 128, resManager.fence_region, vbom);
			obj.initObject(0, 100, 600, obj.getY(), 1, obj.getHeight(), obj.getWidth(), 50);
		}
		else 
		if(randomValue>1)
		{
			obj = new Obstacle(0, 128, resManager.fence_region, vbom);
			obj.initObject(1, 100, 600, obj.getY(), 1, obj.getHeight(), obj.getWidth(), 50);
		}
		else
		{
			obj = new Obstacle(380, 128, resManager.fence_region, vbom);
			obj.initObject(2, 100, 600, obj.getY(), 1, obj.getHeight(), obj.getWidth(), 50);
		}
		
		return obj; 
	}
	
	public ObstacleCoin createCoin(VertexBufferObjectManager vbom, ResourcesManager resManager)
	{
		ObstacleCoin obj;
		
		float randomValue=(float)Math.random()*3f;
		
		if(randomValue>2)
		{
			obj = new ObstacleCoin(-380, 128, resManager.coin_region, vbom);
			obj.initObject(0, 100, 600, obj.getY(), 1, obj.getHeight()*3, obj.getWidth()*3, 50);
		}
		else
		if(randomValue>1)
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
	
	
	public Obstacle createLift(VertexBufferObjectManager vbom, ResourcesManager resManager)
	{
		Obstacle obj= new Obstacle(-380, 128, resManager.bus_a_region, vbom);
		obj.initObject(0, 0, 400, obj.getY(), 1, obj.getHeight(), obj.getWidth(), 110);
		obj.setDislocation(50);
		
		return obj; 
	}
}


