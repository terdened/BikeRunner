package com.brutal;

import java.util.LinkedList;

import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class WorldControllerTemplateCountriside extends WorldController{

	//---------------------------------------------
    // VARIABLES
    //---------------------------------------------

	private int mObstacleCounter=0;
	
	//---------------------------------------------
    // CONSTRUCTOR
    //---------------------------------------------

	public WorldControllerTemplateCountriside(Road pRoad, VertexBufferObjectManager pVbom,
			ResourcesManager pResManager) {
		super(pRoad, pVbom, pResManager);
	}
	
	//---------------------------------------------
    // OVERLOADED METHODS
    //---------------------------------------------
	
	@Override
	public void updateWorld(int pSpeed)
	{
		mCounter++;
		int complexity =this.getComplexityBySpeed(pSpeed);
		
		generateObstacle(complexity,pSpeed);
		generateCoin();
		generateObject();
		
		if(mCounter>=100)
			mCounter=0;
	}
	
	//---------------------------------------------
    // PRIVATE METHODS
    //---------------------------------------------

	private void generateObstacle(int pComplexity, int pSpeed)
	{
		int maxObstacleCounter=0;
		
		if(pComplexity==1)
		{
			maxObstacleCounter=90;
		}
		else
		if(pComplexity==2)	
		{
			maxObstacleCounter=70;
		}
		else
		{
			maxObstacleCounter=50;
		}
		
		mObstacleCounter++;
		
		if(mObstacleCounter==maxObstacleCounter)
		{
			LinkedList<String> ids = new LinkedList<String>();
			
			if(pSpeed<19)
			{
				ids.add("14");
				ids.add("15");
				ids.add("16");
				ids.add("23");
				ids.add("24");
				ids.add("25");
			}
			else
			if(pSpeed<24)
			{
				ids.add("12");
				ids.add("13");
				ids.add("14");
				ids.add("15");
				ids.add("16");
				ids.add("23");
				ids.add("24");
				ids.add("25");
				ids.add("26");
				ids.add("27");
			}
			else
			if(pSpeed<29)
			{
				ids.add("12");
				ids.add("13");
				ids.add("14");
				ids.add("15");
				ids.add("16");
				ids.add("23");
				ids.add("24");
				ids.add("25");
				ids.add("26");
				ids.add("27");
				ids.add("36");
				ids.add("37");
				ids.add("38");
			}
			else
			{
				ids.add("12");
				ids.add("13");
				ids.add("14");
				ids.add("15");
				ids.add("16");
				ids.add("23");
				ids.add("24");
				ids.add("25");
				ids.add("26");
				ids.add("27");
				ids.add("36");
				ids.add("37");
				ids.add("38");
			}
			
			
			
			Obstacle[] obj = mObjFactory.createTemplate(mVbom, mResManager, ids);
			for(int i=0;i<obj.length;i++)
			{
				obj[i].setAlpha(0);
				obj[i].setAction("crash");
				mRoad.addObstacle(obj[i],"front");
			}
			mRoad.sortChildren("front");
		}
		
		if(mObstacleCounter==maxObstacleCounter)
		{
			mObstacleCounter=0;
		}
	}
	
	private void generateCoin()
	{		
		if(mCounter%20==0)
		{
			ObstacleCoin obj = mObjFactory.createCoin(mVbom, mResManager);
			if(obj!=null)
			{
				obj.setAlpha(0);
				
				if(mRoad.getLinesHeight()[obj.getLine()]>0)
					obj.setRealHeight(mRoad.getLinesHeight()[obj.getLine()]);
				
				mRoad.addCoin(obj,"front");
				mRoad.sortChildren("front");
			}
			
		}
	}
	
	private void generateObject()
	{		
		if(mCounter%25==0)
		{			
			RoadObject obj = mObjFactory.createBackgroundObject(mVbom, mResManager);
			obj.initObject(1200, obj.getX(), obj.getY()*0.8f, 1, obj.getHeight()*2, obj.getWidth()*2);
			obj.setAlpha(0);
			mRoad.addObject(obj,"middle");
			mRoad.sortChildren("middle");
		}
		
		if(mCounter%10==0)
		{			
			RoadObject obj = mObjFactory.createBlink(mVbom, mResManager);
			obj.initObject(-1000, obj.getX(), obj.getY(), 1, obj.getHeight(), obj.getWidth());
			obj.setAlpha(0);
			mRoad.addObject(obj,"middle");
			mRoad.sortChildren("middle");
		}
	}
	
	

}
