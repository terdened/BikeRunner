package com.example.wildhunt;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.input.touch.TouchEvent;

import com.example.wildhunt.SceneManager.SceneType;

public class GameScene extends BaseScene implements IOnSceneTouchListener
{
		
	private Road mRoad;
	private WorldManager mWorldManager;
	private int speed = 10;
	private Biker mBiker;
	private float mStartTapX;
	private float mStartTapY;
	
	
    @Override
    public void createScene()
    {
    	createRoad();
    	createWorldManager();
    	createBiker();
    	
    	this.attachChild(mRoad.pBackScene);
    	this.attachChild(mRoad.pMiddleScene);
    	this.attachChild(mRoad.pFrontScene);
    	
    	this.attachChild(mBiker);
    	
    	this.setOnSceneTouchListener(this);
    	this.registerUpdateHandler(new IUpdateHandler() {
   		 
            @Override
            public void reset() { }
     
            @Override
            public void onUpdate(final float pSecondsElapsed) 
            { 
            	mRoad.updateRoad(speed);
            	mWorldManager.updateWorld(speed);
            	mBiker.updateObject(speed);
            }
            	
        });
    }
    
    @Override
    public void onBackKeyPressed()
    {
    	disposeScene();
    	SceneManager.getInstance().loadMenuScene(engine);
    }

    @Override
    public SceneType getSceneType()
    {
    	return SceneType.SCENE_GAME;
    }

    @Override
    public void disposeScene()
    {
        this.detachSelf();
        this.dispose();
    }
    
    public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent)
    {
    	
    	if (pSceneTouchEvent.isActionDown())
        {
        	mStartTapX=pSceneTouchEvent.getX();
        	mStartTapY=pSceneTouchEvent.getY();
        }
        
        if (pSceneTouchEvent.isActionUp())
        {
        	mBiker.setAction(mStartTapX, mStartTapY, pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
        }
        
    	return true;
    }
    
    public void createRoad()
    {
    	 mRoad = new Road();
    }
    
    public void createBiker()
    {
    	mBiker = new Biker(0, 0, resourcesManager.biker_region, vbom);
    }
    
    public void createWorldManager()
    {
    	mWorldManager = new WorldManager(mRoad, vbom, resourcesManager);
    }
}
