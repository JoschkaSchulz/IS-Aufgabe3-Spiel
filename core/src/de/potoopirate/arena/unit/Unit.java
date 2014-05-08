package de.potoopirate.arena.unit;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.math.Vector2;

import de.potoopirate.arena.utils.ResourceLoader;

public abstract class Unit {
	
	protected int mHealth;
	
	protected float mStateTime;
	protected float mSpeed;
	protected TextureRegion mCurrentFrame;
	
	//Animations
	protected TextureRegion[] mIdleFrames;
	protected Animation mIdleAnimation;
	
	//Movement
	protected Vector2 mPosition;
	protected Vector2 mDestination;
	private float mTime;
	
	//Callback
	protected IUnitCallback mUnitCallback;
	
	public Unit(IUnitCallback unitCallback) {
		mStateTime = 0;
		mPosition = new Vector2(0, 0);
		mDestination = new Vector2(0, 0);
		mHealth = 3;
		mSpeed = 50;
		mUnitCallback = unitCallback;
		
		//Walking Animation
		mIdleFrames = new TextureRegion[5];
		for(int i = 0; i < ResourceLoader.UNIT_DUMMY[0].length; i++) {
			mIdleFrames[i] = ResourceLoader.UNIT_DUMMY[0][i];
		}
		mIdleAnimation = new Animation(0.25f, mIdleFrames);
		mIdleAnimation.setPlayMode(PlayMode.LOOP);
	}
	
	public void draw(SpriteBatch batch) {
		batch.draw(mCurrentFrame, mPosition.x, mPosition.y);
	}
	
	public void act(float deltaTime){
		mStateTime += deltaTime;
		
		mCurrentFrame = mIdleAnimation.getKeyFrame(mStateTime);
		
		//very simple movement
		handleMovement(deltaTime);
	}
	
	/**
	 * Handles the movement of the Unit over time
	 * @param deltaTime the time the unit should move
	 */
	private void handleMovement(float deltaTime) {
		if(mTime > 0) {
			float tmpX;
			if(mPosition.x > mDestination.x) {
				tmpX = -(mPosition.x - mDestination.x);
			}else{
				tmpX = mDestination.x - mPosition.x;
			}
			
			mPosition.x += tmpX/mTime * deltaTime;
			
			float tmpY;
			if(mPosition.y > mDestination.y) {
				tmpY = -(mPosition.y - mDestination.y);
			}else{
				tmpY = mDestination.y - mPosition.y;
			}
			
			mPosition.y += tmpY/mTime * deltaTime;
			
			//Reduce the animation time
			if(mTime - deltaTime > 0) {
				mTime -= deltaTime;
			}else{
				mTime = 0;
				mPosition.x = mDestination.x;
				mPosition.y = mDestination.y;
				mUnitCallback.unitStopped(this);
			}
		}
	}
	
	public void moveTo(float x, float y, float time) {
		if(time <= 0) {
			mDestination.x = mPosition.x = x;
			mDestination.y = mPosition.y = y;
		}else{
			mDestination.x = x;
			mDestination.y = y;
			mTime = time;
		}
	}
	
	public interface IUnitCallback {
		public void unitStopped(Unit unit);
	}
}
