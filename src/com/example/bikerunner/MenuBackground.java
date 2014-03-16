package com.example.bikerunner;

import java.util.LinkedList;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class MenuBackground extends Sprite{
	
	public Sprite mCurrentFrame;
	public Sprite mNextFrame;
	public Sprite mCurrentFarFrame;
	public Sprite mNextFarFrame;
	
	public LinkedList<String> mLevelList;
	public int mCurrentLevel;
	private final ResourcesManager pResourcesManager;
	private final VertexBufferObjectManager pVbom;
	
	public MenuBackground(float pX, float pY, ResourcesManager resourcesManager,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, resourcesManager.empty_region, pVertexBufferObjectManager);
		
		pVbom = pVertexBufferObjectManager;
		pResourcesManager=resourcesManager;
		mLevelList= new LinkedList<String>();
		mLevelList.add("Desert");
		mLevelList.add("Coming soon");
		mCurrentFrame=new Sprite(0, 0, resourcesManager.level_background_region[0], pVertexBufferObjectManager);
		mNextFrame=new Sprite(1280, 0, resourcesManager.level_background_region[0], pVertexBufferObjectManager);
		this.attachChild(mCurrentFrame);
		this.attachChild(mNextFrame);
		
		mCurrentFarFrame=new Sprite(0, 0, resourcesManager.level_background_far_region[0], pVertexBufferObjectManager);
		mNextFarFrame=new Sprite(1280, 0, resourcesManager.level_background_far_region[0], pVertexBufferObjectManager);
		this.attachChild(mCurrentFarFrame);
		this.attachChild(mNextFarFrame);
	}
	
	public void update(int speed)
	{
		mCurrentFrame.setX(mCurrentFrame.getX()-speed);
		mNextFrame.setX(mNextFrame.getX()-speed);
		
		if(mCurrentFrame.getX()<=-1280)
		{
			this.detachChild(mCurrentFrame);
			mCurrentFrame.dispose();
			mCurrentFrame = mNextFrame;
			mNextFrame=new Sprite(1280, 0, pResourcesManager.level_background_region[mCurrentLevel], pVbom);
			this.attachChild(mNextFrame);
		}
		
		mCurrentFarFrame.setX(mCurrentFarFrame.getX()-speed/2);
		mNextFarFrame.setX(mNextFarFrame.getX()-speed/2);
		
		if(mCurrentFarFrame.getX()<=-1280)
		{
			this.detachChild(mCurrentFarFrame);
			mCurrentFarFrame.dispose();
			mCurrentFarFrame = mNextFarFrame;
			mNextFarFrame=new Sprite(1280, 0, pResourcesManager.level_background_far_region[mCurrentLevel], pVbom);
			this.attachChild(mNextFarFrame);
		}
	}
}
