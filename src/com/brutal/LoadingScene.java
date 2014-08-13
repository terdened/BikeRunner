package com.brutal;


import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.text.Text;
import org.andengine.opengl.util.GLState;
import org.andengine.util.color.*;

import com.brutal.BaseScene;
import com.brutal.SceneManager.SceneType;

public class LoadingScene extends BaseScene
{
	private Sprite loading;
	
    @Override
    public void createScene()
    {
        setBackground(new Background(Color.BLACK));
        loading = new Sprite(0, 0, resourcesManager.loading_region, vbom)
    	{
    	    @Override
    	    protected void preDraw(GLState pGLState, Camera pCamera) 
    	    {
    	       super.preDraw(pGLState, pCamera);
    	       pGLState.enableDither();
    	    }
    	};
    	float height = this.engine.getEngineOptions().getCamera().getHeight();
    	loading.setScale(1.5f);
    	loading.setPosition(256, (height-320)/2);
    	attachChild(loading);
    }

    @Override
    public void onBackKeyPressed()
    {
        return;
    }

    @Override
    public SceneType getSceneType()
    {
        return SceneType.SCENE_LOADING;
    }

    @Override
    public void disposeScene()
    {

    }
}