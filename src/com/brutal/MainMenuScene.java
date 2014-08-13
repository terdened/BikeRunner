package com.brutal;

import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.Entity;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.input.touch.TouchEvent;

import android.content.Context;

import com.badlogic.gdx.math.Vector2;
import com.brutal.SceneManager;
import com.brutal.SceneManager.SceneType;

public class MainMenuScene extends BaseScene 
implements IOnMenuItemClickListener, IOnSceneTouchListener
{	
	//---------------------------------------------
    // VARIABLES
    //---------------------------------------------
		
	private final int MENU_BUY = 0;
	private final int MENU_OPTIONS = 1;
	private final int MENU_BUY_BIKE = 2;
	private final int MENU_HELP = 3;
	private int mDisplayHeight=800;
	private float mStartTapY=0;
	private float mStartTapX=0;
	private boolean mIsOptionsOpen=false;	
	private MenuBiker mMenuBiker;
	private MenuInfo mMenuInfo;
	private MenuBackground mMenuBackground;
	private PlayerDataManager mPlayerDataManager;
	private OptionsMenu mOptionsMenu;
	private MenuScene mMenuChildScene;
	private Tutorial mTutorial;
	
	//---------------------------------------------
    // CONSTRUCTOR
    //---------------------------------------------
	
	public MainMenuScene(int pDisplayHeight)
	{
		this.mDisplayHeight=pDisplayHeight;
		createInfoText();
        createMenuChildScene();
        if(!mPlayerDataManager.getFirstStart())
        {
        	showTutorial();
        	mPlayerDataManager.updateFirstStart();
        }
	}
	
	//---------------------------------------------
    // OVERLOADED METHODS
    //---------------------------------------------
	
    @Override
    public void createScene()
    {
    	createPlayerDataManager();
    	createBackground();
    	createBike();
        //createMenuChildScene();
        
        resourcesManager.initSoundManager();
        resourcesManager.soundManager.setState("menu");
        
    	this.setOnSceneTouchListener(this);
        this.registerUpdateHandler(new IUpdateHandler() {
      		 
            @Override
            public void reset() { }
     
            @Override
            public void onUpdate(final float pSecondsElapsed) 
            {       	
            	if(mTutorial!=null)
            	{
            		if(mTutorial.canDelete())
            		{
            			mTutorial.dispose();
            			mTutorial = null;
            		}
            	}
            	
            	mMenuBackground.update();
            	mMenuBiker.update();
            }
        });
        
    }
    
    @Override
    public void onBackKeyPressed()
    {
    	System.exit(0);
    }

    @Override
    public SceneType getSceneType()
    {
    	return SceneType.SCENE_MENU;
    }

    @Override
    public void disposeScene()
    {
    	this.resourcesManager.soundManager.setState("stop");
        this.detachSelf();
        this.dispose();
    }
	
	//---------------------------------------------
    // PRIVATE METHODS
    //---------------------------------------------
	
	private void createOptionsMenu()
    {
    	mOptionsMenu = new OptionsMenu(camera, vbom, mPlayerDataManager, resourcesManager, this);
    	mOptionsMenu.createMenuChildScene(camera);
    	mOptionsMenu.setPosition(400,(mDisplayHeight-512)/2);
    	this.setChildScene(mOptionsMenu);
    }
    
    private void buyLevel()
    {
    	int cost=Integer.valueOf(mMenuBackground.mLevelCostList.get(mMenuBackground.mCurrentLevel));
    	if(mPlayerDataManager.getCoins()>=cost)
    	{
    		mPlayerDataManager.removeCoins(cost);
    		mPlayerDataManager.setLevelAccess(mMenuBackground.mLevelList.get(
    				mMenuBackground.mCurrentLevel));
    		mMenuInfo.update();
    		updateMenuChildScene();
    	}
    }
    
    private void buyBike()
    {
    	int cost=Integer.valueOf(mMenuBiker.mLevelCostList.get(mMenuBiker.mCurrentLevel));
    	if(mPlayerDataManager.getCoins()>=cost)
    	{
    		mPlayerDataManager.removeCoins(cost);
    		mPlayerDataManager.setBikeAccess(mMenuBiker.mLevelList.get(mMenuBiker.mCurrentLevel));
    		mMenuInfo.update();
    		updateMenuChildScene();
    	}
    }
    
    private void createBackground()
    {
    	mMenuBackground = new MenuBackground(0,0,this.resourcesManager, this.vbom);
    	this.attachChild(mMenuBackground);
    }
    
    private void createBike()
    {
    	mMenuBiker = new MenuBiker( resourcesManager, vbom);
    	mMenuBiker.setPosition(370, 270);
		this.attachChild(mMenuBiker);
    }
    
    private void createPlayerDataManager()
    {
    	mPlayerDataManager=new PlayerDataManager
    			(this.activity.getSharedPreferences("com.example.bikerunner", Context.MODE_PRIVATE));
    }
    
    private void createInfoText()
    {
    	mMenuInfo = new MenuInfo(1280, mDisplayHeight, resourcesManager, vbom, mPlayerDataManager);
    	mMenuInfo.setBikeName(mMenuBiker.mLevelList.get(mMenuBiker.mCurrentLevel));
    	mMenuInfo.setLevelName(mMenuBackground.mLevelList.get(mMenuBackground.mCurrentLevel));
    	mMenuInfo.update();
    	this.attachChild(mMenuInfo);
    }
   
    private void showTutorial()
    {
    	mTutorial = new Tutorial(512, (mDisplayHeight-256)/2,
    			this.resourcesManager.tutorial_region, vbom);
    	this.attachChild(mTutorial);
    }
    
    private void createMenuChildScene()
	{
    	mMenuChildScene = new MenuScene(camera);
	    mMenuChildScene.setPosition(0, 0);
	    
	    final IMenuItem buyMenuItem = new ScaleMenuItemDecorator(
	    		new SpriteMenuItem(MENU_BUY, resourcesManager.buy_button_region, vbom), 0.8f, 1);
	    mMenuChildScene.addMenuItem(buyMenuItem);
	    
	    final IMenuItem buyBikeMenuItem = new ScaleMenuItemDecorator(
	    		new SpriteMenuItem(MENU_BUY_BIKE, resourcesManager.buy_button_region, vbom), 0.8f, 1);
	    mMenuChildScene.addMenuItem(buyBikeMenuItem);
	    
	    final IMenuItem optionsMenuItem = new ScaleMenuItemDecorator(
	    		new SpriteMenuItem(MENU_OPTIONS, resourcesManager.options_button_region, vbom), 0.8f, 1);
	    mMenuChildScene.addMenuItem(optionsMenuItem);
	    
	    final IMenuItem helpMenuItem = new ScaleMenuItemDecorator(
	    		new SpriteMenuItem(MENU_HELP, resourcesManager.help_button_region, vbom), 0.8f, 1);
	    mMenuChildScene.addMenuItem(helpMenuItem);
	    
	    mMenuChildScene.buildAnimations();
	    mMenuChildScene.setBackgroundEnabled(false);
	    
	    buyMenuItem.setPosition(50, 10);
	    buyBikeMenuItem.setPosition(50, mDisplayHeight-100);
	    optionsMenuItem.setPosition(1170, mDisplayHeight-100);
	    helpMenuItem.setPosition(1070, mDisplayHeight-100);
	    
	    mMenuChildScene.setOnMenuItemClickListener(this);
	    
	    setChildScene(mMenuChildScene);
	    updateMenuChildScene();
	}
    
    private void updateMenuChildScene()
	{
    	if(!mPlayerDataManager.getLevelAccess(mMenuBackground.mLevelList.get(
    			mMenuBackground.mCurrentLevel)))
    		mMenuChildScene.getChildByIndex(0).setY(10);
    	else
    		mMenuChildScene.getChildByIndex(0).setY(-1000);
    	
    	if(mMenuBackground.mLevelList.get(mMenuBackground.mCurrentLevel)=="Coming soon")
    		mMenuChildScene.getChildByIndex(0).setY(-1000);
    	
    	mMenuChildScene.getChildByIndex(0).detachChildren();
    	Entity scene = mMenuInfo.showLevelCost(mMenuBackground.mLevelCostList.get(
    			mMenuBackground.mCurrentLevel));
    	scene.setX(30);
    	mMenuChildScene.getChildByIndex(0).attachChild(scene);
    	
    	if(!mPlayerDataManager.getBikeAccess(mMenuBiker.mLevelList.get(mMenuBiker.mCurrentLevel)))
    		mMenuChildScene.getChildByIndex(1).setY(mDisplayHeight-100);
    	else
    		mMenuChildScene.getChildByIndex(1).setY(-1000);
    	
    	mMenuChildScene.getChildByIndex(1).detachChildren();
    	Entity scene1 = mMenuInfo.showLevelCost(mMenuBiker.mLevelCostList.get(mMenuBiker.mCurrentLevel));
    	scene1.setX(30);
    	mMenuChildScene.getChildByIndex(1).attachChild(scene1);
    	
    	
	}
    
	//---------------------------------------------
    // PUBLIC METHODS
    //---------------------------------------------
	
    public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent)
    {
    	if(!mIsOptionsOpen)
    	{
	    	if (pSceneTouchEvent.isActionDown())
	        {
	        	mStartTapX=pSceneTouchEvent.getX();
	        	mStartTapY=pSceneTouchEvent.getY();
	        }
	    	
	        if (pSceneTouchEvent.isActionUp())
	        {
	        	Vector2 tap = new Vector2(mStartTapX-pSceneTouchEvent.getX(),
	        			mStartTapY-pSceneTouchEvent.getY());
	        	if(tap.len()>50)
	        	{
	        		if(mStartTapY<mDisplayHeight/2)
	        		{
		        		if(tap.x>0)
		            	{
		        			mMenuBackground.next();
		        			mMenuInfo.setBikeName(mMenuBiker.mLevelList.get(mMenuBiker.mCurrentLevel));
		        	    	mMenuInfo.setLevelName(
		        	    			mMenuBackground.mLevelList.get(mMenuBackground.mCurrentLevel));
		        	    	mMenuInfo.update();
		        	    	updateMenuChildScene();
		            	}else
		            	{
		            		mMenuBackground.prev();
		            		mMenuInfo.setBikeName(mMenuBiker.mLevelList.get(mMenuBiker.mCurrentLevel));
		                	mMenuInfo.setLevelName(
		                			mMenuBackground.mLevelList.get(mMenuBackground.mCurrentLevel));
		                	mMenuInfo.update();
		                	updateMenuChildScene();
		            	}
		        		this.resourcesManager.soundManager.changeStage(
	        	    			mMenuBackground.mLevelList.get(mMenuBackground.mCurrentLevel));
	        		}else
	        		{
	        			if(tap.x>0)
		            	{
	        				mMenuBiker.next();
	        				mMenuInfo.setBikeName(mMenuBiker.mLevelList.get(mMenuBiker.mCurrentLevel));
	        				mMenuInfo.setLevelName(
	        						mMenuBackground.mLevelList.get(mMenuBackground.mCurrentLevel));
	        				mMenuInfo.update();
	        				updateMenuChildScene();
		        	    	
		            	}else
		            	{
		            		mMenuBiker.prev();
		            		mMenuInfo.setBikeName(mMenuBiker.mLevelList.get(mMenuBiker.mCurrentLevel));
		            		mMenuInfo.setLevelName(
		            				mMenuBackground.mLevelList.get(mMenuBackground.mCurrentLevel));
		            		mMenuInfo.update();
		            		updateMenuChildScene();
		            	}	
	        		}
	        	}
	        	else
	        	{
	        		if(mPlayerDataManager.getLevelAccess(
	        				mMenuBackground.mLevelList.get(mMenuBackground.mCurrentLevel))&&
	        				mPlayerDataManager.getBikeAccess(mMenuBiker.mLevelList.get(
	        						mMenuBiker.mCurrentLevel)))
	        			SceneManager.getInstance().loadGameScene(engine,
	        					mMenuBackground.mLevelList.get(mMenuBackground.mCurrentLevel), 
	        					mMenuBiker.mLevelList.get(mMenuBiker.mCurrentLevel));
	        	}
	        }
    	}
    	
    	return true;
    }
        
    public boolean onMenuItemClicked(MenuScene pMenuScene, 
    		IMenuItem pMenuItem, float pMenuItemLocalX, float pMenuItemLocalY)
    {
            switch(pMenuItem.getID())
            {
            case MENU_BUY:
            	buyLevel();
                return true;
            case MENU_BUY_BIKE:
            	buyBike();
                return true;
            case MENU_OPTIONS:
            	optionsClick();
                return true;
            case MENU_HELP:
            	showTutorial();
                return true;
            default:
                return false;
        }
    }
    
    public void optionsClick()
    {
    	if(mIsOptionsOpen)
    	{
    		mIsOptionsOpen=false;
    		createMenuChildScene();
    	}
    	else
    	{
    		mIsOptionsOpen=true;
    		createOptionsMenu();
    	}
    }
    
}
