package com.example.bikerunner;

import org.andengine.entity.sprite.AnimatedSprite;

public class MoveDownAnimation extends Animation{

	private float pStartY;
	private float pStopY;
	private float mDeltaY;
	private boolean mIsChangeHeight;
	
	MoveDownAnimation(Biker target) {
		super(target);
		mDeltaY=0;
		mIsChangeHeight=false;
	}
	
	@Override
	public void update()
	{
		mDeltaY+=0.5f;
		pTarget.setY(pTarget.getY()+mDeltaY);
		if(!mIsChangeHeight)
		{
			if(Math.abs(pStopY-pTarget.getY())<50)
			{
				pTarget.moveDown();
				mIsChangeHeight=true;
			}
		}
	}
	
	@Override
	public void initAnimation(String title, int duration)
	{
		pAnimationTitle = title;
		pIsStarted = true;
		pStopY=pTarget.getY()+100;
		pStartY=pTarget.getY();
	}
	
	@Override
	public boolean canDelete()
	{
		if(pTarget.getY()>=pStopY)
		{
			if(!pTarget.thereIsGround())
			{
				pStartY+=100;
				pStopY+=100;
				mIsChangeHeight=false;
				return false;
			}
			else
				return true;
		}
		else
			return false;
		
	}

}
