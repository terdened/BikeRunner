package com.example.bikerunner;

import org.andengine.audio.music.Music;

import android.media.MediaPlayer;

public class MusicManager {
	
	private final ResourcesManager pResourcesManager;
	private Music pCurrentMusic;
	private Music pNextMusic;
	
	MusicManager(final ResourcesManager resourcesManager)
	{
		pResourcesManager=resourcesManager;
		pCurrentMusic=pResourcesManager.intro;
		pNextMusic=pResourcesManager.solo;
		pCurrentMusic.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer arg0) {
				changeMusic();
			}
		});
		pNextMusic.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer arg0) {
				changeMusic();
			}
		});
	}
	
	public void playMusic()
    {
		pCurrentMusic.play();
		pCurrentMusic.setVolume(0.5f);
		pCurrentMusic.pause();
		pCurrentMusic.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer arg0) {
				changeMusic();
			}
		});
		pResourcesManager.motorSound.setLooping(true);
		pResourcesManager.motorSound.play();
		pResourcesManager.motorSound.setVolume(0.1f);
		pResourcesManager.motorSound.pause();
    }
    
    public void resumeMusic()
    {
    	pCurrentMusic.setVolume(0.5f);
    	pCurrentMusic.resume();
    	pResourcesManager.motorSound.setVolume(0.1f);
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
    }
    
    public void chengeMode(String mode, boolean now)
    {
    	if(mode=="main")
    	{
    		pNextMusic=pResourcesManager.main;
    	}else
		if(mode=="solo")
    	{
			pNextMusic=pResourcesManager.solo;
    	}else
		if(mode=="intro")
    	{
			pNextMusic=pResourcesManager.intro;
    	}
    		
    	if(now)
    		changeMusic();
    		
    }
    
    
    private void changeMusic()
    {
    	pCurrentMusic.stop();
    	pCurrentMusic=pNextMusic;
    	pCurrentMusic.seekTo(0);
    	pCurrentMusic.setVolume(0.5f);
    	pCurrentMusic.resume();
    }

}
