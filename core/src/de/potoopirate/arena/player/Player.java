package de.potoopirate.arena.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.potoopirate.arena.unit.Archer;
import de.potoopirate.arena.unit.Knight;
import de.potoopirate.arena.unit.Mage;
import de.potoopirate.arena.unit.Unit;
import de.potoopirate.arena.unit.Unit.IUnitCallback;
import de.potoopirate.arena.utils.ResourceLoader;

public class Player implements IUnitCallback{
	public static final int CURSOR_IDLE 	= 0;
	public static final int CURSOR_MAGE 	= 1;
	public static final int CURSOR_ARCHER 	= 2;
	public static final int CURSOR_KNIGHT 	= 3;
	
	public static final int PLAYER_1 = 0;
	public static final int PLAYER_2 = 1;
	
	private int mPlayerNumber;
	private boolean mChoiseBlocked;
	private int mCursor;
	
	private UnitQueue mQueue;
	
	public Player(int playerNumber) {
		mPlayerNumber = playerNumber;
		mCursor = CURSOR_IDLE;
		mChoiseBlocked = false;
		if(playerNumber == PLAYER_1) {
			mQueue = new UnitQueue(350,400);
		}else{
			mQueue = new UnitQueue(800,400);
			mQueue.setOrientation(UnitQueue.ORIENTATION_RIGHT);
		}
		mQueue.add(new Archer(this));
		mQueue.add(new Archer(this));
	}
	
	public boolean ismChoiseBlocked() {
		return mChoiseBlocked;
	}

	public void setmChoiseBlocked(boolean mChoiseBlocked) {
		this.mChoiseBlocked = mChoiseBlocked;
	}

	public UnitQueue getQueue() {
		return mQueue;
	}
	
	public int getCursor() {
		return mCursor;
	}
	
	public void resetCursor() {
		mCursor = CURSOR_IDLE;
	}

	public void clockAction() {
		switch(getCursor()) {
			case Player.CURSOR_KNIGHT:
				mQueue.add(new Knight(this));
				break;
			case Player.CURSOR_MAGE:
				mQueue.add(new Mage(this));
				break;
			case Player.CURSOR_ARCHER:
				mQueue.add(new Archer(this));
				break;
			default:
			case Player.CURSOR_IDLE:
				break;
		}
	}
	
	public void draw(SpriteBatch batch) {
		mQueue.draw(batch);
	}
	
	public void act(float delta) {
		mQueue.act(delta);
		
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

	@Override
	public void unitStopped(Unit unit) {
		// TODO Auto-generated method stub
		
	}
}
