package com.example.brutal;

import org.andengine.audio.music.Music;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

public class MusicManager {
	
	private final ResourcesManager pResourcesManager;
	private Music pCurrentMusic;
	
	MusicManager(final ResourcesManager resourcesManager)
	{
		pResourcesManager=resourcesManager;
		pCurrentMusic=pResourcesManager.main;
		pCurrentMusic.setLooping(false);
		pCurrentMusic.setOnCompletionListener(new OnCompletionListener()
        {
			@Override
			public void onCompletion( final MediaPlayer pMediaPlayer )
			{
				pCurrentMusic.seekTo(0);
				pCurrentMusic.resume();
			}
        });
		
	}
	
	public void onComplete()
	{
		
	}
	
	public void playMusic()
    {
		pCurrentMusic.play();
		pCurrentMusic.setVolume(0.5f);

		pResourcesManager.motorSound.setLooping(true);
		pResourcesManager.motorSound.play();
		pResourcesManager.motorSound.setVolume(0.3f);
    }
    
    public void resumeMusic()
    {
    	pCurrentMusic.setVolume(0.5f);
    	pCurrentMusic.resume();
    	pResourcesManager.motorSound.setVolume(0.3f);
    	pResourcesManager.motorSound.resume();
    }
    
    public void lowerMusic()
    {
    	pCurrentMusic.setVolume(0.2f);
    	pCurrentMusic.resume();
    	pResourcesManager.motorSound.pause();
    }
    
    public void stopMusic()
    {
    	pCurrentMusic.pause();
    	pResourcesManager.motorSound.pause();
    }

}
