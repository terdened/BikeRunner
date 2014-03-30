package com.example.brutal;


import org.andengine.engine.Engine;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.ui.IGameInterface.OnCreateSceneCallback;

import com.example.brutal.BaseScene;
import com.example.brutal.SplashScene;

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
    
    private String element;
    private String level;
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
    // CLASS LOGIC
    //---------------------------------------------
    
    public void setScene(BaseScene scene)
    {
        engine.setScene(scene);
        currentScene = scene;
        currentSceneType = scene.getSceneType();
    }
    
    public void setScene(SceneType sceneType)
    {
        switch (sceneType)
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
    // GETTERS AND SETTERS
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
    
    public void createSplashScene(OnCreateSceneCallback pOnCreateSceneCallback)
    {
        ResourcesManager.getInstance().loadSplashScreen();
        splashScene = new SplashScene();
        currentScene = splashScene;
        pOnCreateSceneCallback.onCreateSceneFinished(splashScene);
    }

    private void disposeSplashScene()
    {
        ResourcesManager.getInstance().unloadSplashScreen();
        splashScene.disposeScene();
        splashScene = null;
    }
    
    public void createMenuScene()
    {        
        ResourcesManager.getInstance().loadMenuResources();
        menuScene = new MainMenuScene();
        loadingScene = new LoadingScene();
        SceneManager.getInstance().setScene(menuScene);
        disposeSplashScene();
    }
    
    public void createPlayerMenuScene()
    {        
        /*ResourcesManager.getInstance().loadPlayerMenuResources();
        playerMenuScene = new PlayerMenuScene();
        loadingScene = new LoadingScene();
        SceneManager.getInstance().setScene(playerMenuScene);
        //disposeSplashScene();*/
    }
    
    public void loadGameScene(final Engine mEngine, final String stage)
    {
    	
        setScene(loadingScene);
        ResourcesManager.getInstance().unloadMenuResources();
        mEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() 
        {
            public void onTimePassed(final TimerHandler pTimerHandler) 
            {

                ResourcesManager.getInstance().loadGameResources(stage);
                mEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() 
                {
                    public void onTimePassed(final TimerHandler pTimerHandler) 
                    {
		            	String tempElement = element;
		            	String tempLevel = level;
		                mEngine.unregisterUpdateHandler(pTimerHandler);
		                
		
		                menuScene.disposeScene();
		                gameScene = new GameScene();
		                setScene(gameScene);
                    }
                }));
            }
        }));
    }
    
    public void loadMenuScene(final Engine mEngine)
    {
        setScene(loadingScene);
        
        if(gameScene!=null)
        	ResourcesManager.getInstance().soundManager.setState("stop");
        
        ResourcesManager.getInstance().unloadGameResources();
        mEngine.registerUpdateHandler(new TimerHandler(0.1f, new ITimerCallback() 
        {
            public void onTimePassed(final TimerHandler pTimerHandler) 
            {
                mEngine.unregisterUpdateHandler(pTimerHandler);
                ResourcesManager.getInstance().loadMenuResources();                
                menuScene = new MainMenuScene();
                setScene(menuScene);
            }
        }));
    }

    
}
