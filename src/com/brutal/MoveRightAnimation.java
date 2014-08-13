package com.brutal;

public class MoveRightAnimation extends Animation{

	MoveRightAnimation(Biker target) {
		super(target);
	}
	
	@Override
	public void update()
	{
		this.mCounter--;
		mTarget.setX(mTarget.getX()+28);
		if(mCounter==mDuration/2)
		{
			if(mTarget.moveRight())
			{
				if(mTarget.getLine()==2)
				{
					final long[] PLAYER_ANIMATE = new long[] { 0, 0, 200, 0, 0, 200 };
					mTarget.animate(PLAYER_ANIMATE, 0, 5, true);
				}else
				if(mTarget.getLine()==1)
				{
					final long[] PLAYER_ANIMATE = new long[] { 0, 200, 0, 0, 200, 0 };
					mTarget.animate(PLAYER_ANIMATE, 0, 5, true);
				}
			}else
			{
				mTarget.returnLeft();
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
