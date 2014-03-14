package com.example.bikerunner;

import java.util.LinkedList;

import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.badlogic.gdx.math.Vector2;

public class Biker extends AnimatedSprite {



	private int mLine;
	private int mHeight;
	private LinkedList<Animation> pAnimationList;
	private final Road pRoad;
	
	public Biker(float pX, float pY, ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager, final Road road) {
		
		super(pX, pY,pTiledTextureRegion, pVertexBufferObjectManager);
		
		pRoad = road;
		mLine = 1;
		mHeight = 0;
		pAnimationList = new LinkedList<Animation>();
		final long[] PLAYER_ANIMATE = new long[] { 200, 0, 0 };
		animate(PLAYER_ANIMATE, 0, 2, true);
		updateObject(0);
	}
	
	public void updateObject(int speed)
	{
		checkGround();
		
		if((mLine==0)&&(pAnimationList.size()==0))
			this.setX(350-128);
		if((mLine==1)&&(pAnimationList.size()==0))
			this.setX(640-128);
		if((mLine==2)&&(pAnimationList.size()==0))
			this.setX(910-128);
		
		if((mHeight==0)&&(pAnimationList.size()==0))
			this.setY(400);
		if((mHeight==1)&&(pAnimationList.size()==0))
			this.setY(300);
		if((mHeight==2)&&(pAnimationList.size()==0))
			this.setY(200);
		if((mHeight==3)&&(pAnimationList.size()==0))
			this.setY(100);
		
		for(int i=0;i<pAnimationList.size();i++)
		{
			pAnimationList.get(i).update();
			if(pAnimationList.get(i).canDelete())
			{
				pAnimationList.remove(i);
			}
		}
		
	}
	
	public void setAction(float xStart, float yStart, float xStop, float yStop)
	{
		Vector2 direction = new Vector2(xStop-xStart, yStop-yStart);
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
		pAnimationList = new LinkedList<Animation>();
		final long[] PLAYER_ANIMATE = new long[] { 200, 0, 0 };
		animate(PLAYER_ANIMATE, 0, 2, true);
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
			}
		}else
		if(direction=="right")
		{
			if((mLine<2)&&(!isMovingAnimation()))
			{
				pAnimationList.add(new MoveRightAnimation(this));
				pAnimationList.getLast().initAnimation("right", 10);
			}
		}else
		if(direction=="up")
		{
			if((mHeight<3)&&(pAnimationList.size()==0)&&(this.thereIsGround()))
			{
				pAnimationList.add(new MoveUpAnimation(this));
				pAnimationList.getLast().initAnimation("up", 20);
			}
		}else
		if(direction=="down")
		{
			if((mHeight>0)&&(pAnimationList.size()==0))
			{
				pAnimationList.add(new MoveDownAnimation(this));
				pAnimationList.getLast().initAnimation("down", 20);
			}
		}
	}
	
	public void moveLeft()
	{
		mLine--;
	}
	
	public void moveRight()
	{
		mLine++;
	}
	
	public void moveUp()
	{
		mHeight++;
	}
	
	public void moveDown()
	{
		mHeight--;
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
	
	public boolean isFlyingAnimation()
	{
		boolean result=false;
		
		for(int i=0;i<pAnimationList.size();i++)
		{
			if((pAnimationList.get(i).getTitle()=="up")||(pAnimationList.get(i).getTitle()=="down"))
				result=true;
		}
		
		return result;
	}
	
	public boolean isFallingAnimation()
	{
		boolean result=false;
		
		for(int i=0;i<pAnimationList.size();i++)
		{
			if(pAnimationList.get(i).getTitle()=="down")
				result=true;
		}
		
		return result;
	}
	
	public boolean isUpAnimation()
	{
		boolean result=false;
		
		for(int i=0;i<pAnimationList.size();i++)
		{
			if(pAnimationList.get(i).getTitle()=="up")
				result=true;
		}
		
		return result;
	}
	
	public void deleteDownAnimation()
	{
		for(int i=0;i<pAnimationList.size();i++)
		{
			if(pAnimationList.get(i).getTitle()=="down")
			{
				pAnimationList.remove(i);
				break;
			}
		}
	}
	
	private void checkGround()
	{
		if(mHeight>0)
		{
			int[][] bunnedLines = pRoad.getBunnedLines();
			
			if(bunnedLines[mHeight-1][mLine]!=1)
			{
				if(!this.isFlyingAnimation())
				{
					pAnimationList.add(new MoveDownAnimation(this));
					pAnimationList.getLast().initAnimation("down", 20);
				}
			}else
			{
				this.deleteDownAnimation();
			}
		}
	}
	
	public boolean thereIsGround()
	{
		if(mHeight>0)
		{
			int[][] bunnedLines = pRoad.getBunnedLines();
			
			if(bunnedLines[mHeight-1][mLine]!=1)
			{
				return false;
			}
		}
		return true;
	}
	
	public String collisionControl()
    {
    	int[][] bunnedLines=pRoad.getBunnedLines();
    	String gameState="game";
    	
    	for(int i=0;i<3;i++)
    	{
    		for(int j=0;j<3;j++)
        	{
	    		if((mLine==j)&&(mHeight==i))
	    		{
	    			if(bunnedLines[i][j]==1)
	    				gameState="die";
	    			else
	    			if(bunnedLines[i][j]==2)
	    			{
	    				if(!this.isUpAnimation())
	    				{
	    					deleteDownAnimation();
		    				pAnimationList.add(new JumpUpAnimation(this));
							pAnimationList.getLast().initAnimation("jump", 20);
	    				}
	    			}
	    		}
        	}
    	}
    	
    	return gameState;
    }

}
