package com.example.bikerunner;

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
	
	public LinkedList<String> getBunnedLines()
	{
		LinkedList<String> result = new LinkedList<String>();
		
		for(int i=0;i<objectList.size();i++)
		{
			if(objectList.get(i).getClass()==Obstacle.class)
			{
				Obstacle temp = (Obstacle)objectList.get(i);
				if((temp.getZ()>-15-1800)&&(temp.getZ()<temp.getLength()-1800))
				{
					boolean inList=false;
					for(int j=0; j<result.size();j++)
					{
						if(result.get(j)==String.valueOf(temp.getLine()))
						{
							inList=true;
						}
					}
					
					if(!inList)
						result.add(String.valueOf(temp.getLine()));
				}
			}
		}
		
		return result;
	}
	
	public void resetGame()
	{
		LinkedList<RoadObject> toRemove = new LinkedList<RoadObject>();
		for(int i=0;i<objectList.size();i++)
		{
			if(objectList.get(i).getClass()==Obstacle.class)
			{
				toRemove.add(objectList.get(i));
			}
		}
		
		while(toRemove.size()>0)
		{
			this.removeObject(toRemove.getFirst());
			toRemove.removeFirst();
		}
	}

}
