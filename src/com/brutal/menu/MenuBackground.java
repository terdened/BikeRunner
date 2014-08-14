package com.brutal.menu;

import java.util.LinkedList;

import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.brutal.engine.*;

public class MenuBackground extends Sprite{
	
	//---------------------------------------------
    // VARIABLES
    //---------------------------------------------
	
	private int mSpeed;
	
	public LinkedList<Sprite> mCurrentFrame;
	public LinkedList<String> mLevelList;
	public LinkedList<String> mLevelCostList;
	public int mCurrentLevel;
	public boolean mIsChanged;
	
	//---------------------------------------------
    // CONSTRUCTOR
    //---------------------------------------------
	
	public MenuBackground(float pX, float pY, ResourcesManager pResourcesManager,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(pX, pY, pResourcesManager.empty_region, pVertexBufferObjectManager);
		
		mSpeed=0;
		mIsChanged=false;
		mLevelList= new LinkedList<String>();
		mLevelCostList= new LinkedList<String>();
		mLevelList.add("Desert");
		mLevelCostList.add("10");
		mLevelList.add("Countryside");
		mLevelCostList.add("2000");
		mLevelList.add("Coming soon");
		mLevelCostList.add("0");
		
		mCurrentFrame=new LinkedList<Sprite>();
		mCurrentFrame.add(new Sprite(0, 0, 1280, 960, 
				pResourcesManager.level_background_region[0], pVertexBufferObjectManager));
		this.attachChild(mCurrentFrame.getLast());

		mCurrentFrame.add(new Sprite(1280, 0, 1280, 960, 
				pResourcesManager.level_background_region[1], pVertexBufferObjectManager));
		this.attachChild(mCurrentFrame.getLast());
		
		mCurrentFrame.add(new Sprite(2560, 0, 1280, 960, 
				pResourcesManager.level_background_region[2], pVertexBufferObjectManager));
		this.attachChild(mCurrentFrame.getLast());

	}
	
	//---------------------------------------------
    // PRIVATE METHODS
    //---------------------------------------------
	
	private void moveAllStages()
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
			if(Math.abs(Math.abs(mCurrentFrame.getFirst().getX())
					-Math.abs(mCurrentLevel*1280))<Math.abs(mSpeed))
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
				moveAllStages();
			}else
			{
				mSpeed++;
				moveAllStages();
			}
		}
		else
		{
			mSpeed=0;
		}
		
	}
	

}
