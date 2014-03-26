package com.example.brutal;

import org.andengine.entity.sprite.AnimatedSprite;

public class ReturnLeftAnimation extends Animation{

	ReturnLeftAnimation(Biker target) {
		super(target);
	}
	
	@Override
	public void update()
	{
		this.pCounter--;
		pTarget.setX(pTarget.getX()-28);
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
