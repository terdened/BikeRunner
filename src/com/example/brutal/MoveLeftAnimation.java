package com.example.brutal;

import org.andengine.entity.sprite.AnimatedSprite;

public class MoveLeftAnimation extends Animation{

	MoveLeftAnimation(Biker target) {
		super(target);
	}
	
	@Override
	public void update()
	{
		this.pCounter--;
		pTarget.setX(pTarget.getX()-28);
		if(pCounter==pDuration/2)
		{
			pTarget.moveLeft();
		}
	}
	
	@Override
	public boolean canDelete()
	{
		if(pCounter<=0)
		{
			if(pTarget.getLine()==0)
			{
				final long[] PLAYER_ANIMATE = new long[] { 0, 200, 0 };
				pTarget.animate(PLAYER_ANIMATE, 0, 2, true);
			}else
			if(pTarget.getLine()==1)
			{
				final long[] PLAYER_ANIMATE = new long[] { 200, 0,0  };
				pTarget.animate(PLAYER_ANIMATE, 0, 2, true);
			}
			return true;
		}else
			return false;

		
	}

}
