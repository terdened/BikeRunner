package com.brutal.game.worldmanager;

import java.util.LinkedList;

import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.brutal.engine.*;
import com.brutal.game.road.*;


public class WorldManager {
	
	//---------------------------------------------
    // VARIABLES
    //---------------------------------------------

	private final Road mRoad;
	private int mCurrentController;
	private final VertexBufferObjectManager mVbom;
	private final ResourcesManager mResManager;
	private LinkedList<WorldController> mWorldControllers; 
	private String mLevel;
	
	//---------------------------------------------
    // CONSTRUCTOR
    //---------------------------------------------

	public WorldManager(Road pRoad, VertexBufferObjectManager pVbom, 
			ResourcesManager pResManager, String pLevel)
	{
		mRoad = pRoad;
		mVbom = pVbom;
		mResManager = pResManager;
		mLevel = pLevel;
		
		generateLevels();
	}
	
	//---------------------------------------------
    // PRIVATE METHODS
    //---------------------------------------------

	private void generateLevels()
	{
		mCurrentController=0;
		mWorldControllers = new LinkedList<WorldController>();
		
		if(mLevel=="Desert")
		{
			mWorldControllers.add(new WorldControllerRandom(mRoad, mVbom, mResManager));	
			mWorldControllers.add(new WorldControllerTemplate(mRoad, mVbom, mResManager));
			mWorldControllers.add(new WorldControllerRandom(mRoad, mVbom, mResManager));
			mWorldControllers.add(new WorldControllerTemplate(mRoad, mVbom, mResManager));
			
			
			mWorldControllers.add(new WorldControllerBonus(mRoad, mVbom, mResManager));
		}
		else
		if(mLevel=="Countryside")
		{
			
			mWorldControllers.add(new WorldControllerRandomCountriside(mRoad, mVbom, mResManager));	
			mWorldControllers.add(new WorldControllerTemplateCountriside(mRoad, mVbom, mResManager));
			mWorldControllers.add(new WorldControllerRandomCountriside(mRoad, mVbom, mResManager));
			mWorldControllers.add(new WorldControllerTemplateCountriside(mRoad, mVbom, mResManager));
			
			mWorldControllers.add(new WorldControllerBonus(mRoad, mVbom, mResManager));
		}
		else
		{
			mWorldControllers.add(new WorldControllerRandom(mRoad, mVbom, mResManager));	
			mWorldControllers.add(new WorldControllerTemplate(mRoad, mVbom, mResManager));
			mWorldControllers.add(new WorldControllerRandom(mRoad, mVbom, mResManager));
			mWorldControllers.add(new WorldControllerTemplate(mRoad, mVbom, mResManager));
			
			
			mWorldControllers.add(new WorldControllerBonus(mRoad, mVbom, mResManager));
		}
		
	}
	
	//---------------------------------------------
    // PUBLIC METHODS
    //---------------------------------------------

	public void updateWorld(int pSpeed)
	{
		mWorldControllers.get(mCurrentController).updateWorld(pSpeed);
	}
	
	public void increaseLevelComplex()
	{
		if(mCurrentController<mWorldControllers.size()-1)
			mCurrentController++;
		else
			generateLevels();
	}
	
	public void decreaseLevelComplex()
	{
		if(mCurrentController>0)
			mCurrentController--;
	}
	
	public void reset()
	{
		generateLevels();
	}
}
