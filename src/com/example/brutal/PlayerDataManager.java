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
		if(bike=="Blue Harley")
		{
			String key = "com.example.brutal.blueharleyaccess";
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
		if(bike=="Blue Harley")
		{
			String key = "com.example.brutal.blueharleyaccess";
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
