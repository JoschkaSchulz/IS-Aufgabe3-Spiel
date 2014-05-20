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
		
		mFightFrames = new TextureRegion[6];
		int k = 2;
		mFightFrames[0]= ResourceLoader.MAGE[0][0];
		for (int i = 1; i < 6; i++) {
				mFightFrames[i] = ResourceLoader.MAGE[(i+1)/4][k++%4];
			
		}

		mFightAnimation = new Animation(0.25f, mFightFrames);
		mFightAnimation.setPlayMode(PlayMode.NORMAL);
		
		mMoveFrames = new TextureRegion[4];
		
		for (int i = 0; i < ResourceLoader.MAGE[2].length; i++) {
			mMoveFrames[i] = ResourceLoader.MAGE[2][i];
		}
		mMoveAnimation = new Animation(0.25f, mMoveFrames);
		mMoveAnimation.setPlayMode(PlayMode.LOOP);
	}

	@Override
	public void attack(Unit unit) {
		if(unit instanceof Knight) {
			unit.setHealth(unit.getHealth()-1);
		}else if(unit instanceof Archer) {
			unit.setHealth(unit.getHealth()-2);
		}
	}

}
