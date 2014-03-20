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
		currentController=0;
		
		worldControllers = new LinkedList<WorldController>();
		worldControllers.add(new WorldControllerEasy(road, vbom, resManager));
		worldControllers.add(new WorldControllerMedium(road, vbom, resManager));
		worldControllers.add(new WorldControllerHard(road, vbom, resManager));
		worldControllers.add(new WorldControllerVeryHard(road, vbom, resManager));
		worldControllers.add(new WorldControllerHell(road, vbom, resManager));
	}
	
	public void updateWorld(int speed)
	{
		worldControllers.get(currentController).updateWorld(speed);
	}
	
	public void increaseLevelComplex()
	{
		if(currentController<worldControllers.size()-1)
			currentController++;
	}
	
	public void decreaseLevelComplex()
	{
		if(currentController>0)
			currentController--;
	}
	
	public void reset()
	{
		currentController=0;
	}
}
