package com.example.bikerunner;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.IUpdateHandler;
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

import com.example.bikerunner.SceneManager;
import com.example.bikerunner.SceneManager.SceneType;

public class MainMenuScene extends BaseScene implements IOnMenuItemClickListener, IOnSceneTouchListener
{
	
	private MenuScene menuChildScene;
	private final int MENU_PLAY = 0;
	private final int MENU_OPTIONS = 1;
	
	private int pSpeed=10;

	private AnimatedSprite mBike;
	private MenuInfo pMenuInfo;
	private MenuBackground pMenuBackground;
	private PlayerDataManager pPlayerDataManager;
	
    @Override
    public void createScene()
    {
    	createPlayerDataManager();
    	createBackground();
    	createBike();
        //createMenuChildScene();
        createInfoText();
        

    	this.setOnSceneTouchListener(this);
        this.registerUpdateHandler(new IUpdateHandler() {
      		 
            @Override
            public void reset() { }
     
            @Override
            public void onUpdate(final float pSecondsElapsed) 
            { 
            	pMenuBackground.update(pSpeed);
            }
            	
        });
    }
    
    public boolean onSceneTouchEvent(Scene pScene, TouchEvent pSceneTouchEvent)
    {
    	SceneManager.getInstance().loadGameScene(engine);
    	return true;
    }
    
    
    public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem, float pMenuItemLocalX, float pMenuItemLocalY)
    {
            switch(pMenuItem.getID())
            {
            case MENU_PLAY:
            	SceneManager.getInstance().loadGameScene(engine);
                return true;
            case MENU_OPTIONS:
                return true;
            default:
                return false;
        }
    }
    
    
    private void createMenuChildScene()
	{
    	menuChildScene = new MenuScene(camera);
	    menuChildScene.setPosition(0, 0);
	    
	    final IMenuItem playMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_PLAY, resourcesManager.play_region, vbom), 0.8f, 1);
	    final IMenuItem optionsMenuItem = new ScaleMenuItemDecorator(new SpriteMenuItem(MENU_OPTIONS, resourcesManager.options_region, vbom), 0.8f, 1);
	    
	    menuChildScene.addMenuItem(playMenuItem);
	    menuChildScene.addMenuItem(optionsMenuItem);
	    
	    menuChildScene.buildAnimations();
	    menuChildScene.setBackgroundEnabled(false);
	    
	    playMenuItem.setPosition(playMenuItem.getX(), playMenuItem.getY() + 10);
	    optionsMenuItem.setPosition(optionsMenuItem.getX(), optionsMenuItem.getY()+30);
	    
	    menuChildScene.setOnMenuItemClickListener(this);
	    
	    setChildScene(menuChildScene);
	}

    private void createBackground()
    {
    	pMenuBackground = new MenuBackground(0,0,this.resourcesManager, this.vbom);
    	this.attachChild(pMenuBackground);
    }
    
    private void createBike()
    {
		mBike = new AnimatedSprite(400, 270, resourcesManager.bike_region[0], vbom);
		this.attachChild(mBike);
    }
    
    private void createPlayerDataManager()
    {
    	pPlayerDataManager=new PlayerDataManager
    			(this.activity.getSharedPreferences("com.example.bikerunner", Context.MODE_PRIVATE));
    }
    
    private void createInfoText()
    {
    	pMenuInfo = new MenuInfo(1280, 800, resourcesManager, vbom, pPlayerDataManager);
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
