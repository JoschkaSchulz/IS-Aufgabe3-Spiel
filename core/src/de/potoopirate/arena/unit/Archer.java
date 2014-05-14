package de.potoopirate.arena.unit;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

import de.potoopirate.arena.utils.ResourceLoader;

public class Archer extends Unit {

	public Archer(IUnitCallback unitCallback) {
		super(unitCallback);

		mIdleFrames = new TextureRegion[2];
//		for(int i = 0; i < ResourceLoader.ARCHER[0].length; i++) {
//			mIdleFrames[i] = ResourceLoader.ARCHER[0][i];
//		}
		for (int i = 0; i < 2; i++) {
			mIdleFrames[i] = ResourceLoader.ARCHER[0][i];
		}
		mIdleAnimation = new Animation(0.25f, mIdleFrames);
		mIdleAnimation.setPlayMode(PlayMode.LOOP);
	}

}
