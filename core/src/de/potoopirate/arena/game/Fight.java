package de.potoopirate.arena.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import de.potoopirate.arena.player.NoUnitException;
import de.potoopirate.arena.player.Player;
import de.potoopirate.arena.unit.Unit;

public class Fight {

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
	 * triggers if the clock runs out.
	 */
	public void clockAction() {
		try {
			unit1 = mPlayer1.getQueue().pop();
		} catch (NoUnitException e) {
		}

		try {
			unit2 = mPlayer2.getQueue().pop();
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
}
