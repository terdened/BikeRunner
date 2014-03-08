package com.example.bikerunner;

import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class ObjectsFactory {
	
	public RoadObject createRoadPart(VertexBufferObjectManager vbom, ResourcesManager resManager)
	{
		return new RoadObject(0, 156, resManager.road_background_a_region, vbom);
	}
	
	public RoadObject createBackgroundPart(VertexBufferObjectManager vbom, ResourcesManager resManager)
	{
		return new RoadObject(0, 156, resManager.road_background_desert_region, vbom);
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
		float randomValue=(float)Math.random()*3f;
		
		if(randomValue>2)
		{
			obj = new Obstacle(-380, 128, resManager.fence_region, vbom);
			obj.initObject(0, 600, obj.getY(), 1, obj.getHeight(), obj.getWidth(), 10);
		}
		else 
		if(randomValue>1)
		{
			obj = new Obstacle(0, 128, resManager.fence_region, vbom);
			obj.initObject(1, 600, obj.getY(), 1, obj.getHeight(), obj.getWidth(), 10);
		}
		else 
		{
			obj = new Obstacle(380, 128, resManager.fence_region, vbom);
			obj.initObject(2, 600, obj.getY(), 1, obj.getHeight(), obj.getWidth(), 10);
		}
		
		return obj; 
	}
}
