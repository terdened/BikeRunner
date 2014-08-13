package com.brutal;

import java.util.LinkedList;

import org.andengine.audio.music.Music;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

public class SoundManager {

	//---------------------------------------------
    // VARIABLES
    //---------------------------------------------

	private final ResourcesManager mResourcesManager;
	private final PlayerDataManager mDataManager;
	private String mState;
	private LinkedList<Music> mPlayngMusic;
	private LinkedList<Music> mPlayngSound;
	
	//---------------------------------------------
    // CONSTRUCTOR
    //---------------------------------------------

	public SoundManager(final ResourcesManager pResourcesManager, SharedPreferences pPrefs)
	{
		mResourcesManager=pResourcesManager;
		mDataManager = new PlayerDataManager(pPrefs);
		mPlayngMusic = new LinkedList<Music>();
		mPlayngSound = new LinkedList<Music>();
	}

	//---------------------------------------------
    // PUBLIC METHODS
    //---------------------------------------------

	private void clearPlaylist()
	{
		for(int i=0;i<mPlayngMusic.size();i++)
		{
			try
			{
				mPlayngMusic.get(i).pause();
			}
			finally
			{
				
			}
		}
		
		for(int i=0;i<mPlayngSound.size();i++)
		{
			try
			{
				mPlayngSound.get(i).pause();
			}
			finally
			{
				
			}
		}
		
		mPlayngSound.clear();
		mPlayngMusic.clear();
	}
	
	private void clearSoundlist()
	{
		for(int i=0;i<mPlayngSound.size();i++)
		{
			try
			{
				mPlayngSound.get(i).pause();
			}
			finally
			{
				
			}
		}
		
		mPlayngSound.clear();
	}
	
	private void playPlaylist()
	{
		for(int i=0;i<mPlayngMusic.size();i++)
		{
			mPlayngMusic.get(i).play();
			mPlayngMusic.get(i).setVolume(mDataManager.getMusicVolume());
		}
		
		for(int i=0;i<mPlayngSound.size();i++)
		{
			mPlayngSound.get(i).play();
			mPlayngSound.get(i).setVolume(mDataManager.getSoundVolume());
		}
	}
	
	private void updateSound()
	{
		if(mState=="menu")
		{
			clearPlaylist();
			mPlayngMusic.add(mResourcesManager.menu_1);
			mResourcesManager.menu_1.setLooping(true);
			mResourcesManager.menu_2.setLooping(true);
			mResourcesManager.menu_3.setLooping(true);
			playPlaylist();
		}else
		if(mState=="game")
		{
			clearPlaylist();
			mPlayngMusic.add(mResourcesManager.game);
			mResourcesManager.game.setLooping(true);
			mPlayngSound.add(mResourcesManager.motorSound);
			mPlayngSound.getLast().setLooping(true);
			playPlaylist();
		}
		else
		if(mState=="stop")
		{
			clearPlaylist();
		}
	}
	
	public void crash()
	{
		clearSoundlist();
		if(mPlayngSound.indexOf(mResourcesManager.crashSound)==-1)
			mPlayngSound.add(mResourcesManager.crashSound);
		mResourcesManager.crashSound.seekTo(0);
		mResourcesManager.crashSound.resume();
		updateVolume();
		
		mPlayngMusic.getFirst().setVolume(mDataManager.getMusicVolume()/2);
	}
	
	public void pause()
	{
		clearSoundlist();
		updateVolume();
		
		mPlayngMusic.getFirst().setVolume(mDataManager.getMusicVolume()/2);
	}
	
	public void resume()
	{
		clearSoundlist();
		mPlayngMusic.getFirst().setVolume(mDataManager.getMusicVolume());
		mPlayngSound.add(mResourcesManager.motorSound);
		mResourcesManager.motorSound.seekTo(0);
		mResourcesManager.motorSound.resume();
	}
	
	public void restart()
	{
		clearSoundlist();
		mPlayngMusic.getFirst().setVolume(mDataManager.getMusicVolume());
		mPlayngSound.add(mResourcesManager.motorSound);
		mResourcesManager.motorSound.seekTo(0);
		mResourcesManager.motorSound.resume();
	}
	
	public void collectCoin()
	{
		if(mPlayngSound.indexOf(mResourcesManager.coinSound)==-1)
			mPlayngSound.add(mResourcesManager.coinSound);
		
		mResourcesManager.coinSound.seekTo(0);
		mResourcesManager.coinSound.resume();
		mResourcesManager.coinSound.setOnCompletionListener(new OnCompletionListener()
		{

			@Override
			public void onCompletion(MediaPlayer arg0) {
				mPlayngSound.remove(this);
				
			}
			
		});
		updateVolume();
	}
	
	public void changeStage(String pStage)
	{
		if(pStage=="Desert")
		{
			clearPlaylist();
			mPlayngMusic.add(mResourcesManager.menu_1);
			playPlaylist();
		}else
		if(pStage=="Countryside")
		{
			clearPlaylist();
			mPlayngMusic.add(mResourcesManager.menu_2);
			playPlaylist();
		}else
		if(pStage=="Coming soon")
		{
			clearPlaylist();
			mPlayngMusic.add(mResourcesManager.menu_3);
			playPlaylist();
		}else
		{
			clearPlaylist();
			mPlayngMusic.add(mResourcesManager.menu_1);
			playPlaylist();
		}
	}
	
	public void moveRight()
	{
		if(mPlayngSound.indexOf(mResourcesManager.moveRightSound)==-1)
			mPlayngSound.add(mResourcesManager.moveRightSound);
		
		mResourcesManager.moveRightSound.seekTo(0);
		mResourcesManager.moveRightSound.resume();
		mResourcesManager.moveRightSound.setOnCompletionListener(new OnCompletionListener()
		{

			@Override
			public void onCompletion(MediaPlayer arg0) {
				mPlayngSound.remove(this);
				
			}
			
		});
		
		updateVolume();
	}
	
	public void moveLeft()
	{
		if(mPlayngSound.indexOf(mResourcesManager.moveLeftSound)==-1)
			mPlayngSound.add(mResourcesManager.moveLeftSound);
		mResourcesManager.moveLeftSound.seekTo(0);
		mResourcesManager.moveLeftSound.resume();
		mResourcesManager.moveLeftSound.setOnCompletionListener(new OnCompletionListener()
		{

			@Override
			public void onCompletion(MediaPlayer arg0) {
				mPlayngSound.remove(this);
				
			}
			
		});
		updateVolume();
	}
	
	public void updateVolume()
	{
		for(int i=0;i<mPlayngMusic.size();i++)
		{
			mPlayngMusic.get(i).setVolume(mDataManager.getMusicVolume());
		}
		
		for(int i=0;i<mPlayngSound.size();i++)
		{
			mPlayngSound.get(i).setVolume(mDataManager.getSoundVolume());
		}
	}
	
	//---------------------------------------------
    // SETTERS
    //---------------------------------------------
	
	public void setState(String pState)
	{
		mState=pState;
		updateSound();
	}
	
	//---------------------------------------------
    // GETTERS
    //---------------------------------------------
	
	public String getState()
	{
		return this.mState;
	}
	
}
