package com.example.bikerunner;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.Entity;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.input.touch.TouchEvent;
import org.andengine.opengl.util.GLState;
import org.andengine.util.color.Color;


import android.content.Context;
import android.opengl.GLES20;

import com.badlogic.gdx.math.Vector2;
import com.example.bikerunner.SceneManager;
import com.example.bikerunner.SceneManager.SceneType;

public class MainMenuScene extends BaseScene implements IOnMenuItemClickListener, IOnSceneTouchListener
{
	
	private MenuScene menuChildScene;
	private final int MENU_BUY = 0;
	private final int MENU_OPTIONS = 1;
	private final int MENU_BUY_BIKE = 2;
	
	private int pSpeed=10;

	private MenuBiker pMenuBiker;
	private MenuInfo pMenuInfo;
	private MenuBackground pMenuBackground;
	private PlayerDataManager pPlayerDataManager;
	private float pStartTapY=0;
	private float pStartTapX=0;
	
	
    @Override
    public void createScene()
    {
    	createPlayerDataManager();
    	createBackground();
    	createBike();
        //createMenuChildScene();
        createInfoText();
        createMenuChildScene();

    	this.setOnSceneTouchListener(this);
        this.registerUpdateHandler(new IUpdateHandler() {
      		 
            @Override
            public void reset() { }
     
            @Override
            public void onUpdate(final float pSecondsElapsed) 
            { 
            	if(pMenuBackground.mIsChanged)
            	{
            		pSpeed=20;
            	}else
            	{
            		pSpeed=10;
            	}
            	
            	pMenuBackground.update();
            	pMenuBiker.update();
            }
            	
        });
    }
    
    public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent)
    {
    	
    	if (pSceneTouchEvent.isActionDown())
        {
        	pStartTapX=pSceneTouchEvent.getX();
        	pStartTapY=pSceneTouchEvent.getY();
        }
    	
        if (pSceneTouchEvent.isActionUp())
        {
        	Vector2 tap = new Vector2(pStartTapX-pSceneTouchEvent.getX(),pStartTapY-pSceneTouchEvent.getY());
        	if(tap.len()>50)
        	{
        		if(pStartTapY<400)
        		{
	        		if(tap.x>0)
	            	{
	        			pMenuBackground.next();
	        			pMenuInfo.setBikeName(pMenuBiker.mLevelList.get(pMenuBiker.mCurrentLevel));
	        	    	pMenuInfo.setLevelName(pMenuBackground.mLevelList.get(pMenuBackground.mCurrentLevel));
	        	    	pMenuInfo.update();
	        	    	updateMenuChildScene();
	            	}else
	            	{
	            		pMenuBackground.prev();
	            		pMenuInfo.setBikeName(pMenuBiker.mLevelList.get(pMenuBiker.mCurrentLevel));
	                	pMenuInfo.setLevelName(pMenuBackground.mLevelList.get(pMenuBackground.mCurrentLevel));
	                	pMenuInfo.update();
	                	updateMenuChildScene();
	            	}
        		}else
        		{
        			if(tap.x>0)
	            	{
        				pMenuBiker.next();
        				pMenuInfo.setBikeName(pMenuBiker.mLevelList.get(pMenuBiker.mCurrentLevel));
        				pMenuInfo.setLevelName(pMenuBackground.mLevelList.get(pMenuBackground.mCurrentLevel));
        				pMenuInfo.update();
        				updateMenuChildScene();
	        	    	
	            	}else
	            	{
	            		pMenuBiker.prev();
	            		pMenuInfo.setBikeName(pMenuBiker.mLevelList.get(pMenuBiker.mCurrentLevel));
	            		pMenuInfo.setLevelName(pMenuBackground.mLevelList.get(pMenuBackground.mCurrentLevel));
	            		pMenuInfo.update();
	            		updateMenuChildScene();
	            	}	
        		}
        	}
        	else
        	{
        		if(pPlayerDataManager.getLevelAccess(pMenuBackground.mLevelList.get(pMenuBackground.mCurrentLevel))&&
        				pPlayerDataManager.getBikeAccess(pMenuBiker.mLevelList.get(pMenuBiker.mCurrentLevel)))
        			SceneManager.getInstance().loadGameScene(engine,pMenuBackground.mLevelList.get(pMenuBackground.mCurrentLevel));
        	}
        }
    	
    	return true;
    }
    
    private void createMenuChildScene()
	{
    	menuChildScene = new MenuScene(camera);
	    menuChildScene.setPosition(0, 0);
	    
	    final IMenuItem buyMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_BUY, resourcesManager.buy_button_region, vbom), 0.8f, 1);
	    menuChildScene.addMenuItem(buyMenuItem);
	    
	    final IMenuItem buyBikeMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_BUY_BIKE, resourcesManager.buy_button_region, vbom), 0.8f, 1);
	    menuChildScene.addMenuItem(buyBikeMenuItem);
	    
	    menuChildScene.buildAnimations();
	    menuChildScene.setBackgroundEnabled(false);
	    
	    buyMenuItem.setPosition(50, 10);
	    buyBikeMenuItem.setPosition(50, 650);
	    
	    menuChildScene.setOnMenuItemClickListener(this);
	    
	    setChildScene(menuChildScene);
	    updateMenuChildScene();
	}
    
    private void updateMenuChildScene()
	{
    	if(!pPlayerDataManager.getLevelAccess(pMenuBackground.mLevelList.get(pMenuBackground.mCurrentLevel)))
    		menuChildScene.getChildByIndex(0).setY(10);
    	else
    		menuChildScene.getChildByIndex(0).setY(-1000);
    	
    	menuChildScene.getChildByIndex(0).detachChildren();
    	Entity scene = pMenuInfo.showLevelCost(pMenuBackground.mLevelCostList.get(pMenuBackground.mCurrentLevel));
    	scene.setX(30);
    	menuChildScene.getChildByIndex(0).attachChild(scene);
    	
    	if(!pPlayerDataManager.getBikeAccess(pMenuBiker.mLevelList.get(pMenuBiker.mCurrentLevel)))
    		menuChildScene.getChildByIndex(1).setY(650);
    	else
    		menuChildScene.getChildByIndex(1).setY(-1000);
    	
    	menuChildScene.getChildByIndex(1).detachChildren();
    	Entity scene1 = pMenuInfo.showLevelCost(pMenuBiker.mLevelCostList.get(pMenuBiker.mCurrentLevel));
    	scene1.setX(30);
    	menuChildScene.getChildByIndex(1).attachChild(scene1);
	}
    
    public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX, float pMenuItemLocalY)
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
                return true;
            default:
                return false;
        }
    }
    
    private void buyLevel()
    {
    	int cost=Integer.valueOf(pMenuBackground.mLevelCostList.get(pMenuBackground.mCurrentLevel));
    	if(pPlayerDataManager.getCoins()>=cost)
    	{
    		pPlayerDataManager.removeCoins(cost);
    		pPlayerDataManager.setLevelAccess(pMenuBackground.mLevelList.get(pMenuBackground.mCurrentLevel));
    		pMenuInfo.update();
    		updateMenuChildScene();
    	}
    }
    
    private void buyBike()
    {
    	int cost=Integer.valueOf(pMenuBiker.mLevelCostList.get(pMenuBiker.mCurrentLevel));
    	if(pPlayerDataManager.getCoins()>=cost)
    	{
    		pPlayerDataManager.removeCoins(cost);
    		pPlayerDataManager.setBikeAccess(pMenuBiker.mLevelList.get(pMenuBiker.mCurrentLevel));
    		pMenuInfo.update();
    		updateMenuChildScene();
    	}
    }
    

    private void createBackground()
    {
    	pMenuBackground = new MenuBackground(0,0,this.resourcesManager, this.vbom);
    	this.attachChild(pMenuBackground);
    }
    
    private void createBike()
    {
    	pMenuBiker = new MenuBiker( resourcesManager, vbom);
    	pMenuBiker.setPosition(370, 270);
		this.attachChild(pMenuBiker);
    }
    
    private void createPlayerDataManager()
    {
    	pPlayerDataManager=new PlayerDataManager
    			(this.activity.getSharedPreferences("com.example.bikerunner", Context.MODE_PRIVATE));
    }
    
    private void createInfoText()
    {
    	pMenuInfo = new MenuInfo(1280, 800, resourcesManager, vbom, pPlayerDataManager);
    	pMenuInfo.setBikeName(pMenuBiker.mLevelList.get(pMenuBiker.mCurrentLevel));
    	pMenuInfo.setLevelName(pMenuBackground.mLevelList.get(pMenuBackground.mCurrentLevel));
    	pMenuInfo.update();
    	this.attachChild(pMenuInfo);
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
        this.detachSelf();
        this.dispose();
    }
}
