package com.example.bikerunner;

import java.util.LinkedList;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;

import android.content.Context;

import com.badlogic.gdx.math.Vector2;
import com.example.bikerunner.SceneManager.SceneType;

public class GameScene extends BaseScene implements IOnSceneTouchListener
{
		
	private Road mRoad;
	private WorldManager mWorldManager;
	private int speed = 15;
	private Biker mBiker;
	private Background mBackground;
	private TripManager mTripManager;
	private Text pDistanceText;
	private Text pCoinsText;
	
	private float mStartTapX;
	private float mStartTapY;
	private int pTapTime=0;
	private boolean pIsMoved=false;
	private MusicManager pMusicManager;
	private int pDistance=0;
	private int pCoins=0;
	
	private PlayerDataManager pPlayerDataManager;
	
	private String pGameState;
	
    @Override
    public void createScene()
    {
    	createPlayerDataManager();
    	createBackground();
    	createRoad();
    	createWorldManager();
    	createTripManager();
    	createBiker();
    	createScore();
    	pMusicManager = new MusicManager(resourcesManager);
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
            		pDistance++;
            		updateComplex();
	            	mRoad.updateRoad(speed);
	            	mWorldManager.updateWorld(speed);
	            	mTripManager.update(pDistance);
	            	pGameState=mBiker.updateObject(speed);
	            	pDistanceText.setText(String.valueOf(pDistance));
	            	pCoinsText.setText(String.valueOf(pCoins));
	            	if(pGameState=="die")
	            	{
	            		lowerMusic();
	            		updatePlayerData();
	            	}
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
    
    public void collectCoin(ObstacleCoin coin)
    {
        mRoad.deleteCoin(coin);
        pCoins++;
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
	        	speed=15;
	        	this.mWorldManager.reset();
	        	pGameState="wait";
	        	pDistance=0;
	        	pCoins=0;
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
    	mBiker = new Biker(0, 0, resourcesManager.biker_region, vbom, mRoad, this);
    	this.attachChild(mBiker);
    }
    
    public void createScore()
    {
    	pDistanceText = new Text(0, 0, resourcesManager.font, "0", 10, vbom);
    	this.attachChild(pDistanceText);
    	pCoinsText = new Text(0, 60, resourcesManager.font, "0", 10, vbom);
    	this.attachChild(pCoinsText);
    }
    
    public void createBackground()
    {
    	Sprite back = new Sprite(0,320, resourcesManager.road_background_fon_region, vbom);
    	mBackground = new Background(0, 0, resourcesManager.background_region, vbom);
    	mBackground.attachChild(back);
    	this.attachChild(mBackground);
    }
    
    public void createWorldManager()
    {
    	mWorldManager = new WorldManager(mRoad, vbom, resourcesManager);
    }
    
    public void createTripManager()
    {
    	mTripManager = new TripManagerDesert(mRoad, vbom, resourcesManager);
    	mTripManager.initTripManager();
    }
    
    public void playMusic()
    {

    	//pMusicManager.playMusic();
    }
    
    public void resumeMusic()
    {

    	//pMusicManager.resumeMusic();
    }
    
    public void lowerMusic()
    {
    	//pMusicManager.lowerMusic();
    }
    
    public void stopMusic()
    {
    	//pMusicManager.stopMusic();
    }
    
    public void resetGame()
    {
    	mRoad.resetGame();
    	mBiker.resetGame();
    }
    
    public void updateComplex()
    {
    	if((pDistance>0)&&(pDistance%2000==0))
    	{
    		mWorldManager.increaseLevelComplex();
    		speed++;
    	}
    }
    
    private void createPlayerDataManager()
    {
    	pPlayerDataManager=new PlayerDataManager
    			(this.activity.getSharedPreferences("com.example.bikerunner", Context.MODE_PRIVATE));
    }
    
    public void updatePlayerData()
    {
    	if(pPlayerDataManager.getHighScore()<pDistance)
    		pPlayerDataManager.updateHighScore(this.pDistance);
    	
    	pPlayerDataManager.addCoins(pCoins);
    }
}
