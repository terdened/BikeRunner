package com.brutal;

public class ReturnLeftAnimation extends Animation{

	ReturnLeftAnimation(Biker target) {
		super(target);
	}
	
	@Override
	public void update()
	{
		this.mCounter--;
		mTarget.setX(mTarget.getX()-28);
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
