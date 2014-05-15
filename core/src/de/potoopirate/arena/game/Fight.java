package de.potoopirate.arena.game;

import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import de.potoopirate.arena.player.NoUnitException;
import de.potoopirate.arena.player.Player;
import de.potoopirate.arena.unit.Unit;

public class Fight implements Unit.IUnitCallback {

	public static final int UNITSTATE_UNDEFINED = 0;
	public static final int UNITSTATE_REACHED_FIGHT = 1;
	public static final int UNITSTATE_FIGHT_END = 2;
	public static final int UNITSTATE_REACHED_QUEUE_END = 3;
	public static final int UNITSTATE_ORDER_IN_QUEUE = 4;

	private Unit unit1;
	private Unit unit2;

	private Game mGame;
	private Player mPlayer1;
	private Player mPlayer2;

	private Vector2 mLeftPosition;
	private Vector2 mRightPosition;

	private boolean mFightPossible = true;

	public Fight(Game game, Player player1, Player player2) {
		mPlayer1 = player1;
		mPlayer2 = player2;
		mGame = game;
	}

	/**
	 * triggers if the clock runs out.b
	 */
	public void clockAction() {
		mFightPossible = true;
		unit1 = null;
		unit2 = null;
		try {
			unit1 = mPlayer1.getQueue().pop();
			unit1.setCallback(this);
			unit1.setAnimationState(Unit.STATE_MOVE);
			unit1.moveTo(Game.POSITION_LEFT_FIGHT.x,
					Game.POSITION_LEFT_FIGHT.y, 0.75f, UNITSTATE_REACHED_FIGHT);
		} catch (NoUnitException e) {
		}

		try {
			unit2 = mPlayer2.getQueue().pop();
			unit2.setCallback(this);
			unit2.setAnimationState(Unit.STATE_MOVE);
			unit2.moveTo(Game.POSITION_RIGHT_FIGHT.x,
					Game.POSITION_RIGHT_FIGHT.y, 0.75f, UNITSTATE_REACHED_FIGHT);
		} catch (NoUnitException e) {
		}
		
	}

	public void act(float delta) {
		if (unit1 != null)
			unit1.act(delta);
		if (unit2 != null)
			unit2.act(delta);
	}

	public void draw(SpriteBatch batch) {
		if (unit1 != null)
			unit1.draw(batch);
		if (unit2 != null)
			unit2.draw(batch);
	}

	private void fight() {
		if(unit1 != null) {
			unit1.setAnimationState(Unit.STATE_FIGHT, Unit.STATE_MOVE);
		}
		
		if(unit2 != null) {
			unit2.setAnimationState(Unit.STATE_FIGHT, Unit.STATE_MOVE);	
		}
		
	}

	@Override
	public void unitFinishedFight(Unit unit) {
		if(unit1 != null) unit1.attack(unit2);
		if(unit2 != null) unit2.attack(unit1);
		
		if(unit1 != null && unit1.getHealth() <= 0) unit1 = null;
		if(unit2 != null && unit2.getHealth() <= 0) unit2 = null;
		
		//If both are dead start the clock again
		if(unit1 == null && unit2 == null) {
			mGame.clockAction();
		}else{
			unitStopped(unit, UNITSTATE_FIGHT_END);
		}
	}
	
	public void walkToEnd() {
		if(unit1 != null) {
			unit1.setAnimationState(Unit.STATE_MOVE);
			unit1.isFliped(false);
			unit1.moveTo(mPlayer1.getQueue().getLastPositionX(), unit1.getY(), 1f,
					UNITSTATE_REACHED_QUEUE_END);
		}
		
		if(unit2 != null) {
			unit2.setAnimationState(Unit.STATE_MOVE);
			unit2.isFliped(true);
			unit2.moveTo(mPlayer2.getQueue().getLastPositionX(), unit2.getY(), 1f,
					UNITSTATE_REACHED_QUEUE_END);
		}

		//resume the clock
		mGame.startClock();
	}
	
	private void reachedEndQueue() {
		if(unit1 != null) {
			unit1.setAnimationState(Unit.STATE_MOVE);
			unit1.isFliped(true);
			unit1.moveTo(mPlayer1.getQueue().getLastPositionX(), mPlayer1
					.getQueue().getY(), 0.25f, UNITSTATE_ORDER_IN_QUEUE);
		}

		if(unit2 != null) {
			unit2.setAnimationState(Unit.STATE_MOVE);
			unit2.isFliped(false);
			unit2.moveTo(mPlayer2.getQueue().getLastPositionX(), mPlayer2
					.getQueue().getY(), 0.25f, UNITSTATE_ORDER_IN_QUEUE);
		}
	}

	private void orderInQueue() {
		if (unit1 != null) {
			mPlayer1.getQueue().add(unit1);
			unit1.setAnimationState(Unit.STATE_IDLE);
			unit1.setCallback(mPlayer1.getQueue());
			unit1 = null;
		}
		if (unit2 != null) {
			mPlayer2.getQueue().add(unit2);
			unit2.setAnimationState(Unit.STATE_IDLE);
			unit2.setCallback(mPlayer2.getQueue());
			unit2 = null;
		}
	}
	
	@Override
	public void unitStopped(Unit unit, int state) {
		switch (state) {
			case UNITSTATE_REACHED_FIGHT:
				if (mFightPossible)
					mFightPossible = false;
					fight();
				break;
			case UNITSTATE_FIGHT_END:
				walkToEnd();
				break;
			case UNITSTATE_REACHED_QUEUE_END:
				reachedEndQueue();
				break;
			case UNITSTATE_ORDER_IN_QUEUE:
				orderInQueue();
				mFightPossible = true;
				break;
			default:
				break;
		}
	}
}
