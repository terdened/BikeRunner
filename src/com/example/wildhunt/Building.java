package com.example.wildhunt;

import javax.microedition.khronos.opengles.GL10;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.vbo.ISpriteVertexBufferObject;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

import com.badlogic.gdx.physics.box2d.Body;

public class Building extends Sprite
{

	Color originalColor;
	
	Sprite roof;
	Sprite wallTop;
	Sprite wallBottom;
	Sprite wallRight;
	Sprite wallLeft;
	Body body;
	float align=0;
	public Building(float pX, float pY, ITextureRegion pTextureRegion,ITextureRegion wallRegion, ITextureRegion wallSide_region,
			VertexBufferObjectManager vbom) {
		super(pX, pY, pTextureRegion,
				vbom);
		this.setBlendingEnabled(true);
		originalColor=new Color(1,1,1);
		roof=new Sprite(0, 0, pTextureRegion,vbom);
		
		wallTop=new Sprite(0, 0, wallRegion,vbom);
		//wallRight.setRotation(180f);
		attachChild(wallTop);
		
		wallBottom=new Sprite(0, 0, wallRegion,vbom);
		attachChild(wallBottom);
		
		wallRight=new Sprite(0, 0, wallSide_region,vbom);
		
		attachChild(wallRight);
		wallRight.setHeight(this.getHeight());
		wallRight.setPosition(this.getWidth(), 0);
		
		wallLeft=new Sprite(0, 0, wallSide_region,vbom);
		
		attachChild(wallLeft);
		wallLeft.setHeight(this.getHeight());
		wallLeft.setPosition(0, 0);
		attachChild(roof);
		
	}

	public void initBody(Body Body)
	{
		this.body=Body;
	}
	
	
	public void setBrightness(float x,float y)
	{
		float thisX=getX()+getWidth()/2;
		float thisY=getY()+getHeight()/2;
		float distance=(float)Math.sqrt(Math.abs(Math.pow(x-thisX,2)+Math.pow(y-thisY,2)));
		align+=0.2;
		
		float distX=x-thisX;
		float distY=y-thisY;
		
		float roofX=0;
		float roofY=0;
		
		if(distX!=0)
		{
			float signX=1;
			float signY=1;
			
			if(distX<0)
				signX=-1;
			if(distY<0)
				signY=-1;
						
			distX=Math.abs(distX);
			distY=Math.abs(distY);
			
			float dist=Math.abs(distance/4);
			
			float alpha=(float)Math.atan(distY/distX);
			
			
			
			roofX=signX*dist*(float)Math.cos(alpha);
			roofY=signY*dist*(float)Math.sin(alpha);
			
			wallTop.setY(-roofY);
			wallTop.setX(-roofX);
			wallTop.setHeight(roofY);
			wallTop.setSkewCenter(roofX/2,roofY/2);
			wallTop.setSkew((-signX*57.3f*alpha)-90,0);
			wallTop.setX(-signX*Math.abs(roofX)/2);
			
			
			wallBottom.setY(-roofY+this.getHeight());
			wallBottom.setX(-roofX);
			wallBottom.setHeight(roofY);
			wallBottom.setSkewCenter(roofX/2,roofY/2);
			wallBottom.setSkew(-1*((-signX*57.3f*alpha)-90),0);
			wallBottom.setX(-signX*Math.abs(roofX)/2);
			
			
			wallRight.setY(-roofY);
			wallRight.setX(-roofX+this.getWidth());
			wallRight.setWidth(roofX);
			wallRight.setSkewCenter(roofX/2,roofY/2);
			wallRight.setSkew(0,-1*((signY*57.3f*alpha)));
			wallRight.setY(-signY*Math.abs(roofY)/2);
			
			
			wallLeft.setY(-roofY);
			wallLeft.setX(-roofX);
			wallLeft.setWidth(roofX);
			wallLeft.setSkewCenter(roofX/2,roofY/2);
			wallLeft.setSkew(0,(signY*57.3f*alpha));
			wallLeft.setY(-signY*Math.abs(roofY)/2);
			
			/*
			wallRight.setY(-roofY);
			wallRight.setX(-roofX+this.getWidth());
			wallRight.setHeight(roofX);
			wallRight.setSkewCenter(roofX/2,roofY/2);
			wallRight.setSkew(-1*((signX*57.3f*alpha)-90),0);
			wallRight.setY(-signY*Math.abs(roofY)/2);
			*/
		}
		
		roof.setPosition(-roofX, -roofY);
		
		if(roofY<0)
			wallTop.setVisible(true);
		else
			wallTop.setVisible(false);
		
		if(roofY>0)
			wallBottom.setVisible(true);
		else
			wallBottom.setVisible(false);
		
		if(roofX>0)
			wallRight.setVisible(true);
		else
			wallRight.setVisible(false);
		
		if(roofX<0)
			wallLeft.setVisible(true);
		else
			wallLeft.setVisible(false);
		
		
		float brightness=255-distance/3;
		if(brightness<64)
			brightness=64;
		brightness/=255;
		//this.setBlendFunction(brightness, brightness);
		float k=(float)((float)brightness/0.5f);
		float red=k*originalColor.getRed();
		float green=k*originalColor.getGreen();
		float blue=k*originalColor.getBlue();
		
		if(red>1)
			red=1;
		if(green>1)
			green=1;
		if(blue>1)
			blue=1;
		
		Color temp=new Color(red,green,blue);
		this.setColor(temp);
		roof.setColor(temp);
		
		wallTop.setColor(temp);
		wallBottom.setColor(temp);
		wallRight.setColor(temp);
		wallLeft.setColor(temp);
	}
}
