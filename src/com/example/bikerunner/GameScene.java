package com.example.bikerunner;

import java.util.LinkedList;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
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
	            	mBiker.updateObject(speed);
	            	collisionControl();
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
	    		if(new Vector2(pSceneTouchEvent.getX()-mStartTapX, pSceneTouchEvent.getY()-mStartTapY).len()>50)
	    		{
	    			mBiker.setAction(mStartTapX, mStartTapY, pSceneTouchEvent.getX(), pSceneTouchEvent.getY());
	    			pIsMoved=true;
	    		}
	    			
	    	}
	    	
	        if (pSceneTouchEvent.isActionUp())
	        {
	        	if((pTapTime<=10)&&(new Vector2(pSceneTouchEvent.getX()-mStartTapX, pSceneTouchEvent.getY()-mStartTapY).len()>50))
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
	        }
    	}
    	
    	if(pGameState=="die")
    	{       
	        if (pSceneTouchEvent.isActionUp())
	        {
	        	resetGame();
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
    	mBiker = new Biker(0, 0, resourcesManager.biker_region, vbom);
    	this.attachChild(mBiker);
    }
    
    public void createBackground()
    {
    	mBackground = new Background(0, 0, resourcesManager.background_desert_region, vbom);
    	this.attachChild(mBackground);
    }
    
    public void createWorldManager()
    {
    	mWorldManager = new WorldManager(mRoad, vbom, resourcesManager);
    }
    
    public void collisionControl()
    {
    	LinkedList<String> bunnedLines=mRoad.getBunnedLines();
    	for(int i=0;i<bunnedLines.size();i++)
    	{
    		if(mBiker.getLine()==Integer.valueOf(bunnedLines.get(i)))
    		{
    			pGameState="die";
    		}
    	}
    }
    
    public void resetGame()
    {
    	mRoad.resetGame();
    	mBiker.resetGame();
    }
}
