package com.example.bikerunner;

import java.util.LinkedList;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class MenuBackground extends Sprite{
	
	public LinkedList<Sprite> mCurrentFrame;
	
	public LinkedList<String> mLevelList;
	public LinkedList<String> mLevelCostList;
	
	public int mCurrentLevel;
	private final ResourcesManager pResourcesManager;
	private final VertexBufferObjectManager pVbom;
	public boolean mIsChanged;
	private int pSpeed;
	
	public MenuBackground(float pX, float pY, ResourcesManager resourcesManager,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, resourcesManager.empty_region, pVertexBufferObjectManager);
		
		pSpeed=0;
		mIsChanged=false;
		pVbom = pVertexBufferObjectManager;
		pResourcesManager=resourcesManager;
		mLevelList= new LinkedList<String>();
		mLevelCostList= new LinkedList<String>();
		mLevelList.add("Desert");
		mLevelCostList.add("1500");
		mLevelList.add("Countriside");
		mLevelCostList.add("2500");
		
		mCurrentFrame=new LinkedList<Sprite>();
		mCurrentFrame.add(new Sprite(0, 0, resourcesManager.level_background_region[0], pVertexBufferObjectManager));
		this.attachChild(mCurrentFrame.getLast());

		mCurrentFrame.add(new Sprite(1280, 0, resourcesManager.level_background_region[1], pVertexBufferObjectManager));
		this.attachChild(mCurrentFrame.getLast());

	}
	
	public void next()
	{
		if(mCurrentLevel<mLevelList.size()-1)
		{
			mCurrentLevel++;
			mIsChanged=true;
		}
	}
	
	public void prev()
	{
		if(mCurrentLevel>0)
		{
			mCurrentLevel--;
			mIsChanged=true;
		}
	}
	
	public void update()
	{
		if(mCurrentFrame.getFirst().getX()!=-mCurrentLevel*1280)
		{
			if(Math.abs(Math.abs(mCurrentFrame.getFirst().getX())-Math.abs(mCurrentLevel*1280))<Math.abs(pSpeed))
			{
				for(int i=0;i<mCurrentFrame.size();i++)
				{
					mCurrentFrame.get(i).setX(-mCurrentLevel*1280+i*1280);
				}
				pSpeed=0;
			}else
			if(mCurrentFrame.getFirst().getX()>-mCurrentLevel*1280)
			{
				pSpeed--;
				moveAllStages();
			}else
			{
				pSpeed++;
				moveAllStages();
			}
		}
		else
		{
			pSpeed=0;
		}
		
	}
	
	private void moveAllStages()
	{
		for(int i=0;i<mCurrentFrame.size();i++)
		{
			mCurrentFrame.get(i).setX(mCurrentFrame.get(i).getX()+pSpeed);
		}
	}
}
