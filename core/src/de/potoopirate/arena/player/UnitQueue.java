package de.potoopirate.arena.player;

import java.util.ArrayList;
import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import de.potoopirate.arena.unit.Unit;

public class UnitQueue{
	
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
	
	public void reorder() {
		for(int i = 0; i < mUnits.size(); i++) {
			if(mOrientation == ORIENTATION_LEFT) {
				mUnits.get(i).moveTo(mPosition.x-(i*128), mPosition.y, 2f);
			}else{
				mUnits.get(i).moveTo(mPosition.x+(i*128), mPosition.y, 2f);
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
	
	public void add(Unit unit) {
		if(mUnits.isEmpty()) {
			unit.setPosition(mPosition.x, mPosition.y);
		}else{
			//calculate the current position in queue
			float tmpX = getLastPositionX();
			unit.setPosition(tmpX, mPosition.y);
		}
		
		//Flip unit
		if(mOrientation == ORIENTATION_RIGHT) unit.isFliped(true);
		
		mUnits.add(unit);
		
		reorder();
	}
	
	public float getY() {
		return mPosition.y;
	}
	
	public float getLastPositionX() {
		if(mOrientation == ORIENTATION_LEFT) {
			return mPosition.x-(mUnits.size()*128);
		}else{
			return mPosition.x+(mUnits.size()*128);
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
}
