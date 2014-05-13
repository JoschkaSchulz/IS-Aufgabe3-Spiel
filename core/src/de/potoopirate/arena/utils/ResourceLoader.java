package de.potoopirate.arena.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ResourceLoader {
	static public TextureRegion[][] UNIT_DUMMY;
	static public TextureRegion[][] MOVE_DUMMY;
	static public TextureRegion[] CLOCK;
	static public final int CLOCK_IMAGES = 9;
	static public TextureRegion LEFT_PLAYER_BACKGROUND;
	static public TextureRegion RIGHT_PLAYER_BACKGROUND;
	static public TextureRegion ICON_MAGE;
	static public TextureRegion ICON_KIGHT;
	static public TextureRegion ICON_ARCHER;
	static public TextureRegion ICON_IDLE;
	static public TextureRegion CURSOR;
	
	static public BitmapFont FONT;
	
	static public void load() {
		Texture unitDummy = new Texture("gfx/unitdummy.png");
		UNIT_DUMMY = TextureRegion.split(unitDummy, 128, 128);
		Texture moveDummy = new Texture("gfx/moveit.png");
		MOVE_DUMMY = TextureRegion.split(moveDummy, 128, 256);
		
		
		Texture clock = new Texture("gfx/clock.png");
		TextureRegion[][] tmpClock = TextureRegion.split(clock, 256, 256);
		CLOCK = new TextureRegion[CLOCK_IMAGES];
		int clockCounter = 0;
		for(int i = 0; i < tmpClock.length; i++) {
			for(int j = 0; j < tmpClock[0].length; j++) {
				if(clockCounter < CLOCK_IMAGES) {
					CLOCK[clockCounter] = tmpClock[i][j];
					clockCounter++;
				}
			}
		}
		
		Texture playerHud = new Texture("gfx/players_hud.png");
		TextureRegion[][] splitHud = TextureRegion.split(playerHud, 64, 64);
		ICON_MAGE = splitHud[0][0];
		ICON_KIGHT = splitHud[0][1];
		ICON_ARCHER = splitHud[0][2];
		ICON_IDLE = splitHud[0][3];
		CURSOR = splitHud[0][4];
		
		Texture playerBackgrounds = new Texture("gfx/player_background.png");
		TextureRegion[][] tmpPlayerBackground = TextureRegion.split(playerBackgrounds, 640, 384);
		LEFT_PLAYER_BACKGROUND = tmpPlayerBackground[0][0];
		RIGHT_PLAYER_BACKGROUND = tmpPlayerBackground[1][0];
	
		FONT = new BitmapFont();
	}
}
