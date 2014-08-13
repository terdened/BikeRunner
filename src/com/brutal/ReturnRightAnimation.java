package com.brutal;

public class ReturnRightAnimation extends Animation{

	//---------------------------------------------
    // CONSTRUCTOR
    //---------------------------------------------

	ReturnRightAnimation(Biker pTarget) {
		super(pTarget);
	}
	
	//---------------------------------------------
    // OVERLOADED METHODS
    //---------------------------------------------
	
	@Override
	public void update()
	{
		this.mCounter--;
		mTarget.setX(mTarget.getX()+28);
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
