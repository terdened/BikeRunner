package com.example.brutal;


import java.io.IOException;
import java.util.LinkedList;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.audio.sound.Sound;
import org.andengine.audio.sound.SoundFactory;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.opengl.font.Font;
import org.andengine.opengl.font.FontFactory;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.ITiledTextureRegion;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
//import org.andengine.util.color.Color;
import org.andengine.util.debug.Debug;

import android.content.Context;
import android.graphics.Color;

import com.example.brutal.GameActivity;


public class ResourcesManager
{
    //---------------------------------------------
    // VARIABLES
    //---------------------------------------------
    
	public ITextureRegion splash_region;
	private BitmapTextureAtlas splashTextureAtlas;	
	
	public ITextureRegion empty_region;
	public ITextureRegion[] level_background_region;
	public ITiledTextureRegion[] bike_region;
	public ITiledTextureRegion biker_menu_region;
	
	private BuildableBitmapTextureAtlas menuTextureAtlas;
	
	public ITextureRegion road_background_fon_region;
	public ITextureRegion[] road_background_object;
	public ITextureRegion[] road_background_blink_region;
	public ITextureRegion background_region;
	public ITextureRegion sign_speed_region;
	public ITiledTextureRegion fence_region;
	public ITiledTextureRegion bus_a_region;
	public ITiledTextureRegion tramp_a_region;
	public ITiledTextureRegion biker_region;
	public ITiledTextureRegion coin_region;
	public ITextureRegion lock_region;
	public ITextureRegion buy_button_region;
	public ITextureRegion options_button_region;
	public ITextureRegion mute_button_region;
	public ITextureRegion close_button_region;
	public ITextureRegion volume_up_button_region;
	public ITextureRegion volume_down_button_region;
	
	public ITextureRegion pause_background_region;
	public ITextureRegion pause_button_region;
	public ITextureRegion restart_button_region;
	public ITextureRegion resume_button_region;
	public ITextureRegion main_menu_button_region;
	
	public ITextureRegion[] road_background_region;
	

	public Music game;
	public Music menu;
	
	public Music motorSound;
	public Music crashSound;
	public Music coinSound;
	public Music moveLeftSound;
	public Music moveRightSound;
	
	public SoundManager soundManager;
	public LinkedList<ITextureRegion> objectsList=new LinkedList <ITextureRegion>();
	private BuildableBitmapTextureAtlas gameTextureAtlas;
	
    private static final ResourcesManager INSTANCE = new ResourcesManager();
    
    public Engine engine;
    public GameActivity activity;
    public BoundCamera camera;
    public VertexBufferObjectManager vbom;
    
    public Font font;
    
    //---------------------------------------------
    // TEXTURES & TEXTURE REGIONS
    //---------------------------------------------
    
    //---------------------------------------------
    // CLASS LOGIC
    //---------------------------------------------

    public void initSoundManager()
    {
    	if(soundManager==null)
    	 soundManager = new SoundManager(this,this.activity.getSharedPreferences("com.example.bikerunner", Context.MODE_PRIVATE));
    		
    }

    private void loadMenuFonts()
    {
    	FontFactory.setAssetBasePath("font/");
        final ITexture mainFontTexture = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);

        font = FontFactory.createStrokeFromAsset(activity.getFontManager(), mainFontTexture, activity.getAssets(), "font.TTF", 50, true, Color.WHITE, 2, Color.BLACK);
        font.load();
    }
    
    public void loadMenuResources()
    {
    	loadMenuGraphics();
        loadMenuAudio();
        loadMenuFonts();
    }
    
    public void unloadMenuResources()
    {
        unloadMenuAudio();
        unloadMenuGraphics();
    }
    
    public void unloadGameResources()
    {
        unloadGameGraphics();
        unloadGameAudio();
    }
    
    

	public void loadGameResources(String stage)
    {
        loadGameGraphics(stage);
        loadGameFonts();
        loadGameAudio();
    }
    
    private void loadMenuGraphics()
    {
    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");
    	menuTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 2048, 2048, TextureOptions.BILINEAR);
    	    	
    	level_background_region = new ITextureRegion[2];
    	level_background_region[0] = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "level_background_a.png");
    	level_background_region[1] = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "level_background_b.png");

    	biker_menu_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(menuTextureAtlas, activity, "biker_a.png", 3, 1);

    	bike_region =  new ITiledTextureRegion[2];
    	bike_region[0]= BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(menuTextureAtlas, activity, "bike_a.png", 1, 2);
    	bike_region[1]= BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(menuTextureAtlas, activity, "bike_b.png", 1, 2);
	    
    	coin_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(menuTextureAtlas, activity, "coin.png", 4, 2);
    	lock_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "lock.png");
    	buy_button_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "buy_button.png");
    	options_button_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "options_button.png");
    	pause_background_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "background.png");
    	mute_button_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "mute_button.png");
    	close_button_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "close_button.png");
    	volume_up_button_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "volume_up_button.png");
    	volume_down_button_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "volume_down_button.png");
    	
    	empty_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "empty.png");
    	
    	try
    	{
    	    this.menuTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
    	    this.menuTextureAtlas.load();
    	} 
    	catch (final TextureAtlasBuilderException e)
    	{
    	        Debug.e(e);
    	}
    }
    
    
    private void loadMenuAudio()
    {
	    try {
			menu = MusicFactory.createMusicFromAsset(this.engine.getMusicManager(), this.activity ,"mfx/menu.mp3");
	    } catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    private void loadGameGraphics(String stage)
    {
    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game/");
	    gameTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 2048, 2048, TextureOptions.BILINEAR);
	    	   
	    objectsList=new LinkedList <ITextureRegion>();
	    
	    
	    if(stage=="Desert")
	    {
	    	road_background_fon_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "road/background/background_desert.png");
	    	road_background_object=new ITextureRegion[2];
	    	road_background_object[0]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "road/background/background_desert_grass.png");
	    	road_background_object[1]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "road/background/background_desert_bush.png");  
		    road_background_blink_region=new ITextureRegion[2];
		    road_background_blink_region[0]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "road/background/background_blink_a.png");
		    road_background_blink_region[1]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "road/background/background_blink_b.png");
		    background_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "background/background_desert.png");
	    }
	    else
    	if(stage=="Countriside")
	    {
	    	road_background_fon_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "road/background/background_countriside.png");
	    	road_background_object=new ITextureRegion[2];
	    	road_background_object[0]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "road/background/background_desert_grass.png");
	    	road_background_object[1]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "road/background/background_desert_bush.png");  
		    road_background_blink_region=new ITextureRegion[2];
		    road_background_blink_region[0]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "road/background/background_blink_a.png");
		    road_background_blink_region[1]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "road/background/background_blink_b.png");
		    background_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "background/background_desert.png");
		}
    	else
    	{
    		road_background_object=new ITextureRegion[2];
	    	road_background_object[0]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "road/background/background_desert_grass.png");
	    	road_background_object[1]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "road/background/background_desert_bush.png");  
		    road_background_blink_region=new ITextureRegion[2];
		    road_background_blink_region[0]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "road/background/background_blink_a.png");
		    road_background_blink_region[1]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "road/background/background_blink_b.png");
		    background_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "background/background_desert.png");
    	}
	    
	    sign_speed_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "road/background/sign_speed.png");
	    fence_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "road/fence.png", 3, 1);
	    bus_a_region= BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "road/bus_b.png",  3, 1);
	    tramp_a_region= BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "road/tramp_a.png", 3, 1);
	    biker_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "biker/biker_b.png", 3, 1);
	    
	    coin_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "road/coin.png", 4, 2);
	    
	    
	    road_background_region=new ITextureRegion[1];
	    road_background_region[0]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "background/mountain_a_desert.png");
	    
	    pause_background_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "menu/background.png");
	    pause_button_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "menu/pause_button.png"); 
		restart_button_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "menu/restart_button.png");
		resume_button_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "menu/resume_button.png"); 
		main_menu_button_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "menu/main_menu_button.png"); 
		
	    
	    
	    try 
	    {
	        this.gameTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
	        this.gameTextureAtlas.load();
	    } 
 	    catch (final TextureAtlasBuilderException e)
	    {
	        Debug.e(e);
	    } 	
    }
    
    private void loadGameFonts()
    {
    	FontFactory.setAssetBasePath("font/");
        final ITexture mainFontTexture = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);

        font = FontFactory.createStrokeFromAsset(activity.getFontManager(), mainFontTexture, activity.getAssets(), "font.TTF", 50, true, Color.WHITE, 2, Color.BLACK);
        font.load();
    }
    
    private void loadGameAudio()
    {
	    try {
			motorSound = MusicFactory.createMusicFromAsset(this.engine.getMusicManager(), this.activity ,"mfx/motor.mp3");
			crashSound = MusicFactory.createMusicFromAsset(this.engine.getMusicManager(), this.activity ,"mfx/crash.mp3");
			coinSound = MusicFactory.createMusicFromAsset(this.engine.getMusicManager(), this.activity ,"mfx/coin.mp3");
			moveLeftSound = MusicFactory.createMusicFromAsset(this.engine.getMusicManager(), this.activity ,"mfx/move_left.mp3");
			moveRightSound= MusicFactory.createMusicFromAsset(this.engine.getMusicManager(), this.activity ,"mfx/move_right.mp3");
			game = MusicFactory.createMusicFromAsset(this.engine.getMusicManager(), this.activity ,"mfx/game.mp3");
	    } catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void unloadGame()
    {
    	
    }
    
    public void unloadMenuTextures()
    {
        
    }
    
    public void unloadMenuAudio()
    {
        menu = null;
    }
    
    public void unloadMenuGraphics()
    {
    	menuTextureAtlas.unload();
    	level_background_region = null;
    	biker_menu_region = null;
    	bike_region  = null;
    	coin_region = null;
    	lock_region  = null;
    	buy_button_region  = null;
    	options_button_region = null;
    	pause_background_region = null;
    	mute_button_region = null;
    	close_button_region = null;
    	volume_up_button_region = null;
    	volume_down_button_region = null;
    	empty_region  = null;
    }
    
    public void unloadGameGraphics()
    {
    	gameTextureAtlas.unload(); 
    	objectsList=null;
    	road_background_fon_region=null;
    	road_background_object=null;
    	road_background_blink_region=null;
    	background_region=null;
    	sign_speed_region=null;
    	fence_region=null;
    	bus_a_region=null;
    	tramp_a_region=null;
    	biker_region=null;
    	coin_region=null;
    	road_background_region=null;
    	pause_background_region=null;
    	pause_button_region=null;
    	restart_button_region=null;
    	resume_button_region=null;
    	main_menu_button_region=null;
    }
    
    private void unloadGameAudio() {
		motorSound = null;
		crashSound = null;
		coinSound = null;
		moveLeftSound = null;
		moveRightSound = null;
		game= null;
	}
    
    public void loadSplashScreen()
    {
    
    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
    	splashTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR);
    	splash_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(splashTextureAtlas, activity, "splash.png", 0, 0);
    	splashTextureAtlas.load();
    	
    }
    
    public void unloadSplashScreen()
    {
    	splashTextureAtlas.unload();
    	splash_region = null;
    }
    
    
    /**
     * @param engine
     * @param activity
     * @param camera
     * @param vbom
     * <br><br>
     * We use this method at beginning of game loading, to prepare Resources Manager properly,
     * setting all needed parameters, so we can latter access them from different classes (eg. scenes)
     */
    public static void prepareManager(Engine engine, GameActivity activity, BoundCamera camera, VertexBufferObjectManager vbom)
    {
        getInstance().engine = engine;
        getInstance().activity = activity;
        getInstance().camera = camera;
        getInstance().vbom = vbom;
    }
    
    //---------------------------------------------
    // GETTERS AND SETTERS
    //---------------------------------------------
    
    public static ResourcesManager getInstance()
    {
        return INSTANCE;
    }
}

