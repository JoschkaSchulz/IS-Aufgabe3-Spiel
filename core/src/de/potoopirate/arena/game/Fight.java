package de.potoopirate.arena.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import de.potoopirate.arena.player.NoUnitException;
import de.potoopirate.arena.player.Player;
import de.potoopirate.arena.unit.Unit;

public class Fight implements Unit.IUnitCallback{

	public static final int UNITSTATE_UNDEFINED    	= 0;
	public static final int UNITSTATE_REACHEDFIGHT 	= 1;
	
	private Unit unit1;
	private Unit unit2;
	
	private Player mPlayer1;
	private Player mPlayer2;
	
	private Vector2 mLeftPosition;
	private Vector2 mRightPosition;
	
	public Fight(Player player1, Player player2) {
		mPlayer1 = player1;
		mPlayer2 = player2;
	}
	
	/**
	 * triggers if the clock runs out.b
	 */
	public void clockAction() {
		unit1 = null;
		unit2 = null;
		try {
			unit1 = mPlayer1.getQueue().pop();
			unit1.setCallback(this);
			unit1.moveTo(Game.POSITION_LEFT_FIGHT.x, Game.POSITION_LEFT_FIGHT.y, 2f, UNITSTATE_REACHEDFIGHT);
		} catch (NoUnitException e) {
		}

		try {
			unit2 = mPlayer2.getQueue().pop();
			unit2.setCallback(this);
			unit2.moveTo(Game.POSITION_RIGHT_FIGHT.x, Game.POSITION_RIGHT_FIGHT.y, 2f, UNITSTATE_REACHEDFIGHT);
		} catch (NoUnitException e) {
		}
	}
	
	public void act(float delta) {
		if(unit1 != null) unit1.act(delta);
		if(unit2 != null) unit2.act(delta);
	}
	
	public void draw(SpriteBatch batch) {
		if(unit1 != null) unit1.draw(batch);
		if(unit2 != null) unit2.draw(batch);
	}

	private void fight() {
		
	}
	
	@Override
	public void unitStopped(Unit unit, int state) {
		switch(state) {
			case UNITSTATE_REACHEDFIGHT:
				System.out.println("Unit reached the fight!");
				fight();
				break;
		}
	}
}
