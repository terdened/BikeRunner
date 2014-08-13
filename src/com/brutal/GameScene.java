package com.brutal;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;

import android.content.Context;

import com.badlogic.gdx.math.Vector2;
import com.brutal.SceneManager.SceneType;

public class GameScene extends BaseScene implements IOnSceneTouchListener, IOnMenuItemClickListener
{
	//---------------------------------------------
    // VARIABLES
    //---------------------------------------------
	
	private Road mRoad;
	private WorldManager mWorldManager;
	private int mSpeed = 15;
	private Biker mBiker;
	private Background mBackground;
	private TripManager mTripManager;
	private Text mDistanceText;
	private Text mCoinsText;
	private float mStartTapX;
	private float mStartTapY;
	private boolean mIsMoved=false;
	private int mDistance=0;
	private int mCoins=0;
	private MenuScene menuChildScene;
	private PlayerDataManager mPlayerDataManager;
	private String mGameState;
	private String mLevel="";
	
	//---------------------------------------------
    // CONSTRUCTOR
    //---------------------------------------------
	
	GameScene()
	{
		
	}
	
	//---------------------------------------------
    // OVERLOADED METHODS
    //---------------------------------------------
	
    @Override
    public void createScene()
    {
    	createPlayerDataManager();
    	createBackground();
    	createRoad();
    	createBiker();
    	createScore();
    	//createFog();
    	
    	resourcesManager.soundManager.setState("game");
    	//pMusicManager = new MusicManager(resourcesManager);
    	//playMusic();

    	mGameState = "game";
    	createMenuChildScene();
    	
    	this.setOnSceneTouchListener(this);
    	this.registerUpdateHandler(new IUpdateHandler() {
   		 
            @Override
            public void reset() { }
     
            @Override
            public void onUpdate(final float pSecondsElapsed) 
            { 
            	if(mGameState=="game")
            	{
            		mDistance++;
            		updateComplex();
	            	mRoad.updateRoad(mSpeed);
	            	mWorldManager.updateWorld(mSpeed);
	            	mTripManager.update(mDistance);
	            	mGameState=mBiker.updateObject(mSpeed);
	            	mDistanceText.setText(String.valueOf(mDistance));
	            	mCoinsText.setText(String.valueOf(mCoins));
	            	
	            	if(mGameState=="die")
	            	{
	            		mGameState="die";
	            		resourcesManager.soundManager.crash();
	            		updateMenuChildScene();
	            	}
            	}
            }
            	
        });
    }
    
    @Override
    public void onBackKeyPressed()
    {
    	if(mGameState=="game")
    		pause();
    	else
    	{
    		if(mGameState!="die")
    			resume();
    		else
    			restart();
    	}
    }

    @Override
    public SceneType getSceneType()
    {
    	return SceneType.SCENE_GAME;
    }

    @Override
    public void disposeScene()
    {
    	die();
    	this.resourcesManager.soundManager.setState("stop");
        this.detachSelf();
        this.dispose();
    }
    
    @Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
	
        switch(pMenuItem.getID())
        {
        case 0:
        	pause();
            return true;
        case 1:
        	restart();
            return true;
        case 2:
        	resume();
        	return true;
        case 3:
        	disposeScene();
        	SceneManager.getInstance().loadMenuScene(engine);
        	return true;
        default:
            return false;
        }
	}
    
    //---------------------------------------------
    // PRIVATE METHODS
    //---------------------------------------------
	
    private void createPlayerDataManager()
    {
    	mPlayerDataManager=new PlayerDataManager
    			(this.activity.getSharedPreferences("com.example.bikerunner", Context.MODE_PRIVATE));
    }
      
    private void createMenuChildScene()
   	{
       	menuChildScene = new MenuScene(camera);
   	    menuChildScene.setPosition(0, 0);
   	    
   	    Sprite background = new Sprite(0,0,resourcesManager.pause_background_region,vbom) ;
   	    menuChildScene.attachChild(background);
   	    
   	    final IMenuItem pause = new ScaleMenuItemDecorator(
   	    		new SpriteMenuItem(0, resourcesManager.pause_button_region, vbom), 0.8f, 1);
   	    menuChildScene.addMenuItem(pause);
   	    
   	    final IMenuItem restart = new ScaleMenuItemDecorator(
   	    		new SpriteMenuItem(1, resourcesManager.restart_button_region, vbom), 0.8f, 1);
   	    menuChildScene.addMenuItem(restart);
   	    
   	    final IMenuItem resume = new ScaleMenuItemDecorator(
   	    		new SpriteMenuItem(2, resourcesManager.resume_button_region, vbom), 0.8f, 1);
	    menuChildScene.addMenuItem(resume);
	    
	    final IMenuItem main_menu = new ScaleMenuItemDecorator(
	    		new SpriteMenuItem(3, resourcesManager.main_menu_button_region, vbom), 0.8f, 1);
	    menuChildScene.addMenuItem(main_menu);
	    
   	    menuChildScene.buildAnimations();
   	    menuChildScene.setBackgroundEnabled(false);
   	    
   	    pause.setPosition(1200, 10);
   	    restart.setPosition(600, 300);
   	    resume.setPosition(650, 300);
   	    
   	    menuChildScene.setOnMenuItemClickListener(this);
   	    
   	    setChildScene(menuChildScene);
   	    updateMenuChildScene();
   	}
       
	private void updateMenuChildScene()
   	{
		if(mGameState=="game")
		{
			menuChildScene.getChildByIndex(0).setPosition(0, -2000);
			menuChildScene.getChildByIndex(1).setPosition(1180, 0);
			menuChildScene.getChildByIndex(2).setPosition(0, -2000);
			menuChildScene.getChildByIndex(3).setPosition(0, -2000);
			menuChildScene.getChildByIndex(4).setPosition(0, -2000);
			
		}else
		if(mGameState=="pause")
		{
			menuChildScene.getChildByIndex(0).setPosition(400, 100);
			menuChildScene.getChildByIndex(1).setPosition(1200, -2000);
			menuChildScene.getChildByIndex(2).setPosition(605, 450);
			menuChildScene.getChildByIndex(3).setPosition(705, 450);
			menuChildScene.getChildByIndex(4).setPosition(505, 450);
		}
		if(mGameState=="die")
		{
			menuChildScene.getChildByIndex(0).setPosition(400, 100);
			menuChildScene.getChildByIndex(1).setPosition(1200, -2000);
			menuChildScene.getChildByIndex(2).setPosition(660, 450);
			menuChildScene.getChildByIndex(3).setPosition(700, -2000);
			menuChildScene.getChildByIndex(4).setPosition(550, 450);
		}
   	}
	
	private void pause()
	{
		mGameState="pause";
		this.mBiker.pause();
		updateMenuChildScene();
		resourcesManager.soundManager.pause();
	}
	
	private void die()
	{
		updatePlayerData();
		resetGame();
    	mSpeed=15;
    	this.mWorldManager.reset();
    	mGameState="die";
    	mDistance=0;
    	mCoins=0;
    	updateMenuChildScene();
    	createTripManager();
	}
	
	private void resume()
	{
		mGameState="game";
		this.mBiker.resume();
		updateMenuChildScene();
		resourcesManager.soundManager.resume();
		
	}
	
	private void restart()
	{
    	resourcesManager.soundManager.restart();
		die();
		mGameState="game";
		updateMenuChildScene();
	}
    
    //---------------------------------------------
    // PUBLIC METHODS
    //---------------------------------------------
	  
    public void initScene(String pLevel)
	{
		mLevel = pLevel;
    	createTripManager();
    	createWorldManager();
	}
    
    public void collectCoin(ObstacleCoin pCoin)
    {
        this.resourcesManager.soundManager.collectCoin();
        mRoad.deleteCoin(pCoin);
        mCoins++;
    }
    
    public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent)
    {
    	if(mGameState=="game")
	    	{
	    	if (pSceneTouchEvent.isActionDown())
	        {
	        	mStartTapX=pSceneTouchEvent.getX();
	        	mStartTapY=pSceneTouchEvent.getY();
	        }
	        
	    	Vector2 swipe=new Vector2();
	    	swipe.x=mStartTapX-pSceneTouchEvent.getX();
	    	swipe.y=mStartTapY-pSceneTouchEvent.getY();
	    	
	    	if((swipe.len()>50)&&(mIsMoved==false))
	    	{
    			mBiker.setAction(mStartTapX, mStartTapY, pSceneTouchEvent.getX(),
    					pSceneTouchEvent.getY());
    			mIsMoved=true;
	    	}
	    	
	        if (pSceneTouchEvent.isActionUp())
	        {
	        	if((swipe.len()<30)&&(!mIsMoved))
	        	{
	        		mBiker.setAction(0, 0, 0, 100);
	        	}
	        	else
	        	if((swipe.len()>=30)&&(!mIsMoved))
	        	{
	        		mBiker.setAction(mStartTapX, mStartTapY, pSceneTouchEvent.getX(),
	        				pSceneTouchEvent.getY());			
	        	}
	        	//pTapTime=0;
	        	mIsMoved=false;
	        }
	        //pTapTime++;
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
    	mDistanceText = new Text(0, 0, resourcesManager.font, "0", 10, vbom);
    	this.attachChild(mDistanceText);
    	mCoinsText = new Text(0, 60, resourcesManager.font, "0", 10, vbom);
    	this.attachChild(mCoinsText);
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
    	mWorldManager = new WorldManager(mRoad, vbom, resourcesManager, this.mLevel);
    }
    
    public void createTripManager()
    {
    	if(this.mLevel=="Desert")
    	{
    		mTripManager = new TripManagerDesert(mRoad, vbom, resourcesManager);
    	}
    	else
		if(this.mLevel=="Countryside")
    	{
    		mTripManager = new TripManagerCountriside(mRoad, vbom, resourcesManager);
    	}
    	else
    	{
    		mTripManager = new TripManagerDesert(mRoad, vbom, resourcesManager);
    	}
    	mTripManager.initTripManager();
    }

    public void resetGame()
    {
    	mRoad.resetGame();
    	mBiker.resetGame();
    }
    
    public void updateComplex()
    {
    	if((mDistance>0)&&(mDistance%1000==0))
    	{
    		mWorldManager.increaseLevelComplex();
    		mSpeed++;
    	}
    }
    
    public void updatePlayerData()
    {
    	if(mPlayerDataManager.getHighScore()<mDistance)
    		mPlayerDataManager.updateHighScore(this.mDistance);
    	
    	mPlayerDataManager.addCoins(mCoins);
    }
    
}
