package com.brutal.engine;


import java.io.IOException;
import java.util.LinkedList;

import org.andengine.audio.music.Music;
import org.andengine.audio.music.MusicFactory;
import org.andengine.engine.Engine;
import org.andengine.engine.camera.BoundCamera;
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
import org.andengine.util.debug.Debug;

import com.brutal.engine.GameActivity;

import android.content.Context;
import android.graphics.Color;



public class ResourcesManager
{
    //---------------------------------------------
    // VARIABLES
    //---------------------------------------------

	private BitmapTextureAtlas mSplashTextureAtlas;
	private BitmapTextureAtlas mLoadingTextureAtlas;
	private BuildableBitmapTextureAtlas mMenuTextureAtlas;
	private BuildableBitmapTextureAtlas mMenuTutorialTextureAtlas;
	private BuildableBitmapTextureAtlas mMenuBackgroundTextureAtlas;
	private BuildableBitmapTextureAtlas mGameTextureAtlas;
	private BuildableBitmapTextureAtlas mGameEnviromentTextureAtlas;
	private BuildableBitmapTextureAtlas mGameBackgroundTextureAtlas;
    private static final ResourcesManager INSTANCE = new ResourcesManager();
	
	public ITextureRegion splash_region;	
	public ITextureRegion loading_region;	
	public ITextureRegion empty_region;
	public ITextureRegion[] level_background_region;
	public ITiledTextureRegion[] bike_region;
	public ITiledTextureRegion biker_menu_region;
	public ITextureRegion road_background_fon_region;
	public ITextureRegion[] road_background_object;
	public ITextureRegion[] road_background_blink_region;
	public ITextureRegion background_region;
	public ITextureRegion[] fog_region;
	public ITextureRegion sign_speed_region;
	public ITiledTextureRegion fence_region;
	public ITiledTextureRegion[] bus_region;
	public ITiledTextureRegion[] pickup_region;
	public ITiledTextureRegion[] building_region;
	public ITiledTextureRegion tramp_a_region;
	public ITiledTextureRegion biker_region;
	public ITiledTextureRegion coin_region;
	public ITiledTextureRegion tutorial_region;
	public ITextureRegion lock_region;
	public ITextureRegion buy_button_region;
	public ITextureRegion options_button_region;
	public ITextureRegion help_button_region;
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
	public Music menu_1;
	public Music menu_2;
	public Music menu_3;
	public Music motorSound;
	public Music crashSound;
	public Music coinSound;
	public Music moveLeftSound;
	public Music moveRightSound;
	public SoundManager soundManager;
	public LinkedList<ITextureRegion> objectsList=new LinkedList <ITextureRegion>();
	public Engine engine;
    public GameActivity activity;
    public BoundCamera camera;
    public VertexBufferObjectManager vbom;
    public Font font;
    
    //---------------------------------------------
    // PRIVATE METHODS
    //---------------------------------------------
    
    private void loadMenuFonts()
    {
    	FontFactory.setAssetBasePath("font/");
        final ITexture mainFontTexture = new BitmapTextureAtlas(activity.getTextureManager(), 256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);

        font = FontFactory.createStrokeFromAsset(activity.getFontManager(), mainFontTexture, activity.getAssets(), "font.TTF", 50, true, Color.WHITE, 2, Color.BLACK);
        font.load();
    }
    
    private void loadMenuGraphics()
    {
    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");
    	mMenuTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 2048, 2048, TextureOptions.DEFAULT);
    	    	
    	
    	biker_menu_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mMenuTextureAtlas, activity, "biker_a.png", 3, 1);

    	bike_region =  new ITiledTextureRegion[2];
    	bike_region[0]= BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mMenuTextureAtlas, activity, "bike_a.png", 1, 2);
    	bike_region[1]= BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mMenuTextureAtlas, activity, "bike_b.png", 1, 2);
	    
    	coin_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mMenuTextureAtlas, activity, "coin.png", 4, 2);
    	
    	lock_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mMenuTextureAtlas, activity, "lock.png");
    	buy_button_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mMenuTextureAtlas, activity, "buy_button.png");
    	options_button_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mMenuTextureAtlas, activity, "options_button.png");
    	help_button_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mMenuTextureAtlas, activity, "help_button.png");
    	
    	pause_background_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mMenuTextureAtlas, activity, "background.png");
    	mute_button_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mMenuTextureAtlas, activity, "mute_button.png");
    	close_button_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mMenuTextureAtlas, activity, "close_button.png");
    	volume_up_button_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mMenuTextureAtlas, activity, "volume_up_button.png");
    	volume_down_button_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mMenuTextureAtlas, activity, "volume_down_button.png");
    	
    	empty_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mMenuTextureAtlas, activity, "empty.png");
    	
    	try
    	{
    	    this.mMenuTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
    	    this.mMenuTextureAtlas.load();
    	} 
    	catch (final TextureAtlasBuilderException e)
    	{
    	        Debug.e(e);
    	}
    	
    	mMenuTutorialTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 2048, 2048, TextureOptions.DEFAULT);
    	tutorial_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mMenuTutorialTextureAtlas, activity, "tutorial.png", 3, 4);
    	
    	
    	
    	try
    	{
    	    this.mMenuTutorialTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
    	    this.mMenuTutorialTextureAtlas.load();
    	} 
    	catch (final TextureAtlasBuilderException e)
    	{
    	        Debug.e(e);
    	}
    	
    	mMenuBackgroundTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 2048, 2048, TextureOptions.DEFAULT);
    	
    	level_background_region = new ITextureRegion[3];
    	level_background_region[0] = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mMenuBackgroundTextureAtlas, activity, "level_background_a.png");
    	level_background_region[1] = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mMenuBackgroundTextureAtlas, activity, "level_background_b.png");
    	level_background_region[2] = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mMenuBackgroundTextureAtlas, activity, "level_background_c.png");

    	
    	try
    	{
    	    this.mMenuBackgroundTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
    	    this.mMenuBackgroundTextureAtlas.load();
    	} 
    	catch (final TextureAtlasBuilderException e)
    	{
    	        Debug.e(e);
    	}
    }
    
    private void loadMenuAudio()
    {
	    try {
			menu_1 = MusicFactory.createMusicFromAsset(this.engine.getMusicManager(), this.activity ,"mfx/menu_1.MP3");
			menu_2 = MusicFactory.createMusicFromAsset(this.engine.getMusicManager(), this.activity ,"mfx/menu_2.MP3");
			menu_3 = MusicFactory.createMusicFromAsset(this.engine.getMusicManager(), this.activity ,"mfx/menu_3.MP3");
	    } catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    private void loadGameGraphics(String stage, String bike)
    {
    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/game/");
	    mGameTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 2048, 2048, TextureOptions.DEFAULT);
	    mGameEnviromentTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 2048, 2048, TextureOptions.DEFAULT);
	    mGameBackgroundTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 2048, 2048, TextureOptions.DEFAULT);
	    
	    objectsList=new LinkedList <ITextureRegion>();
	    
	    
	    if(stage=="Desert")
	    {
	    	road_background_region=new ITextureRegion[5];
		    road_background_region[0]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameBackgroundTextureAtlas, activity, "background/mountain_a_desert.png");
		    road_background_region[1]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameBackgroundTextureAtlas, activity, "background/mountain_b_desert.png");
		    road_background_region[2]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameBackgroundTextureAtlas, activity, "background/sandcastle_desert.png");
		    road_background_region[3]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameBackgroundTextureAtlas, activity, "background/backgorund_desert_skull_a.png");
		    road_background_region[4]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameBackgroundTextureAtlas, activity, "background/mountain_c_desert.png");
		    
		    fence_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mGameTextureAtlas, activity, "road/fence.png", 3, 1);
		    
		    bus_region = new ITiledTextureRegion[3];
		    bus_region[0] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mGameTextureAtlas, activity, "road/bus_a.png",  3, 1);
		    bus_region[1] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mGameTextureAtlas, activity, "road/bus_b.png",  3, 1);
		    bus_region[2] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mGameTextureAtlas, activity, "road/bus_c.png",  3, 1);
		    
		    
		    
	    	road_background_fon_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameEnviromentTextureAtlas, activity, "road/background/background_desert.png");
	    	road_background_object=new ITextureRegion[7];
	    	road_background_object[0]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameEnviromentTextureAtlas, activity, "road/background/background_desert_grass.png");
	    	road_background_object[1]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameEnviromentTextureAtlas, activity, "road/background/background_desert_bush.png");  
	    	road_background_object[2]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameEnviromentTextureAtlas, activity, "road/background/background_desert_tree_a.png");
	    	road_background_object[3]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameEnviromentTextureAtlas, activity, "road/background/background_desert_tree_b.png");
	    	road_background_object[4]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameEnviromentTextureAtlas, activity, "road/background/background_desert_tree_c.png");
	    	road_background_object[5]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameEnviromentTextureAtlas, activity, "road/background/background_desert_cactus_a.png");
	    	road_background_object[6]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameEnviromentTextureAtlas, activity, "road/background/background_rock_c.png");
	    	road_background_blink_region=new ITextureRegion[2];
		    road_background_blink_region[0]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameEnviromentTextureAtlas, activity, "road/background/background_blink_a.png");
		    road_background_blink_region[1]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameEnviromentTextureAtlas, activity, "road/background/background_blink_b.png");
		    background_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameBackgroundTextureAtlas, activity, "background/background_desert.png");
	    }
	    else
    	if(stage=="Countryside")
	    {
    		road_background_region=new ITextureRegion[3];
    	    road_background_region[0]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameBackgroundTextureAtlas, activity, "background/house_a_countreside.png");
    	    road_background_region[1]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameBackgroundTextureAtlas, activity, "background/house_b_countreside.png");
    	    road_background_region[2]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameBackgroundTextureAtlas, activity, "background/backgorund_desert_skull_a.png");
    		
	    	road_background_fon_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameTextureAtlas, activity, "road/background/background_countriside.png");
	    	road_background_object=new ITextureRegion[4];
	    	road_background_object[0]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameTextureAtlas, activity, "road/background/background_desert_grass.png");
	    	road_background_object[1]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameTextureAtlas, activity, "road/background/background_desert_bush.png");  
	    	road_background_object[2]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameTextureAtlas, activity, "road/background/background_countriside_cow_a.png"); 
	    	road_background_object[3]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameTextureAtlas, activity, "road/background/background_countriside_cow_b.png");
	    	
	    	fence_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mGameTextureAtlas, activity, "road/seno.png", 3, 1);
	 	    
	    	building_region = new ITiledTextureRegion[1];
	    	building_region[0] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mGameTextureAtlas, activity, "road/building_a.png",  3, 1);
	 	    
	    	bus_region = new ITiledTextureRegion[1];
		    bus_region[0] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mGameTextureAtlas, activity, "road/bus_a.png",  3, 1);
		   
		    pickup_region = new ITiledTextureRegion[1];
		    pickup_region[0] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mGameTextureAtlas, activity, "road/pickup_a.png",  3, 1);
		   
		    
	    	road_background_blink_region=new ITextureRegion[2];
		    road_background_blink_region[0]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameTextureAtlas, activity, "road/background/background_blink_c.png");
		    road_background_blink_region[1]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameTextureAtlas, activity, "road/background/background_blink_d.png");
		    background_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameBackgroundTextureAtlas, activity, "background/background_desert.png");
		}
    	else
    	{
    		road_background_region=new ITextureRegion[5];
    	    road_background_region[0]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameBackgroundTextureAtlas, activity, "background/mountain_a_desert.png");
    	    road_background_region[1]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameBackgroundTextureAtlas, activity, "background/mountain_b_desert.png");
    	    road_background_region[2]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameBackgroundTextureAtlas, activity, "background/sandcastle_desert.png");
    	    road_background_region[3]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameBackgroundTextureAtlas, activity, "background/backgorund_desert_skull_a.png");
    	    road_background_region[4]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameBackgroundTextureAtlas, activity, "background/mountain_c_desert.png");
    		
    	    fence_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mGameTextureAtlas, activity, "road/fence.png", 3, 1);
    	    
    	    bus_region = new ITiledTextureRegion[3];
    	    bus_region[0] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mGameTextureAtlas, activity, "road/bus_a.png",  3, 1);
    	    bus_region[1] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mGameTextureAtlas, activity, "road/bus_b.png",  3, 1);
    	    bus_region[2] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mGameTextureAtlas, activity, "road/bus_c.png",  3, 1);
    	    
    	    
    		road_background_object=new ITextureRegion[2];
	    	road_background_object[0]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameTextureAtlas, activity, "road/background/background_desert_grass.png");
	    	road_background_object[1]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameTextureAtlas, activity, "road/background/background_desert_bush.png");  
		    road_background_blink_region=new ITextureRegion[2];
		    road_background_blink_region[0]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameTextureAtlas, activity, "road/background/background_blink_a.png");
		    road_background_blink_region[1]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameTextureAtlas, activity, "road/background/background_blink_b.png");
		    background_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameBackgroundTextureAtlas, activity, "background/background_desert.png");
    	}
	    
	    tramp_a_region= BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mGameTextureAtlas, activity, "road/tramp_a.png", 3, 1);
	    
	    if(bike=="Red Harley")
	    	biker_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mGameTextureAtlas, activity, "biker/biker_a.png", 3, 2);
	    else
	    if(bike=="Black Harley")	
	    	biker_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mGameTextureAtlas, activity, "biker/biker_b.png", 3, 2);
	    else
	    	biker_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mGameTextureAtlas, activity, "biker/biker_a.png", 3, 2);
	    
	   
	    coin_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(mGameTextureAtlas, activity, "road/coin.png", 4, 2);
	    
	    
	    
	    
	    pause_background_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameTextureAtlas, activity, "menu/background.png");
	    pause_button_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameTextureAtlas, activity, "menu/pause_button.png"); 
		restart_button_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameTextureAtlas, activity, "menu/restart_button.png");
		resume_button_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameTextureAtlas, activity, "menu/resume_button.png"); 
		main_menu_button_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(mGameTextureAtlas, activity, "menu/main_menu_button.png"); 
		
	    try 
	    {
	        this.mGameTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
	        this.mGameTextureAtlas.load();
	    } 
 	    catch (final TextureAtlasBuilderException e)
	    {
	        Debug.e(e);
	    } 	
	    
	    try 
	    {
	        this.mGameEnviromentTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
	        this.mGameEnviromentTextureAtlas.load();
	    } 
 	    catch (final TextureAtlasBuilderException e)
	    {
	        Debug.e(e);
	    } 
	    
	    try 
	    {
	        this.mGameBackgroundTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
	        this.mGameBackgroundTextureAtlas.load();
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
    
    private void loadGameAudio(String level)
    {
	    try {
			motorSound = MusicFactory.createMusicFromAsset(this.engine.getMusicManager(), this.activity ,"mfx/motor.mp3");
			crashSound = MusicFactory.createMusicFromAsset(this.engine.getMusicManager(), this.activity ,"mfx/crash.mp3");
			coinSound = MusicFactory.createMusicFromAsset(this.engine.getMusicManager(), this.activity ,"mfx/coin.mp3");
			moveLeftSound = MusicFactory.createMusicFromAsset(this.engine.getMusicManager(), this.activity ,"mfx/move_left.mp3");
			moveRightSound= MusicFactory.createMusicFromAsset(this.engine.getMusicManager(), this.activity ,"mfx/move_right.mp3");
			
			if(level == "Desert")
				game = MusicFactory.createMusicFromAsset(this.engine.getMusicManager(), this.activity ,"mfx/game_1.MP3");
			else
			if(level == "Countryside")
				game = MusicFactory.createMusicFromAsset(this.engine.getMusicManager(), this.activity ,"mfx/game_2.MP3");
			else
				game = MusicFactory.createMusicFromAsset(this.engine.getMusicManager(), this.activity ,"mfx/game_1.MP3");
	    } catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    private void loadLoadingGraphics()
    {
    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
    	mLoadingTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.NEAREST);
    	loading_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mLoadingTextureAtlas, activity, "loading.png", 0, 0);
    	mLoadingTextureAtlas.load();
    }
    
    private void unloadGameAudio() {
    	this.engine.getMusicManager().remove(motorSound);
    	this.engine.getMusicManager().remove(crashSound);
    	this.engine.getMusicManager().remove(coinSound);
    	this.engine.getMusicManager().remove(moveLeftSound);
    	this.engine.getMusicManager().remove(moveRightSound);
    	this.engine.getMusicManager().remove(game);
    	
		motorSound = null;
		crashSound = null;
		coinSound = null;
		moveLeftSound = null;
		moveRightSound = null;
		game= null;
	}
    
    //---------------------------------------------
    // PUBLIC METHODS
    //---------------------------------------------
   
    public void initSoundManager()
    {
    	if(soundManager==null)
    	 soundManager = new SoundManager(this,this.activity.getSharedPreferences("com.example.bikerunner", Context.MODE_PRIVATE));
    		
    }

    public void loadMenuResources()
    {
    	loadMenuGraphics();
        loadMenuAudio();
        loadMenuFonts();
    }
    
    public void loadLoadingResources()
    {
    	loadLoadingGraphics();
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
  
	public void loadGameResources(String stage, String bike)
    {
        loadGameGraphics(stage, bike);
        loadGameFonts();
        loadGameAudio(stage);
    }
    
    public void unloadGame()
    {
    	
    }
    
    public void unloadMenuTextures()
    {
        
    }
    
    public void unloadMenuAudio()
    {
    	this.engine.getMusicManager().remove(menu_1);
    	this.engine.getMusicManager().remove(menu_2);
    	this.engine.getMusicManager().remove(menu_3);
        menu_1 = null;
        menu_2 = null;
        menu_3 = null;
    }
    
    public void unloadMenuGraphics()
    {
    	mMenuTextureAtlas.unload();
    	mMenuTutorialTextureAtlas.unload();
    	mMenuBackgroundTextureAtlas.unload();
    	
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
    	help_button_region= null;
    	tutorial_region = null;
    	level_background_region =  null;
    	
    }
    
    public void unloadGameGraphics()
    {
    	mGameTextureAtlas.unload(); 
    	mGameEnviromentTextureAtlas.unload(); 
    	mGameBackgroundTextureAtlas.unload(); 
    	
    	objectsList=null;
    	road_background_fon_region=null;
    	road_background_object=null;
    	road_background_blink_region=null;
    	background_region=null;
    	sign_speed_region=null;
    	fence_region=null;
    	bus_region=null;
    	tramp_a_region=null;
    	biker_region=null;
    	coin_region=null;
    	road_background_region=null;
    	pause_background_region=null;
    	pause_button_region=null;
    	restart_button_region=null;
    	resume_button_region=null;
    	main_menu_button_region=null;
    	road_background_region=null;
    	building_region = null; 
    	pickup_region = null;
    }
    
    public void loadSplashScreen()
    {
    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
    	mSplashTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.NEAREST);
    	splash_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(mSplashTextureAtlas, activity, "splash.png", 0, 0);
    	mSplashTextureAtlas.load();
    	//loadLoadingResources();
    }
    
    public void unloadSplashScreen()
    {
    	mSplashTextureAtlas.unload();
    	splash_region = null;
    }
    
    public static void prepareManager(Engine engine, GameActivity activity, BoundCamera camera, VertexBufferObjectManager vbom)
    {
        getInstance().engine = engine;
        getInstance().activity = activity;
        getInstance().camera = camera;
        getInstance().vbom = vbom;
    }
    
    //---------------------------------------------
    // GETTERS
    //---------------------------------------------
    
    public static ResourcesManager getInstance()
    {
        return INSTANCE;
    }
}

