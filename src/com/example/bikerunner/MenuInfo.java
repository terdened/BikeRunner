package com.example.bikerunner;

import org.andengine.entity.Entity;
import org.andengine.entity.text.Text;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

public class MenuInfo extends Entity {
	
	private int pHeight;
	private int pWidth;
	
	private Text pHighScore;
	private Text pCoins;
	private Text pTapToPlay;
	

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
		
		init();
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
		
		pTapToPlay = new Text(0, 0, pResourcesManager.font,"Tap to play!", 10, pVbom);
		pTapToPlay.setX(pWidth/2-pTapToPlay.getWidth()/2);
		pTapToPlay.setY(pHeight/2-pTapToPlay.getHeight()/2);
		pTapToPlay.setRotation(-20);
		
		this.attachChild(pHighScore);
		this.attachChild(pCoins);
		this.attachChild(pTapToPlay);
	}
	
	public void update()
	{
		this.detachChild(pHighScore);
		this.detachChild(pCoins);
		this.detachChild(pTapToPlay);
		
		pHighScore = new Text(0, 0, pResourcesManager.font, 
				String.valueOf(pPlayerDataManager.getHighScore()), 10, pVbom);
		pHighScore.setX(pWidth-pHighScore.getWidth()-10);
		pHighScore.setY(10);
		
		pCoins = new Text(0, 60, pResourcesManager.font, 
				String.valueOf(pPlayerDataManager.getCoins()), 10, pVbom);
		pCoins.setX(pWidth-pCoins.getWidth()-10);
		pCoins.setY(60);
		
		pTapToPlay = new Text(0, 0, pResourcesManager.font,"Tap to play!", 10, pVbom);
		pTapToPlay.setX(pWidth/2-pCoins.getWidth()/2);
		pTapToPlay.setY(pHeight/2-pCoins.getHeight()/2-100);
		
		this.attachChild(pHighScore);
		this.attachChild(pCoins);
		this.attachChild(pTapToPlay);
	}
}
