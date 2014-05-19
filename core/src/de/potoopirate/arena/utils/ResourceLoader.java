package de.potoopirate.arena.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ResourceLoader {
	static public TextureRegion[][] UNIT_DUMMY;
	static public TextureRegion[][] MOVE_DUMMY;
	static public TextureRegion[][] MAGE;
	static public TextureRegion[][] KNIGHT;
	static public TextureRegion[][] ARCHER;
	static public TextureRegion[] HEART;
	static public TextureRegion[] CLOCK;
	static public TextureRegion[] FIELD;
	static public final int CLOCK_IMAGES = 9;
	static public TextureRegion CIRCLE;
	static public TextureRegion CURSOR;
	static public TextureRegion ICON_MAGE;
	static public TextureRegion ICON_KIGHT;
	static public TextureRegion ICON_ARCHER;
	static public TextureRegion ICON_IDLE;
	static public TextureRegion LEFT_PLAYER_BACKGROUND;
	static public TextureRegion RIGHT_PLAYER_BACKGROUND;
	static public TextureRegion RING;
	
	static public BitmapFont FONT;
	
	static public void load() {
		Texture unitDummy = new Texture("gfx/unitdummy.png");
		UNIT_DUMMY = TextureRegion.split(unitDummy, 128, 128);
		Texture moveDummy = new Texture("gfx/moveit.png");
		MOVE_DUMMY = TextureRegion.split(moveDummy, 128, 256);
		
		Texture archer = new Texture("gfx/archer.png");
		ARCHER = TextureRegion.split(archer, 209, 256);
		Texture mage = new Texture("gfx/mage.png");
		MAGE = TextureRegion.split(mage, 205, 256);
		Texture knight = new Texture("gfx/knight.png");
		KNIGHT = TextureRegion.split(knight, 242, 256);
		
		
//		Texture clock = new Texture("gfx/clock.png");
//		TextureRegion[][] tmpClock = TextureRegion.split(clock, 256, 256);
		CLOCK = new TextureRegion[CLOCK_IMAGES];
//		int clockCounter = 0;
//		for(int i = 0; i < tmpClock.length; i++) {
//			for(int j = 0; j < tmpClock[0].length; j++) {
//				if(clockCounter < CLOCK_IMAGES) {
//					CLOCK[clockCounter] = tmpClock[i][j];
//					clockCounter++;
//				}
//			}
//		}
		for (int i =0; i<9;i++){
			Texture clock = new Texture("gfx/clock"+i+".png");
			CLOCK[i] = new TextureRegion(clock);
		}
		
		FIELD = new TextureRegion[2];
		
		Texture fieldLeft = new Texture("gfx/bg_left.png");
		FIELD[0] = new TextureRegion(fieldLeft);
		
		Texture fieldRight = new Texture("gfx/bg_right.png");
		FIELD[1] = new TextureRegion(fieldRight);
		
		Texture circle = new Texture("gfx/win.png");
		CIRCLE = new TextureRegion(circle);
		
		Texture playerHud = new Texture("gfx/players_hud.png");
		TextureRegion[][] splitHud = TextureRegion.split(playerHud, 64, 64);
		ICON_MAGE = splitHud[0][0];
		ICON_KIGHT = splitHud[0][1];
		ICON_ARCHER = splitHud[0][2];
		ICON_IDLE = splitHud[0][3];
		CURSOR = splitHud[0][4];
		
		Texture left = new Texture("gfx/wall_left.png");
		LEFT_PLAYER_BACKGROUND = new TextureRegion(left);
		
		Texture right = new Texture("gfx/wall_right.png");
		RIGHT_PLAYER_BACKGROUND = new TextureRegion(right);
		
//		Texture playerBackgrounds = new Texture("gfx/player_background.png");
//		TextureRegion[][] tmpPlayerBackground = TextureRegion.split(playerBackgrounds, 640, 384);
		
		Texture ring = new Texture("gfx/ring.png");
		RING = new TextureRegion(ring);
		
	
		HEART = new TextureRegion[2];

		Texture heart = new Texture("gfx/heart.png");
		TextureRegion[][] tmpHeart = TextureRegion.split(heart, 13, 11);
		HEART[0] = tmpHeart[0][0];
		HEART[1] = tmpHeart[0][1];
		
		FONT = new BitmapFont();
	}
}
