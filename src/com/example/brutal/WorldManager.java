package com.example.brutal;

import java.util.LinkedList;

import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class WorldManager {
	
	private final Road mRoad;
	private int mCounter;
	private int currentController;
	private final VertexBufferObjectManager mVbom;
	private final ResourcesManager mResManager;
	private LinkedList<WorldController> worldControllers; 
	
	public WorldManager(Road road, VertexBufferObjectManager vbom, ResourcesManager resManager)
	{
		mRoad = road;
		mCounter=0;
		mVbom = vbom;
		mResManager = resManager;
		
		generateLevels();
	}
	
	private void generateLevels()
	{
		currentController=0;
		worldControllers = new LinkedList<WorldController>();
		
		for(int i=0;i<4;i++)
		{
			int random=(int) (Math.random()*2);
			
			if(random<1)
				worldControllers.add(new WorldControllerTemplate(mRoad, mVbom, mResManager));
			else
				worldControllers.add(new WorldControllerRandom(mRoad, mVbom, mResManager));
		}
		
		worldControllers.add(new WorldControllerBonus(mRoad, mVbom, mResManager));
		
	}
	
	public void updateWorld(int speed)
	{
		worldControllers.get(currentController).updateWorld(speed);
	}
	
	public void increaseLevelComplex()
	{
		if(currentController<worldControllers.size()-1)
			currentController++;
		else
			generateLevels();
	}
	
	public void decreaseLevelComplex()
	{
		if(currentController>0)
			currentController--;
	}
	
	public void reset()
	{
		generateLevels();
	}
}
