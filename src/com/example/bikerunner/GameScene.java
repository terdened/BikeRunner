package com.example.bikerunner;

import java.util.LinkedList;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.input.touch.TouchEvent;

import com.badlogic.gdx.math.Vector2;
import com.example.bikerunner.SceneManager.SceneType;

public class GameScene extends BaseScene implements IOnSceneTouchListener
{
		
	private Road mRoad;
	private WorldManager mWorldManager;
	private int speed = 15;
	private Biker mBiker;
	private Background mBackground;
	
	private float mStartTapX;
	private float mStartTapY;
	private int pTapTime=0;
	private boolean pIsMoved=false;
	
	private String pGameState;
	
    @Override
    public void createScene()
    {
    	createBackground();
    	createRoad();
    	createWorldManager();
    	createBiker();
    	playMusic();
    	
    	pGameState = "wait";
    	
    	this.setOnSceneTouchListener(this);
    	this.registerUpdateHandler(new IUpdateHandler() {
   		 
            @Override
            public void reset() { }
     
            @Override
            public void onUpdate(final float pSecondsElapsed) 
            { 
            	if(pGameState=="game")
            	{
	            	mRoad.updateRoad(speed);
	            	mWorldManager.updateWorld(speed);
	            	pGameState=mBiker.updateObject(speed);
	            	if(pGameState=="die")
	            		lowerMusic();
            	}
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
    	if(pGameState=="game")
	    	{
	    	if (pSceneTouchEvent.isActionDown())
	        {
	        	mStartTapX=pSceneTouchEvent.getX();
	        	mStartTapY=pSceneTouchEvent.getY();
	        }
	        
	    	if((pTapTime>10)&&(pIsMoved==false))
	    	{
    			mBiker.setAction(mStartTapX, mStartTapY, pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
    			pIsMoved=true;
	    	}
	    	
	        if (pSceneTouchEvent.isActionUp())
	        {
	        	if(pTapTime<=10)
	        	{
	        		mBiker.setAction(mStartTapX, mStartTapY, pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
	        	}
	        	pTapTime=0;
	        	pIsMoved=false;
	        }
	        pTapTime++;
    	}
    	
    	if(pGameState=="wait")
    	{       
	        if (pSceneTouchEvent.isActionUp())
	        {
	        	pGameState="game";
	        	resumeMusic();
	        }
    	}
    	
    	if(pGameState=="die")
    	{       
	        if (pSceneTouchEvent.isActionUp())
	        {
	        	resetGame();
	        	stopMusic();
	        	pGameState="wait";
	        }
    	}
        
    	return true;
    }
    
    public void createRoad()
    {
    	mRoad = new Road();
    	this.attachChild(mRoad.pBackScene);
     	this.attachChild(mRoad.pMiddleScene);
     	this.attachChild(mRoad.pFrontScene);
    }
    
    public void createBiker()
    {
    	mBiker = new Biker(0, 0, resourcesManager.biker_region, vbom, mRoad);
    	this.attachChild(mBiker);
    }
    
    public void createBackground()
    {
    	Sprite back = new Sprite(0,325, resourcesManager.road_background_desert_region, vbom);
    	mBackground = new Background(0, 0, resourcesManager.background_desert_region, vbom);
    	mBackground.attachChild(back);
    	this.attachChild(mBackground);
    }
    
    public void createWorldManager()
    {
    	mWorldManager = new WorldManager(mRoad, vbom, resourcesManager);
    }
    
    public void playMusic()
    {
    	resourcesManager.music.setLooping(true);
    	resourcesManager.music.play();
    	resourcesManager.music.setVolume(0.5f);
    	resourcesManager.music.pause();
    	resourcesManager.motorSound.setLooping(true);
    	resourcesManager.motorSound.play();
    	resourcesManager.motorSound.setVolume(0.5f);
    	resourcesManager.motorSound.pause();
    }
    
    public void resumeMusic()
    {
    	resourcesManager.music.setVolume(0.5f);
    	resourcesManager.music.resume();
    	resourcesManager.motorSound.setVolume(0.5f);
    	resourcesManager.motorSound.resume();
    }
    
    public void lowerMusic()
    {
    	resourcesManager.music.setVolume(0.2f);
    	resourcesManager.music.resume();
    	resourcesManager.motorSound.pause();
    }
    
    public void stopMusic()
    {
    	resourcesManager.music.pause();
    }
    
    public void resetGame()
    {
    	mRoad.resetGame();
    	mBiker.resetGame();
    }
}
