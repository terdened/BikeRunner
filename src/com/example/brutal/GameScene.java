package com.example.brutal;

import java.util.LinkedList;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.Entity;
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
import com.example.brutal.SceneManager.SceneType;

public class GameScene extends BaseScene implements IOnSceneTouchListener, IOnMenuItemClickListener
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
	private MenuScene menuChildScene;
	
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

    	pGameState = "game";
    	createMenuChildScene();
    	
    	
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
	            		pGameState="die";
	            		updateMenuChildScene();
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
    	die();
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
    
    private void createMenuChildScene()
   	{
       	menuChildScene = new MenuScene(camera);
   	    menuChildScene.setPosition(0, 0);
   	    
   	    Sprite background = new Sprite(0,0,resourcesManager.pause_background_region,vbom) ;
   	    menuChildScene.attachChild(background);
   	    
   	    final IMenuItem pause = new ScaleMenuItemDecorator(new SpriteMenuItem(0, resourcesManager.pause_button_region, vbom), 0.8f, 1);
   	    menuChildScene.addMenuItem(pause);
   	    
   	    final IMenuItem restart = new ScaleMenuItemDecorator(new SpriteMenuItem(1, resourcesManager.restart_button_region, vbom), 0.8f, 1);
   	    menuChildScene.addMenuItem(restart);
   	    
   	    final IMenuItem resume = new ScaleMenuItemDecorator(new SpriteMenuItem(2, resourcesManager.resume_button_region, vbom), 0.8f, 1);
	    menuChildScene.addMenuItem(resume);
	    
	    final IMenuItem main_menu = new ScaleMenuItemDecorator(new SpriteMenuItem(3, resourcesManager.main_menu_button_region, vbom), 0.8f, 1);
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
		if(pGameState=="game")
		{
			menuChildScene.getChildByIndex(0).setPosition(0, -2000);
			menuChildScene.getChildByIndex(1).setPosition(1180, 0);
			menuChildScene.getChildByIndex(2).setPosition(0, -2000);
			menuChildScene.getChildByIndex(3).setPosition(0, -2000);
			menuChildScene.getChildByIndex(4).setPosition(0, -2000);
			
		}else
		if(pGameState=="pause")
		{
			menuChildScene.getChildByIndex(0).setPosition(400, 100);
			menuChildScene.getChildByIndex(1).setPosition(1200, -2000);
			menuChildScene.getChildByIndex(2).setPosition(650, 450);
			menuChildScene.getChildByIndex(3).setPosition(750, 450);
			menuChildScene.getChildByIndex(4).setPosition(550, 450);
		}
		if(pGameState=="die")
		{
			menuChildScene.getChildByIndex(0).setPosition(400, 100);
			menuChildScene.getChildByIndex(1).setPosition(1200, -2000);
			menuChildScene.getChildByIndex(2).setPosition(700, 450);
			menuChildScene.getChildByIndex(3).setPosition(700, -2000);
			menuChildScene.getChildByIndex(4).setPosition(550, 450);
		}
   	}
	
	private void pause()
	{
		pGameState="pause";
		updateMenuChildScene();
	}
	
	private void die()
	{

		updatePlayerData();
		resetGame();
    	stopMusic();
    	speed=15;
    	this.mWorldManager.reset();
    	pGameState="die";
    	pDistance=0;
    	pCoins=0;
    	updateMenuChildScene();
    	createTripManager();
	}
	
	private void resume()
	{
		pGameState="game";
		updateMenuChildScene();
		
	}
	
	private void restart()
	{
		die();
		pGameState="game";
		updateMenuChildScene();
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
    
}
