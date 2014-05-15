package de.potoopirate.arena.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import de.potoopirate.arena.player.Player;
import de.potoopirate.arena.utils.ResourceLoader;

public class Clock {
	
	private static final float CLOCK_COUNT = 2;
	
	private IClockListener mGame;
	private float mStateTime;
	private boolean isPaused;
	private Player mPlayer1;
	private Player mPlayer2;
	
	private int mX;
	private int mY;
	
	public Clock(IClockListener game, Player player1, Player player2) {
		mGame = game;
		isPaused = false;
		mX = (Gdx.graphics.getWidth()/2) - (ResourceLoader.CLOCK[0].getRegionWidth()/2);
		mY = (Gdx.graphics.getHeight()/2) - (ResourceLoader.CLOCK[0].getRegionHeight()/2) - 100;
		mStateTime = 0;
		mPlayer1 = player1;
		mPlayer2 = player2;
	}
	
	public void act(float delta) {
		if(!isPaused) {
			mStateTime += delta;
			if(mStateTime >= CLOCK_COUNT) {
				mStateTime = 0;
				mGame.clockAction();
				isPaused = true;
				mPlayer1.setmChoiseBlocked(true);
				mPlayer2.setmChoiseBlocked(true);
			}
		}
//		if(Gdx.input.isKeyPressed(Keys.SPACE)) resumeClock();
	}
	
	public void resumeClock() {
		isPaused = false;
		mPlayer1.setmChoiseBlocked(false);
		mPlayer2.setmChoiseBlocked(false);
	}
	
	public void draw(SpriteBatch batch) {
		if(isPaused) {
			batch.draw(ResourceLoader.CLOCK[0], mX, mY, 
					ResourceLoader.CLOCK[0].getRegionWidth(),
					ResourceLoader.CLOCK[0].getRegionHeight());
		}else{
			batch.draw(ResourceLoader.CLOCK[(int)((mStateTime*4)%8)+1], mX, mY, 
					ResourceLoader.CLOCK[0].getRegionWidth(),
					ResourceLoader.CLOCK[0].getRegionHeight());
		}
	}
	
	public interface IClockListener{
		public void clockAction();
	}
}
