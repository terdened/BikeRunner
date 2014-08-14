package com.brutal.game.biker;

import java.util.LinkedList;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.brutal.game.GameScene;
import com.brutal.game.road.*;


public class Biker extends AnimatedSprite 
{
    //---------------------------------------------
    // VARIABLES
    //---------------------------------------------
	
	private int mLine;
	private int mHeight;
	private int mDeltaY;
	private int mDeltaTrampY;
	private boolean mIsJump=false;
	private int mJumpCounter=100;
	private LinkedList<Animation> mAnimationList;
	private final Road mRoad;
	private final GameScene mScene;
	
    //---------------------------------------------
    // CONSTRUCTOR
    //---------------------------------------------
	
	public Biker(float pX, float pY, ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager,
			final Road road, GameScene scene) 
	{
		super(pX, pY,pTiledTextureRegion, pVertexBufferObjectManager);
		
		mScene = scene;
		mRoad = road;
		mLine = 1;
		mHeight = 0;
		mDeltaY = 0;
		mDeltaTrampY = 0;
		mAnimationList = new LinkedList<Animation>();
		
		final long[] PLAYER_ANIMATE = new long[] { 0, 200, 0, 0, 200, 0 };
		animate(PLAYER_ANIMATE, 0, 5, true);
		updateObject(0);
	}
	
	//---------------------------------------------
    // PRIVATE METHODS
    //---------------------------------------------
	
	private void moveBiker(String pDirection)
	{
		if(pDirection=="left")
		{
			if((mLine>0)&&(!isMovingAnimation()))
			{
				mAnimationList.add(new MoveLeftAnimation(this));
				mAnimationList.getLast().initAnimation("left", 10);
				if(this.thereIsGround())
				{
					mScene.resourcesManager.soundManager.moveLeft();
				}
			}
		}else
		if(pDirection=="right")
		{
			if((mLine<2)&&(!isMovingAnimation()))
			{
				mAnimationList.add(new MoveRightAnimation(this));
				mAnimationList.getLast().initAnimation("right", 10);
				if(this.thereIsGround())
				{
					mScene.resourcesManager.soundManager.moveRight();
				}
			}
		}else
		if(pDirection=="up")
		{
			if((mAnimationList.size()==0)&&(this.thereIsGround()))
			{
				mDeltaY=17;
				initJump();
			}
			
		}else
		if(pDirection=="down")
		{
			if(!this.thereIsGround())
			{
				mDeltaY-=15;
				resetJump();
			}
		}
	}
	
	private void resetJump()
	{
		mIsJump=false;
	}
	
	private void initJump()
	{
		mIsJump=true;
		mJumpCounter=100;
	}
	
	private void checkGround()
	{
		if(!thereIsGround())
		{
			if(!mIsJump)
				mDeltaY--;
			else
			{
				if((mJumpCounter>0)&&(mDeltaY<2))
				{
					mJumpCounter--;
				}
				else
				{
					mDeltaY--;
					resetJump();
				}
			}
		}else
		{
			mDeltaY=0;
			resetJump();
		}
		
	}
	
	
	//---------------------------------------------
    // PUBLIC METHODS
    //---------------------------------------------
	
	public String updateObject(int pSpeed)
	{
		updateHeight();
		
		String result = collisionControl();
		coinCollisionControl();
		
		
		if((mLine==0)&&(mAnimationList.size()==0))
			this.setX(350-128);
		if((mLine==1)&&(mAnimationList.size()==0))
			this.setX(640-128);
		if((mLine==2)&&(mAnimationList.size()==0))
			this.setX(910-128);
		
		for(int i=0;i<mAnimationList.size();i++)
		{
			mAnimationList.get(i).update();
			if(mAnimationList.get(i).canDelete())
			{
				mAnimationList.remove(i);
			}
		}
		
		return result;
		
	}
	
	public void setAction(float pXStart, float pYStart, float pXStop, float pYStop)
	{
		if(Math.abs(pXStart-pXStop)>Math.abs(pYStart-pYStop))
		{
			if(pXStart>pXStop)
			{
				moveBiker("left");
			}
			else
			{
				moveBiker("right");
				
			}
		}else
		{
			if(pYStart>pYStop)
			{
				moveBiker("up");
			}
			else
			{
				moveBiker("down");
			}
		}
	}
	
	public void resetGame()
	{
		mLine = 1;
		mHeight = 0;
		mDeltaY = 0;
		mAnimationList = new LinkedList<Animation>();
		final long[] PLAYER_ANIMATE = new long[] {  0, 200, 0, 0, 200, 0 };
		animate(PLAYER_ANIMATE, 0, 5, true);
		updateObject(0);
	}
	
	public boolean moveLeft()
	{
		boolean result=false;
		int[] linesHeight=mRoad.getLinesHeight();
		
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
		int[] linesHeight=mRoad.getLinesHeight();
		
		if(linesHeight[mLine+1]-mHeight<30)
    	{
			mLine++;
			result=true;
    	}
		
		return result;
	}
	
	public void returnLeft()
	{
		mAnimationList=new LinkedList<Animation>();
		mAnimationList.add(new ReturnLeftAnimation(this));
		mAnimationList.getLast().initAnimation("return_left", 5);
	}
	
	public void returnRight()
	{
		mAnimationList=new LinkedList<Animation>();
		mAnimationList.add(new ReturnRightAnimation(this));
		mAnimationList.getLast().initAnimation("return_right", 5);
	}
	
	public boolean isMovingAnimation()
	{
		boolean result=false;
		
		for(int i=0;i<mAnimationList.size();i++)
		{
			if((mAnimationList.get(i).getTitle()=="right")||(mAnimationList.get(i).getTitle()=="left"))
				result=true;
		}
		
		return result;
	}
		
	public boolean thereIsGround()
	{
		if(mHeight>0)
		{
			int[] linesHeight=mRoad.getLinesHeight();
			
			if(linesHeight[mLine]<mHeight)
	    	{
				if(mDeltaTrampY>0)
				{
					mDeltaY+=mDeltaTrampY;
					mDeltaTrampY=0;
				}
				return false;
	    	}
			else
			{
				mDeltaTrampY=0;
				return true;
			}
		}
		else
		{
			mDeltaTrampY=0;
			return true;
		}
	}
	
	public String collisionControl()
    {
		
    	int[] linesHeight=mRoad.getLinesHeight();
    	String gameState="game";
    	
    	if(linesHeight[mLine]-mHeight>30)
    	{
    		if(linesHeight[mLine]-mHeight>Math.abs(mDeltaY)+30)
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
    		mDeltaTrampY=Math.abs(linesHeight[mLine]-mHeight);
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
    	ObstacleCoin[] linesCoin=mRoad.getLineCoins();
    	
    	if(linesCoin[mLine]!=null)
    	{	
	    	if(linesCoin[mLine].getObstacleHeight()>mHeight)
	    	{
	    		mScene.collectCoin(linesCoin[mLine]);
	    	}
    	}
    }
	
	private void updateHeight()
	{
		mHeight+=mDeltaY;
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
	
	//---------------------------------------------
    // Getters
    //---------------------------------------------
	
	public int getLine()
	{
		return mLine;
	}
	
	public int getBikeHight()
	{
		return mHeight;
	}
}
