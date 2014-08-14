package com.brutal.engine;


import org.andengine.engine.Engine;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;

import com.brutal.engine.BaseScene;
import com.brutal.game.GameScene;
import com.brutal.loading.LoadingScene;
import com.brutal.menu.MainMenuScene;
import com.brutal.splash.SplashScene;

import android.util.DisplayMetrics;


public class SceneManager
{
    //---------------------------------------------
    // SCENES
    //---------------------------------------------
    
    private BaseScene splashScene;
    private BaseScene menuScene;
    private BaseScene playerMenuScene;
    private BaseScene gameScene;
    private BaseScene loadingScene;
    
    //---------------------------------------------
    // VARIABLES
    //---------------------------------------------

    private static final SceneManager INSTANCE = new SceneManager();
    private SceneType currentSceneType = SceneType.SCENE_SPLASH; 
    private BaseScene currentScene;
    private Engine engine = ResourcesManager.getInstance().engine;
    
    public enum SceneType
    {
        SCENE_SPLASH,
        SCENE_MENU,
        SCENE_GAME,
        SCENE_LOADING,
        SCENE_PLAYER_MENU
    }
    
    //---------------------------------------------
    // PRIVATE METHODS
    //---------------------------------------------

    private int EstimateHeight()
    {
    	DisplayMetrics displaymetrics = new DisplayMetrics();
    	ResourcesManager.getInstance().activity.getWindowManager().getDefaultDisplay().getMetrics(displaymetrics); 
 	
    	return (int)engine.getEngineOptions().getCamera().getHeight();
    }
    
    private void disposeSplashScene()
    {
        ResourcesManager.getInstance().unloadSplashScreen();
        splashScene.disposeScene();
        splashScene = null;
    }
    
    //---------------------------------------------
    // PUBLIC METHODS
    //---------------------------------------------

    public void createSplashScene(OnCreateSceneCallback pOnCreateSceneCallback)
    {
        ResourcesManager.getInstance().loadSplashScreen();
        splashScene = new SplashScene();
        currentScene = splashScene;
        pOnCreateSceneCallback.onCreateSceneFinished(splashScene);
    }
    
    public void createMenuScene()
    {        
        ResourcesManager.getInstance().loadMenuResources();
        menuScene = new MainMenuScene(EstimateHeight());
        ResourcesManager.getInstance().loadLoadingResources();
        loadingScene = new LoadingScene();
        SceneManager.getInstance().setScene(menuScene);
        disposeSplashScene();
    }
    
    public void loadGameScene(final Engine pEngine, final String pStage, final String pBike)
    {
    	
        setScene(loadingScene);
        ResourcesManager.getInstance().unloadMenuResources();
        pEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() 
        {
            public void onTimePassed(final TimerHandler pTimerHandler) 
            {

                ResourcesManager.getInstance().loadGameResources(pStage,pBike);
                pEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() 
                {
                    public void onTimePassed(final TimerHandler pTimerHandler) 
                    {
                    	pEngine.unregisterUpdateHandler(pTimerHandler);
		                menuScene.disposeScene();
		                GameScene tempScene=new GameScene();
		                tempScene.initScene(pStage);
		                gameScene = tempScene;
		                
		                setScene(gameScene);
                    }
                }));
            }
        }));
    }
    
    public void loadMenuScene(final Engine pEngine)
    {
        setScene(loadingScene);
        
        if(gameScene!=null)
        	ResourcesManager.getInstance().soundManager.setState("stop");
        
        ResourcesManager.getInstance().unloadGameResources();
        pEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() 
        {
            public void onTimePassed(final TimerHandler pTimerHandler) 
            {
            	pEngine.unregisterUpdateHandler(pTimerHandler);
                ResourcesManager.getInstance().loadMenuResources();                
                menuScene = new MainMenuScene(EstimateHeight());
                setScene(menuScene);
            }
        }));
    }
  
    //---------------------------------------------
    // SETTERS
    //---------------------------------------------
    
    public void setScene(BaseScene pScene)
    {
        engine.setScene(pScene);
        currentScene = pScene;
        currentSceneType = pScene.getSceneType();
    }
    
    public void setScene(SceneType pSceneType)
    {
        switch (pSceneType)
        {
            case SCENE_MENU:
                setScene(menuScene);
                break;
            case SCENE_GAME:
                setScene(gameScene);
                break;
            case SCENE_SPLASH:
                setScene(splashScene);
                break;
            case SCENE_LOADING:
                setScene(loadingScene);
                break;
            case SCENE_PLAYER_MENU:
                setScene(playerMenuScene);
                break;
            default:
                break;
        }
    }
    
    //---------------------------------------------
    // GETTERS
    //---------------------------------------------
    
    public static SceneManager getInstance()
    {
        return INSTANCE;
    }
    
    public SceneType getCurrentSceneType()
    {
        return currentSceneType;
    }
    
    public BaseScene getCurrentScene()
    {
        return currentScene;
    }
    
}
