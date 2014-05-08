package de.potoopirate.arena.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ResourceLoader {
	static public TextureRegion[][] UNIT_DUMMY;
	
	static public void load() {
		Texture unitDummy = new Texture("gfx/unitdummy.png");
		UNIT_DUMMY = TextureRegion.split(unitDummy, 128, 128);
	}
}
