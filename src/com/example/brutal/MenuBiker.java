package com.example.brutal;

import java.util.LinkedList;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class MenuBiker extends Entity {
	
	public LinkedList<AnimatedSprite> mCurrentFrame;
	public AnimatedSprite mBiker;
	public LinkedList<String> mLevelList;
	public LinkedList<String> mLevelCostList;
	
	public int mCurrentLevel;
	private final ResourcesManager pResourcesManager;
	private final VertexBufferObjectManager pVbom;
	public boolean mIsChanged;
	private int pSpeed;
	
	MenuBiker( ResourcesManager resourcesManager, VertexBufferObjectManager pVertexBufferObjectManager)
	{
		super();
		
		pVbom=pVertexBufferObjectManager;
		pResourcesManager=resourcesManager;
		
		pSpeed=0;
		mIsChanged=false;
		mLevelList= new LinkedList<String>();
		mLevelCostList= new LinkedList<String>();
		mLevelList.add("Red Harley");
		mLevelCostList.add("1500");
		mLevelList.add("Blue Harley");
		mLevelCostList.add("2500");
		
		mCurrentFrame=new LinkedList<AnimatedSprite>();
		mCurrentFrame.add(new AnimatedSprite(0, 0, resourcesManager.bike_region[0], pVertexBufferObjectManager));
		final long[] PLAYER_ANIMATE = new long[] { 100, 100 };
		mCurrentFrame.getLast().animate(PLAYER_ANIMATE, 0, 1, true);
		this.attachChild(mCurrentFrame.getLast());

		mCurrentFrame.add(new AnimatedSprite(1280, 0, resourcesManager.bike_region[1], pVertexBufferObjectManager));
		mCurrentFrame.getLast().animate(PLAYER_ANIMATE, 0, 1, true);
		this.attachChild(mCurrentFrame.getLast());
		
		mBiker=new AnimatedSprite(380, 50, resourcesManager.biker_menu_region, pVertexBufferObjectManager);
		mBiker.animate(PLAYER_ANIMATE, 0, 1, true);
		this.attachChild(mBiker);
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
				moveAllBikes();
			}else
			{
				pSpeed++;
				moveAllBikes();
			}
		}
		else
		{
			pSpeed=0;
		}
		
	}
	
	private void moveAllBikes()
	{
		for(int i=0;i<mCurrentFrame.size();i++)
		{
			mCurrentFrame.get(i).setX(mCurrentFrame.get(i).getX()+pSpeed);
		}
	}

}
