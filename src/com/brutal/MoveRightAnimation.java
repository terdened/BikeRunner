package com.brutal;

import org.andengine.entity.sprite.AnimatedSprite;

public class MoveRightAnimation extends Animation{

	MoveRightAnimation(Biker target) {
		super(target);
	}
	
	@Override
	public void update()
	{
		this.pCounter--;
		pTarget.setX(pTarget.getX()+28);
		if(pCounter==pDuration/2)
		{
			if(pTarget.moveRight())
			{
				if(pTarget.getLine()==2)
				{
					final long[] PLAYER_ANIMATE = new long[] { 0, 0, 200, 0, 0, 200 };
					pTarget.animate(PLAYER_ANIMATE, 0, 5, true);
				}else
				if(pTarget.getLine()==1)
				{
					final long[] PLAYER_ANIMATE = new long[] { 0, 200, 0, 0, 200, 0 };
					pTarget.animate(PLAYER_ANIMATE, 0, 5, true);
				}
			}else
			{
				pTarget.returnLeft();
			}
		}
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
