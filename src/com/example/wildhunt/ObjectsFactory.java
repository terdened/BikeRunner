package com.example.wildhunt;

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
	
		return obj; 
	}
}
