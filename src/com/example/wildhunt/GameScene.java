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
	LinkedList<RoadTile> roadTiles;
	Light light;
    @Override
    public void createScene()
    {
    	createBackground();
        
        createControllers();
        buildings=new LinkedList<Building>();
        roadTiles=new LinkedList<RoadTile>();
        
        createLight();
        
        createRoadTile(0,0);
        createRoadTile(256,0);
        createRoadTile(512,0);
        createRoadTile(768,0);
        createRoadTile(1024,0);
        createRoadTile(0,256);
        createRoadTile(256,256);
        createRoadTile(512,256);
        createRoadTile(768,256);
        createRoadTile(1024,256);
        createRoadTile(0,512);
        createRoadTile(256,512);
        createRoadTile(512,512);
        createRoadTile(768,512);
        createRoadTile(1024,512);
        createRoadTile(0,768);
        createRoadTile(256,768);
        createRoadTile(512,768);
        createRoadTile(768,768);
        createRoadTile(1024,768);
        
        createRoadTile(0,1024);
        createRoadTile(256,1024);
        createRoadTile(512,1024);
        createRoadTile(768,1024);
        createRoadTile(1024,1024);
        
        createRoadTile(0,1280);
        createRoadTile(256,1280);
        createRoadTile(512,1280);
        createRoadTile(768,1280);
        createRoadTile(1024,1280);
        
        createRoadTile(0,1536);
        createRoadTile(256,1536);
        createRoadTile(512,1536);
        createRoadTile(768,1536);
        createRoadTile(1024,1536);
        
        createRoadTile(0,1792);
        createRoadTile(256,1792);
        createRoadTile(512,1792);
        createRoadTile(768,1792);
        createRoadTile(1024,1792);
        
        
        createBuilding(0,0);
        createBuilding(1000,0);
        
        createBuilding(0,500);
        createBuilding(1000,500);
        
        createBuilding(0,1000);
        createBuilding(1000,1000);
        
        createBuilding(0,1500);
        createBuilding(1000,1500);
        
        createBuilding(0,2000);
        createBuilding(1000,2000);
        
        createBuilding(0,2500);
        createBuilding(1000,2500);


        createPlayer();
    }
    

    private void createBackground()
    {
    	Color temp=new Color(0,0.04f,0.06f);
    	setBackground(new Background(temp));

    }
    
    private void createLight()
    {
    	light=new Light(0,0,resourcesManager.light_region,vbom);
    	light.registerUpdateHandler(new IUpdateHandler(){
    		
		 	@Override
		 	public void reset() { }
		 	
    		@Override
    		public final void onUpdate(final float pSecondsElapsed) 
    		{
    			float tempX=player.getX()-(light.getWidth()-player.getWidth())/2;
    			float tempY=player.getY()-(light.getHeight()-player.getHeight())/2;
    			light.setX(tempX);
    			light.setY(tempY);
    		}
    	
    	});
    	
    	light.setColor(0.7f,0.7f,0.7f);
    	light.setScale(2.5f);
    	
    	this.attachChild(light);

    }
    
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
    	
    	Building building=new Building(x,y,resourcesManager.building_region,resourcesManager.wall_region,vbom);
    	buildings.add(building);
    	
    	
    	this.attachChild(building);
    }
    
    private void createRoadTile(float x, float y)
    {
    	
    	RoadTile road=new RoadTile(x,y,resourcesManager.roadtile_region,vbom);
    	roadTiles.add(road);
    	
    	
    	this.attachChild(road);
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
