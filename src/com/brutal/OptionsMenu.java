package com.brutal;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.Entity;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.vbo.ISpriteVertexBufferObject;
import org.andengine.entity.text.Text;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.IVertexBufferObject;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class OptionsMenu extends MenuScene implements IOnMenuItemClickListener{

	private final PlayerDataManager pDataManager;
	private final ResourcesManager pResourcesManager;
	private final VertexBufferObjectManager vbom;
	private final MainMenuScene pScene;
	private Text musicText;
	private Text musicValue;
	private Text soundText;
	private Text soundValue;
	private Text muteText;
	
	public OptionsMenu(Camera camera, VertexBufferObjectManager pVertexBufferObject, 
			final PlayerDataManager dataManager	,ResourcesManager resourcesManager
			,MainMenuScene scene) {
		pDataManager=dataManager;
		pResourcesManager=resourcesManager;
		vbom=pVertexBufferObject;
		this.mCamera=camera;
		pScene=scene;
	}
	
	public void createMenuChildScene(Camera camera)
   	{
   	    Sprite background = new Sprite(0,0,pResourcesManager.pause_background_region,vbom) ;
   	    this.attachChild(background);
   	    
   	    final IMenuItem mute = new ScaleMenuItemDecorator(new SpriteMenuItem(0, pResourcesManager.mute_button_region, vbom), 0.8f, 1);
   	    this.addMenuItem(mute);
	    
   	    final IMenuItem close = new ScaleMenuItemDecorator(new SpriteMenuItem(1, pResourcesManager.close_button_region, vbom), 0.8f, 1);
	    this.addMenuItem(close);
	    
	    final IMenuItem musicVolumeDown = new ScaleMenuItemDecorator(new SpriteMenuItem(2, pResourcesManager.volume_down_button_region, vbom), 0.8f, 1);
	    this.addMenuItem(musicVolumeDown);
	    final IMenuItem musicVolumeUp = new ScaleMenuItemDecorator(new SpriteMenuItem(3, pResourcesManager.volume_up_button_region, vbom), 0.8f, 1);
	    this.addMenuItem(musicVolumeUp);
	    final IMenuItem soundVolumeDown = new ScaleMenuItemDecorator(new SpriteMenuItem(4, pResourcesManager.volume_down_button_region, vbom), 0.8f, 1);
	    this.addMenuItem(soundVolumeDown);
	    final IMenuItem soundVolumeUp = new ScaleMenuItemDecorator(new SpriteMenuItem(5, pResourcesManager.volume_up_button_region, vbom), 0.8f, 1);
	    this.addMenuItem(soundVolumeUp);
   	    
   	    this.buildAnimations();
   	    this.setBackgroundEnabled(false);
   	    
   	    mute.setPosition(20, 20);
   	    close.setPosition(400, 20);
   	    
   	    musicVolumeDown.setPosition(200, 150);
   	    musicVolumeUp.setPosition(400, 150);
	    
   		soundVolumeDown.setPosition(200, 300);
   		soundVolumeUp.setPosition(400, 300);
   	    
   	    this.setOnMenuItemClickListener(this);
   	    
   	    musicText=new Text(20, 150, pResourcesManager.font, "Music", 5, vbom);
		int musicVolume = (int)(pDataManager.getMusicVolume()*10);
	    musicValue=new Text(musicText.getWidth()+170, 150, pResourcesManager.font,String.valueOf(musicVolume) , 2 , vbom);
	    
	    int soundVolume = (int)(pDataManager.getSoundVolume()*10);
	    soundText=new Text(20, 300, pResourcesManager.font, "Sound", 5, vbom);
	    soundValue=new Text(soundText.getWidth()+170, 300, pResourcesManager.font, String.valueOf(soundVolume), 2 , vbom); 
	    
	    String muteValue="On";
	    if(!pDataManager.getSoundAvailable())
	    {
	    	muteValue="Off";
	    }
	    
	    muteText =new Text(120, 20, pResourcesManager.font, muteValue, 3 , vbom); 
	    
	    this.attachChild(muteText);
   	    this.attachChild(musicText);
	   	this.attachChild(musicValue);
	   	this.attachChild(soundText);
	   	this.attachChild(soundValue);
   	}
	
	private void updateText()
	{
		int musicVolume = (int)(pDataManager.getMusicVolume()*10);
   	    musicValue.setText(String.valueOf(musicVolume));    
   	    int soundVolume = (int)(pDataManager.getSoundVolume()*10);
   	    soundValue.setText(String.valueOf(soundVolume)); 
   	    
   	    String muteValue="On";
	    if(!pDataManager.getSoundAvailable())
	    {
	    	muteValue="Off";
	    }
	    muteText.setText(muteValue) ;
   	}

	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		
		switch(pMenuItem.getID())
        {
	        case 0:
	        {
	        	pDataManager.setSoundAvailable(!pDataManager.getSoundAvailable());
	        	updateText();
	        	pResourcesManager.soundManager.updateVolume();
	        	pResourcesManager.soundManager.updateVolume();
	            return true;
	        }
	        case 1:
	        {
	        	pScene.optionsClick();
	            return true;
	        }
	        case 2:
	        	if(pDataManager.getMusicVolume()>0.1)
	        		pDataManager.decreaseMusicVolume();
	        	updateText();
	        	pResourcesManager.soundManager.updateVolume();
	        	pResourcesManager.soundManager.updateVolume();
	        	return true;
	        case 3:
	        	pDataManager.increaseMusicVolume();
	        	updateText();
	        	pResourcesManager.soundManager.updateVolume();
	        	pResourcesManager.soundManager.updateVolume();
	        	return true;
	        case 4:
	        	if(pDataManager.getSoundVolume()>0.1)
	        		pDataManager.decreaseSoundVolume();
	        	updateText();
	        	pResourcesManager.soundManager.updateVolume();
	        	pResourcesManager.soundManager.updateVolume();
	        	return true;
	        case 5:
	        	pDataManager.increaseSoundVolume();
	        	updateText();
	        	pResourcesManager.soundManager.updateVolume();
	        	pResourcesManager.soundManager.updateVolume();
	        	return true;
	        default:
	            return false;
        }
	}

}
