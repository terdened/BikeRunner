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
	
	public ITextureRegion loading_region;
	private BitmapTextureAtlas loadingTextureAtlas;	
	
	public ITextureRegion empty_region;
	public ITextureRegion[] level_background_region;
	public ITiledTextureRegion[] bike_region;
	public ITiledTextureRegion biker_menu_region;
	
	private BuildableBitmapTextureAtlas menuTextureAtlas;
	private BuildableBitmapTextureAtlas menuTutorialTextureAtlas;
	private BuildableBitmapTextureAtlas menuBackgroundTextureAtlas;
	
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
	private BuildableBitmapTextureAtlas gameTextureAtlas;
	private BuildableBitmapTextureAtlas gameEnviromentTextureAtlas;
	private BuildableBitmapTextureAtlas gameBackgroundTextureAtlas;
	
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
    
    private void loadMenuGraphics()
    {
    	BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/menu/");
    	menuTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 2048, 2048, TextureOptions.NEAREST);
    	    	
    	
    	biker_menu_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(menuTextureAtlas, activity, "biker_a.png", 3, 1);

    	bike_region =  new ITiledTextureRegion[2];
    	bike_region[0]= BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(menuTextureAtlas, activity, "bike_a.png", 1, 2);
    	bike_region[1]= BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(menuTextureAtlas, activity, "bike_b.png", 1, 2);
	    
    	coin_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(menuTextureAtlas, activity, "coin.png", 4, 2);
    	
    	lock_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "lock.png");
    	buy_button_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "buy_button.png");
    	options_button_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "options_button.png");
    	help_button_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuTextureAtlas, activity, "help_button.png");
    	
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
    	
    	menuTutorialTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 2048, 2048, TextureOptions.NEAREST);
    	tutorial_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(menuTutorialTextureAtlas, activity, "tutorial.png", 3, 2);
    	
    	
    	
    	try
    	{
    	    this.menuTutorialTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
    	    this.menuTutorialTextureAtlas.load();
    	} 
    	catch (final TextureAtlasBuilderException e)
    	{
    	        Debug.e(e);
    	}
    	
    	menuBackgroundTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 2048, 2048, TextureOptions.NEAREST);
    	
    	level_background_region = new ITextureRegion[3];
    	level_background_region[0] = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuBackgroundTextureAtlas, activity, "level_background_a.png");
    	level_background_region[1] = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuBackgroundTextureAtlas, activity, "level_background_b.png");
    	level_background_region[2] = BitmapTextureAtlasTextureRegionFactory.createFromAsset(menuBackgroundTextureAtlas, activity, "level_background_c.png");

    	
    	try
    	{
    	    this.menuBackgroundTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 1, 0));
    	    this.menuBackgroundTextureAtlas.load();
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
	    gameTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 2048, 2048, TextureOptions.NEAREST);
	    gameEnviromentTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 2048, 2048, TextureOptions.NEAREST);
	    gameBackgroundTextureAtlas = new BuildableBitmapTextureAtlas(activity.getTextureManager(), 2048, 2048, TextureOptions.NEAREST);
	    
	    objectsList=new LinkedList <ITextureRegion>();
	    
	    
	    if(stage=="Desert")
	    {
	    	road_background_region=new ITextureRegion[5];
		    road_background_region[0]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameBackgroundTextureAtlas, activity, "background/mountain_a_desert.png");
		    road_background_region[1]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameBackgroundTextureAtlas, activity, "background/mountain_b_desert.png");
		    road_background_region[2]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameBackgroundTextureAtlas, activity, "background/sandcastle_desert.png");
		    road_background_region[3]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameBackgroundTextureAtlas, activity, "background/backgorund_desert_skull_a.png");
		    road_background_region[4]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameBackgroundTextureAtlas, activity, "background/mountain_c_desert.png");
		    
		    fence_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "road/fence.png", 3, 1);
		    
		    bus_region = new ITiledTextureRegion[3];
		    bus_region[0] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "road/bus_a.png",  3, 1);
		    bus_region[1] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "road/bus_b.png",  3, 1);
		    bus_region[2] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "road/bus_c.png",  3, 1);
		    
		    
		    
	    	road_background_fon_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameEnviromentTextureAtlas, activity, "road/background/background_desert.png");
	    	road_background_object=new ITextureRegion[7];
	    	road_background_object[0]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameEnviromentTextureAtlas, activity, "road/background/background_desert_grass.png");
	    	road_background_object[1]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameEnviromentTextureAtlas, activity, "road/background/background_desert_bush.png");  
	    	road_background_object[2]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameEnviromentTextureAtlas, activity, "road/background/background_desert_tree_a.png");
	    	road_background_object[3]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameEnviromentTextureAtlas, activity, "road/background/background_desert_tree_b.png");
	    	road_background_object[4]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameEnviromentTextureAtlas, activity, "road/background/background_desert_tree_c.png");
	    	road_background_object[5]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameEnviromentTextureAtlas, activity, "road/background/background_desert_cactus_a.png");
	    	road_background_object[6]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameEnviromentTextureAtlas, activity, "road/background/background_rock_c.png");
	    	road_background_blink_region=new ITextureRegion[2];
		    road_background_blink_region[0]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameEnviromentTextureAtlas, activity, "road/background/background_blink_a.png");
		    road_background_blink_region[1]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameEnviromentTextureAtlas, activity, "road/background/background_blink_b.png");
		    background_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameBackgroundTextureAtlas, activity, "background/background_desert.png");
	    }
	    else
    	if(stage=="Countriside")
	    {
    		road_background_region=new ITextureRegion[3];
    	    road_background_region[0]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameBackgroundTextureAtlas, activity, "background/house_a_countreside.png");
    	    road_background_region[1]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameBackgroundTextureAtlas, activity, "background/house_b_countreside.png");
    	    road_background_region[2]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameBackgroundTextureAtlas, activity, "background/backgorund_desert_skull_a.png");
    		
	    	road_background_fon_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "road/background/background_countriside.png");
	    	road_background_object=new ITextureRegion[4];
	    	road_background_object[0]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "road/background/background_desert_grass.png");
	    	road_background_object[1]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "road/background/background_desert_bush.png");  
	    	road_background_object[2]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "road/background/background_countriside_cow_a.png"); 
	    	road_background_object[3]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "road/background/background_countriside_cow_b.png");
	    	
	    	fence_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "road/seno.png", 3, 1);
	 	    
	    	building_region = new ITiledTextureRegion[1];
	    	building_region[0] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "road/building_a.png",  3, 1);
	 	    
	    	bus_region = new ITiledTextureRegion[1];
		    bus_region[0] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "road/bus_a.png",  3, 1);
		   
		    pickup_region = new ITiledTextureRegion[1];
		    pickup_region[0] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "road/pickup_a.png",  3, 1);
		   
		    
	    	road_background_blink_region=new ITextureRegion[2];
		    road_background_blink_region[0]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "road/background/background_blink_c.png");
		    road_background_blink_region[1]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "road/background/background_blink_d.png");
		    background_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameBackgroundTextureAtlas, activity, "background/background_desert.png");
		}
    	else
    	{
    		road_background_region=new ITextureRegion[5];
    	    road_background_region[0]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameBackgroundTextureAtlas, activity, "background/mountain_a_desert.png");
    	    road_background_region[1]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameBackgroundTextureAtlas, activity, "background/mountain_b_desert.png");
    	    road_background_region[2]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameBackgroundTextureAtlas, activity, "background/sandcastle_desert.png");
    	    road_background_region[3]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameBackgroundTextureAtlas, activity, "background/backgorund_desert_skull_a.png");
    	    road_background_region[4]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameBackgroundTextureAtlas, activity, "background/mountain_c_desert.png");
    		
    	    fence_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "road/fence.png", 3, 1);
    	    
    	    bus_region = new ITiledTextureRegion[3];
    	    bus_region[0] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "road/bus_a.png",  3, 1);
    	    bus_region[1] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "road/bus_b.png",  3, 1);
    	    bus_region[2] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "road/bus_c.png",  3, 1);
    	    
    	    
    		road_background_object=new ITextureRegion[2];
	    	road_background_object[0]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "road/background/background_desert_grass.png");
	    	road_background_object[1]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "road/background/background_desert_bush.png");  
		    road_background_blink_region=new ITextureRegion[2];
		    road_background_blink_region[0]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "road/background/background_blink_a.png");
		    road_background_blink_region[1]= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameTextureAtlas, activity, "road/background/background_blink_b.png");
		    background_region= BitmapTextureAtlasTextureRegionFactory.createFromAsset(gameBackgroundTextureAtlas, activity, "background/background_desert.png");
    	}
	    
	    tramp_a_region= BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "road/tramp_a.png", 3, 1);
	    
	    if(bike=="Red Harley")
	    	biker_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "biker/biker_a.png", 3, 2);
	    else
	    if(bike=="Black Harley")	
	    	biker_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "biker/biker_b.png", 3, 2);
	    else
	    	biker_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "biker/biker_a.png", 3, 2);
	    
	   
	    coin_region = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(gameTextureAtlas, activity, "road/coin.png", 4, 2);
	    
	    
	    
	    
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
	    
	    try 
	    {
	        this.gameEnviromentTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
	        this.gameEnviromentTextureAtlas.load();
	    } 
 	    catch (final TextureAtlasBuilderException e)
	    {
	        Debug.e(e);
	    } 
	    
	    try 
	    {
	        this.gameBackgroundTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 0));
	        this.gameBackgroundTextureAtlas.load();
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
			if(level == "Countriside")
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
    	loadingTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.NEAREST);
    	loading_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(loadingTextureAtlas, activity, "loading.png", 0, 0);
    	loadingTextureAtlas.load();
    }
    
    public void unloadGame()
    {
    	
    }
    
    public void unloadMenuTextures()
    {
        
    }
    
    public void unloadMenuAudio()
    {
        menu_1 = null;
        menu_2 = null;
        menu_3 = null;
    }
    
    public void unloadMenuGraphics()
    {
    	menuTextureAtlas.unload();
    	menuTutorialTextureAtlas.unload();
    	menuBackgroundTextureAtlas.unload();
    	
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
    	gameTextureAtlas.unload(); 
    	gameEnviromentTextureAtlas.unload(); 
    	gameBackgroundTextureAtlas.unload(); 
    	
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
    	splashTextureAtlas = new BitmapTextureAtlas(activity.getTextureManager(), 1024, 1024, TextureOptions.NEAREST);
    	splash_region = BitmapTextureAtlasTextureRegionFactory.createFromAsset(splashTextureAtlas, activity, "splash.png", 0, 0);
    	splashTextureAtlas.load();
    	//loadLoadingResources();
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

