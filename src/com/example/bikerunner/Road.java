package com.example.bikerunner;

import java.util.LinkedList;

import org.andengine.entity.Entity;

public class Road {
	
	//private
	private LinkedList<RoadObject> objectList;
	private LinkedList<Obstacle> obstacleList;
	public Entity pBackScene;
	public Entity pFrontScene;
	public Entity pMiddleScene;
	
	public Road()
	{
		pBackScene = new Entity();
		pFrontScene = new Entity();
		pMiddleScene = new Entity();
		objectList = new LinkedList<RoadObject>();
		obstacleList = new LinkedList<Obstacle>();
	}
	
	public void updateRoad(int speed)
	{
		for(int i=0; i<objectList.size();i++)
		{
			objectList.get(i).updateObject(speed);
			
			if(objectList.get(i).canDelete())
				this.removeObject(objectList.get(i));
		}
		
		for(int i=0; i<obstacleList.size();i++)
		{
			obstacleList.get(i).updateObject(speed);
			
			if(obstacleList.get(i).canDelete())
				this.removeObstacle(obstacleList.get(i));
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
			for(int i=0;i<pFrontScene.getChildCount();i++)
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
	
	public void addObstacle(Obstacle obj, String scene)
	{
		obstacleList.add(obj);
		
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
	
	public void removeObstacle(Obstacle obj)
	{
		obstacleList.remove(obj);
		
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
	
	public int[] getLinesHeight()
	{
		int[] result = new int[]{0,0,0};
		
		for(int i=0;i<obstacleList.size();i++)
		{
			if(obstacleList.get(i).getObstacleHeight()>0)
			{
				result[obstacleList.get(i).getLine()]=obstacleList.get(i).getObstacleHeight();
			}
		}
		
		return result;
	}
	
	
	
	public void resetGame()
	{
		LinkedList<Obstacle> toRemove = new LinkedList<Obstacle>();
		for(int i=0;i<obstacleList.size();i++)
		{
				toRemove.add(obstacleList.get(i));
		}
		
		while(toRemove.size()>0)
		{
			this.removeObstacle(toRemove.getFirst());
			toRemove.removeFirst();
		}
	}

}
