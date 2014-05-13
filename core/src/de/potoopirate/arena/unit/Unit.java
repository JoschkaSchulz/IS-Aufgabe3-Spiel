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
	protected int mMovementState;
	protected TextureRegion mCurrentFrame;
	
	//Animations
	protected TextureRegion[] mIdleFrames;
	protected Animation mIdleAnimation;
	
	//Movement
	protected Vector2 mPosition;
	protected Vector2 mDestination;
	protected float mWidth;
	protected boolean isFliped;
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
		isFliped = false;
		
		//Walking Animation
		mIdleFrames = new TextureRegion[5];
		for(int i = 0; i < ResourceLoader.UNIT_DUMMY[0].length; i++) {
			mIdleFrames[i] = ResourceLoader.UNIT_DUMMY[0][i];
		}
		mIdleAnimation = new Animation(0.25f, mIdleFrames);
		mIdleAnimation.setPlayMode(PlayMode.LOOP);
		
		//Width
		mWidth = mIdleFrames[0].getRegionWidth();
	}
	
	public void setCallback(IUnitCallback callback) {
		mUnitCallback = callback;
	}
	
	public void isFliped(boolean flip) {
		isFliped = flip;
	}
	
	public float getY() {
		return mPosition.y;
	}
	
	public void setPosition(float x, float y) {
		mPosition.x = x;
		mPosition.y = y;
	}
	
	public float getWidth() {
		return mWidth;
	}
	
	public void draw(SpriteBatch batch) {
		batch.draw(mCurrentFrame, mPosition.x, mPosition.y);
//		batch.draw(mCurrentFrame.getTexture(), mPosition.x, mPosition.y, 
//				(float)mCurrentFrame.getRegionWidth(), (float)mCurrentFrame.getRegionHeight(), 
//				0, 0, mCurrentFrame.getRegionWidth(), mCurrentFrame.getRegionHeight(), 
//				isFliped, false);
	}
	
	public void act(float deltaTime){
		mStateTime += deltaTime;
		
		mCurrentFrame = new TextureRegion(mIdleAnimation.getKeyFrame(mStateTime));
		mCurrentFrame.flip(isFliped, false);
		
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
				mUnitCallback.unitStopped(this, mMovementState);
			}
		}
	}
	
	public void moveTo(float x, float y, float time) {
		moveTo(x, y, time, -1);
	}
	
	public void moveTo(float x, float y, float time, int state) {
		if(time <= 0) {
			mDestination.x = mPosition.x = x;
			mDestination.y = mPosition.y = y;
		}else{
			mDestination.x = x;
			mDestination.y = y;
			mTime = time;
			mMovementState = state;
		}
	}
	
	public interface IUnitCallback {
		public void unitStopped(Unit unit, int state);
	}
	
	public String toString() {
		return "Unit: " + mPosition.toString();
	}
}
