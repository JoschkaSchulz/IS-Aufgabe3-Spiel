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
	public static final int UNITSTATE_REACHED_QUEUE_END = 2;
	public static final int UNITSTATE_ORDER_IN_QUEUE = 3;

	private Unit unit1;
	private Unit unit2;

	private Player mPlayer1;
	private Player mPlayer2;

	private Vector2 mLeftPosition;
	private Vector2 mRightPosition;

	private boolean mFightPossible = true;

	public Fight(Player player1, Player player2) {
		mPlayer1 = player1;
		mPlayer2 = player2;
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
			unit1.moveTo(Game.POSITION_LEFT_FIGHT.x,
					Game.POSITION_LEFT_FIGHT.y, 2f, UNITSTATE_REACHED_FIGHT);
		} catch (NoUnitException e) {
		}

		try {
			unit2 = mPlayer2.getQueue().pop();
			unit2.setCallback(this);
			unit2.moveTo(Game.POSITION_RIGHT_FIGHT.x,
					Game.POSITION_RIGHT_FIGHT.y, 2f, UNITSTATE_REACHED_FIGHT);
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
		
		unit1.moveTo(mPlayer1.getQueue().getLastPositionX(), unit1.getY(), 3f,
				UNITSTATE_REACHED_QUEUE_END);
		unit2.moveTo(mPlayer2.getQueue().getLastPositionX(), unit2.getY(), 3f,
				UNITSTATE_REACHED_QUEUE_END);

	}

	private void reachedEndQueue() {
		unit1.moveTo(mPlayer1.getQueue().getLastPositionX(), mPlayer1
				.getQueue().getY(), 1f, UNITSTATE_ORDER_IN_QUEUE);
		unit2.moveTo(mPlayer2.getQueue().getLastPositionX(), mPlayer2
				.getQueue().getY(), 1f, UNITSTATE_ORDER_IN_QUEUE);
	}

	private void orderInQueue() {
		if (unit1 != null) {
			mPlayer1.getQueue().add(unit1);
			unit1 = null;
		}
		if (unit2 != null) {
			mPlayer2.getQueue().add(unit2);
			unit2 = null;
		}
	}

	@Override
	public void unitStopped(Unit unit, int state) {
		switch (state) {
		case UNITSTATE_REACHED_FIGHT:
			System.out.println("Unit reached the fight!");
			if (mFightPossible)
				fight();
			break;
		case UNITSTATE_REACHED_QUEUE_END:
			reachedEndQueue();
			break;
		case UNITSTATE_ORDER_IN_QUEUE:
			orderInQueue();
			break;

		}
	}
}
