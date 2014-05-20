package de.potoopirate.arena.player;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import de.potoopirate.arena.unit.Unit;
import de.potoopirate.arena.unit.Unit.IUnitCallback;

public class UnitQueue implements IUnitCallback{
	
	public static final int ORIENTATION_LEFT 	= 0;
	public static final int ORIENTATION_RIGHT 	= 1;
	
	public static final int UNIT_WIDTH = 85;
	
	private ArrayList<Unit> mUnits;
	private Vector2 mPosition;
	private int mOrientation;
	
	/**
	 * creates a UnitQueue from another unitQueue
	 * @param unitQueue the UnitQueue  where the new one should be created from
	 */
	public UnitQueue(UnitQueue unitQueue) {
		mPosition = new Vector2(unitQueue.getX(), unitQueue.getY());
		mUnits = new ArrayList<Unit>(unitQueue.getUnits());
	}
	
	public UnitQueue(int x, int y) {
		mUnits = new ArrayList<Unit>();
		mPosition = new Vector2(x, y);
	}
	
	private ArrayList<Unit> getUnits() {
		return mUnits;
	}
	
	public void setOrientation(int orientation) {
		mOrientation = orientation;
	}
	
	public void restartGame() {
		mUnits.clear();
	}
	
	public void reorder() {
		Unit unit;
		for(int i = 0; i < mUnits.size(); i++) {
			unit = mUnits.get(i);
			if(mOrientation == ORIENTATION_LEFT) {
				if(unit.getX() < mPosition.x-(i*UNIT_WIDTH)) //Toleranz: && unit.getX() > mPosition.x-(i*UNIT_WIDTH) + 5) 
					unit.setAnimationState(Unit.STATE_MOVE);
				unit.moveTo(mPosition.x-(i*UNIT_WIDTH), mPosition.y, 2f);
			}else{
				if(unit.getX() > mPosition.x-(i*UNIT_WIDTH)) //Toleranz: && unit.getX() < mPosition.x-(i*UNIT_WIDTH) - 5) 
					unit.setAnimationState(Unit.STATE_MOVE);
				unit.moveTo(mPosition.x+(i*UNIT_WIDTH), mPosition.y, 2f);
			}
		}
	}
	
	public Unit pop() throws NoUnitException{
		if(mUnits.size() > 0) {
			if(mUnits.get(0) != null) {
				Unit u = mUnits.get(0);
				mUnits.remove(0);
				reorder();
				return u;
			}else{
				throw new NoUnitException("The Element is null.");
			}
		}else{
			throw new NoUnitException("The Queue is empty.");
		}
	}
	
	public Unit getUnitAtPosition(int position) {
		return mUnits.get(position);
	}
	
	public void add(Unit unit) {
		if(mUnits.isEmpty()) {
			unit.setPosition(mPosition.x, mPosition.y);
		}else{
			//calculate the current position in queue
			float tmpX = getLastPositionX();
			unit.setPosition(tmpX, mPosition.y);
		}
		
		//Flip unit
		if(mOrientation == ORIENTATION_RIGHT) unit.isFliped(false);
		
		mUnits.add(unit);
		
		reorder();
	}
	
	public float getY() {
		return mPosition.y;
	}
	
	public float getX() {
		return mPosition.x;
	}
	
	public float getLastPositionX() {
		if(mOrientation == ORIENTATION_LEFT) {
			return mPosition.x-(mUnits.size()*UNIT_WIDTH);
		}else{
			return mPosition.x+(mUnits.size()*UNIT_WIDTH);
		}
	}
	
	public int getSize() {
		return mUnits.size();
	}
	
	public void act(float delta) {
		for(Unit unit : mUnits) {
			unit.act(delta);
		}
	}
	
	public void draw(SpriteBatch batch) {
		for(Unit unit : mUnits) {
			unit.draw(batch);
		}
	}

	@Override
	public void unitStopped(Unit unit, int state) {
		unit.setAnimationState(Unit.STATE_IDLE);
	}

	@Override
	public void unitFinishedFight(Unit unit) {
		
	}
}
