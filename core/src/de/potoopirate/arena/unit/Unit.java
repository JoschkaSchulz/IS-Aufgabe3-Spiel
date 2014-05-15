package de.potoopirate.arena.unit;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.math.Vector2;

import de.potoopirate.arena.utils.ResourceLoader;

public abstract class Unit {

	public static final int STATE_IDLE = 0;
	public static final int STATE_MOVE = 1;
	public static final int STATE_FIGHT = 2;
	
	protected int mHealth;

	protected float mStateTime;
	protected float mSpeed;
	protected int mMovementState;
	protected TextureRegion mCurrentFrame;

	// Animations
	protected TextureRegion[] mIdleFrames;
	protected Animation mIdleAnimation;
	protected TextureRegion[] mFightFrames;
	protected Animation mFightAnimation;
	protected TextureRegion[] mMoveFrames;
	protected Animation mMoveAnimation;
	protected int mAnimationState;
	protected int mFollowAnimation;

	// Movement
	protected Vector2 mPosition;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isFliped ? 1231 : 1237);
		result = prime * result
				+ ((mPosition == null) ? 0 : mPosition.hashCode());
		result = prime * result + Float.floatToIntBits(mStateTime);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Unit other = (Unit) obj;
		if (isFliped != other.isFliped)
			return false;
		if (mPosition == null) {
			if (other.mPosition != null)
				return false;
		} else if (!mPosition.equals(other.mPosition))
			return false;
		if (Float.floatToIntBits(mStateTime) != Float
				.floatToIntBits(other.mStateTime))
			return false;
		return true;
	}

	protected Vector2 mDestination;
	protected float mWidth;
	protected boolean isFliped;
	private float mTime;

	// Callback
	protected IUnitCallback mUnitCallback;

	public Unit(IUnitCallback unitCallback) {
		mStateTime = 0;
		mPosition = new Vector2(0, 0);
		mDestination = new Vector2(0, 0);
		mHealth = 3;
		mSpeed = 50;
		mUnitCallback = unitCallback;
		isFliped = true;
		mAnimationState = mFollowAnimation = STATE_IDLE;

		// Walking Animation
		mIdleFrames = new TextureRegion[5];
		for (int i = 0; i < ResourceLoader.UNIT_DUMMY[0].length; i++) {
			mIdleFrames[i] = ResourceLoader.UNIT_DUMMY[0][i];
		}
		mIdleAnimation = new Animation(0.25f, mIdleFrames);
		mIdleAnimation.setPlayMode(PlayMode.LOOP);
		
		mFightFrames = new TextureRegion[4];
		for (int i = 0; i < ResourceLoader.MOVE_DUMMY[0].length; i++) {
			mFightFrames[i] = ResourceLoader.MOVE_DUMMY[0][i];
		}
		mFightAnimation = new Animation(0.25f, mFightFrames);
		mFightAnimation.setPlayMode(PlayMode.NORMAL);

		mMoveFrames = new TextureRegion[4];
		for (int i = 0; i < ResourceLoader.MOVE_DUMMY[0].length; i++) {
			mMoveFrames[i] = ResourceLoader.MOVE_DUMMY[0][i];
		}
		mMoveAnimation = new Animation(0.25f, mMoveFrames);
		mMoveAnimation.setPlayMode(PlayMode.LOOP);

		// Width
		mWidth = mIdleFrames[0].getRegionWidth();
	}

	public void setAnimationState(int state, int followAnimation) {
		mFollowAnimation = followAnimation;
		setAnimationState(state);
	}
	
	public void setAnimationState(int state) {
		mAnimationState = state;
		mStateTime = 0;
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
	
	public float getX() {
		return mPosition.x;
	}

	public void setPosition(float x, float y) {
		mPosition.x = x;
		mPosition.y = y;
	}

	public float getWidth() {
		return mWidth;
	}

	public void draw(SpriteBatch batch) {
		batch.draw(mCurrentFrame, mPosition.x, mPosition.y,
				mCurrentFrame.getRegionWidth() / 2,
				mCurrentFrame.getRegionHeight() / 2);
		ResourceLoader.FONT.draw(batch, "Leben: " + getHealth(), 
				mPosition.x + (mCurrentFrame.getRegionWidth()/4 - 30), 
				mPosition.y + (mCurrentFrame.getRegionHeight()/2)+20);
		// batch.draw(mCurrentFrame.getTexture(), mPosition.x, mPosition.y,
		// (float)mCurrentFrame.getRegionWidth(),
		// (float)mCurrentFrame.getRegionHeight(),
		// 0, 0, mCurrentFrame.getRegionWidth(),
		// mCurrentFrame.getRegionHeight(),
		// isFliped, false);
	}

	public void act(float deltaTime) {
		mStateTime += deltaTime;
		
		switch(mAnimationState) {
			default:
			case STATE_IDLE:
				mCurrentFrame = new TextureRegion(
						mIdleAnimation.getKeyFrame(mStateTime));
				break;
			case STATE_MOVE:
				mCurrentFrame = new TextureRegion(
						mMoveAnimation.getKeyFrame(mStateTime));
				break;
			case STATE_FIGHT:
				if(mFightAnimation.isAnimationFinished(mStateTime)) {
					mUnitCallback.unitFinishedFight(this);
					setAnimationState(mFollowAnimation);
					mFollowAnimation = STATE_IDLE;
					mCurrentFrame = new TextureRegion(
						mIdleAnimation.getKeyFrame(mStateTime));
				}else{
					mCurrentFrame = new TextureRegion(
						mFightAnimation.getKeyFrame(mStateTime));
				}
				break;
		}
		
		//Flip the unit if it's on the right side
		mCurrentFrame.flip(isFliped, false);

		// very simple movement
		handleMovement(deltaTime);
	}
	

	/**
	 * Handles the movement of the Unit over time
	 * 
	 * @param deltaTime
	 *            the time the unit should move
	 */
	private void handleMovement(float deltaTime) {
		if (mTime > 0) {
			float tmpX;
			if (mPosition.x > mDestination.x) {
				tmpX = -(mPosition.x - mDestination.x);
			} else {
				tmpX = mDestination.x - mPosition.x;
			}

			mPosition.x += tmpX / mTime * deltaTime;

			float tmpY;
			if (mPosition.y > mDestination.y) {
				tmpY = -(mPosition.y - mDestination.y);
			} else {
				tmpY = mDestination.y - mPosition.y;
			}

			mPosition.y += tmpY / mTime * deltaTime;

			// Reduce the animation time
			if (mTime - deltaTime > 0) {
				mTime -= deltaTime;
			} else {
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
		if (time <= 0) {
			mDestination.x = mPosition.x = x;
			mDestination.y = mPosition.y = y;
		} else {
			mDestination.x = x;
			mDestination.y = y;
			mTime = time;
			mMovementState = state;
		}
	}

	public interface IUnitCallback {
		public void unitStopped(Unit unit, int state);
		public void unitFinishedFight(Unit unit);
	}

	public String toString() {
		return "Unit: " + mPosition.toString();
	}
	
	public abstract void attack(Unit unit);
	
	public int getHealth() {
		return mHealth;
	}
	
	public void setHealth(int health) {
		mHealth = health;
	}
}
