package com.brutal.menu;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class Tutorial extends AnimatedSprite
{
	//---------------------------------------------
    // VARIABLES
    //---------------------------------------------

	int mCounter = 0;
	
	//---------------------------------------------
    // CONSTRUCTOR
    //---------------------------------------------

	public Tutorial(float pX, float pY,
			ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pTiledTextureRegion, pVertexBufferObjectManager);
		
		this.setScale(2);
		final long[] PLAYER_ANIMATE = new long[] { 1500, 1500, 2500,
												   2500, 2000, 2500,
												   1500, 2500, 2500,
												   2500, 2500, 1500};
		animate(PLAYER_ANIMATE, 0, 11, false);
		mCounter = 0;
	}
	
	//---------------------------------------------
    // PUBLIC METHODS
    //---------------------------------------------

	public Boolean canDelete()
	{
		mCounter++;
		
		if(this.isAnimationRunning())
			return false;
		else
		{
			this.detachSelf();
			return true;
		}
	}
	
	
}
