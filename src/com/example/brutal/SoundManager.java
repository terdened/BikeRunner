package com.example.brutal;

import java.util.LinkedList;

import org.andengine.audio.music.Music;
import org.andengine.audio.sound.Sound;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

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
			pPlayngMusic.add(pResourcesManager.menu_1);
			pResourcesManager.menu_1.setLooping(true);
			pResourcesManager.menu_2.setLooping(true);
			pResourcesManager.menu_3.setLooping(true);
			playPlaylist();
		}else
		if(pState=="game")
		{
			clearPlaylist();
			pPlayngMusic.add(pResourcesManager.game);
			pResourcesManager.game.setLooping(true);
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
		if(pPlayngSound.indexOf(pResourcesManager.crashSound)==-1)
			pPlayngSound.add(pResourcesManager.crashSound);
		pResourcesManager.crashSound.seekTo(0);
		pResourcesManager.crashSound.resume();
		updateVolume();
		
		pPlayngMusic.getFirst().setVolume(pDataManager.getMusicVolume()/2);
	}
	
	public void pause()
	{
		clearSoundlist();
		updateVolume();
		
		pPlayngMusic.getFirst().setVolume(pDataManager.getMusicVolume()/2);
	}
	
	public void resume()
	{
		clearSoundlist();
		pPlayngMusic.getFirst().setVolume(pDataManager.getMusicVolume());
		pPlayngSound.add(pResourcesManager.motorSound);
		pResourcesManager.motorSound.seekTo(0);
		pResourcesManager.motorSound.resume();
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
		if(pPlayngSound.indexOf(pResourcesManager.coinSound)==-1)
			pPlayngSound.add(pResourcesManager.coinSound);
		
		pResourcesManager.coinSound.seekTo(0);
		pResourcesManager.coinSound.resume();
		pResourcesManager.coinSound.setOnCompletionListener(new OnCompletionListener()
		{

			@Override
			public void onCompletion(MediaPlayer arg0) {
				pPlayngSound.remove(this);
				
			}
			
		});
		updateVolume();
	}
	
	public void changeStage(String stage)
	{
		if(stage=="Desert")
		{
			clearPlaylist();
			pPlayngMusic.add(pResourcesManager.menu_1);
			playPlaylist();
		}else
		if(stage=="Countriside")
		{
			clearPlaylist();
			pPlayngMusic.add(pResourcesManager.menu_2);
			playPlaylist();
		}else
		if(stage=="Coming soon")
		{
			clearPlaylist();
			pPlayngMusic.add(pResourcesManager.menu_3);
			playPlaylist();
		}else
		{
			clearPlaylist();
			pPlayngMusic.add(pResourcesManager.menu_1);
			playPlaylist();
		}
	}
	
	public void moveRight()
	{
		if(pPlayngSound.indexOf(pResourcesManager.moveRightSound)==-1)
			pPlayngSound.add(pResourcesManager.moveRightSound);
		
		pResourcesManager.moveRightSound.seekTo(0);
		pResourcesManager.moveRightSound.resume();
		pResourcesManager.moveRightSound.setOnCompletionListener(new OnCompletionListener()
		{

			@Override
			public void onCompletion(MediaPlayer arg0) {
				pPlayngSound.remove(this);
				
			}
			
		});
		
		updateVolume();
	}
	
	public void moveLeft()
	{
		if(pPlayngSound.indexOf(pResourcesManager.moveLeftSound)==-1)
			pPlayngSound.add(pResourcesManager.moveLeftSound);
		pResourcesManager.moveLeftSound.seekTo(0);
		pResourcesManager.moveLeftSound.resume();
		pResourcesManager.moveLeftSound.setOnCompletionListener(new OnCompletionListener()
		{

			@Override
			public void onCompletion(MediaPlayer arg0) {
				pPlayngSound.remove(this);
				
			}
			
		});
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
	
	public String getState()
	{
		return this.pState;
	}
	
}
