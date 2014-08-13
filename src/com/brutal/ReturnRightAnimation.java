package com.brutal;

import org.andengine.entity.sprite.AnimatedSprite;

public class ReturnRightAnimation extends Animation{

	ReturnRightAnimation(Biker target) {
		super(target);
	}
	
	@Override
	public void update()
	{
		this.pCounter--;
		pTarget.setX(pTarget.getX()+28);
	}
	
	@Override
	public boolean canDelete()
	{
		if(pCounter<=0)
		{
			return true;
		}else
			return false;

		
	}

}
