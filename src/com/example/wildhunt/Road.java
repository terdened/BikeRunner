package com.example.wildhunt;

import java.util.LinkedList;

import org.andengine.entity.Entity;

public class Road {
	
	//private
	private LinkedList<RoadObject> objectList;
	public Entity pBackScene;
	public Entity pFrontScene;
	public Entity pMiddleScene;
	
	public Road()
	{
		pBackScene = new Entity();
		pFrontScene = new Entity();
		pMiddleScene = new Entity();
		objectList = new LinkedList<RoadObject>();
	}
	
	public void updateRoad(int speed)
	{
		for(int i=0; i<objectList.size();i++)
		{
			objectList.get(i).updateObject(speed);
			
			if(objectList.get(i).canDelete())
				this.removeObject(objectList.get(i));
		}
		
		
	}
	
	public void sortChildren(String scene)
	{
		if(scene=="back")
		{
			this.pBackScene.sortChildren();
			for(int i=0;i<objectList.size();i++)
			{
				this.pBackScene.getChildByIndex(i).setZIndex(i);
			}
		}else
		if(scene=="front")
		{
			this.pFrontScene.sortChildren();
			for(int i=0;i<objectList.size();i++)
			{
				this.pFrontScene.getChildByIndex(i).setZIndex(i);
			}
		}else
		if(scene=="middle")
		{
			this.pMiddleScene.sortChildren();
			for(int i=0;i<objectList.size();i++)
			{
				this.pMiddleScene.getChildByIndex(i).setZIndex(i);
			}
		}
			
	}
	
	public void addObject(RoadObject obj, String scene)
	{
		objectList.add(obj);
		
		if(scene=="back")
		{
			pBackScene.attachChild(obj);
		}else
		if(scene=="front")
		{
			pFrontScene.attachChild(obj);
		}else
		if(scene=="middle")
		{
			pMiddleScene.attachChild(obj);
		}
	}
	
	public void removeObject(RoadObject obj)
	{
		objectList.remove(obj);
		
		try
		{
			pBackScene.detachChild(obj);
		}
		finally
		{
			
		}
		
		try
		{
			pMiddleScene.detachChild(obj);
		}
		finally
		{
			
		}
		
		try
		{
			pFrontScene.detachChild(obj);
		}
		finally
		{
			
		}
		
	}

}
