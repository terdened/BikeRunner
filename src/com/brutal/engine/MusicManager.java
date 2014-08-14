package com.brutal.engine;

import org.andengine.audio.music.Music;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

public class MusicManager {
	
	//---------------------------------------------
    // VARIABLES
    //---------------------------------------------

	private final ResourcesManager mResourcesManager;
	
	private Music mCurrentMusic;
	
	//---------------------------------------------
    // CONSTRUCTOR
    //---------------------------------------------
	
	MusicManager(final ResourcesManager pResourcesManager)
	{
		mResourcesManager=pResourcesManager;
		mCurrentMusic=mResourcesManager.game;
		mCurrentMusic.setLooping(false);
		mCurrentMusic.setOnCompletionListener(new OnCompletionListener()
        {
			@Override
			public void onCompletion( final MediaPlayer pMediaPlayer )
			{
				mCurrentMusic.seekTo(0);
				mCurrentMusic.resume();
			}
        });
		
	}
	
	//---------------------------------------------
    // PUBLIC METHODS
    //---------------------------------------------
	
	public void onComplete()
	{
		
	}
	
	public void playMusic()
    {
		mCurrentMusic.play();
		mCurrentMusic.setVolume(0.5f);

		mResourcesManager.motorSound.setLooping(true);
		mResourcesManager.motorSound.play();
		mResourcesManager.motorSound.setVolume(0.3f);
    }
    
    public void resumeMusic()
    {
    	mCurrentMusic.setVolume(0.5f);
    	mCurrentMusic.resume();
    	mResourcesManager.motorSound.setVolume(0.3f);
    	mResourcesManager.motorSound.resume();
    }
    
    public void lowerMusic()
    {
    	mCurrentMusic.setVolume(0.2f);
    	mCurrentMusic.resume();
    	mResourcesManager.motorSound.pause();
    }
    
    public void stopMusic()
    {
    	mCurrentMusic.pause();
    	mResourcesManager.motorSound.pause();
    }

}
