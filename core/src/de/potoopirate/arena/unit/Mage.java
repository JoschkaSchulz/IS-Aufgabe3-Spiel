package de.potoopirate.arena.unit;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

import de.potoopirate.arena.utils.ResourceLoader;

public class Mage extends Unit {

	public Mage(IUnitCallback unitCallback) {
		super(unitCallback);

		mIdleFrames = new TextureRegion[2];
		// for(int i = 0; i < ResourceLoader.MAGE[0].length; i++) {
		for (int i = 0; i < 2; i++) {
			mIdleFrames[i] = ResourceLoader.MAGE[0][i];
		}
		mIdleAnimation = new Animation(0.25f, mIdleFrames);
		mIdleAnimation.setPlayMode(PlayMode.LOOP);
	}

}
