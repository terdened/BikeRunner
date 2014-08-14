package com.brutal.menu;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.brutal.engine.*;

public class MenuInfo extends Entity {
	
	//---------------------------------------------
    // VARIABLES
    //---------------------------------------------
	
	private int mHeight;
	private int mWidth;	
	private Text mHighScore;
	private Text mBikeName;
	private Text mLevelName;
	private Text mCoins;
	private Text mTapToPlay;
	private Text mLevelCost;	
	private String mLevelNameString;
	private String mBikeNameString;
	private AnimatedSprite mCoinIcon;
	private Sprite mLockIcon;
	private Sprite mBikeLockIcon;
	private final ResourcesManager mResourcesManager;
	private final VertexBufferObjectManager mVbom;
	private final PlayerDataManager mPlayerDataManager;
	
	//---------------------------------------------
    // CONSTRUCTOR
    //---------------------------------------------
	
	MenuInfo(int pWidth, int pHeight, ResourcesManager pResourcesManager,VertexBufferObjectManager pVbom,
			PlayerDataManager pPlayerDataManager)
	{
		mPlayerDataManager=pPlayerDataManager;
		mResourcesManager=pResourcesManager;
		mVbom=pVbom;
		mHeight=pHeight;
		mWidth=pWidth;
		mLevelNameString="";
		mBikeNameString="";
		init();
	}
	
	//---------------------------------------------
    // PUBLIC METHODS
    //---------------------------------------------
	
	void setLevelName(String pName)
	{
		mLevelNameString=pName;
	}
	
	void setBikeName(String pName)
	{
		mBikeNameString=pName;
	}
	
	public Entity showLevelCost(String pCost)
	{
		Entity layer = new Entity();
		
		mLevelCost = new Text(60, 10, mResourcesManager.font,pCost, pCost.length(), mVbom);
		
		mCoinIcon = new AnimatedSprite(0, 25, mResourcesManager.coin_region, mVbom);
		final long[] PLAYER_ANIMATE = new long[] { 200, 0, 0, 0, 0, 0, 0, 0 };
		mCoinIcon.animate(PLAYER_ANIMATE, 0, 7, true);
		
		layer.attachChild(mCoinIcon);
		layer.attachChild(mLevelCost);
		
		return layer;
	}
	
	public void init()
	{
		mHighScore = new Text(0, 0, mResourcesManager.font, 
				String.valueOf(mPlayerDataManager.getHighScore()), 10, mVbom);
		mHighScore.setX(mWidth-mHighScore.getWidth()-10);
		
		mCoins = new Text(0, 60, mResourcesManager.font, 
				String.valueOf(mPlayerDataManager.getCoins()), 10, mVbom);
		mCoins.setX(mWidth-mCoins.getWidth()-10);
		mCoins.setY(50);
		
		mLevelName= new Text(0, 0, mResourcesManager.font, mLevelNameString, mLevelNameString.length(), mVbom);
		mLevelName.setX(mWidth/2-mLevelName.getWidth()/2);
		
		mBikeName = new Text(0, 0, mResourcesManager.font, mBikeNameString, mBikeNameString.length(), mVbom);
		mBikeName.setX(mWidth/2-mBikeName.getWidth()/2);
		mBikeName.setY(mHeight-130);
		
		if(mPlayerDataManager.getLevelAccess(mLevelNameString)&&mPlayerDataManager.getBikeAccess(mBikeNameString))
		{
			mTapToPlay = new Text(0, 0, mResourcesManager.font,"Tap to play!", 10, mVbom);
			mTapToPlay.setX(mWidth/2-mTapToPlay.getWidth()/2 -100);
			mTapToPlay.setY(mHeight/2-mCoins.getHeight()/2+140);
			this.attachChild(mTapToPlay);
		}
		
		mCoinIcon = new AnimatedSprite(mCoins.getX()-60, mCoins.getY()+15, mResourcesManager.coin_region, mVbom);
		final long[] PLAYER_ANIMATE = new long[] { 200, 0, 0, 0, 0, 0, 0, 0 };
		mCoinIcon.animate(PLAYER_ANIMATE, 0, 7, true);
		
		if(!mPlayerDataManager.getLevelAccess(mLevelNameString))
		{
			mLockIcon = new Sprite(mLevelName.getX()-50, mLevelName.getY()+10, mResourcesManager.lock_region, mVbom);
			this.attachChild(mLockIcon);
		}
		
		if(!mPlayerDataManager.getBikeAccess(mBikeNameString))
		{
			mBikeLockIcon = new Sprite(mBikeName.getX()-50, mBikeName.getY()+10, mResourcesManager.lock_region, mVbom);
			this.attachChild(mBikeLockIcon);
		}
		
		this.attachChild(mBikeName);
		this.attachChild(mLevelName);
		this.attachChild(mCoinIcon);
		this.attachChild(mHighScore);
		this.attachChild(mCoins);
	}
	
	public void update()
	{

		try{
			this.detachChild(mLockIcon);
		}
		finally
		{
		}
		

		try{
			this.detachChild(mBikeLockIcon);
		}
		finally
		{
		}
		
		try{
			this.detachChild(mTapToPlay);
		}
		finally
		{
		}
		
		this.detachChild(mHighScore);
		this.detachChild(mCoins);
		this.detachChild(mCoinIcon);
		this.detachChild(mLevelName);
		this.detachChild(mBikeName);
		
		mHighScore = new Text(0, 0, mResourcesManager.font, 
				String.valueOf(mPlayerDataManager.getHighScore()), 10, mVbom);
		mHighScore.setX(mWidth-mHighScore.getWidth()-10);
		mHighScore.setY(10);
		
		mCoins = new Text(0, 60, mResourcesManager.font, 
				String.valueOf(mPlayerDataManager.getCoins()), 10, mVbom);
		mCoins.setX(mWidth-mCoins.getWidth()-10);
		mCoins.setY(60);
		
		mLevelName= new Text(0, 0, mResourcesManager.font, mLevelNameString, mLevelNameString.length(), mVbom);
		mLevelName.setX(mWidth/2-mLevelName.getWidth()/2);
		
		mBikeName = new Text(0, 0, mResourcesManager.font, mBikeNameString, mBikeNameString.length(), mVbom);
		mBikeName.setX(mWidth/2-mBikeName.getWidth()/2);
		mBikeName.setY(mHeight-70);
		
		if(mPlayerDataManager.getLevelAccess(mLevelNameString)&&mPlayerDataManager.getBikeAccess(mBikeNameString))
		{
			mTapToPlay = new Text(0, 0, mResourcesManager.font,"Tap to play!", 10, mVbom);
			mTapToPlay.setX(mWidth/2-mCoins.getWidth()/2 - 100);
			mTapToPlay.setY(mHeight/2-mCoins.getHeight()/2+200);
			this.attachChild(mTapToPlay);
		}
		
		mCoinIcon = new AnimatedSprite(mCoins.getX()-60, mCoins.getY()+15, mResourcesManager.coin_region, mVbom);
		final long[] PLAYER_ANIMATE = new long[] { 200, 0, 0, 0, 0, 0, 0, 0 };
		mCoinIcon.animate(PLAYER_ANIMATE, 0, 7, true);
		
		if(!mPlayerDataManager.getLevelAccess(mLevelNameString))
		{
			mLockIcon = new Sprite(mLevelName.getX()-50, mLevelName.getY()+10, mResourcesManager.lock_region, mVbom);
			this.attachChild(mLockIcon);
		}
		
		if(!mPlayerDataManager.getBikeAccess(mBikeNameString))
		{
			mBikeLockIcon = new Sprite(mBikeName.getX()-50, mBikeName.getY()+10, mResourcesManager.lock_region, mVbom);
			this.attachChild(mBikeLockIcon);
		}
		
		this.attachChild(mBikeName);
		this.attachChild(mLevelName);
		this.attachChild(mCoinIcon);
		this.attachChild(mHighScore);
		this.attachChild(mCoins);
	}
}
