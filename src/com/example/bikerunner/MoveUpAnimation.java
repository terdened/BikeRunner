package com.example.bikerunner;

import org.andengine.entity.sprite.AnimatedSprite;

public class MoveUpAnimation extends Animation{

	private float mDeltaY;
	
	MoveUpAnimation(Biker target) {
		super(target);
		mDeltaY=-10;
	}
	
	@Override
	public void update()
	{
		this.pCounter--;
		mDeltaY+=0.5f;
		pTarget.setY(pTarget.getY()+mDeltaY);
		if(pCounter==pDuration/2)
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
