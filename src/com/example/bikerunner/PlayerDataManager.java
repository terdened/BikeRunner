package com.example.bikerunner;

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
		String key = "com.example.bikerunner.highscore";
		pPrefs.edit().putInt(key, newHighScore).commit();
	}
	
	public int getHighScore()
	{
		String key = "com.example.bikerunner.highscore";
		int highScore = pPrefs.getInt(key, 0);
		return highScore;
		
	}
	
	public void addCoins(int coins)
	{
		String key = "com.example.bikerunner.coins";
		pPrefs.edit().putInt(key, coins+getCoins()).commit();
	}
	
	public void removeCoins(int coins)
	{
		if(coins<=getCoins())
		{
			String key = "com.example.bikerunner.coins";
			pPrefs.edit().putInt(key, getCoins()-coins).commit();
		}
	}
	
	public int getCoins()
	{
		String key = "com.example.bikerunner.coins";
		int coins = pPrefs.getInt(key, 0);
		return coins;
		
	}
}
