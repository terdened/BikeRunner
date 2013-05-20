package com.example.wildhunt;

import java.util.LinkedList;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.vbo.ISpriteVertexBufferObject;
import org.andengine.entity.sprite.vbo.ITiledSpriteVertexBufferObject;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Player extends AnimatedSprite
{

	float currentSpeedX=0;
	float currentSpeedY=0;
	float speedX=0;
	float speedY=0;
	float acceleration=0.2f;
	Boolean isMove=false;
	
	private Body body;
	
	 private void createPhysics( PhysicsWorld physicsWorld)
	 {        
	     body = PhysicsFactory.createBoxBody(physicsWorld, this, BodyType.DynamicBody, PhysicsFactory.createFixtureDef(0, 0, 0));
	     body.setUserData("player");
	     body.setFixedRotation(true);  
	     physicsWorld.registerPhysicsConnector(new PhysicsConnector(this, body, true, false)
	     {
	         @Override
	         public void onUpdate(float pSecondsElapsed)
	         {
	             super.onUpdate(pSecondsElapsed);                  
	         }
	     });
	    
	 }
	
	
	public Player(float pX, float pY,PhysicsWorld physicsWorld, ITiledTextureRegion pTiledTextureRegion,
			VertexBufferObjectManager vbom) {
		super(pX, pY, pTiledTextureRegion,
				vbom);
		
		createPhysics(physicsWorld);
		// TODO Auto-generated constructor stub
	}


	public void move()
	{
		
		
		
		if(speedX!=currentSpeedX)
		{
			
			
			if(currentSpeedX<speedX)
			{
				currentSpeedX+=acceleration;
			}else
			{
				currentSpeedX-=acceleration;
			}
		}
		
		if(speedY!=currentSpeedY)
		{
			
			if(currentSpeedY<speedY)
			{
				currentSpeedY+=acceleration;
			}else
			{
				currentSpeedY-=acceleration;
			}
		}
		if(Math.abs(currentSpeedX)<acceleration)
			currentSpeedX=0;
		if(Math.abs(currentSpeedY)<acceleration)
			currentSpeedY=0;
		
		Vector2 temp=new Vector2(currentSpeedX,currentSpeedY);
		
		
		//this.setY();
		isMove=false;
		
		
		if(currentSpeedX!=0)
		{				
			if(currentSpeedX>0)	
			{
				body.setTransform(body.getPosition(), (float)(Math.atan(currentSpeedY/currentSpeedX))+1.57f);
			    this.setRotation((float)(Math.atan(currentSpeedY/currentSpeedX)*180/3.14)+90);
			}
			else
			{
				body.setTransform(body.getPosition(), (float)(Math.atan(currentSpeedY/currentSpeedX))+1.57f);
				this.setRotation((float)(Math.atan(currentSpeedY/currentSpeedX)*180/3.14)-90);
			}
			
		}
		body.setLinearVelocity(temp);
		

	}
	
	public void setAcceleration(float x, float y)
	{
		speedX=x*10;
		speedY=y*10;
	}
}
