package com.brutal;

import java.util.LinkedList;

import org.andengine.entity.Entity;
import org.andengine.entity.IEntityMatcher;

public class Road {
	
	//private
	private LinkedList<RoadObject> objectList;
	private LinkedList<Obstacle> obstacleList;
	private LinkedList<ObstacleCoin> coinsList;
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
		coinsList = new LinkedList<ObstacleCoin>();
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
		
		for(int i=0; i<coinsList.size();i++)
		{
			coinsList.get(i).updateObject(speed);
			
			if(coinsList.get(i).canDelete())
				this.removeCoin(coinsList.get(i));
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
			sortObstacles();
			this.pFrontScene.sortChildren();
		}else
		if(scene=="middle")
		{
			sortObjects();
			this.pMiddleScene.sortChildren();
		}
			
	}
	
	public void sortObstacles()
	{
		int lastValue=-10000;
		
		for(int i=0;i<this.pFrontScene.getChildCount();i++)
		{
			Obstacle temp =(Obstacle)(pFrontScene.getChildByIndex(i));
			lastValue=(int)temp.getZ();
			this.pFrontScene.getChildByIndex(i).setZIndex(10000-lastValue);
		}
			
	}
	
	public void sortObjects()
	{
		
		for(int i=0;i<this.pMiddleScene.getChildCount();i++)
		{
			RoadObject temp =(RoadObject)(pMiddleScene.getChildByIndex(i));
			int depth=(int)temp.getZ();
			this.pMiddleScene.getChildByIndex(i).setZIndex(10000-depth);
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
	
	
	public void addCoin(ObstacleCoin obj, String scene)
	{
		coinsList.add(obj);
		
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
	
	public void deleteCoin(ObstacleCoin obj)
	{
		for(int i=0;i<coinsList.size();i++)
		{
			if(coinsList.get(i)==obj)
				coinsList.get(i).delete();
		}
	}
	
	public void removeCoin(Obstacle obj)
	{
		coinsList.remove(obj);
		
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
	
	public int[] getLinesHeightByPosition(int position)
	{
		int[] result = new int[]{0,0,0};
		
		for(int i=0;i<obstacleList.size();i++)
		{
			if(obstacleList.get(i).getObstacleHeightByPosition(position)>0)
			{
				result[obstacleList.get(i).getLine()]=obstacleList.get(i).getObstacleHeightByPosition(position);
			}
		}
		
		return result;
	}
	
	public int[] getLinesHeightByDeep(float deep)
	{
		int[] result = new int[]{0,0,0};
		
		for(int i=0;i<obstacleList.size();i++)
		{
			if(obstacleList.get(i).getObstacleHeightByDeep(deep)>0)
			{
				result[obstacleList.get(i).getLine()]=obstacleList.get(i).getObstacleHeightByDeep(deep);
			}
		}
		
		return result;
	}
	
	public ObstacleCoin[] getLineCoins()
	{
		ObstacleCoin[] result = new ObstacleCoin[]{null,null,null};
		
		for(int i=0;i<coinsList.size();i++)
		{
			if(coinsList.get(i).getObstacleHeight()>0)
			{
				result[coinsList.get(i).getLine()]=(ObstacleCoin) coinsList.get(i);
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
		
		LinkedList<RoadObject> toRemoveObject = new LinkedList<RoadObject>();
		for(int i=0;i<objectList.size();i++)
		{
			toRemoveObject.add(objectList.get(i));
		}
		
		while(toRemoveObject.size()>0)
		{
			this.removeObject(toRemoveObject.getFirst());
			toRemoveObject.removeFirst();
		}
		
		LinkedList<Obstacle> toRemoveCoin = new LinkedList<Obstacle>();
		for(int i=0;i<coinsList.size();i++)
		{
			toRemoveCoin.add(coinsList.get(i));
		}
		
		while(toRemoveCoin.size()>0)
		{
			this.removeObstacle(toRemoveCoin.getFirst());
			toRemoveCoin.removeFirst();
		}
		
		coinsList=new LinkedList<ObstacleCoin>();
		
	}

}