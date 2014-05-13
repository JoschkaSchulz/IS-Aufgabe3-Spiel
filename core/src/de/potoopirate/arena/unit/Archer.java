package de.potoopirate.arena.unit;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;

import de.potoopirate.arena.utils.ResourceLoader;

public class Archer extends Unit {

	public Archer(IUnitCallback unitCallback) {
		super(unitCallback);

		mIdleFrames = new TextureRegion[4];
		for(int i = 0; i < ResourceLoader.MOVE_DUMMY[2].length; i++) {
			mIdleFrames[i] = ResourceLoader.MOVE_DUMMY[2][i];
		}
		mIdleAnimation = new Animation(0.25f, mIdleFrames);
		mIdleAnimation.setPlayMode(PlayMode.LOOP);
	}

}
