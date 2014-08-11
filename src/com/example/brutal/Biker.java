package com.example.brutal;

import java.util.LinkedList;

import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.badlogic.gdx.math.Vector2;

public class Biker extends AnimatedSprite {



	private int mLine;
	private int mHeight;
	private int pDeltaY;
	private int pDeltaTrampY;
	private boolean isJump=false;
	private int jumpCounter=100;
	
	private LinkedList<Animation> pAnimationList;
	private final Road pRoad;
	private final GameScene pScene;
	
	public Biker(float pX, float pY, ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, final Road road, GameScene scene) {
		
		super(pX, pY,pTiledTextureRegion, pVertexBufferObjectManager);
		
		pScene = scene;
		pRoad = road;
		mLine = 1;
		mHeight = 0;
		pDeltaY = 0;
		pDeltaTrampY = 0;
		pAnimationList = new LinkedList<Animation>();
		final long[] PLAYER_ANIMATE = new long[] { 0, 200, 0, 0, 200, 0 };
		animate(PLAYER_ANIMATE, 0, 5, true);
		updateObject(0);
	}
	
	public String updateObject(int speed)
	{
		updateHeight();
		
		String result = collisionControl();
		coinCollisionControl();
		
		
		if((mLine==0)&&(pAnimationList.size()==0))
			this.setX(350-128);
		if((mLine==1)&&(pAnimationList.size()==0))
			this.setX(640-128);
		if((mLine==2)&&(pAnimationList.size()==0))
			this.setX(910-128);
		
		for(int i=0;i<pAnimationList.size();i++)
		{
			pAnimationList.get(i).update();
			if(pAnimationList.get(i).canDelete())
			{
				pAnimationList.remove(i);
			}
		}
		
		return result;
		
	}
	
	public void setAction(float xStart, float yStart, float xStop, float yStop)
	{
		if(Math.abs(xStart-xStop)>Math.abs(yStart-yStop))
		{
			if(xStart>xStop)
			{
				moveBiker("left");
			}
			else
			{
				moveBiker("right");
				
			}
		}else
		{
			if(yStart>yStop)
			{
				moveBiker("up");
			}
			else
			{
				moveBiker("down");
			}
		}
	}
	
	public int getLine()
	{
		return mLine;
	}
	
	public int getBikeHight()
	{
		return mHeight;
	}
	
	public void resetGame()
	{
		mLine = 1;
		mHeight = 0;
		pDeltaY = 0;
		pAnimationList = new LinkedList<Animation>();
		final long[] PLAYER_ANIMATE = new long[] {  0, 200, 0, 0, 200, 0 };
		animate(PLAYER_ANIMATE, 0, 5, true);
		updateObject(0);
	}
	
	private void moveBiker(String direction)
	{
		if(direction=="left")
		{
			if((mLine>0)&&(!isMovingAnimation()))
			{
				pAnimationList.add(new MoveLeftAnimation(this));
				pAnimationList.getLast().initAnimation("left", 10);
				if(this.thereIsGround())
				{
					pScene.resourcesManager.soundManager.moveLeft();
				}
			}
		}else
		if(direction=="right")
		{
			if((mLine<2)&&(!isMovingAnimation()))
			{
				pAnimationList.add(new MoveRightAnimation(this));
				pAnimationList.getLast().initAnimation("right", 10);
				if(this.thereIsGround())
				{
					pScene.resourcesManager.soundManager.moveRight();
				}
			}
		}else
		if(direction=="up")
		{
			if((pAnimationList.size()==0)&&(this.thereIsGround()))
			{
				pDeltaY=17;
				initJump();
			}
			
		}else
		if(direction=="down")
		{
			if(!this.thereIsGround())
			{
				pDeltaY-=15;
				resetJump();
			}
		}
	}
	
	public boolean moveLeft()
	{
		boolean result=false;
		int[] linesHeight=pRoad.getLinesHeight();
		
		if(linesHeight[mLine-1]-mHeight<30)
    	{
			mLine--;
			result=true;
    	}
		
		return result;
	}
	
	public boolean moveRight()
	{
		boolean result=false;
		int[] linesHeight=pRoad.getLinesHeight();
		
		if(linesHeight[mLine+1]-mHeight<30)
    	{
			mLine++;
			result=true;
    	}
		
		return result;
	}
	
	public void returnLeft()
	{
		pAnimationList=new LinkedList<Animation>();
		pAnimationList.add(new ReturnLeftAnimation(this));
		pAnimationList.getLast().initAnimation("return_left", 5);
	}
	
	public void returnRight()
	{
		pAnimationList=new LinkedList<Animation>();
		pAnimationList.add(new ReturnRightAnimation(this));
		pAnimationList.getLast().initAnimation("return_right", 5);
	}
	
	public boolean isMovingAnimation()
	{
		boolean result=false;
		
		for(int i=0;i<pAnimationList.size();i++)
		{
			if((pAnimationList.get(i).getTitle()=="right")||(pAnimationList.get(i).getTitle()=="left"))
				result=true;
		}
		
		return result;
	}
	

	
	private void checkGround()
	{
		if(!thereIsGround())
		{
			if(!isJump)
				pDeltaY--;
			else
			{
				if((jumpCounter>0)&&(pDeltaY<2))
				{
					jumpCounter--;
				}
				else
				{
					pDeltaY--;
					resetJump();
				}
			}
		}else
		{
			pDeltaY=0;
			resetJump();
		}
		
	}
	
	private void resetJump()
	{
		isJump=false;
	}
	
	private void initJump()
	{
		isJump=true;
		jumpCounter=100;
	}
	
	public boolean thereIsGround()
	{
		if(mHeight>0)
		{
			int[] linesHeight=pRoad.getLinesHeight();
			
			if(linesHeight[mLine]<mHeight)
	    	{
				if(pDeltaTrampY>0)
				{
					pDeltaY+=pDeltaTrampY;
					pDeltaTrampY=0;
				}
				return false;
	    	}
			else
			{
				pDeltaTrampY=0;
				return true;
			}
		}
		else
		{
			pDeltaTrampY=0;
			return true;
		}
	}
	
	public String collisionControl()
    {
		
    	int[] linesHeight=pRoad.getLinesHeight();
    	//int[] prevLinesHeight=pRoad.getLinesHeightByPosition(-20);
    	String gameState="game";
    	
    	if(linesHeight[mLine]-mHeight>30)
    	{
    		if(linesHeight[mLine]-mHeight>Math.abs(pDeltaY)+30)
    		{
    			gameState="die";
    			pause();
    		}
    		else
	    	if(linesHeight[mLine]-mHeight>0)
	    		mHeight=linesHeight[mLine];
    		
    		checkGround();
    	}
    	else
    	if(linesHeight[mLine]-mHeight>0)
    	{
    		checkGround();
    		pDeltaTrampY=Math.abs(linesHeight[mLine]-mHeight);
    		mHeight=linesHeight[mLine];
    	}
    	else
    	{
    		checkGround();
    	}
    	
    	return gameState;
    }
	
	public void coinCollisionControl()
    {
    	ObstacleCoin[] linesCoin=pRoad.getLineCoins();
    	
    	if(linesCoin[mLine]!=null)
    	{	
	    	if(linesCoin[mLine].getObstacleHeight()>mHeight)
	    	{
	    		pScene.collectCoin(linesCoin[mLine]);
	    	}
    	}
    }
	
	private void updateHeight()
	{
		mHeight+=pDeltaY;
		this.setY(400-mHeight);
	}
	
	public void pause()
	{
		this.stopAnimation();
	}
	
	public void resume()
	{
		if(mLine==0)
		{
			final long[] PLAYER_ANIMATE = new long[] { 200, 0, 0, 200, 0, 0 };
			animate(PLAYER_ANIMATE, 0, 5, true);
		}
		else
		if(mLine==1)
		{
			final long[] PLAYER_ANIMATE = new long[] { 0, 200, 0, 0, 200, 0 };
			animate(PLAYER_ANIMATE, 0, 5, true);
		}
		else
		if(mLine==2)
		{
			final long[] PLAYER_ANIMATE = new long[] { 0, 0, 200, 0, 0, 200 };
			animate(PLAYER_ANIMATE, 0, 5, true);
		}
	}

}
