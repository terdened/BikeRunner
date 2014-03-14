package com.example.bikerunner;

import org.andengine.entity.sprite.AnimatedSprite;

public class JumpUpAnimation extends Animation{

	private float mDeltaY;
	
	JumpUpAnimation(Biker target) {
		super(target);
		mDeltaY=-20;
	}
	
	@Override
	public void update()
	{
		this.pCounter--;
		mDeltaY+=0.5f;
		pTarget.setY(pTarget.getY()+mDeltaY);
		if(pCounter==pDuration*0.33)
		{
			pTarget.moveUp();
		}else
		if(pCounter==pDuration*0.66)
		{
			pTarget.moveUp();
		}
	}
	
	@Override
	public void initAnimation(String title, int duration)
	{
		pCounter = duration;
		pDuration = duration;
		pAnimationTitle = title;
		pIsStarted = true;
	}
	
	@Override
	public boolean canDelete()
	{
		if(pCounter<=0)
			return true;
		else
			return false;
		
	}

}
