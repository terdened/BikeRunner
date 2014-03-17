package com.example.bikerunner;


import java.io.IOException;
import java.util.LinkedList;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
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

import com.example.bikerunner.GameActivity;


public class ResourcesManager
{
    //---------------------------------------------
    // VARIABLES
    //---------------------------------------------
    
	public ITextureRegion splash_region;
	private BitmapTextureAtlas splashTextureAtlas;	
	
	public ITextureRegion empty_region;
	public ITextureRegion play_region;
	public ITextureRegion options_region;
	public ITextureRegion[] level_background_region;
	public ITextureRegion[] level_background_far_region;
	public ITiledTextureRegion[] bike_region;
	
	private BuildableBitmapTextureAtlas menuTextureAtlas;
	
	public ITextureRegion road_background_desert_region;
	public ITextureRegion road_background_desert_grass_region;
	public ITextureRegion road_background_desert_bush_region;
	public ITextureRegion road_background_blink_region;
	public ITextureRegion background_desert_region;
	public ITextureRegion sign_speed_region;
	public ITiledTextureRegion fence_region;
	public ITiledTextureRegion bus_a_region;
	public ITiledTextureRegion tramp_a_region;
	public ITiledTextureRegion biker_region;
	public ITiledTextureRegion coin_region;
	
	public ITextureRegion[] road_background_region;
	
	public Music intro;
	public Music main;
	public Music solo;
	
	public Music motorSound;
	
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
    
    public void loadGameResources()
    {
        loadGameGraphics();
        loadGameFonts();
        loadGameAudio();
    }
    
    private void loadMenuGraphics()
    {
    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");
    	menuTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 2048, 2048, TextureOptions.BILINEAR);
    	play_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "play.png");
    	options_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "options.png");
    	
    	level_background_region = new ITextureRegion[1];
    	level_background_region[0] = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "level_background_a.png");
    	
    	level_background_far_region = new ITextureRegion[1];
    	level_background_far_region[0] = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "level_background_far_a.png");
    	
    	bike_region =  new ITiledTextureRegion[1];
    	bike_region[0]= BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(menuTextureAtlas, activity, "bike_a.png", 1, 1);
	    
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
        
    }

    private void loadGameGraphics()
    {
    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game/");
	    gameTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 2048, 2048, TextureOptions.BILINEAR);
	    	   
	    objectsList=new LinkedList <ITextureRegion>();
	    
	    road_background_desert_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "road/background/background_desert.png");
	    road_background_desert_grass_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "road/background/background_desert_grass.png");
	    road_background_desert_bush_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "road/background/background_desert_bush.png");  
	    road_background_blink_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "road/background/background_blink.png");
	    background_desert_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "background/background_desert.png");
	    sign_speed_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "road/sign_speed.png");
	    fence_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "road/fence.png", 3, 1);
	    bus_a_region= BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "road/bus_b.png", 3, 1);
	    tramp_a_region= BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "road/tramp_a.png", 3, 1);
	    biker_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "biker/biker_a.png", 3, 1);
	    
	    coin_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "road/coin.png", 4, 2);
	    
	    
	    road_background_region=new ITextureRegion[1];
	    road_background_region[0]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "background/mountain_a_desert.png");
	    
	    
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
    	MusicFactory mf=new MusicFactory();
	    try {
			intro = MusicFactory.createMusicFromAsset(this.engine.getMusicManager(), this.activity ,"mfx/intro.ogg");
			main = MusicFactory.createMusicFromAsset(this.engine.getMusicManager(), this.activity ,"mfx/main.ogg");
			solo = MusicFactory.createMusicFromAsset(this.engine.getMusicManager(), this.activity ,"mfx/solo.ogg");
			motorSound = MusicFactory.createMusicFromAsset(this.engine.getMusicManager(), this.activity ,"mfx/motor.mp3");
	    } catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void unloadGame()
    {
        intro.stop();
        main.stop();
        solo.stop();
        motorSound.stop();
    }
    
    public void unloadMenuTextures()
    {
        
    }
        
    public void loadMenuTextures()
    {
        
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
    
    public void unloadPlayerMenuTextures()
    {
        
    }
        
    private void loadPlayerMenuAudio()
    {
        
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

