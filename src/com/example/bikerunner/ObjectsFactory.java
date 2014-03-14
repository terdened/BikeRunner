package com.example.bikerunner;

import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class ObjectsFactory {
	
	public RoadObject createRoadPart(VertexBufferObjectManager vbom, ResourcesManager resManager)
	{
		return new RoadObject(0, 156, resManager.road_background_a_region, vbom);
	}
	
	public RoadObject createGrass(VertexBufferObjectManager vbom, ResourcesManager resManager)
	{
		int sign=1;
		if(Math.random()*2>1)
		{
			sign=-1;
		}
		return new RoadObject(((float)Math.random()*5000+1000)*sign, 40, resManager.road_background_desert_grass_region, vbom);
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
		return new RoadObject(((float)Math.random()*5000+1000)*sign, 40, resManager.road_background_desert_bush_region, vbom);
	}
	
	public RoadObject createBackgroundPart(VertexBufferObjectManager vbom, ResourcesManager resManager)
	{
		RoadObject obj = new RoadObject(0, 156, resManager.road_background_desert_region, vbom);
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
		
		
		float randomValue=(float)Math.random()*6f;
		
		if(randomValue>5)
		{
			obj = new Obstacle(-380, 128, resManager.bus_a_region, vbom);
			obj.initObject(0, 600, obj.getY(), 1, obj.getHeight(), obj.getWidth(), 110);
			obj.initCollision(new int[][]{{1,0,0}, 
										  {1,0,0},
										  {0,0,0}});
			obj.setDislocation(50);
		}
		else
		if(randomValue>4)
		{
			obj = new Obstacle(-380, 128, resManager.bus_a_region, vbom);
			obj.initObject(1, 600, obj.getY(), 1, obj.getHeight(), obj.getWidth(), 110);
			obj.initCollision(new int[][]{{0,1,0}, 
										  {0,1,0},
										  {0,0,0}});
			
		}
		else 
		if(randomValue>3)
		{
			obj = new Obstacle(0, 128, resManager.bus_a_region, vbom);
			obj.initObject(2, 600, obj.getY(), 1, obj.getHeight(), obj.getWidth(), 110);
			obj.initCollision(new int[][]{{0,0,1}, 
										  {0,0,1},
										  {0,0,0}});
			obj.setDislocation(-50);
		}
		else 
		if(randomValue>2)
		{
			obj = new Obstacle(-380, 128, resManager.fence_region, vbom);
			obj.initObject(0, 600, obj.getY(), 1, obj.getHeight(), obj.getWidth(), 50);
			obj.initCollision(new int[][]{{1,0,0}, 
										  {0,0,0},
										  {0,0,0}});
		}
		else 
		if(randomValue>1)
		{
			obj = new Obstacle(0, 128, resManager.fence_region, vbom);
			obj.initObject(1, 600, obj.getY(), 1, obj.getHeight(), obj.getWidth(), 50);
			obj.initCollision(new int[][]{{0,1,0}, 
										  {0,0,0},
										  {0,0,0}});
		}
		else
		{
			obj = new Obstacle(380, 128, resManager.fence_region, vbom);
			obj.initObject(2, 600, obj.getY(), 1, obj.getHeight(), obj.getWidth(), 50);
			obj.initCollision(new int[][]{{0,0,1}, 
										  {0,0,0},
										  {0,0,0}});
		}
		
		return obj; 
	}
	
	public Obstacle createLift(VertexBufferObjectManager vbom, ResourcesManager resManager)
	{
		Obstacle obj= new Obstacle(-380, 128, resManager.bus_a_region, vbom);
		obj.initObject(0, 400, obj.getY(), 1, obj.getHeight(), obj.getWidth(), 110);
		obj.initCollision(new int[][]{{1,0,0}, 
									  {1,0,0},
									  {0,0,0}});
		obj.setDislocation(50);
		
		return obj; 
	}
}



