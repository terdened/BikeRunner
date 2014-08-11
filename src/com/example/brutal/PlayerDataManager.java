package com.example.brutal;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class PlayerDataManager {
	
	private SharedPreferences pPrefs;
	
	public PlayerDataManager(SharedPreferences prefs) 
	{
		pPrefs = prefs;
	}

	public void updateHighScore(int newHighScore)
	{
		String key = "com.example.brutal.highscore";
		pPrefs.edit().putInt(key, newHighScore).commit();
	}
	
	public int getHighScore()
	{
		String key = "com.example.brutal.highscore";
		int highScore = pPrefs.getInt(key, 0);
		return highScore;
		
	}
	
	public void updateFirstStart()
	{
		String key = "com.example.brutal.firststart";
		pPrefs.edit().putBoolean(key, true).commit();
	}
	
	public Boolean getFirstStart()
	{
		String key = "com.example.brutal.firststart";
		boolean firstStart = pPrefs.getBoolean(key, false);
		return firstStart;
		
	}
	
	public void setSoundAvailable(boolean music)
	{
		String key = "com.example.brutal.musicavailable";
		pPrefs.edit().putBoolean(key, music).commit();
	}
	
	public boolean getSoundAvailable()
	{
		String key = "com.example.brutal.musicavailable";
		boolean music = pPrefs.getBoolean(key, true);
		return music;
	}
	
	public void setMusicVolume(float volume)
	{
		String key = "com.example.brutal.musicvolume";
		pPrefs.edit().putFloat(key, volume).commit();
		setSoundAvailable(true);
	}
	
	public float getMusicVolume()
	{
		float volume =0;
		if(getSoundAvailable())
		{
			String key = "com.example.brutal.musicvolume";
			volume = pPrefs.getFloat(key, 1);
			
		}
		
		return volume;
		
		
	}
	
	public void setSoundVolume(float volume)
	{
		String key = "com.example.brutal.soundvolume";
		pPrefs.edit().putFloat(key, volume).commit();
		setSoundAvailable(true);
	}
	
	public void increaseSoundVolume()
	{
		String key = "com.example.brutal.soundvolume";
		float volume = pPrefs.getFloat(key, 1);
		if(volume<1)
		{
			volume+=0.1f;
			pPrefs.edit().putFloat(key, volume).commit();
		}
		setSoundAvailable(true);
	}
	
	public void decreaseSoundVolume()
	{
		
		String key = "com.example.brutal.soundvolume";
		float volume = pPrefs.getFloat(key, 1);
		volume-=0.1f;
		pPrefs.edit().putFloat(key, volume).commit();
		setSoundAvailable(true);
	}
	
	public void increaseMusicVolume()
	{
		String key = "com.example.brutal.musicvolume";
		float volume = pPrefs.getFloat(key, 1);
		if(volume<1)
		{
			volume+=0.1f;
			pPrefs.edit().putFloat(key, volume).commit();
		}
		setSoundAvailable(true);
	}
	
	public void decreaseMusicVolume()
	{
		String key = "com.example.brutal.musicvolume";
		float volume = pPrefs.getFloat(key, 1);
		volume-=0.1f;
		pPrefs.edit().putFloat(key, volume).commit();
		setSoundAvailable(true);
	}
	
	public float getSoundVolume()
	{
		if(getSoundAvailable())
		{
			String key = "com.example.brutal.soundvolume";
			float volume = pPrefs.getFloat(key, 1);
			return volume;
		}else
		{
			return 0;
		}
	}
	
	public boolean getLevelAccess(String level)
	{
		boolean result=false;
		
		if(level=="Desert")
		{
			String key = "com.example.brutal.desertaccess";
			result = pPrefs.getBoolean(key, true);
		}
		else
		if(level=="Countriside")
		{
			String key = "com.example.brutal.countrisideaccess";
			result = pPrefs.getBoolean(key, false);
		}
		else
		if(level=="Coming soon")
		{
			result = false;
		}
		
		return result;
	}
	
	public void setLevelAccess(String level)
	{		
		if(level=="Desert")
		{
			String key = "com.example.brutal.desertaccess";
			pPrefs.edit().putBoolean(key, true).commit();
		}
		else
		if(level=="Countriside")
		{
			String key = "com.example.brutal.countrisideaccess";
			pPrefs.edit().putBoolean(key, true).commit();
		}
	}
	
	public boolean getBikeAccess(String bike)
	{
		boolean result=false;
		
		if(bike=="Red Harley")
		{
			String key = "com.example.brutal.redharleyaccess";
			result = pPrefs.getBoolean(key, true);
		}
		else
		if(bike=="Black Harley")
		{
			String key = "com.example.brutal.blackharleyaccess";
			result = pPrefs.getBoolean(key, false);
		}
		
		return result;
	}
	
	public void setBikeAccess(String bike)
	{		
		if(bike=="Red Harley")
		{
			String key = "com.example.brutal.redharleyaccess";
			pPrefs.edit().putBoolean(key, true).commit();
		}
		else
		if(bike=="Black Harley")
		{
			String key = "com.example.brutal.blackharleyaccess";
			pPrefs.edit().putBoolean(key, true).commit();
		}
	}
	
	public void addCoins(int coins)
	{
		String key = "com.example.brutal.coins";
		pPrefs.edit().putInt(key, coins+getCoins()).commit();
	}
	
	public void removeCoins(int coins)
	{
		if(coins<=getCoins())
		{
			String key = "com.example.brutal.coins";
			pPrefs.edit().putInt(key, getCoins()-coins).commit();
		}
	}
	
	public int getCoins()
	{
		String key = "com.example.brutal.coins";
		int coins = pPrefs.getInt(key, 9999);
		return coins;
		
	}
}
