package com.brutal.menu;

import java.util.LinkedList;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.brutal.engine.*;

public class MenuBiker extends Entity {
	
	//---------------------------------------------
    // VARIABLES
    //---------------------------------------------
	
	private int mSpeed;
	
	public LinkedList<AnimatedSprite> mCurrentFrame;
	public AnimatedSprite mBiker;
	public LinkedList<String> mLevelList;
	public LinkedList<String> mLevelCostList;
	public int mCurrentLevel;
	public boolean mIsChanged;
	
	//---------------------------------------------
    // CONSTRUCTOR
    //---------------------------------------------
	
	MenuBiker( ResourcesManager pResourcesManager, VertexBufferObjectManager pVertexBufferObjectManager)
	{
		super();
		
		mSpeed=0;
		mIsChanged=false;
		mLevelList= new LinkedList<String>();
		mLevelCostList= new LinkedList<String>();
		mLevelList.add("Red Harley");
		mLevelCostList.add("10");
		mLevelList.add("Black Harley");
		mLevelCostList.add("500");
		
		mCurrentFrame=new LinkedList<AnimatedSprite>();
		mCurrentFrame.add(new AnimatedSprite(0, 0, pResourcesManager.bike_region[0], pVertexBufferObjectManager));
		final long[] PLAYER_ANIMATE = new long[] { 100, 100 };
		mCurrentFrame.getLast().animate(PLAYER_ANIMATE, 0, 1, true);
		this.attachChild(mCurrentFrame.getLast());

		mCurrentFrame.add(new AnimatedSprite(1280, 0, pResourcesManager.bike_region[1], pVertexBufferObjectManager));
		mCurrentFrame.getLast().animate(PLAYER_ANIMATE, 0, 1, true);
		this.attachChild(mCurrentFrame.getLast());
		
		final long[] ANIMATE = new long[] { 200, 200, 200 };
		mBiker=new AnimatedSprite(390, -30, pResourcesManager.biker_menu_region, pVertexBufferObjectManager);
		mBiker.animate(ANIMATE, 0, 2, true);
		this.attachChild(mBiker);
	}
	
	//---------------------------------------------
    // PRIVATE METHODS
    //---------------------------------------------
	
	private void moveAllBikes()
	{
		for(int i=0;i<mCurrentFrame.size();i++)
		{
			mCurrentFrame.get(i).setX(mCurrentFrame.get(i).getX()+mSpeed);
		}
	}
	
	//---------------------------------------------
    // PUBLIC METHODS
    //---------------------------------------------
	
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
			if(Math.abs(Math.abs(mCurrentFrame.getFirst().getX())-Math.abs(mCurrentLevel*1280))<Math.abs(mSpeed))
			{
				for(int i=0;i<mCurrentFrame.size();i++)
				{
					mCurrentFrame.get(i).setX(-mCurrentLevel*1280+i*1280);
				}
				mSpeed=0;
			}else
			if(mCurrentFrame.getFirst().getX()>-mCurrentLevel*1280)
			{
				mSpeed--;
				moveAllBikes();
			}else
			{
				mSpeed++;
				moveAllBikes();
			}
		}
		else
		{
			mSpeed=0;
		}
		
	}
	
}
