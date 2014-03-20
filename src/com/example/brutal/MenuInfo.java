package com.example.brutal;

import org.andengine.entity.Entity;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class MenuInfo extends Entity {
	
	private int pHeight;
	private int pWidth;
	
	private Text pHighScore;
	private Text pBikeName;
	private Text pLevelName;
	private Text pCoins;
	private Text pTapToPlay;
	private Text pLevelCost;
	private AnimatedSprite pLevelCoinIcon;
	
	private String pLevelNameString;
	private String pBikeNameString;
	private AnimatedSprite pCoinIcon;
	private Sprite pLockIcon;
	private Sprite pBikeLockIcon;

	private final ResourcesManager pResourcesManager;
	private final VertexBufferObjectManager pVbom;
	private final PlayerDataManager pPlayerDataManager;
	
	MenuInfo(int width, int height, ResourcesManager resourcesManager,VertexBufferObjectManager vbom,
			PlayerDataManager playerDataManager)
	{
		pPlayerDataManager=playerDataManager;
		pResourcesManager=resourcesManager;
		pVbom=vbom;
		pHeight=height;
		pWidth=width;
		pLevelNameString="";
		pBikeNameString="";
		init();
	}
	
	void setLevelName(String name)
	{
		pLevelNameString=name;
	}
	
	void setBikeName(String name)
	{
		pBikeNameString=name;
	}
	
	public Entity showLevelCost(String cost)
	{
		Entity layer = new Entity();
		
		pLevelCost = new Text(60, 10, pResourcesManager.font,cost, cost.length(), pVbom);
		
		pCoinIcon = new AnimatedSprite(0, 25, pResourcesManager.coin_region, pVbom);
		final long[] PLAYER_ANIMATE = new long[] { 200, 0, 0, 0, 0, 0, 0, 0 };
		pCoinIcon.animate(PLAYER_ANIMATE, 0, 7, true);
		
		layer.attachChild(pCoinIcon);
		layer.attachChild(pLevelCost);
		
		return layer;
	}
	
	public void init()
	{
		pHighScore = new Text(0, 0, pResourcesManager.font, 
				String.valueOf(pPlayerDataManager.getHighScore()), 10, pVbom);
		pHighScore.setX(pWidth-pHighScore.getWidth()-10);
		
		pCoins = new Text(0, 60, pResourcesManager.font, 
				String.valueOf(pPlayerDataManager.getCoins()), 10, pVbom);
		pCoins.setX(pWidth-pCoins.getWidth()-10);
		pCoins.setY(50);
		
		pLevelName= new Text(0, 0, pResourcesManager.font, pLevelNameString, pLevelNameString.length(), pVbom);
		pLevelName.setX(pWidth/2-pLevelName.getWidth()/2);
		
		pBikeName = new Text(0, 0, pResourcesManager.font, pBikeNameString, pBikeNameString.length(), pVbom);
		pBikeName.setX(pWidth/2-pBikeName.getWidth()/2);
		pBikeName.setY(pHeight-130);
		
		if(pPlayerDataManager.getLevelAccess(pLevelNameString)&&pPlayerDataManager.getBikeAccess(pBikeNameString))
		{
			pTapToPlay = new Text(0, 0, pResourcesManager.font,"Tap to play!", 10, pVbom);
			pTapToPlay.setX(pWidth/2-pTapToPlay.getWidth()/2 -100);
			pTapToPlay.setY(pHeight/2-pCoins.getHeight()/2+140);
			this.attachChild(pTapToPlay);
		}
		
		pCoinIcon = new AnimatedSprite(pCoins.getX()-60, pCoins.getY()+15, pResourcesManager.coin_region, pVbom);
		final long[] PLAYER_ANIMATE = new long[] { 200, 0, 0, 0, 0, 0, 0, 0 };
		pCoinIcon.animate(PLAYER_ANIMATE, 0, 7, true);
		
		if(!pPlayerDataManager.getLevelAccess(pLevelNameString))
		{
			pLockIcon = new Sprite(pLevelName.getX()-50, pLevelName.getY()+10, pResourcesManager.lock_region, pVbom);
			this.attachChild(pLockIcon);
		}
		
		if(!pPlayerDataManager.getBikeAccess(pBikeNameString))
		{
			pBikeLockIcon = new Sprite(pBikeName.getX()-50, pBikeName.getY()+10, pResourcesManager.lock_region, pVbom);
			this.attachChild(pBikeLockIcon);
		}
		
		this.attachChild(pBikeName);
		this.attachChild(pLevelName);
		this.attachChild(pCoinIcon);
		this.attachChild(pHighScore);
		this.attachChild(pCoins);
	}
	
	public void update()
	{

		try{
			this.detachChild(pLockIcon);
		}
		finally
		{
		}
		

		try{
			this.detachChild(pBikeLockIcon);
		}
		finally
		{
		}
		
		try{
			this.detachChild(pTapToPlay);
		}
		finally
		{
		}
		
		this.detachChild(pHighScore);
		this.detachChild(pCoins);
		this.detachChild(pCoinIcon);
		this.detachChild(pLevelName);
		this.detachChild(pBikeName);
		
		pHighScore = new Text(0, 0, pResourcesManager.font, 
				String.valueOf(pPlayerDataManager.getHighScore()), 10, pVbom);
		pHighScore.setX(pWidth-pHighScore.getWidth()-10);
		pHighScore.setY(10);
		
		pCoins = new Text(0, 60, pResourcesManager.font, 
				String.valueOf(pPlayerDataManager.getCoins()), 10, pVbom);
		pCoins.setX(pWidth-pCoins.getWidth()-10);
		pCoins.setY(60);
		
		pLevelName= new Text(0, 0, pResourcesManager.font, pLevelNameString, pLevelNameString.length(), pVbom);
		pLevelName.setX(pWidth/2-pLevelName.getWidth()/2);
		
		pBikeName = new Text(0, 0, pResourcesManager.font, pBikeNameString, pBikeNameString.length(), pVbom);
		pBikeName.setX(pWidth/2-pBikeName.getWidth()/2);
		pBikeName.setY(pHeight-130);
		
		if(pPlayerDataManager.getLevelAccess(pLevelNameString)&&pPlayerDataManager.getBikeAccess(pBikeNameString))
		{
			pTapToPlay = new Text(0, 0, pResourcesManager.font,"Tap to play!", 10, pVbom);
			pTapToPlay.setX(pWidth/2-pCoins.getWidth()/2 - 100);
			pTapToPlay.setY(pHeight/2-pCoins.getHeight()/2+140);
			this.attachChild(pTapToPlay);
		}
		
		pCoinIcon = new AnimatedSprite(pCoins.getX()-60, pCoins.getY()+15, pResourcesManager.coin_region, pVbom);
		final long[] PLAYER_ANIMATE = new long[] { 200, 0, 0, 0, 0, 0, 0, 0 };
		pCoinIcon.animate(PLAYER_ANIMATE, 0, 7, true);
		
		if(!pPlayerDataManager.getLevelAccess(pLevelNameString))
		{
			pLockIcon = new Sprite(pLevelName.getX()-50, pLevelName.getY()+10, pResourcesManager.lock_region, pVbom);
			this.attachChild(pLockIcon);
		}
		
		if(!pPlayerDataManager.getBikeAccess(pBikeNameString))
		{
			pBikeLockIcon = new Sprite(pBikeName.getX()-50, pBikeName.getY()+10, pResourcesManager.lock_region, pVbom);
			this.attachChild(pBikeLockIcon);
		}
		
		this.attachChild(pBikeName);
		this.attachChild(pLevelName);
		this.attachChild(pCoinIcon);
		this.attachChild(pHighScore);
		this.attachChild(pCoins);
	}
}
