package com.example.brutal;

import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Basic extends Sprite
{
	float xPos=0;
	float yPos=0;
	Body body;
	public Basic(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager pVertexBufferObjectManager) {
		super(0, 0, pTextureRegion, pVertexBufferObjectManager);
		xPos=pX;
		yPos=pY;
	}
	
	public void initBody(final PhysicsWorld physicsWorld)
	{

		FixtureDef FIXTURE_DEF = PhysicsFactory.createFixtureDef(0, 0.01f, 0.5f);
		
		Body tempBody;
		tempBody = PhysicsFactory.createBoxBody(physicsWorld, this, BodyType.StaticBody, FIXTURE_DEF);
		tempBody.setUserData("basic");
		this.body=tempBody;
	}
	
	public void update()
	{
		setPosition(xPos,yPos);
	}
}
