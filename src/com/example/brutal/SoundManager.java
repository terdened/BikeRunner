package com.example.brutal;

import java.util.LinkedList;

import org.andengine.audio.music.Music;
import org.andengine.audio.sound.Sound;

import android.content.SharedPreferences;

public class SoundManager {

	private final ResourcesManager pResourcesManager;
	private final PlayerDataManager pDataManager;
	private String pState;
	private LinkedList<Music> pPlayngMusic;
	private LinkedList<Music> pPlayngSound;
	
	public SoundManager(final ResourcesManager resourcesManager, SharedPreferences prefs)
	{
		pResourcesManager=resourcesManager;
		pDataManager = new PlayerDataManager(prefs);
		pPlayngMusic = new LinkedList<Music>();
		pPlayngSound = new LinkedList<Music>();
	}
	
	public void setState(String state)
	{
		pState=state;
		updateSound();
	}
	
	private void clearPlaylist()
	{
		for(int i=0;i<pPlayngMusic.size();i++)
		{
			try
			{
				pPlayngMusic.get(i).pause();
			}
			finally
			{
				
			}
		}
		
		for(int i=0;i<pPlayngSound.size();i++)
		{
			try
			{
				pPlayngSound.get(i).pause();
			}
			finally
			{
				
			}
		}
		
		pPlayngSound.clear();
		pPlayngMusic.clear();
	}
	
	private void clearSoundlist()
	{
		for(int i=0;i<pPlayngSound.size();i++)
		{
			try
			{
				pPlayngSound.get(i).pause();
			}
			finally
			{
				
			}
		}
		
		pPlayngSound.clear();
	}
	
	private void playPlaylist()
	{
		for(int i=0;i<pPlayngMusic.size();i++)
		{
			pPlayngMusic.get(i).play();
			pPlayngMusic.get(i).setVolume(pDataManager.getMusicVolume());
		}
		
		for(int i=0;i<pPlayngSound.size();i++)
		{
			pPlayngSound.get(i).play();
			pPlayngSound.get(i).setVolume(pDataManager.getSoundVolume());
		}
	}
	
	private void updateSound()
	{
		if(pState=="menu")
		{
			clearPlaylist();
			pPlayngMusic.add(pResourcesManager.menu);
			playPlaylist();
		}else
		if(pState=="game")
		{
			clearPlaylist();
			pPlayngMusic.add(pResourcesManager.game);
			pPlayngSound.add(pResourcesManager.motorSound);
			pPlayngSound.getLast().setLooping(true);
			playPlaylist();
		}
		else
		if(pState=="stop")
		{
			clearPlaylist();
		}
	}
	
	public void crash()
	{
		clearSoundlist();
		pPlayngSound.add(pResourcesManager.crashSound);
		//pResourcesManager.crashSound.play();
		pResourcesManager.crashSound.seekTo(0);
		pResourcesManager.crashSound.resume();
		updateVolume();
		
		pPlayngMusic.getFirst().setVolume(pDataManager.getMusicVolume()/2);
	}
	
	public void restart()
	{
		clearSoundlist();
		pPlayngMusic.getFirst().setVolume(pDataManager.getMusicVolume());
		pPlayngSound.add(pResourcesManager.motorSound);
		pResourcesManager.motorSound.seekTo(0);
		pResourcesManager.motorSound.resume();
	}
	
	public void collectCoin()
	{
		pPlayngSound.add(pResourcesManager.coinSound);
		pResourcesManager.coinSound.seekTo(0);
		pResourcesManager.coinSound.resume();
		updateVolume();
	}
	
	public void moveRight()
	{
		pPlayngSound.add(pResourcesManager.moveRightSound);
		pPlayngSound.getLast().seekTo(0);
		pPlayngSound.getLast().resume();
		updateVolume();
	}
	
	public void moveLeft()
	{
		pPlayngSound.add(pResourcesManager.moveLeftSound);
		pPlayngSound.getLast().seekTo(0);
		pPlayngSound.getLast().resume();
		updateVolume();
	}
	
	public void updateVolume()
	{
		for(int i=0;i<pPlayngMusic.size();i++)
		{
			pPlayngMusic.get(i).setVolume(pDataManager.getMusicVolume());
		}
		
		for(int i=0;i<pPlayngSound.size();i++)
		{
			pPlayngSound.get(i).setVolume(pDataManager.getSoundVolume());
		}
	}
	
}
