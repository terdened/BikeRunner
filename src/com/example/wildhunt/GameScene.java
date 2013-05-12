package com.example.wildhunt;

import java.util.LinkedList;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl;
import org.andengine.engine.camera.hud.controls.BaseOnScreenControl;
import org.andengine.engine.camera.hud.controls.AnalogOnScreenControl.IAnalogOnScreenControlListener;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.util.GLState;
import org.andengine.util.color.Color;


import android.opengl.GLES20;

import com.example.wildhunt.SceneManager.SceneType;

public class GameScene extends BaseScene
{
		
	Player player;
	Sprite background;
	LinkedList<Building> buildings;
    @Override
    public void createScene()
    {
    	setBackground(new Background(Color.BLACK));
    	//createBackground();
        
        createControllers();
        buildings=new LinkedList<Building>();
        
        createBuilding(0,0);
        createBuilding(400,0);
        createBuilding(800,0);
        createBuilding(1200,0);
        createBuilding(1600,0);
        createBuilding(2000,0);
        createBuilding(2400,0);

        createBuilding(0,500);
        createBuilding(400,500);
        createBuilding(800,500);
        createBuilding(1200,500);
        createBuilding(1600,500);
        createBuilding(2000,500);
        createBuilding(2400,500);
        
        createBuilding(0,1000);
        createBuilding(400,1000);
        createBuilding(800,1000);
        createBuilding(1200,1000);
        createBuilding(1600,1000);
        createBuilding(2000,1000);
        createBuilding(2400,1000);
        createPlayer();
    }
    

    /*private void createBackground()
    {
    	background=new Sprite(0,0,resourcesManager.background_region,vbom);
    	this.attachChild(background);
    }*/
    
    private void createPlayer()
    {
    	player=new Player(640.0f,350.0f,resourcesManager.player_region,vbom);
    	player.registerUpdateHandler(new IUpdateHandler(){
    		
    		 	@Override
    		 	public void reset() { }
    		 	
        		@Override
        		public final void onUpdate(final float pSecondsElapsed) 
        		{
        			player.move();
        			for(int i=0;i<buildings.size();i++)
        			{
        				buildings.get(i).setBrightness(player.getX()+player.getWidth()/2, player.getY()+player.getHeight()/2);
        			}
        		}
        	
    	});
    	this.attachChild(player);
    	camera.setChaseEntity(player);
    }
    
    private void createBuilding(float x, float y)
    {
    	
    	Building building=new Building(x,y,resourcesManager.building_region,vbom);
    	buildings.add(building);
    	
    	
    	this.attachChild(building);
    }
    
    private void createControllers()
	{
	    
		final AnalogOnScreenControl analogOnScreenControl = new AnalogOnScreenControl (40, 600, camera, resourcesManager.base_region, resourcesManager.knob_region, 0.1f, 200, vbom , new IAnalogOnScreenControlListener() {
			@Override
			public void onControlChange(final BaseOnScreenControl pBaseOnScreenControl, final float pValueX, final float pValueY) {
				player.setAcceleration(pValueX,pValueY);
				
				
				//else
				//player.setRotation(0);
			}

			@Override
			public void onControlClick(final AnalogOnScreenControl pAnalogOnScreenControl) {
				
			}
		});
		analogOnScreenControl.getControlBase().setBlendFunction(GLES20.GL_SRC_ALPHA, GLES20.GL_ONE_MINUS_SRC_ALPHA);
		analogOnScreenControl.getControlBase().setAlpha(0.5f);
		analogOnScreenControl.getControlBase().setScaleCenter(0, 128);
		analogOnScreenControl.getControlBase().setScale(1.25f);
		analogOnScreenControl.getControlKnob().setScale(1.25f);
		analogOnScreenControl.refreshControlKnobPosition();
	    
		setChildScene(analogOnScreenControl);
	    
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
}
