package com.brutal;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.menu.MenuScene;
import org.andengine.entity.scene.menu.MenuScene.IOnMenuItemClickListener;
import org.andengine.entity.scene.menu.item.IMenuItem;
import org.andengine.entity.scene.menu.item.SpriteMenuItem;
import org.andengine.entity.scene.menu.item.decorator.ScaleMenuItemDecorator;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class OptionsMenu extends MenuScene implements IOnMenuItemClickListener{

	//---------------------------------------------
    // VARIABLES
    //---------------------------------------------
	
	private final PlayerDataManager mDataManager;
	private final ResourcesManager mResourcesManager;
	private final VertexBufferObjectManager mVbom;
	private final MainMenuScene mScene;
	private Text musicText;
	private Text mMusicValue;
	private Text mSoundText;
	private Text mSoundValue;
	private Text muteText;
	
	//---------------------------------------------
    // CONSTRUCTOR
    //---------------------------------------------
	
	public OptionsMenu(Camera pCamera, VertexBufferObjectManager pVertexBufferObject, 
			final PlayerDataManager pDataManager ,ResourcesManager pResourcesManager
			,MainMenuScene pScene) {
		mDataManager=pDataManager;
		mResourcesManager=pResourcesManager;
		mVbom=pVertexBufferObject;
		this.mCamera=pCamera;
		mScene=pScene;
	}
	
	//---------------------------------------------
    // OVERLOADED METHODS
    //---------------------------------------------
	
	@Override
	public boolean onMenuItemClicked(MenuScene pMenuScene, IMenuItem pMenuItem,
			float pMenuItemLocalX, float pMenuItemLocalY) {
		
		switch(pMenuItem.getID())
        {
	        case 0:
	        {
	        	mDataManager.setSoundAvailable(!mDataManager.getSoundAvailable());
	        	updateText();
	        	mResourcesManager.soundManager.updateVolume();
	        	mResourcesManager.soundManager.updateVolume();
	            return true;
	        }
	        case 1:
	        {
	        	mScene.optionsClick();
	            return true;
	        }
	        case 2:
	        	if(mDataManager.getMusicVolume()>0.1)
	        		mDataManager.decreaseMusicVolume();
	        	updateText();
	        	mResourcesManager.soundManager.updateVolume();
	        	mResourcesManager.soundManager.updateVolume();
	        	return true;
	        case 3:
	        	mDataManager.increaseMusicVolume();
	        	updateText();
	        	mResourcesManager.soundManager.updateVolume();
	        	mResourcesManager.soundManager.updateVolume();
	        	return true;
	        case 4:
	        	if(mDataManager.getSoundVolume()>0.1)
	        		mDataManager.decreaseSoundVolume();
	        	updateText();
	        	mResourcesManager.soundManager.updateVolume();
	        	mResourcesManager.soundManager.updateVolume();
	        	return true;
	        case 5:
	        	mDataManager.increaseSoundVolume();
	        	updateText();
	        	mResourcesManager.soundManager.updateVolume();
	        	mResourcesManager.soundManager.updateVolume();
	        	return true;
	        default:
	            return false;
        }
	}
	
	//---------------------------------------------
    // PRIVATE METHODS
    //---------------------------------------------
	
	private void updateText()
	{
		int musicVolume = (int)(mDataManager.getMusicVolume()*10);
   	    mMusicValue.setText(String.valueOf(musicVolume));    
   	    int soundVolume = (int)(mDataManager.getSoundVolume()*10);
   	    mSoundValue.setText(String.valueOf(soundVolume)); 
   	    
   	    String muteValue="On";
	    if(!mDataManager.getSoundAvailable())
	    {
	    	muteValue="Off";
	    }
	    muteText.setText(muteValue) ;
   	}
	
	//---------------------------------------------
    // PUBLIC METHODS
    //---------------------------------------------
	
	public void createMenuChildScene(Camera pCamera)
   	{
   	    Sprite background = new Sprite(0,0,mResourcesManager.pause_background_region,mVbom);
   	    this.attachChild(background);
   	    
   	    final IMenuItem mute = new ScaleMenuItemDecorator(new SpriteMenuItem(
   	    		0, mResourcesManager.mute_button_region, mVbom), 0.8f, 1);
   	    this.addMenuItem(mute);
	    
   	    final IMenuItem close = new ScaleMenuItemDecorator(new SpriteMenuItem(
   	    		1, mResourcesManager.close_button_region, mVbom), 0.8f, 1);
	    this.addMenuItem(close);
	    
	    final IMenuItem musicVolumeDown = new ScaleMenuItemDecorator(new SpriteMenuItem(
	    		2, mResourcesManager.volume_down_button_region, mVbom), 0.8f, 1);
	    this.addMenuItem(musicVolumeDown);
	    final IMenuItem musicVolumeUp = new ScaleMenuItemDecorator(new SpriteMenuItem(
	    		3, mResourcesManager.volume_up_button_region, mVbom), 0.8f, 1);
	    this.addMenuItem(musicVolumeUp);
	    final IMenuItem soundVolumeDown = new ScaleMenuItemDecorator(new SpriteMenuItem(
	    		4, mResourcesManager.volume_down_button_region, mVbom), 0.8f, 1);
	    this.addMenuItem(soundVolumeDown);
	    final IMenuItem soundVolumeUp = new ScaleMenuItemDecorator(new SpriteMenuItem(
	    		5, mResourcesManager.volume_up_button_region, mVbom), 0.8f, 1);
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
   	    
   	    musicText=new Text(20, 150, mResourcesManager.font, "Music", 5, mVbom);
		int musicVolume = (int)(mDataManager.getMusicVolume()*10);
	    mMusicValue=new Text(musicText.getWidth()+170, 150, 
	    		mResourcesManager.font,String.valueOf(musicVolume) , 2 , mVbom);
	    
	    int soundVolume = (int)(mDataManager.getSoundVolume()*10);
	    mSoundText=new Text(20, 300, mResourcesManager.font, "Sound", 5, mVbom);
	    mSoundValue=new Text(mSoundText.getWidth()+170, 300, 
	    		mResourcesManager.font, String.valueOf(soundVolume), 2 , mVbom); 
	    
	    String muteValue="On";
	    if(!mDataManager.getSoundAvailable())
	    {
	    	muteValue="Off";
	    }
	    
	    muteText =new Text(120, 20, mResourcesManager.font, muteValue, 3 , mVbom); 
	    
	    this.attachChild(muteText);
   	    this.attachChild(musicText);
	   	this.attachChild(mMusicValue);
	   	this.attachChild(mSoundText);
	   	this.attachChild(mSoundValue);
   	}

}
