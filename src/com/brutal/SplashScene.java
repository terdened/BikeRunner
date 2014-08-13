package com.brutal;


import org.andengine.engine.camera.Camera;
import org.andengine.entity.sprite.Sprite;
import org.andengine.opengl.util.GLState;

import com.brutal.BaseScene;
import com.brutal.SceneManager.SceneType;


public class SplashScene extends BaseScene
{
	//---------------------------------------------
    // VARIABLES
    //---------------------------------------------
	
	private Sprite mSplash;
	
	//---------------------------------------------
    // OVERLOADED METHODS
    //---------------------------------------------
	
    @Override
    public void createScene()
    {
    	mSplash = new Sprite(0, 0, resourcesManager.splash_region, vbom)
    	{
    	    @Override
    	    protected void preDraw(GLState pGLState, Camera pCamera) 
    	    {
    	       super.preDraw(pGLState, pCamera);
    	       pGLState.enableDither();
    	    }
    	};
    	float height = this.engine.getEngineOptions().getCamera().getHeight();
    	mSplash.setScale(1.5f);
    	mSplash.setPosition(384, (height-320)/2);
    	attachChild(mSplash);
    }

    @Override
    public void onBackKeyPressed()
    {

    }

    @Override
    public SceneType getSceneType()
    {
    	return SceneType.SCENE_SPLASH;
    }

    @Override
    public void disposeScene()
    {
    	mSplash.detachSelf();
        mSplash.dispose();
        this.detachSelf();
        this.dispose();
    }
}