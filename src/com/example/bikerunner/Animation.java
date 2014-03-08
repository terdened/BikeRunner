package com.example.bikerunner;

import org.andengine.entity.sprite.AnimatedSprite;

public class Animation {
	protected String pAnimationTitle;
	protected int pCounter;
	protected final Biker pTarget;
	protected boolean pIsStarted;
	
	Animation(Biker target)
	{
		pTarget=target;
		pIsStarted=false;
	}
	
	public void initAnimation(String title, int duration)
	{
		pCounter = duration;
		pAnimationTitle = title;
		pIsStarted = true;
	}
	
	public void update()
	{
		
	}
	
	public boolean canDelete()
	{
		return true;
	}
}
