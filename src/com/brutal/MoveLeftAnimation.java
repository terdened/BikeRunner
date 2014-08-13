package com.brutal;

public class MoveLeftAnimation extends Animation{

	//---------------------------------------------
    // CONSTRUCTOR
    //---------------------------------------------
	
	MoveLeftAnimation(Biker pTarget) {
		super(pTarget);
	}
	
	//---------------------------------------------
    // OVERLOADED METHODS
    //---------------------------------------------
	
	@Override
	public void update()
	{
		this.mCounter--;
		mTarget.setX(mTarget.getX()-28);
		if(mCounter==mDuration/2)
		{
			if(mTarget.moveLeft())
			{
				if(mTarget.getLine()==0)
				{
					final long[] PLAYER_ANIMATE = new long[] {200, 0,  0, 200, 0, 0 };
					mTarget.animate(PLAYER_ANIMATE, 0, 5, true);
				}else
				if(mTarget.getLine()==1)
				{
					final long[] PLAYER_ANIMATE = new long[] {  0,200,0, 0, 200, 0  };
					mTarget.animate(PLAYER_ANIMATE, 0, 5, true);
				}
			}else
			{
				mTarget.returnRight();
			}
		}
	}
	
	@Override
	public boolean canDelete()
	{
		if(mCounter<=0)
		{
			return true;
		}else
			return false;
	}

}
