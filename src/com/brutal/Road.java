package com.brutal;

import java.util.LinkedList;

import org.andengine.entity.Entity;

public class Road {
	
	//---------------------------------------------
    // VARIABLES
    //---------------------------------------------

	private LinkedList<RoadObject> mObjectList;
	private LinkedList<Obstacle> mObstacleList;
	private LinkedList<ObstacleCoin> mCoinsList;
	public Entity mBackScene;
	public Entity mFrontScene;
	public Entity mMiddleScene;

	//---------------------------------------------
    // CONSTRUCTOR
    //---------------------------------------------

	public Road()
	{
		mBackScene = new Entity();
		mFrontScene = new Entity();
		mMiddleScene = new Entity();
		mObjectList = new LinkedList<RoadObject>();
		mObstacleList = new LinkedList<Obstacle>();
		mCoinsList = new LinkedList<ObstacleCoin>();
	}
	
	//---------------------------------------------
    // PUBLIC METHODS
    //---------------------------------------------
	
	public void updateRoad(int pSpeed)
	{
		for(int i=0; i<mObjectList.size();i++)
		{
			mObjectList.get(i).updateObject(pSpeed);
			
			if(mObjectList.get(i).canDelete())
				this.removeObject(mObjectList.get(i));
		}
		
		for(int i=0; i<mObstacleList.size();i++)
		{
			mObstacleList.get(i).updateObject(pSpeed);
			
			if(mObstacleList.get(i).canDelete())
				this.removeObstacle(mObstacleList.get(i));
		}
		
		for(int i=0; i<mCoinsList.size();i++)
		{
			mCoinsList.get(i).updateObject(pSpeed);
			
			if(mCoinsList.get(i).canDelete())
				this.removeCoin(mCoinsList.get(i));
		}
	}
	
	public void sortChildren(String pScene)
	{
		if(pScene=="back")
		{
			this.mBackScene.sortChildren();
			for(int i=0;i<mObjectList.size();i++)
			{
				this.mBackScene.getChildByIndex(i).setZIndex(i);
			}
		}else
		if(pScene=="front")
		{
			sortObstacles();
			this.mFrontScene.sortChildren();
		}else
		if(pScene=="middle")
		{
			sortObjects();
			this.mMiddleScene.sortChildren();
		}
			
	}
	
	public void sortObstacles()
	{
		int lastValue=-10000;
		
		for(int i=0;i<this.mFrontScene.getChildCount();i++)
		{
			Obstacle temp =(Obstacle)(mFrontScene.getChildByIndex(i));
			lastValue=(int)temp.getZ();
			this.mFrontScene.getChildByIndex(i).setZIndex(10000-lastValue);
		}
			
	}
	
	public void sortObjects()
	{
		
		for(int i=0;i<this.mMiddleScene.getChildCount();i++)
		{
			RoadObject temp =(RoadObject)(mMiddleScene.getChildByIndex(i));
			int depth=(int)temp.getZ();
			this.mMiddleScene.getChildByIndex(i).setZIndex(10000-depth);
		}
			
	}
	
	public void addObject(RoadObject pObj, String pScene)
	{
		mObjectList.add(pObj);
		
		if(pScene=="back")
		{
			mBackScene.attachChild(pObj);
		}else
		if(pScene=="front")
		{
			mFrontScene.attachChild(pObj);
		}else
		if(pScene=="middle")
		{
			mMiddleScene.attachChild(pObj);
		}
	}
	
	public void removeObject(RoadObject pObj)
	{
		mObjectList.remove(pObj);
		
		try
		{
			mBackScene.detachChild(pObj);
		}
		finally
		{
			
		}
		
		try
		{
			mMiddleScene.detachChild(pObj);
		}
		finally
		{
			
		}
		
		try
		{
			mFrontScene.detachChild(pObj);
		}
		finally
		{
			
		}
	}
	
	public void addObstacle(Obstacle pObj, String pScene)
	{
		mObstacleList.add(pObj);
		
		if(pScene=="back")
		{
			mBackScene.attachChild(pObj);
		}else
		if(pScene=="front")
		{
			mFrontScene.attachChild(pObj);
		}else
		if(pScene=="middle")
		{
			mMiddleScene.attachChild(pObj);
		}
	}
	
	public void removeObstacle(Obstacle pObj)
	{
		mObstacleList.remove(pObj);
		
		try
		{
			mBackScene.detachChild(pObj);
		}
		finally
		{
			
		}
		
		try
		{
			mMiddleScene.detachChild(pObj);
		}
		finally
		{
			
		}
		
		try
		{
			mFrontScene.detachChild(pObj);
		}
		finally
		{
			
		}
	}
	
	public void addCoin(ObstacleCoin pObj, String pScene)
	{
		mCoinsList.add(pObj);
		
		if(pScene=="back")
		{
			mBackScene.attachChild(pObj);
		}else
		if(pScene=="front")
		{
			mFrontScene.attachChild(pObj);
		}else
		if(pScene=="middle")
		{
			mMiddleScene.attachChild(pObj);
		}
	}
	
	public void deleteCoin(ObstacleCoin pObj)
	{
		for(int i=0;i<mCoinsList.size();i++)
		{
			if(mCoinsList.get(i)==pObj)
				mCoinsList.get(i).delete();
		}
	}
	
	public void removeCoin(Obstacle pObj)
	{
		mCoinsList.remove(pObj);
		
		try
		{
			mBackScene.detachChild(pObj);
		}
		finally
		{
			
		}
		
		try
		{
			mMiddleScene.detachChild(pObj);
		}
		finally
		{
			
		}
		
		try
		{
			mFrontScene.detachChild(pObj);
		}
		finally
		{
			
		}
	}

	public void resetGame()
	{
		LinkedList<Obstacle> toRemove = new LinkedList<Obstacle>();
		for(int i=0;i<mObstacleList.size();i++)
		{
			toRemove.add(mObstacleList.get(i));
		}
		
		while(toRemove.size()>0)
		{
			this.removeObstacle(toRemove.getFirst());
			toRemove.removeFirst();
		}
		
		LinkedList<RoadObject> toRemoveObject = new LinkedList<RoadObject>();
		for(int i=0;i<mObjectList.size();i++)
		{
			toRemoveObject.add(mObjectList.get(i));
		}
		
		while(toRemoveObject.size()>0)
		{
			this.removeObject(toRemoveObject.getFirst());
			toRemoveObject.removeFirst();
		}
		
		LinkedList<Obstacle> toRemoveCoin = new LinkedList<Obstacle>();
		for(int i=0;i<mCoinsList.size();i++)
		{
			toRemoveCoin.add(mCoinsList.get(i));
		}
		
		while(toRemoveCoin.size()>0)
		{
			this.removeObstacle(toRemoveCoin.getFirst());
			toRemoveCoin.removeFirst();
		}
		
		mCoinsList=new LinkedList<ObstacleCoin>();
		
	}

	//---------------------------------------------
    // GETTERS
    //---------------------------------------------
	
	public int[] getLinesHeight()
	{
		int[] result = new int[]{0,0,0};
		
		for(int i=0;i<mObstacleList.size();i++)
		{
			if(mObstacleList.get(i).getObstacleHeight()>0)
			{
				result[mObstacleList.get(i).getLine()]=
						mObstacleList.get(i).getObstacleHeight();
			}
		}
		
		return result;
	}
	
	public int[] getLinesHeightByPosition(int pPosition)
	{
		int[] result = new int[]{0,0,0};
		
		for(int i=0;i<mObstacleList.size();i++)
		{
			if(mObstacleList.get(i).getObstacleHeightByPosition(pPosition)>0)
			{
				result[mObstacleList.get(i).getLine()]=mObstacleList.get(i).getObstacleHeightByPosition(pPosition);
			}
		}
		
		return result;
	}
	
	public int[] getLinesHeightByDeep(float pDeep)
	{
		int[] result = new int[]{0,0,0};
		
		for(int i=0;i<mObstacleList.size();i++)
		{
			if(mObstacleList.get(i).getObstacleHeightByDeep(pDeep)>0)
			{
				result[mObstacleList.get(i).getLine()]=mObstacleList.get(i).getObstacleHeightByDeep(pDeep);
			}
		}
		
		return result;
	}
	
	public ObstacleCoin[] getLineCoins()
	{
		ObstacleCoin[] result = new ObstacleCoin[]{null,null,null};
		
		for(int i=0;i<mCoinsList.size();i++)
		{
			if(mCoinsList.get(i).getObstacleHeight()>0)
			{
				result[mCoinsList.get(i).getLine()]=(ObstacleCoin) mCoinsList.get(i);
			}
		}
		
		return result;
	}
}
