package com.brutal.game.biker;

public class Animation 
{	
	//---------------------------------------------
    // VARIABLES
    //---------------------------------------------
	
	protected String mAnimationTitle;
	protected float mCounter;
	protected int mDuration;
	protected final Biker mTarget;
	protected boolean mIsStarted;
	
    //---------------------------------------------
    // CONSTRUCTOR
    //---------------------------------------------
	
	Animation(Biker pTarget)
	{
		mTarget=pTarget;
		mIsStarted=false;
	}
	
    //---------------------------------------------
    // PUBLIC METHODS
    //---------------------------------------------
	
	public void initAnimation(String pTitle, int pDuration)
	{
		mCounter = pDuration;
		mDuration = pDuration;
		mAnimationTitle = pTitle;
		mIsStarted = true;
	}
	
	public void update()
	{
		
	}
	
	public boolean canDelete()
	{
		return true;
	}
	
	public String getTitle()
	{
		return this.mAnimationTitle;
	}
}
