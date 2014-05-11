package de.potoopirate.arena.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Player {
	public static final int CURSOR_IDLE 	= 0;
	public static final int CURSOR_MAGE 	= 1;
	public static final int CURSOR_ARCHER 	= 2;
	public static final int CURSOR_KNIGHT 	= 3;
	
	public static final int PLAYER_1 = 0;
	public static final int PLAYER_2 = 1;
	
	private int mPlayerNumber;
	private boolean mChoiseBlocked;
	private int mCursor;
	
	public Player(int playerNumber) {
		mPlayerNumber = playerNumber;
		mCursor = CURSOR_IDLE;
		mChoiseBlocked = false;
	}
	
	public boolean ismChoiseBlocked() {
		return mChoiseBlocked;
	}

	public void setmChoiseBlocked(boolean mChoiseBlocked) {
		this.mChoiseBlocked = mChoiseBlocked;
	}

	public int getCursor() {
		return mCursor;
	}
	
	public void resetCursor() {
		mCursor = CURSOR_IDLE;
	}
	
	public void act(float delta) {
		if(!mChoiseBlocked) {
			switch(mPlayerNumber) {
				case PLAYER_1:
					if(Gdx.input.isKeyPressed(Keys.A)) {
						mCursor = CURSOR_MAGE;
					}else if(Gdx.input.isKeyPressed(Keys.D)){
						mCursor = CURSOR_ARCHER;
					}else if(Gdx.input.isKeyPressed(Keys.W)){
						mCursor = CURSOR_KNIGHT;
					}else if(Gdx.input.isKeyPressed(Keys.S)){
						mCursor = CURSOR_IDLE;
					}
					break;
				case PLAYER_2:
					if(Gdx.input.isKeyPressed(Keys.LEFT)) {
						mCursor = CURSOR_MAGE;
					}else if(Gdx.input.isKeyPressed(Keys.RIGHT)){
						mCursor = CURSOR_ARCHER;
					}else if(Gdx.input.isKeyPressed(Keys.UP)){
						mCursor = CURSOR_KNIGHT;
					}else if(Gdx.input.isKeyPressed(Keys.DOWN)){
						mCursor = CURSOR_IDLE;
					}
					break;
			}
		}
	}
}
