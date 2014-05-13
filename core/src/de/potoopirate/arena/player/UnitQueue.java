package de.potoopirate.arena.player;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import de.potoopirate.arena.unit.Unit;

public class UnitQueue {
	
	public static final int ORIENTATION_LEFT 	= 0;
	public static final int ORIENTATION_RIGHT 	= 1;
	
	private ArrayList<Unit> mUnits;
	private Vector2 mPosition;
	private int mOrientation;
	
	public UnitQueue(int x, int y) {
		mUnits = new ArrayList<Unit>();
		mPosition = new Vector2(x, y);
	}
	
	public void setOrientation(int orientation) {
		mOrientation = orientation;
	}
	
	public Unit pop() throws NoUnitException{
		if(mUnits.size() > 0) {
			if(mUnits.get(0) != null) {
				Unit u = mUnits.get(0);
				mUnits.remove(0);
				return u;
			}else{
				throw new NoUnitException("The Element is null.");
			}
		}else{
			throw new NoUnitException("The Queue is empty.");
		}
	}
	
	public void add(Unit unit) {
		if(mUnits.isEmpty()) {
			unit.setPosition(mPosition.x, mPosition.y);
		}else{
			//calculate the current position in queue
			float tmpX = mPosition.x;
			for(Unit u : mUnits) {
				if(mOrientation == ORIENTATION_LEFT) {
					tmpX -= 10 + u.getWidth();
				}else{
					tmpX += 10 + u.getWidth();
				}
			}
			unit.setPosition(tmpX, mPosition.y);
		}
		
		//Flip unit
		if(mOrientation == ORIENTATION_RIGHT) unit.isFliped(true);
		
		mUnits.add(unit);
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
}
