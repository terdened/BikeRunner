package com.brutal;

import android.content.SharedPreferences;

public class PlayerDataManager {
	
	//---------------------------------------------
    // VARIABLES
    //---------------------------------------------
	
	private SharedPreferences mPrefs;
	
	//---------------------------------------------
    // CONSTRUCTOR
    //---------------------------------------------
	
	public PlayerDataManager(SharedPreferences pPrefs) 
	{
		mPrefs = pPrefs;
	}

	//---------------------------------------------
    // PUBLIC METHODS
    //---------------------------------------------
	
	public void updateHighScore(int pNewHighScore)
	{
		String key = "com.example.brutal.highscore";
		mPrefs.edit().putInt(key, pNewHighScore).commit();
	}
	
	public void updateFirstStart()
	{
		String key = "com.example.brutal.firststart";
		mPrefs.edit().putBoolean(key, true).commit();
	}
	
	public void increaseSoundVolume()
	{
		String key = "com.example.brutal.soundvolume";
		float volume = mPrefs.getFloat(key, 0.4f);
		if(volume<1)
		{
			volume+=0.1f;
			mPrefs.edit().putFloat(key, volume).commit();
		}
		setSoundAvailable(true);
	}
	
	public void decreaseSoundVolume()
	{
		
		String key = "com.example.brutal.soundvolume";
		float volume = mPrefs.getFloat(key, 0.4f);
		volume-=0.1f;
		mPrefs.edit().putFloat(key, volume).commit();
		setSoundAvailable(true);
	}
	
	public void increaseMusicVolume()
	{
		String key = "com.example.brutal.musicvolume";
		float volume = mPrefs.getFloat(key, 0.7f);
		if(volume<1)
		{
			volume+=0.1f;
			mPrefs.edit().putFloat(key, volume).commit();
		}
		setSoundAvailable(true);
	}
	
	public void decreaseMusicVolume()
	{
		String key = "com.example.brutal.musicvolume";
		float volume = mPrefs.getFloat(key, 0.7f);
		volume-=0.1f;
		mPrefs.edit().putFloat(key, volume).commit();
		setSoundAvailable(true);
	}
	
	public void addCoins(int pCoins)
	{
		String key = "com.example.brutal.coins";
		mPrefs.edit().putInt(key, pCoins+getCoins()).commit();
	}
	
	public void removeCoins(int pCoins)
	{
		if(pCoins<=getCoins())
		{
			String key = "com.example.brutal.coins";
			mPrefs.edit().putInt(key, getCoins()-pCoins).commit();
		}
	}
	
	//---------------------------------------------
    // SETTERS
    //---------------------------------------------


	public void setSoundAvailable(boolean pMusic)
	{
		String key = "com.example.brutal.musicavailable";
		mPrefs.edit().putBoolean(key, pMusic).commit();
	}
	
	public void setMusicVolume(float pVolume)
	{
		String key = "com.example.brutal.musicvolume";
		mPrefs.edit().putFloat(key, pVolume).commit();
		setSoundAvailable(true);
	}
	
	public void setSoundVolume(float pVolume)
	{
		String key = "com.example.brutal.soundvolume";
		mPrefs.edit().putFloat(key, pVolume).commit();
		setSoundAvailable(true);
	}
	
	public void setLevelAccess(String pLevel)
	{		
		if(pLevel=="Desert")
		{
			String key = "com.example.brutal.desertaccess";
			mPrefs.edit().putBoolean(key, true).commit();
		}
		else
		if(pLevel=="Countryside")
		{
			String key = "com.example.brutal.countrisideaccess";
			mPrefs.edit().putBoolean(key, true).commit();
		}
	}
	
	public void setBikeAccess(String pBike)
	{		
		if(pBike=="Red Harley")
		{
			String key = "com.example.brutal.redharleyaccess";
			mPrefs.edit().putBoolean(key, true).commit();
		}
		else
		if(pBike=="Black Harley")
		{
			String key = "com.example.brutal.blackharleyaccess";
			mPrefs.edit().putBoolean(key, true).commit();
		}
	}
	
	//---------------------------------------------
    // GETTERS
    //---------------------------------------------

	
	public int getHighScore()
	{
		String key = "com.example.brutal.highscore";
		int highScore = mPrefs.getInt(key, 0);
		return highScore;
		
	}
	
	public Boolean getFirstStart()
	{
		String key = "com.example.brutal.firststart";
		boolean firstStart = mPrefs.getBoolean(key, false);
		return firstStart;
		
	}
	
	public boolean getSoundAvailable()
	{
		String key = "com.example.brutal.musicavailable";
		boolean music = mPrefs.getBoolean(key, true);
		return music;
	}
	
	public float getMusicVolume()
	{
		float volume =0;
		if(getSoundAvailable())
		{
			String key = "com.example.brutal.musicvolume";
			volume = mPrefs.getFloat(key, 0.7f);
			
		}
		
		return volume;
	}
	
	public float getSoundVolume()
	{
		if(getSoundAvailable())
		{
			String key = "com.example.brutal.soundvolume";
			float volume = mPrefs.getFloat(key, 0.4f);
			return volume;
		}else
		{
			return 0;
		}
	}
	
	public boolean getLevelAccess(String pLevel)
	{
		boolean result=false;
		
		if(pLevel=="Desert")
		{
			String key = "com.example.brutal.desertaccess";
			result = mPrefs.getBoolean(key, true);
		}
		else
		if(pLevel=="Countryside")
		{
			String key = "com.example.brutal.countrisideaccess";
			result = mPrefs.getBoolean(key, false);
		}
		else
		if(pLevel=="Coming soon")
		{
			result = false;
		}
		
		return result;
	}
	
	public boolean getBikeAccess(String pBike)
	{
		boolean result=false;
		
		if(pBike=="Red Harley")
		{
			String key = "com.example.brutal.redharleyaccess";
			result = mPrefs.getBoolean(key, true);
		}
		else
		if(pBike=="Black Harley")
		{
			String key = "com.example.brutal.blackharleyaccess";
			result = mPrefs.getBoolean(key, false);
		}
		
		return result;
	}
	
	public int getCoins()
	{
		String key = "com.example.brutal.coins";
		int coins = mPrefs.getInt(key, 0);
		return coins;
		
	}
	
	
	
	
}
