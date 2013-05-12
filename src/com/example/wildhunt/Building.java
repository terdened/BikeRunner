package com.example.wildhunt;

import javax.microedition.khronos.opengles.GL10;

import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.sprite.vbo.ISpriteVertexBufferObject;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.util.color.Color;

public class Building extends Sprite
{

	Color originalColor;
	
	public Building(float pX, float pY, ITextureRegion pTextureRegion,
			VertexBufferObjectManager vbom) {
		super(pX, pY, pTextureRegion,
				vbom);
		this.setBlendingEnabled(true);
		originalColor=new Color(1,1,1);
		
		// TODO Auto-generated constructor stub
	}

	public void setBrightness(float x,float y)
	{
		float thisX=getX()+getWidth()/2;
		float thisY=getY()+getHeight()/2;
		float distance=(float)Math.sqrt(Math.abs(Math.pow(x-thisX,2)+Math.pow(y-thisY,2)));
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
	}
}
